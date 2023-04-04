USE `mcmilan`;

-- procedura koja za zadatu osobu i zadati salon namjestaja kreira novi fiskalni racun ako ta osoba radi u tom salonu namjestaja
DROP procedure IF EXISTS `novi_fiskalni_racun`;
DELIMITER $$
CREATE PROCEDURE `novi_fiskalni_racun`(in pJMBG char(13), in pIdSalonaNamjestaja int, out pStatus bool)
BEGIN
    select count(*)>0 into pStatus
    from radnik
    where JMBG=pJMBG and IdSalonaNamjestaja=pIdSalonaNamjestaja;
    
    if pStatus=true then 
		insert into fiskalni_racun (DatumRacuna, UkupanIznos, JMBG, IdSalonaNamjestaja, Izdat) values (DATE(NOW()), 0.0, pJMBG, pIdSalonaNamjestaja, 0);
	end if;
END$$
DELIMITER ;

-- procedura koja brise neki racun i sve njemu pripadajuce stavke
DROP procedure IF EXISTS `brisi_fiskalni_racun`;
DELIMITER $$
CREATE PROCEDURE `brisi_fiskalni_racun` (in pIdRacuna int, out pStatus bool)
BEGIN
	select count(*)>0 into pStatus
	from fiskalni_racun
	where IdRacuna=pIdRacuna;
	if pStatus=true then
		DELETE FROM fiskalni_racun_stavka WHERE IdRacuna=pIdRacuna;
		DELETE FROM fiskalni_racun WHERE IdRacuna=pIdRacuna;
	end if;
END$$
DELIMITER ;

-- procedura koja za zadati salon namjestaja, zadati model i zadatu kolicinu provjerava da li u tom salonu postoji dovoljna kolicina namjestaja tog modela
DROP procedure IF EXISTS `da_li_ima_dovoljno_namjestaja_u_salonu`;
DELIMITER $$
CREATE PROCEDURE `da_li_ima_dovoljno_namjestaja_u_salonu` (in pIdModelaNamjestaja int, in pIdSalonaNamjestaja int, in pKolicina int, out pStatus bool)
BEGIN
	select Kolicina>=pKolicina into pStatus
	from model_namjestaja_se_cuva_u_salonu_namjestaja
    where IdModelaNamjestaja=pIdModelaNamjestaja and IdSalonaNamjestaja=pIdSalonaNamjestaja;
END$$
DELIMITER ;

-- procedura koja dodaje stavku na racun ako postoji dovoljna kolicina tog namjestaja u salonu namjestaja
DROP procedure IF EXISTS `dodaj_stavku_na_racun`;
DELIMITER $$
CREATE PROCEDURE `dodaj_stavku_na_racun` (in pIdRacuna int, in pIdModelaNamjestaja int, in pKolicina int, out pStatus bool)
BEGIN
	declare vIdSalonaNamjestaja int;
	declare vImaDovoljno bool default false;
	declare vValidIdRacuna bool default false;
    declare vValidModelNamjestaja bool default false;
    
    select count(*)>0 into vValidModelNamjestaja
    from model_namjestaja
    where IdModelaNamjestaja = pIdModelaNamjestaja;
    
    select count(*)>0 into vValidIdRacuna
    from fiskalni_racun
    where IdRacuna = pIdRacuna;
    
    select IdSalonaNamjestaja into vIdSalonaNamjestaja
    from fiskalni_racun
    where IdRacuna=pIdRacuna;
    
	set pStatus=false;
    
    if vValidIdRacuna=false or vValidModelNamjestaja=false or vIdSalonaNamjestaja is null then
		set pStatus=false;
	else
		call da_li_ima_dovoljno_namjestaja_u_salonu(pIdModelaNamjestaja, vIdSalonaNamjestaja, pKolicina, vImaDovoljno);
        if vImaDovoljno=false then
			set pStatus=false;
        else
			insert into fiskalni_racun_stavka (IdRacuna, IdModelaNamjestaja, Kolicina) values (pIdRacuna, pIdModelaNamjestaja, pKolicina);
			set pStatus=true;
        end if;
    end if;
END$$
DELIMITER ;

-- procedura koja sluzi za brisanje stavke sa racuna
DROP procedure IF EXISTS `brisi_stavku_sa_racuna`;
DELIMITER $$
CREATE PROCEDURE `brisi_stavku_sa_racuna` (in pIdRacuna int, in pIdModelaNamjestaja int, out pStatus bool)
BEGIN
    select count(*)>0 into pStatus
    from fiskalni_racun_stavka
    where IdRacuna=pIdRacuna and pIdModelaNamjestaja=IdModelaNamjestaja;
    
    if pStatus=true then
		delete from fiskalni_racun_stavka where IdRacuna=pIdRacuna and pIdModelaNamjestaja=IdModelaNamjestaja;
    end if;
END$$
DELIMITER ;

DROP procedure IF EXISTS `login_provjera`;
DELIMITER $$
USE `mcmilan`$$
CREATE PROCEDURE `login_provjera` (in pUsername varchar(30), in pPassword varchar(30), out pStatus bool)
BEGIN
    select count(*)>0 into pStatus
	from korisnicki_nalog kn
	where kn.Username=pUsername and kn.Password=pPassword;
END$$
DELIMITER ;