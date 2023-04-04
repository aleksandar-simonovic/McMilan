USE `mcmilan`;

-- Triger koji sluzi za sracunavanje cijene stavke na osnovu prodajne cijene modela namjestaja i kupljene kolicine, te umanjuje kupljenu kolicinu iz salona namjestaja
DROP TRIGGER IF EXISTS `mcmilan`.`fiskalni_racun_stavka_before_insert`;
DELIMITER $$
CREATE TRIGGER `mcmilan`.`fiskalni_racun_stavka_before_insert` BEFORE INSERT ON `fiskalni_racun_stavka` FOR EACH ROW
BEGIN
	declare vIdSalonaNamjestaja int;
	
	select IdSalonaNamjestaja into vIdSalonaNamjestaja
	from fiskalni_racun
	where IdRacuna = new.IdRacuna;
	
	set new.Cijena = new.Kolicina * (select ProdajnaCijena from model_namjestaja where new.IdModelaNamjestaja = model_namjestaja.IdModelaNamjestaja);
	
	update model_namjestaja_se_cuva_u_salonu_namjestaja
	set Kolicina = Kolicina - new.Kolicina
	where new.IdModelaNamjestaja = IdModelaNamjestaja and vIdSalonaNamjestaja = IdSalonaNamjestaja;
END$$
DELIMITER ;

-- Triger koji sluzi za uvecavanje ukupne cijene na fiskalnom racunu nakon dodavanja nove stavke na fiskalni racun
DROP TRIGGER IF EXISTS `mcmilan`.`fiskalni_racun_stavka_after_insert`;
DELIMITER $$
CREATE TRIGGER `mcmilan`.`fiskalni_racun_stavka_after_insert` AFTER INSERT ON `fiskalni_racun_stavka` FOR EACH ROW
BEGIN
	update fiskalni_racun
   	set UkupanIznos = UkupanIznos + new.Cijena
   	where fiskalni_racun.IdRacuna = new.IdRacuna;
END$$
DELIMITER ;

-- Triger koji sluzi za umanjivanje ukupnog iznosa na fiskalnom racunu kao i uvecavanje stanja u salonu namjestaja ako se obrise stavka sa fiskalnog racuna
DROP TRIGGER IF EXISTS `mcmilan`.`fiskalni_racun_stavka_after_delete`;
DELIMITER $$
CREATE TRIGGER `mcmilan`.`fiskalni_racun_stavka_after_delete` AFTER DELETE ON `fiskalni_racun_stavka` FOR EACH ROW
BEGIN
	declare vIdSalonaNamjestaja int;
	
	select IdSalonaNamjestaja into vIdSalonaNamjestaja
	from fiskalni_racun
	where IdRacuna = old.IdRacuna;
	
	update fiskalni_racun
    set UkupanIznos = UkupanIznos - old.Cijena
    where IdRacuna = old.IdRacuna;
	
	update model_namjestaja_se_cuva_u_salonu_namjestaja
	set Kolicina = Kolicina + old.Kolicina
	where IdModelaNamjestaja = old.IdModelaNamjestaja and IdSalonaNamjestaja = vIdSalonaNamjestaja;
END$$
DELIMITER ;