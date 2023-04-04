CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `trgovac_view` AS
    SELECT 
        `osoba`.`JMBG` AS `JMBG`,
        `radnik`.`Username` AS `Username`,
        `osoba`.`Ime` AS `Ime`,
        `osoba`.`Prezime` AS `Prezime`,
        `osoba`.`DatumRodjenja` AS `DatumRodjenja`,
        `osoba`.`PolOsobe` AS `PolOsobe`,
        CONCAT(`adresa`.`Grad`,
                ', ',
                `adresa`.`NazivUlice`,
                ' ',
                `adresa`.`BrojUlice`) AS `Previbaliste`,
        `salon_namjestaja`.`IdSalonaNamjestaja` AS `IdSalonaNamjestaja`,
        `salon_namjestaja`.`NazivSalona` AS `Naziv salona`,
        `radni_ugovor`.`DatumUgovoraOd` AS `Datum Od`,
        `radni_ugovor`.`DatumUgovoraDo` AS `Datum Do`,
        COUNT(`fiskalni_racun`.`IdRacuna`) AS `Ukupno izdatih racuna`,
        IFNULL(SUM(`fiskalni_racun`.`UkupanIznos`), 0) AS `Ukupan iznos prodaje`
    FROM
        ((((((`osoba`
        JOIN `adresa` ON ((`osoba`.`AdresaID` = `adresa`.`AdresaID`)))
        JOIN `radnik` ON ((`osoba`.`JMBG` = `radnik`.`JMBG`)))
        JOIN `salon_namjestaja` ON ((`radnik`.`IdSalonaNamjestaja` = `salon_namjestaja`.`IdSalonaNamjestaja`)))
        JOIN `korisnicki_nalog` ON ((`radnik`.`Username` = `korisnicki_nalog`.`Username`)))
        JOIN `radni_ugovor` ON ((`osoba`.`JMBG` = `radni_ugovor`.`JMBG`)))
        LEFT JOIN `fiskalni_racun` ON ((`osoba`.`JMBG` = `fiskalni_racun`.`JMBG`)))
    WHERE
        (`radnik`.`Uloga` = 'Trgovac')
    GROUP BY `osoba`.`JMBG`
	
CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `salon_statistika` AS
    SELECT 
        `salon_namjestaja`.`IdSalonaNamjestaja` AS `IdSalonaNamjestaja`,
        `model_namjestaja`.`IdModelaNamjestaja` AS `IdModelaNamjestaja`,
        `model_namjestaja`.`TipNamjestaja` AS `TipNamjestaja`,
        `model_namjestaja`.`NazivModela` AS `NazivModela`,
        `katalog_namjestaja`.`NazivKataloga` AS `NazivKataloga`,
        `model_namjestaja`.`ProdajnaCijena` AS `ProdajnaCijena`,
        `model_namjestaja_se_cuva_u_salonu_namjestaja`.`Kolicina` AS `Kolicina`
    FROM
        (((`salon_namjestaja`
        JOIN `model_namjestaja_se_cuva_u_salonu_namjestaja` ON ((`salon_namjestaja`.`IdSalonaNamjestaja` = `model_namjestaja_se_cuva_u_salonu_namjestaja`.`IdSalonaNamjestaja`)))
        JOIN `model_namjestaja` ON ((`model_namjestaja_se_cuva_u_salonu_namjestaja`.`IdModelaNamjestaja` = `model_namjestaja`.`IdModelaNamjestaja`)))
        LEFT JOIN `katalog_namjestaja` ON ((`model_namjestaja`.`IdKataloga` = `katalog_namjestaja`.`IdKataloga`)))