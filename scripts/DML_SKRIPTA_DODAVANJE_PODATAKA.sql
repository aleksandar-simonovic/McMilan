use mcmilan;

-- ADRESA
INSERT INTO `mcmilan`.`adresa` (`Drzava`, `Grad`, `PTTBroj`, `NazivUlice`, `BrojUlice`) VALUES ('Bosna i Hercegovina', 'Banja Luka', '78000', 'Veljka Mladjenovica', 'bb');
INSERT INTO `mcmilan`.`adresa` (`Drzava`, `Grad`, `PTTBroj`, `NazivUlice`, `BrojUlice`) VALUES ('Bosna i Hercegovina', 'Banja Luka', '78000', 'Milosa Obilica', '12');
INSERT INTO `mcmilan`.`adresa` (`Drzava`, `Grad`, `PTTBroj`, `NazivUlice`, `BrojUlice`) VALUES ('Bosna i Hercegovina', 'Banja Luka', '78000', 'Ranka Sipke', '21');
INSERT INTO `mcmilan`.`adresa` (`Drzava`, `Grad`, `PTTBroj`, `NazivUlice`, `BrojUlice`) VALUES ('Bosna i Hercegovina', 'Banja Luka', '78000', 'Stepe Stepanovica', '50');
INSERT INTO `mcmilan`.`adresa` (`Drzava`, `Grad`, `PTTBroj`, `NazivUlice`, `BrojUlice`) VALUES ('Bosna i Hercegovina', 'Banja Luka', '78000', 'Bulevar Cara Dusana', '6');
INSERT INTO `mcmilan`.`adresa` (`Drzava`, `Grad`, `PTTBroj`, `NazivUlice`, `BrojUlice`) VALUES ('Bosna i Hercegovina', 'Banja Luka', '78000', 'Kralja Petra 1 Karadjordjevica', '13');
INSERT INTO `mcmilan`.`adresa` (`Drzava`, `Grad`, `PTTBroj`, `NazivUlice`, `BrojUlice`) VALUES ('Bosna i Hercegovina', 'Banja Luka', '78000', 'Carice Milice', '12');
INSERT INTO `mcmilan`.`adresa` (`Drzava`, `Grad`, `PTTBroj`, `NazivUlice`, `BrojUlice`) VALUES ('Bosna i Hercegovina', 'Banja Luka', '78000', 'Cara Lazara', '20');
INSERT INTO `mcmilan`.`adresa` (`Drzava`, `Grad`, `PTTBroj`, `NazivUlice`, `BrojUlice`) VALUES ('Bosna i Hercegovina', 'Banja Luka', '78000', 'Prvog krajiskog korpusa', '29');
INSERT INTO `mcmilan`.`adresa` (`Drzava`, `Grad`, `PTTBroj`, `NazivUlice`, `BrojUlice`) VALUES ('Bosna i Hercegovina', 'Banja Luka', '78000', 'Mirka Kovacevica', '6');
SELECT * FROM mcmilan.adresa;

-- FIRMA
INSERT INTO `mcmilan`.`firma` (`JIB`, `PIB`, `Naziv`, `DatumOsnivanja`, `AdresaID`, `Email`) VALUES ('4402370030001', '4402370030001', 'Galerija McMilan', '2009-12-20', '1', 'galerija@mcmilan.com');
SELECT * FROM mcmilan.firma;

-- TELEFON_FIRME
INSERT INTO `mcmilan`.`telefon_firme` (`JIB`, `BrojTelefona`) VALUES ('4402370030001', '051450352');
SELECT * FROM mcmilan.telefon_firme;

-- SALON_NAMJESTAJA
INSERT INTO `mcmilan`.`salon_namjestaja` (`NazivSalona`, `AdresaID`, `JIB`) VALUES ('Salon Namjestaja McMilan', '1', '4402370030001');
SELECT * FROM mcmilan.salon_namjestaja;

-- SKLADISTE
INSERT INTO `mcmilan`.`skladiste` (`TrenutniKapacitetProcentualno`, `JIB`, `AdresaID`) VALUES ('20', '4402370030001', '1');
SELECT * FROM mcmilan.skladiste;

-- PROIZVODNI_SEKTOR
INSERT INTO `mcmilan`.`proizvodni_sektor` (`NazivSektora`, `JIB`, `AdresaID`) VALUES ('Sjecenje materijala', '4402370030001', '1');
INSERT INTO `mcmilan`.`proizvodni_sektor` (`NazivSektora`, `JIB`, `AdresaID`) VALUES ('Lakirnica', '4402370030001', '1');
INSERT INTO `mcmilan`.`proizvodni_sektor` (`NazivSektora`, `JIB`, `AdresaID`) VALUES ('Kantovanje materijala', '4402370030001', '1');
INSERT INTO `mcmilan`.`proizvodni_sektor` (`NazivSektora`, `JIB`, `AdresaID`) VALUES ('Sklapanje proizvoda', '4402370030001', '1');
INSERT INTO `mcmilan`.`proizvodni_sektor` (`NazivSektora`, `JIB`, `AdresaID`) VALUES ('CNC obrada', '4402370030001', '1');
SELECT * FROM mcmilan.proizvodni_sektor;

-- OSOBA
INSERT INTO `mcmilan`.`osoba` (`JMBG`, `Ime`, `Prezime`, `DatumRodjenja`, `PolOsobe`, `AdresaID`) VALUES ('0000000000000', 'Aleksandar', 'Simonovic', '1997-11-26', 'M', 2);
INSERT INTO `mcmilan`.`osoba` (`JMBG`, `Ime`, `Prezime`, `DatumRodjenja`, `PolOsobe`, `AdresaID`) VALUES ('1111111111111', 'Darija', 'Stefanovic', '1991-01-01', 'F', 3);
INSERT INTO `mcmilan`.`osoba` (`JMBG`, `Ime`, `Prezime`, `DatumRodjenja`, `PolOsobe`, `AdresaID`) VALUES ('2222222222222', 'Bojana', 'Bojanic', '1992-02-02', 'F', 4);
INSERT INTO `mcmilan`.`osoba` (`JMBG`, `Ime`, `Prezime`, `DatumRodjenja`, `PolOsobe`, `AdresaID`) VALUES ('3333333333333', 'Dalibor', 'Jovanovic', '1993-03-03', 'M', 3);
INSERT INTO `mcmilan`.`osoba` (`JMBG`, `Ime`, `Prezime`, `DatumRodjenja`, `PolOsobe`, `AdresaID`) VALUES ('4444444444444', 'Marija', 'Pesic', '1994-04-04', 'F', 5);
INSERT INTO `mcmilan`.`osoba` (`JMBG`, `Ime`, `Prezime`, `DatumRodjenja`, `PolOsobe`, `AdresaID`) VALUES ('5555555555555', 'Lazar', 'Vukasinovic', '1995-05-05', 'M', 4);
INSERT INTO `mcmilan`.`osoba` (`JMBG`, `Ime`, `Prezime`, `DatumRodjenja`, `PolOsobe`, `AdresaID`) VALUES ('6666666666666', 'Vesna', 'Novakovic', '1996-06-06', 'F', 6);
INSERT INTO `mcmilan`.`osoba` (`JMBG`, `Ime`, `Prezime`, `DatumRodjenja`, `PolOsobe`, `AdresaID`) VALUES ('7777777777777', 'Strahinja', 'Borisavljevic', '1989-9-9', 'M', '7');
INSERT INTO `mcmilan`.`osoba` (`JMBG`, `Ime`, `Prezime`, `DatumRodjenja`, `PolOsobe`, `AdresaID`) VALUES ('8888888888888', 'Vladimir', 'Borisavljevic', '1987-7-7', 'M', '7');
INSERT INTO `mcmilan`.`osoba` (`JMBG`, `Ime`, `Prezime`, `DatumRodjenja`, `PolOsobe`, `AdresaID`) VALUES ('9999999999999', 'Milan', 'Cvetkovic', '1985-5-5', 'M', '8');
INSERT INTO `mcmilan`.`osoba` (`JMBG`, `Ime`, `Prezime`, `DatumRodjenja`, `PolOsobe`, `AdresaID`) VALUES ('0101010101010', 'Radovan', 'Pajic', '1983-3-3', 'M', '9');
INSERT INTO `mcmilan`.`osoba` (`JMBG`, `Ime`, `Prezime`, `DatumRodjenja`, `PolOsobe`, `AdresaID`) VALUES ('1212121212121', 'Duska', 'Petrovic', '1986-6-6', 'F', '9');
SELECT * FROM mcmilan.osoba;

-- TELEFON_OSOBE
INSERT INTO `mcmilan`.`telefon_osobe` (`BrojTelefona`, `JMBG`) VALUES ('065000000', '0000000000000');
INSERT INTO `mcmilan`.`telefon_osobe` (`BrojTelefona`, `JMBG`) VALUES ('065111111', '1111111111111');
INSERT INTO `mcmilan`.`telefon_osobe` (`BrojTelefona`, `JMBG`) VALUES ('065222222', '2222222222222');
INSERT INTO `mcmilan`.`telefon_osobe` (`BrojTelefona`, `JMBG`) VALUES ('065333333', '3333333333333');
INSERT INTO `mcmilan`.`telefon_osobe` (`BrojTelefona`, `JMBG`) VALUES ('065444444', '4444444444444');
INSERT INTO `mcmilan`.`telefon_osobe` (`BrojTelefona`, `JMBG`) VALUES ('065555555', '5555555555555');
INSERT INTO `mcmilan`.`telefon_osobe` (`BrojTelefona`, `JMBG`) VALUES ('065666666', '6666666666666');
INSERT INTO `mcmilan`.`telefon_osobe` (`BrojTelefona`, `JMBG`) VALUES ('065777777', '7777777777777');
INSERT INTO `mcmilan`.`telefon_osobe` (`BrojTelefona`, `JMBG`) VALUES ('065888888', '8888888888888');
INSERT INTO `mcmilan`.`telefon_osobe` (`BrojTelefona`, `JMBG`) VALUES ('065999999', '9999999999999');
INSERT INTO `mcmilan`.`telefon_osobe` (`BrojTelefona`, `JMBG`) VALUES ('065010101', '0101010101010');
INSERT INTO `mcmilan`.`telefon_osobe` (`BrojTelefona`, `JMBG`) VALUES ('065121212', '1212121212121');
SELECT * FROM mcmilan.telefon_osobe;

-- ZAPOSLENI
INSERT INTO `mcmilan`.`zaposleni` (`JMBG`) VALUES ('3333333333333');
INSERT INTO `mcmilan`.`zaposleni` (`JMBG`) VALUES ('4444444444444');
INSERT INTO `mcmilan`.`zaposleni` (`JMBG`) VALUES ('5555555555555');
INSERT INTO `mcmilan`.`zaposleni` (`JMBG`) VALUES ('6666666666666');
INSERT INTO `mcmilan`.`zaposleni` (`JMBG`) VALUES ('7777777777777');
INSERT INTO `mcmilan`.`zaposleni` (`JMBG`) VALUES ('8888888888888');
INSERT INTO `mcmilan`.`zaposleni` (`JMBG`) VALUES ('9999999999999');
INSERT INTO `mcmilan`.`zaposleni` (`JMBG`) VALUES ('0101010101010');
INSERT INTO `mcmilan`.`zaposleni` (`JMBG`) VALUES ('1212121212121');
SELECT * FROM mcmilan.zaposleni;

-- KORISNICKI_NALOG
INSERT INTO `mcmilan`.`korisnicki_nalog` (`Username`, `Password`) VALUES ('aleksandar', 'simonovic123');
INSERT INTO `mcmilan`.`korisnicki_nalog` (`Username`, `Password`) VALUES ('dalibor', 'jovanovic123');
INSERT INTO `mcmilan`.`korisnicki_nalog` (`Username`, `Password`) VALUES ('marija', 'pesic123');
INSERT INTO `mcmilan`.`korisnicki_nalog` (`Username`, `Password`) VALUES ('lazar', 'vukasinovic123');
INSERT INTO `mcmilan`.`korisnicki_nalog` (`Username`, `Password`) VALUES ('vesna', 'novakovic123');
INSERT INTO `mcmilan`.`korisnicki_nalog` (`Username`, `Password`) VALUES ('duska', 'petrovic123');
SELECT * FROM mcmilan.korisnicki_nalog;

-- VLASNIK
INSERT INTO `mcmilan`.`vlasnik` (`JMBG`, `Username`) VALUES ('0000000000000', 'aleksandar');
SELECT * FROM mcmilan.vlasnik;

-- VLASNIK_POSJEDUJE_FIRMU
INSERT INTO `mcmilan`.`vlasnik_posjeduje_firmu` (`JMBG`, `JIB`, `Datum`, `VlasnickiUdio`) VALUES ('0000000000000', '4402370030001', '2009-12-20', '100');
SELECT * FROM mcmilan.vlasnik_posjeduje_firmu;

-- RADNI_UGOVOR
INSERT INTO `mcmilan`.`radni_ugovor` (`JMBG`, `JIB`, `MjesecnaPlata`, `ValutaPlate`, `DatumUgovoraOd`, `DatumUgovoraDo`) VALUES ('1111111111111', '4402370030001', '1000', 'BAM', '2010-01-01', '2014-01-01');
INSERT INTO `mcmilan`.`radni_ugovor` (`JMBG`, `JIB`, `MjesecnaPlata`, `ValutaPlate`, `DatumUgovoraOd`, `DatumUgovoraDo`) VALUES ('2222222222222', '4402370030001', '1500', 'BAM', '2014-02-02', '2018-02-02');
INSERT INTO `mcmilan`.`radni_ugovor` (`JMBG`, `JIB`, `MjesecnaPlata`, `ValutaPlate`, `DatumUgovoraOd`, `DatumUgovoraDo`) VALUES ('3333333333333', '4402370030001', '1300', 'BAM', '2010-03-03', '2024-03-03');
INSERT INTO `mcmilan`.`radni_ugovor` (`JMBG`, `JIB`, `MjesecnaPlata`, `ValutaPlate`, `DatumUgovoraOd`, `DatumUgovoraDo`) VALUES ('4444444444444', '4402370030001', '1400', 'BAM', '2021-04-04', '2025-04-04');
INSERT INTO `mcmilan`.`radni_ugovor` (`JMBG`, `JIB`, `MjesecnaPlata`, `ValutaPlate`, `DatumUgovoraOd`, `DatumUgovoraDo`) VALUES ('5555555555555', '4402370030001', '1200', 'BAM', '2020-05-05', '2024-05-05');
INSERT INTO `mcmilan`.`radni_ugovor` (`JMBG`, `JIB`, `MjesecnaPlata`, `ValutaPlate`, `DatumUgovoraOd`, `DatumUgovoraDo`) VALUES ('6666666666666', '4402370030001', '1600', 'BAM', '2019-06-06', '2023-06-06');
INSERT INTO `mcmilan`.`radni_ugovor` (`JMBG`, `JIB`, `MjesecnaPlata`, `ValutaPlate`, `DatumUgovoraOd`, `DatumUgovoraDo`) VALUES ('7777777777777', '4402370030001', '1300', 'BAM', '2019-07-07', '2023-07-07');
INSERT INTO `mcmilan`.`radni_ugovor` (`JMBG`, `JIB`, `MjesecnaPlata`, `ValutaPlate`, `DatumUgovoraOd`, `DatumUgovoraDo`) VALUES ('8888888888888', '4402370030001', '1200', 'BAM', '2021-08-08', '2025-08-08');
INSERT INTO `mcmilan`.`radni_ugovor` (`JMBG`, `JIB`, `MjesecnaPlata`, `ValutaPlate`, `DatumUgovoraOd`, `DatumUgovoraDo`) VALUES ('9999999999999', '4402370030001', '1000', 'BAM', '2022-01-01', '2026-01-01');
INSERT INTO `mcmilan`.`radni_ugovor` (`JMBG`, `JIB`, `MjesecnaPlata`, `ValutaPlate`, `DatumUgovoraOd`, `DatumUgovoraDo`) VALUES ('0101010101010', '4402370030001', '900' , 'BAM', '2020-02-20', '2024-02-20');
INSERT INTO `mcmilan`.`radni_ugovor` (`JMBG`, `JIB`, `MjesecnaPlata`, `ValutaPlate`, `DatumUgovoraOd`, `DatumUgovoraDo`) VALUES ('1212121212121', '4402370030001', '1200', 'BAM', '2019-03-21', '2023-03-21');
SELECT * FROM mcmilan.radni_ugovor;

-- RADNIK
INSERT INTO `mcmilan`.`radnik` (`Uloga`, `JMBG`, `IdSalonaNamjestaja`, `Username`) VALUES ('Trgovac', '6666666666666', '1', 'vesna');
INSERT INTO `mcmilan`.`radnik` (`Uloga`, `JMBG`, `IdSalonaNamjestaja`, `Username`) VALUES ('Trgovac', '1212121212121', '1', 'duska');
INSERT INTO `mcmilan`.`radnik` (`Uloga`, `JMBG`, `IdSalonaNamjestaja`, `Username`) VALUES ('Trgovac', '3333333333333', '1', 'dalibor');
INSERT INTO `mcmilan`.`radnik` (`Uloga`, `JMBG`, `IdProizvodnogSektora`) VALUES ('Fizicki radnik', '7777777777777', '1');
INSERT INTO `mcmilan`.`radnik` (`Uloga`, `JMBG`, `IdProizvodnogSektora`) VALUES ('Fizicki radnik', '8888888888888', '2');
INSERT INTO `mcmilan`.`radnik` (`Uloga`, `JMBG`, `IdProizvodnogSektora`) VALUES ('Fizicki radnik', '9999999999999', '3');
INSERT INTO `mcmilan`.`radnik` (`Uloga`, `JMBG`, `IdProizvodnogSektora`) VALUES ('Fizicki radnik', '0101010101010', '4');
SELECT * FROM mcmilan.radnik;

-- MASINA
INSERT INTO `mcmilan`.`masina` (`SerijskiBroj`, `Model`, `Proizvodjac`, `IdProizvodnogSektora`, `GodinaProizvodnje`) VALUES ('11', 'CNC 3000 Pro', 'CNC&Co', '5', '2008');
INSERT INTO `mcmilan`.`masina` (`SerijskiBroj`, `Model`, `Proizvodjac`, `IdProizvodnogSektora`, `GodinaProizvodnje`) VALUES ('22', 'Raskrajac 2000 Pro', 'Raskrajac&Co', '1', '2010');
INSERT INTO `mcmilan`.`masina` (`SerijskiBroj`, `Model`, `Proizvodjac`, `IdProizvodnogSektora`, `GodinaProizvodnje`) VALUES ('33', 'Kantarica 1000 Max', 'Kantarica&Co', '2', '2011');
INSERT INTO `mcmilan`.`masina` (`SerijskiBroj`, `Model`, `Proizvodjac`, `IdProizvodnogSektora`, `GodinaProizvodnje`) VALUES ('44', 'Tracna Pila 5000 Ultra', 'Pila&Co', '1', '2015');
INSERT INTO `mcmilan`.`masina` (`SerijskiBroj`, `Model`, `Proizvodjac`, `IdProizvodnogSektora`, `GodinaProizvodnje`) VALUES ('55', 'Kompresor 4000 Power', 'Kompresor&Co', '3', '2018');
SELECT * FROM mcmilan.masina;

-- RADNIK_POZNAJE_MASINU
INSERT INTO `mcmilan`.`radnik_poznaje_masinu` (`JMBG`, `SerijskiBroj`) VALUES ('7777777777777', '11');
INSERT INTO `mcmilan`.`radnik_poznaje_masinu` (`JMBG`, `SerijskiBroj`) VALUES ('8888888888888', '22');
INSERT INTO `mcmilan`.`radnik_poznaje_masinu` (`JMBG`, `SerijskiBroj`) VALUES ('9999999999999', '33');
INSERT INTO `mcmilan`.`radnik_poznaje_masinu` (`JMBG`, `SerijskiBroj`) VALUES ('0101010101010', '44');
INSERT INTO `mcmilan`.`radnik_poznaje_masinu` (`JMBG`, `SerijskiBroj`) VALUES ('7777777777777', '55');
INSERT INTO `mcmilan`.`radnik_poznaje_masinu` (`JMBG`, `SerijskiBroj`) VALUES ('8888888888888', '11');
INSERT INTO `mcmilan`.`radnik_poznaje_masinu` (`JMBG`, `SerijskiBroj`) VALUES ('9999999999999', '22');
SELECT * FROM mcmilan.radnik_poznaje_masinu;

-- DIZAJNER
INSERT INTO `mcmilan`.`dizajner` (`JMBG`, `Zvanje`, `Username`) VALUES ('4444444444444', 'Diplomirani arhitekta', 'marija');
SELECT * FROM mcmilan.dizajner;

-- KATALOG_NAMJESTAJA
INSERT INTO `mcmilan`.`katalog_namjestaja` (`NazivKataloga`) VALUES ('Dnevni boravak');
INSERT INTO `mcmilan`.`katalog_namjestaja` (`NazivKataloga`) VALUES ('Spavaci i djeciji program');
INSERT INTO `mcmilan`.`katalog_namjestaja` (`NazivKataloga`) VALUES ('Ormari i ugradbeni plakari');
INSERT INTO `mcmilan`.`katalog_namjestaja` (`NazivKataloga`) VALUES ('Predsoblja i komode');
INSERT INTO `mcmilan`.`katalog_namjestaja` (`NazivKataloga`) VALUES ('Kancelarijski program');
INSERT INTO `mcmilan`.`katalog_namjestaja` (`NazivKataloga`) VALUES ('Kuhinje i trpezarije');
SELECT * FROM mcmilan.katalog_namjestaja;

-- MODEL_NAMJESTAJA
ALTER TABLE mcmilan.model_namjestaja AUTO_INCREMENT = 0;
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Ugaona garnitura', 'Artic II', '270', '170', 'Po izboru kupca', '1750', '1');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Ugaona garnitura', 'Madera', '280', '218', 'Po izboru kupca', '2260', '1');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Ugaona garnitura', 'Verona', '292', '234', 'Braon', '3890', '1');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `SirinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Trosjed', 'Prato', '230', 'Siva', '1080', '1');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `VisinaCm`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Fotelja', 'Solaro', '70', '75', '78', 'Plava/Crna/Zuta', '499', '1');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `VisinaCm`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Fotelja', 'Moskva', '60', '120', '40', 'Bijela', '410', '1');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `VisinaCm`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Stolic', 'Lotos', '60', '120', '43', 'Bijela', '430', '1');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `VisinaCm`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Radni sto', 'Kiki', '74', '91', '50', 'Siva', '185', '2');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Krevet samac', 'Pinokio', '90', '200', 'Bijela', '220', '2');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Krevet bracni', 'Beta', '160', '200', 'Po izboru kupca', '960', '2');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Krevet bracni', 'Omega', '160', '200', 'Po izboru kupca', '1130', '2');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `VisinaCm`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Ormar', 'Sierra', '210', '210', '62.5', 'Crna', '880', '3');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `VisinaCm`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Ormar', 'Navara 165', '215', '165', '60', 'Hrast', '715', '3');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `VisinaCm`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Ormar', 'Trio-G33', '200', '125', '56', 'Hrast', '789', '3');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `VisinaCm`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Predsoblje', 'Kent', '195', '114', '36', 'Bijela', '390', '4');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `VisinaCm`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Cipelar', 'Arco 2', '60', '102', '30', 'Hrast', '210', '4');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `VisinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Vjesalica', 'Pepe', '200', 'Crna', '110', '4');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `VisinaCm`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Radna stolica', 'Nily', '90', '53', '59', 'Crna', '290', '5');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `VisinaCm`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Radna stolica', 'Wolf II', '105', '58', '60', 'Crna', '169', '5');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `SirinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Radni sto', 'KS6', '125', 'Hrast', '250', '5');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `SirinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Kuhinja', 'Kent', '200', 'Po izboru kupca', '890', '6');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `SirinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Kuhinja', 'Diva', '200', 'Bijela', '940', '6');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `SirinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Huhinja', 'Olya', '200', 'Bijela', '599', '6');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Trpezarijski sto', 'Paladium', '100', '200', 'Hrast', '1130', '6');
INSERT INTO `mcmilan`.`model_namjestaja` (`TipNamjestaja`, `NazivModela`, `SirinaCm`, `DubinaCm`, `Boja`, `ProdajnaCijena`, `IdKataloga`) VALUES ('Trpezarijski sto', 'Jez', '90', '160', 'Hrast', '930', '6');
SELECT * FROM mcmilan.model_namjestaja;

-- DIZAJNER_DIZAJNIRA_MODEL_NAMJESTAJA
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '1');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '2');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '3');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '4');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '5');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '6');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '7');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '8');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '9');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '10');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '11');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '12');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '13');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '14');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '15');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '16');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '17');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '18');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '19');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '20');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '21');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '22');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '23');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '24');
INSERT INTO `mcmilan`.`dizajner_dizajnira_model_namjestaja` (`JMBG`, `IdModelaNamjestaja`) VALUES ('4444444444444', '25');
SELECT * FROM mcmilan.dizajner_dizajnira_model_namjestaja;

-- MENADZER
INSERT INTO `mcmilan`.`menadzer` (`JMBG`, `Zvanje`, `Username`) VALUES ('5555555555555', 'Diplomirani menadzer', 'lazar');
SELECT * FROM mcmilan.menadzer;

-- DOBAVLJAC
INSERT INTO `mcmilan`.`dobavljac` (`JIB`, `Naziv`, `KontaktTelefon`, `Email`) VALUES ('1122334455667', 'MaterialCompany', '051112233', 'materialcompany@gmail.com');
SELECT * FROM mcmilan.dobavljac;

-- MODEL_NAMJESTAJA_SE_CUVA_U_SALONU_NAMJESTAJA
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('1', '1', '5');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('2', '1', '7');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('3', '1', '10');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('4', '1', '20');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('5', '1', '3');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('6', '1', '5');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('7', '1', '1');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('8', '1', '7');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('9', '1', '4');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('10', '1', '13');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('11', '1', '22');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('12', '1', '14');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('13', '1', '6');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('14', '1', '5');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('15', '1', '12');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('16', '1', '10');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('17', '1', '3');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('18', '1', '2');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('19', '1', '1');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('20', '1', '16');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('21', '1', '4');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('22', '1', '2');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('23', '1', '11');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('24', '1', '9');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_salonu_namjestaja` (`IdModelaNamjestaja`, `IdSalonaNamjestaja`, `Kolicina`) VALUES ('25', '1', '13');
SELECT * FROM mcmilan.model_namjestaja_se_cuva_u_salonu_namjestaja;

-- MODEL_NAMJESTAJA_SE_CUVA_U_SKLADISTU
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('1', '1', '31');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('2', '1', '42');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('3', '1', '57');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('4', '1', '63');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('5', '1', '11');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('6', '1', '25');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('7', '1', '52');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('8', '1', '19');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('9', '1', '27');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('10', '1', '34');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('11', '1', '43');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('12', '1', '61');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('13', '1', '12');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('14', '1', '33');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('15', '1', '44');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('16', '1', '21');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('17', '1', '42');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('18', '1', '54');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('19', '1', '61');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('20', '1', '72');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('21', '1', '21');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('22', '1', '33');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('23', '1', '15');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('24', '1', '22');
INSERT INTO `mcmilan`.`model_namjestaja_se_cuva_u_skladistu` (`IdModelaNamjestaja`, `IdSkladista`, `Kolicina`) VALUES ('25', '1', '38');
SELECT * FROM mcmilan.model_namjestaja_se_cuva_u_skladistu;

-- BANKA
INSERT INTO `mcmilan`.`banka` (`Naziv`, `KontaktTelefon`, `Email`, `AdresaID`) VALUES ('Najnovija Banka', '051112233', 'najnovijabanka@bank.com', '10');
SELECT * FROM mcmilan.banka;

-- BANKOVNI_RACUN
INSERT INTO `mcmilan`.`bankovni_racun` (`BrojRacuna`, `IdBanke`, `JIB`) VALUES ('11223344', '1', '1122334455667');
SELECT * FROM mcmilan.bankovni_racun;