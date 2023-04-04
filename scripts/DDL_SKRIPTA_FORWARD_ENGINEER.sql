-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema McMilan
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema McMilan
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `McMilan` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `McMilan` ;

-- -----------------------------------------------------
-- Table `McMilan`.`ADRESA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`ADRESA` (
  `AdresaID` INT NOT NULL AUTO_INCREMENT,
  `Drzava` VARCHAR(60) NOT NULL,
  `Grad` VARCHAR(50) NOT NULL,
  `PTTBroj` INT NOT NULL,
  `NazivUlice` VARCHAR(50) NOT NULL,
  `BrojUlice` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`AdresaID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`OSOBA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`OSOBA` (
  `JMBG` CHAR(13) NOT NULL,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `DatumRodjenja` DATE NOT NULL,
  `PolOsobe` CHAR(1) NOT NULL,
  `AdresaID` INT NULL,
  INDEX `fk_OSOBA_ADRESA_idx` (`AdresaID` ASC) VISIBLE,
  PRIMARY KEY (`JMBG`),
  CONSTRAINT `fk_OSOBA_ADRESA`
    FOREIGN KEY (`AdresaID`)
    REFERENCES `McMilan`.`ADRESA` (`AdresaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`TELEFON_OSOBE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`TELEFON_OSOBE` (
  `BrojTelefona` VARCHAR(20) NOT NULL,
  `JMBG` CHAR(13) NOT NULL,
  PRIMARY KEY (`JMBG`, `BrojTelefona`),
  INDEX `fk_TELEFON_OSOBA1_idx` (`JMBG` ASC) VISIBLE,
  CONSTRAINT `fk_TELEFON_OSOBA1`
    FOREIGN KEY (`JMBG`)
    REFERENCES `McMilan`.`OSOBA` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`KORISNICKI_NALOG`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`KORISNICKI_NALOG` (
  `Username` VARCHAR(30) NOT NULL,
  `Password` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`Username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`VLASNIK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`VLASNIK` (
  `JMBG` CHAR(13) NOT NULL,
  `Username` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`JMBG`),
  INDEX `fk_VLASNIK_OSOBA1_idx` (`JMBG` ASC) VISIBLE,
  INDEX `fk_VLASNIK_KORISNICKI_NALOG1_idx` (`Username` ASC) VISIBLE,
  CONSTRAINT `fk_VLASNIK_OSOBA1`
    FOREIGN KEY (`JMBG`)
    REFERENCES `McMilan`.`OSOBA` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VLASNIK_KORISNICKI_NALOG1`
    FOREIGN KEY (`Username`)
    REFERENCES `McMilan`.`KORISNICKI_NALOG` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`ZAPOSLENI`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`ZAPOSLENI` (
  `JMBG` CHAR(13) NOT NULL,
  PRIMARY KEY (`JMBG`),
  CONSTRAINT `fk_ZAPOSLENI_OSOBA1`
    FOREIGN KEY (`JMBG`)
    REFERENCES `McMilan`.`OSOBA` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`MENADZER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`MENADZER` (
  `JMBG` CHAR(13) NOT NULL,
  `Zvanje` VARCHAR(100) NULL,
  `Username` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`JMBG`),
  INDEX `fk_MENADŽER_KORISNIČKI_NALOG1_idx` (`Username` ASC) VISIBLE,
  CONSTRAINT `fk_MENADŽER_ZAPOSLENI1`
    FOREIGN KEY (`JMBG`)
    REFERENCES `McMilan`.`ZAPOSLENI` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MENADŽER_KORISNIČKI_NALOG1`
    FOREIGN KEY (`Username`)
    REFERENCES `McMilan`.`KORISNICKI_NALOG` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`DIZAJNER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`DIZAJNER` (
  `JMBG` CHAR(13) NOT NULL,
  `Zvanje` VARCHAR(50) NULL,
  `Username` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`JMBG`),
  INDEX `fk_DIZAJNER_KORISNIČKI_NALOG1_idx` (`Username` ASC) VISIBLE,
  CONSTRAINT `fk_DIZAJNER_ZAPOSLENI1`
    FOREIGN KEY (`JMBG`)
    REFERENCES `McMilan`.`ZAPOSLENI` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DIZAJNER_KORISNIČKI_NALOG1`
    FOREIGN KEY (`Username`)
    REFERENCES `McMilan`.`KORISNICKI_NALOG` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`FIRMA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`FIRMA` (
  `JIB` CHAR(13) NOT NULL,
  `PIB` VARCHAR(13) NOT NULL,
  `Naziv` VARCHAR(50) NOT NULL,
  `DatumOsnivanja` DATE NULL,
  `AdresaID` INT NOT NULL,
  `WebsiteURL` VARCHAR(50) NULL,
  `Email` VARCHAR(50) NULL,
  PRIMARY KEY (`JIB`),
  INDEX `fk_FIRMA_ADRESA1_idx` (`AdresaID` ASC) VISIBLE,
  UNIQUE INDEX `PIB_UNIQUE` (`PIB` ASC) VISIBLE,
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE,
  UNIQUE INDEX `WebsiteURL_UNIQUE` (`WebsiteURL` ASC) VISIBLE,
  CONSTRAINT `fk_FIRMA_ADRESA1`
    FOREIGN KEY (`AdresaID`)
    REFERENCES `McMilan`.`ADRESA` (`AdresaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`PROIZVODNI_SEKTOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`PROIZVODNI_SEKTOR` (
  `IdProizvodnogSektora` INT NOT NULL AUTO_INCREMENT,
  `NazivSektora` VARCHAR(50) NOT NULL,
  `JIB` CHAR(13) NOT NULL,
  `AdresaID` INT NOT NULL,
  PRIMARY KEY (`IdProizvodnogSektora`),
  INDEX `fk_PROIZVODNI_SEKTOR_FIRMA1_idx` (`JIB` ASC) VISIBLE,
  INDEX `fk_PROIZVODNI_SEKTOR_ADRESA1_idx` (`AdresaID` ASC) VISIBLE,
  CONSTRAINT `fk_PROIZVODNI_SEKTOR_FIRMA1`
    FOREIGN KEY (`JIB`)
    REFERENCES `McMilan`.`FIRMA` (`JIB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROIZVODNI_SEKTOR_ADRESA1`
    FOREIGN KEY (`AdresaID`)
    REFERENCES `McMilan`.`ADRESA` (`AdresaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`SALON_NAMJESTAJA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`SALON_NAMJESTAJA` (
  `IdSalonaNamjestaja` INT NOT NULL AUTO_INCREMENT,
  `NazivSalona` VARCHAR(50) NOT NULL,
  `AdresaID` INT NOT NULL,
  `JIB` CHAR(13) NOT NULL,
  PRIMARY KEY (`IdSalonaNamjestaja`),
  INDEX `fk_SALON_NAMJEŠTAJA_ADRESA1_idx` (`AdresaID` ASC) VISIBLE,
  INDEX `fk_SALON_NAMJEŠTAJA_FIRMA1_idx` (`JIB` ASC) VISIBLE,
  CONSTRAINT `fk_SALON_NAMJEŠTAJA_ADRESA1`
    FOREIGN KEY (`AdresaID`)
    REFERENCES `McMilan`.`ADRESA` (`AdresaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SALON_NAMJEŠTAJA_FIRMA1`
    FOREIGN KEY (`JIB`)
    REFERENCES `McMilan`.`FIRMA` (`JIB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`RADNIK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`RADNIK` (
  `Uloga` VARCHAR(20) NULL,
  `JMBG` CHAR(13) NOT NULL,
  `IdProizvodnogSektora` INT NULL,
  `IdSalonaNamjestaja` INT NULL,
  `Username` VARCHAR(30) NULL,
  PRIMARY KEY (`JMBG`),
  INDEX `fk_FIZIČKI_RADNIK_PROIZVODNI_SEKTOR1_idx` (`IdProizvodnogSektora` ASC) VISIBLE,
  INDEX `fk_RADNIK_SALON_NAMJEŠTAJA1_idx` (`IdSalonaNamjestaja` ASC) VISIBLE,
  INDEX `fk_RADNIK_KORISNIČKI_NALOG1_idx` (`Username` ASC) VISIBLE,
  CONSTRAINT `fk_FIZIČKI_RADNIK_ZAPOSLENI1`
    FOREIGN KEY (`JMBG`)
    REFERENCES `McMilan`.`ZAPOSLENI` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FIZIČKI_RADNIK_PROIZVODNI_SEKTOR1`
    FOREIGN KEY (`IdProizvodnogSektora`)
    REFERENCES `McMilan`.`PROIZVODNI_SEKTOR` (`IdProizvodnogSektora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RADNIK_SALON_NAMJEŠTAJA1`
    FOREIGN KEY (`IdSalonaNamjestaja`)
    REFERENCES `McMilan`.`SALON_NAMJESTAJA` (`IdSalonaNamjestaja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RADNIK_KORISNIČKI_NALOG1`
    FOREIGN KEY (`Username`)
    REFERENCES `McMilan`.`KORISNICKI_NALOG` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`TELEFON_FIRME`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`TELEFON_FIRME` (
  `JIB` CHAR(13) NOT NULL,
  `BrojTelefona` VARCHAR(20) NOT NULL,
  INDEX `fk_TELEFON_FIRME_FIRMA1_idx` (`JIB` ASC) VISIBLE,
  PRIMARY KEY (`JIB`, `BrojTelefona`),
  CONSTRAINT `fk_TELEFON_FIRME_FIRMA1`
    FOREIGN KEY (`JIB`)
    REFERENCES `McMilan`.`FIRMA` (`JIB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`VLASNIK_POSJEDUJE_FIRMU`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`VLASNIK_POSJEDUJE_FIRMU` (
  `JMBG` CHAR(13) NOT NULL,
  `JIB` CHAR(13) NOT NULL,
  `Datum` DATE NOT NULL,
  `VlasnickiUdio` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`JMBG`, `JIB`, `Datum`),
  INDEX `fk_VLASNIK_has_FIRMA_FIRMA1_idx` (`JIB` ASC) VISIBLE,
  INDEX `fk_VLASNIK_has_FIRMA_VLASNIK1_idx` (`JMBG` ASC) VISIBLE,
  CONSTRAINT `fk_VLASNIK_has_FIRMA_VLASNIK1`
    FOREIGN KEY (`JMBG`)
    REFERENCES `McMilan`.`VLASNIK` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_VLASNIK_has_FIRMA_FIRMA1`
    FOREIGN KEY (`JIB`)
    REFERENCES `McMilan`.`FIRMA` (`JIB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`MASINA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`MASINA` (
  `SerijskiBroj` INT NOT NULL,
  `Model` VARCHAR(50) NOT NULL,
  `Proizvodjac` VARCHAR(50) NOT NULL,
  `IdProizvodnogSektora` INT NOT NULL,
  `Ispravna` TINYINT NULL,
  `GodinaProizvodnje` INT NULL,
  PRIMARY KEY (`SerijskiBroj`),
  INDEX `fk_MAŠINA_PROIZVODNI_SEKTOR1_idx` (`IdProizvodnogSektora` ASC) VISIBLE,
  CONSTRAINT `fk_MAŠINA_PROIZVODNI_SEKTOR1`
    FOREIGN KEY (`IdProizvodnogSektora`)
    REFERENCES `McMilan`.`PROIZVODNI_SEKTOR` (`IdProizvodnogSektora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`KVAR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`KVAR` (
  `SerijskiBroj` INT NOT NULL,
  `DatumDesavanjaKvara` DATE NOT NULL,
  `OpisKvara` VARCHAR(50) NULL,
  `DatumOtklanjanjaKvara` DATE NULL,
  PRIMARY KEY (`SerijskiBroj`, `DatumDesavanjaKvara`),
  CONSTRAINT `fk_KVAR_MAŠINA1`
    FOREIGN KEY (`SerijskiBroj`)
    REFERENCES `McMilan`.`MASINA` (`SerijskiBroj`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`SKLADISTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`SKLADISTE` (
  `IdSkladista` INT NOT NULL AUTO_INCREMENT,
  `TrenutniKapacitetProcentualno` INT NULL,
  `JIB` CHAR(13) NOT NULL,
  `AdresaID` INT NOT NULL,
  PRIMARY KEY (`IdSkladista`),
  INDEX `fk_SKLADIŠTE_FIRMA1_idx` (`JIB` ASC) VISIBLE,
  INDEX `fk_SKLADIŠTE_ADRESA1_idx` (`AdresaID` ASC) VISIBLE,
  CONSTRAINT `fk_SKLADIŠTE_FIRMA1`
    FOREIGN KEY (`JIB`)
    REFERENCES `McMilan`.`FIRMA` (`JIB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SKLADIŠTE_ADRESA1`
    FOREIGN KEY (`AdresaID`)
    REFERENCES `McMilan`.`ADRESA` (`AdresaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`MATERIJAL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`MATERIJAL` (
  `IdMaterijala` INT NOT NULL AUTO_INCREMENT,
  `TipMaterijala` VARCHAR(50) NOT NULL,
  `NazivMaterijala` VARCHAR(50) NULL,
  PRIMARY KEY (`IdMaterijala`),
  UNIQUE INDEX `TipMaterijala_UNIQUE` (`TipMaterijala` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`KATALOG_NAMJESTAJA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`KATALOG_NAMJESTAJA` (
  `IdKataloga` INT NOT NULL AUTO_INCREMENT,
  `NazivKataloga` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`IdKataloga`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`MODEL_NAMJESTAJA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`MODEL_NAMJESTAJA` (
  `IdModelaNamjestaja` INT NOT NULL AUTO_INCREMENT,
  `TipNamjestaja` VARCHAR(20) NULL,
  `NazivModela` VARCHAR(20) NOT NULL,
  `VisinaCm` DECIMAL(6,2) NULL,
  `SirinaCm` DECIMAL(6,2) NULL,
  `DubinaCm` DECIMAL(6,2) NULL,
  `Boja` VARCHAR(20) NULL,
  `ProdajnaCijena` DECIMAL(6,2) NOT NULL,
  `IdKataloga` INT NULL,
  PRIMARY KEY (`IdModelaNamjestaja`),
  INDEX `fk_MODEL_NAMJEŠTAJA_KATALOG_NAMJEŠTAJA1_idx` (`IdKataloga` ASC) VISIBLE,
  UNIQUE INDEX `NazivModela_UNIQUE` (`NazivModela` ASC) VISIBLE,
  CONSTRAINT `fk_MODEL_NAMJEŠTAJA_KATALOG_NAMJEŠTAJA1`
    FOREIGN KEY (`IdKataloga`)
    REFERENCES `McMilan`.`KATALOG_NAMJESTAJA` (`IdKataloga`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`DOBAVLJAC`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`DOBAVLJAC` (
  `JIB` CHAR(13) NOT NULL,
  `Naziv` VARCHAR(50) NOT NULL,
  `KontaktTelefon` VARCHAR(20) NULL,
  `Email` VARCHAR(50) NULL,
  PRIMARY KEY (`JIB`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`BANKA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`BANKA` (
  `IdBanke` INT NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  `KontaktTelefon` VARCHAR(20) NULL,
  `Email` VARCHAR(50) NULL,
  `AdresaID` INT NOT NULL,
  PRIMARY KEY (`IdBanke`),
  INDEX `fk_BANKA_ADRESA1_idx` (`AdresaID` ASC) VISIBLE,
  CONSTRAINT `fk_BANKA_ADRESA1`
    FOREIGN KEY (`AdresaID`)
    REFERENCES `McMilan`.`ADRESA` (`AdresaID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`BANKOVNI_RACUN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`BANKOVNI_RACUN` (
  `BrojRacuna` INT NOT NULL,
  `IdBanke` INT NOT NULL,
  `JIB` CHAR(13) NOT NULL,
  PRIMARY KEY (`BrojRacuna`),
  INDEX `fk_BANKOVNI_RAČUN_BANKA1_idx` (`IdBanke` ASC) VISIBLE,
  INDEX `fk_BANKOVNI_RAČUN_DOBAVLJAČ1_idx` (`JIB` ASC) VISIBLE,
  CONSTRAINT `fk_BANKOVNI_RAČUN_BANKA1`
    FOREIGN KEY (`IdBanke`)
    REFERENCES `McMilan`.`BANKA` (`IdBanke`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BANKOVNI_RAČUN_DOBAVLJAČ1`
    FOREIGN KEY (`JIB`)
    REFERENCES `McMilan`.`DOBAVLJAC` (`JIB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`ISPLATA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`ISPLATA` (
  `BrojIsplate` INT NOT NULL,
  `DatumIsplate` DATE NOT NULL,
  `IznosIsplate` DECIMAL(15,2) NOT NULL,
  `BrojRacuna` INT NOT NULL,
  PRIMARY KEY (`BrojIsplate`),
  INDEX `fk_ISPLATA_BANKOVNI_RAČUN1_idx` (`BrojRacuna` ASC) VISIBLE,
  CONSTRAINT `fk_ISPLATA_BANKOVNI_RAČUN1`
    FOREIGN KEY (`BrojRacuna`)
    REFERENCES `McMilan`.`BANKOVNI_RACUN` (`BrojRacuna`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`NABAVKA_MATERIJALA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`NABAVKA_MATERIJALA` (
  `BrojNabavke` INT NOT NULL AUTO_INCREMENT,
  `DatumNabavke` DATE NOT NULL,
  `DatumIsporuke` DATE NOT NULL,
  `JMBG` CHAR(13) NOT NULL,
  `BrojIsplate` INT NULL,
  `JIB` CHAR(13) NOT NULL,
  `IdSkladista` INT NOT NULL,
  PRIMARY KEY (`BrojNabavke`),
  INDEX `fk_NABAVKA_MATERIJALA_MENADŽER1_idx` (`JMBG` ASC) VISIBLE,
  INDEX `fk_NABAVKA_MATERIJALA_ISPLATA1_idx` (`BrojIsplate` ASC) VISIBLE,
  INDEX `fk_NABAVKA_MATERIJALA_DOBAVLJAČ1_idx` (`JIB` ASC) VISIBLE,
  INDEX `fk_NABAVKA_MATERIJALA_SKLADIŠTE1_idx` (`IdSkladista` ASC) VISIBLE,
  CONSTRAINT `fk_NABAVKA_MATERIJALA_MENADŽER1`
    FOREIGN KEY (`JMBG`)
    REFERENCES `McMilan`.`MENADZER` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NABAVKA_MATERIJALA_ISPLATA1`
    FOREIGN KEY (`BrojIsplate`)
    REFERENCES `McMilan`.`ISPLATA` (`BrojIsplate`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NABAVKA_MATERIJALA_DOBAVLJAČ1`
    FOREIGN KEY (`JIB`)
    REFERENCES `McMilan`.`DOBAVLJAC` (`JIB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NABAVKA_MATERIJALA_SKLADIŠTE1`
    FOREIGN KEY (`IdSkladista`)
    REFERENCES `McMilan`.`SKLADISTE` (`IdSkladista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`NABAVKA_MATERIJALA_STAVKA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`NABAVKA_MATERIJALA_STAVKA` (
  `BrojNabavke` INT NOT NULL,
  `IdMaterijala` INT NOT NULL,
  `MjernaJedinica` VARCHAR(5) NOT NULL,
  `Kolicina` DECIMAL(10,2) NOT NULL,
  `NabavnaCijena` DECIMAL(6,2) NOT NULL,
  `VrijednostBezPDV` DECIMAL(10,2) NOT NULL,
  `PDV` DECIMAL(6,2) NOT NULL,
  `VrijednostSaPDV` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`BrojNabavke`, `IdMaterijala`),
  INDEX `fk_NABAVKA_MATERIJALA_has_MATERIJAL_MATERIJAL1_idx` (`IdMaterijala` ASC) VISIBLE,
  INDEX `fk_NABAVKA_MATERIJALA_has_MATERIJAL_NABAVKA_MATERIJALA1_idx` (`BrojNabavke` ASC) VISIBLE,
  CONSTRAINT `fk_NABAVKA_MATERIJALA_has_MATERIJAL_NABAVKA_MATERIJALA1`
    FOREIGN KEY (`BrojNabavke`)
    REFERENCES `McMilan`.`NABAVKA_MATERIJALA` (`BrojNabavke`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_NABAVKA_MATERIJALA_has_MATERIJAL_MATERIJAL1`
    FOREIGN KEY (`IdMaterijala`)
    REFERENCES `McMilan`.`MATERIJAL` (`IdMaterijala`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`MATERIJAL_U_SKLADISTU`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`MATERIJAL_U_SKLADISTU` (
  `IdMaterijala` INT NOT NULL,
  `Kolicina` DECIMAL(10,2) NOT NULL,
  `MjernaJedinica` VARCHAR(5) NOT NULL,
  `IdSkladista` INT NOT NULL,
  PRIMARY KEY (`IdMaterijala`, `IdSkladista`),
  INDEX `fk_MATERIJAL_has_SKLADIŠTE_MATERIJAL1_idx` (`IdMaterijala` ASC) VISIBLE,
  INDEX `fk_MATERIJAL_U_SKLADIŠTU_SKLADIŠTE1_idx` (`IdSkladista` ASC) VISIBLE,
  CONSTRAINT `fk_MATERIJAL_has_SKLADIŠTE_MATERIJAL1`
    FOREIGN KEY (`IdMaterijala`)
    REFERENCES `McMilan`.`MATERIJAL` (`IdMaterijala`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MATERIJAL_U_SKLADIŠTU_SKLADIŠTE1`
    FOREIGN KEY (`IdSkladista`)
    REFERENCES `McMilan`.`SKLADISTE` (`IdSkladista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`MODEL_NAMJESTAJA_ZAHTIJEVA_MATERIJAL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`MODEL_NAMJESTAJA_ZAHTIJEVA_MATERIJAL` (
  `IdMaterijala` INT NOT NULL,
  `IdModelaNamjestaja` INT NOT NULL,
  `Kolicina` DECIMAL(10,2) NOT NULL,
  `MjernaJedinica` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`IdMaterijala`, `IdModelaNamjestaja`),
  INDEX `fk_MATERIJAL_has_MODEL_NAMJEŠTAJA_MODEL_NAMJEŠTAJA1_idx` (`IdModelaNamjestaja` ASC) VISIBLE,
  INDEX `fk_MATERIJAL_has_MODEL_NAMJEŠTAJA_MATERIJAL1_idx` (`IdMaterijala` ASC) VISIBLE,
  CONSTRAINT `fk_MATERIJAL_has_MODEL_NAMJEŠTAJA_MATERIJAL1`
    FOREIGN KEY (`IdMaterijala`)
    REFERENCES `McMilan`.`MATERIJAL` (`IdMaterijala`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MATERIJAL_has_MODEL_NAMJEŠTAJA_MODEL_NAMJEŠTAJA1`
    FOREIGN KEY (`IdModelaNamjestaja`)
    REFERENCES `McMilan`.`MODEL_NAMJESTAJA` (`IdModelaNamjestaja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`RADNI_UGOVOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`RADNI_UGOVOR` (
  `JMBG` CHAR(13) NOT NULL,
  `JIB` CHAR(13) NOT NULL,
  `MjesecnaPlata` DECIMAL(6,2) NOT NULL,
  `ValutaPlate` VARCHAR(5) NOT NULL DEFAULT 'BAM',
  `DatumUgovoraOd` DATE NOT NULL,
  `DatumUgovoraDo` DATE NOT NULL,
  PRIMARY KEY (`JMBG`, `JIB`, `DatumUgovoraOd`),
  INDEX `fk_ZAPOSLENI_has_FIRMA_FIRMA1_idx` (`JIB` ASC) VISIBLE,
  INDEX `fk_RADNI_UGOVOR_OSOBA1_idx` (`JMBG` ASC) VISIBLE,
  CONSTRAINT `fk_ZAPOSLENI_has_FIRMA_FIRMA1`
    FOREIGN KEY (`JIB`)
    REFERENCES `McMilan`.`FIRMA` (`JIB`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RADNI_UGOVOR_OSOBA1`
    FOREIGN KEY (`JMBG`)
    REFERENCES `McMilan`.`OSOBA` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`RADNIK_POZNAJE_MASINU`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`RADNIK_POZNAJE_MASINU` (
  `JMBG` CHAR(13) NOT NULL,
  `SerijskiBroj` INT NOT NULL,
  PRIMARY KEY (`JMBG`, `SerijskiBroj`),
  INDEX `fk_FIZIČKI_RADNIK_has_MAŠINA_MAŠINA1_idx` (`SerijskiBroj` ASC) VISIBLE,
  INDEX `fk_FIZIČKI_RADNIK_has_MAŠINA_FIZIČKI_RADNIK1_idx` (`JMBG` ASC) VISIBLE,
  CONSTRAINT `fk_FIZIČKI_RADNIK_has_MAŠINA_FIZIČKI_RADNIK1`
    FOREIGN KEY (`JMBG`)
    REFERENCES `McMilan`.`RADNIK` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FIZIČKI_RADNIK_has_MAŠINA_MAŠINA1`
    FOREIGN KEY (`SerijskiBroj`)
    REFERENCES `McMilan`.`MASINA` (`SerijskiBroj`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`DIZAJNER_DIZAJNIRA_MODEL_NAMJESTAJA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`DIZAJNER_DIZAJNIRA_MODEL_NAMJESTAJA` (
  `JMBG` CHAR(13) NOT NULL,
  `IdModelaNamjestaja` INT NOT NULL,
  PRIMARY KEY (`JMBG`, `IdModelaNamjestaja`),
  INDEX `fk_DIZAJNER_has_MODEL_NAMJEŠTAJA_MODEL_NAMJEŠTAJA1_idx` (`IdModelaNamjestaja` ASC) VISIBLE,
  INDEX `fk_DIZAJNER_has_MODEL_NAMJEŠTAJA_DIZAJNER1_idx` (`JMBG` ASC) VISIBLE,
  CONSTRAINT `fk_DIZAJNER_has_MODEL_NAMJEŠTAJA_DIZAJNER1`
    FOREIGN KEY (`JMBG`)
    REFERENCES `McMilan`.`DIZAJNER` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DIZAJNER_has_MODEL_NAMJEŠTAJA_MODEL_NAMJEŠTAJA1`
    FOREIGN KEY (`IdModelaNamjestaja`)
    REFERENCES `McMilan`.`MODEL_NAMJESTAJA` (`IdModelaNamjestaja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`FISKALNI_RACUN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`FISKALNI_RACUN` (
  `IdRacuna` INT NOT NULL AUTO_INCREMENT,
  `DatumRacuna` DATE NOT NULL,
  `UkupanIznos` DECIMAL(15,2) NOT NULL DEFAULT 0.0,
  `JMBG` CHAR(13) NOT NULL,
  `IdSalonaNamjestaja` INT NOT NULL,
  `Izdat` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`IdRacuna`),
  INDEX `fk_FISKALNI_RAČUN_RADNIK1_idx` (`JMBG` ASC) VISIBLE,
  INDEX `fk_FISKALNI_RAČUN_SALON_NAMJEŠTAJA1_idx` (`IdSalonaNamjestaja` ASC) VISIBLE,
  CONSTRAINT `fk_FISKALNI_RAČUN_RADNIK1`
    FOREIGN KEY (`JMBG`)
    REFERENCES `McMilan`.`RADNIK` (`JMBG`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FISKALNI_RAČUN_SALON_NAMJEŠTAJA1`
    FOREIGN KEY (`IdSalonaNamjestaja`)
    REFERENCES `McMilan`.`SALON_NAMJESTAJA` (`IdSalonaNamjestaja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`FISKALNI_RACUN_STAVKA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`FISKALNI_RACUN_STAVKA` (
  `IdRacuna` INT NOT NULL,
  `IdModelaNamjestaja` INT NOT NULL,
  `Cijena` DECIMAL(10,2) NULL,
  `Kolicina` INT NULL,
  PRIMARY KEY (`IdRacuna`, `IdModelaNamjestaja`),
  INDEX `fk_FISKALNI_RAČUN_STAVKA_MODEL_NAMJEŠTAJA1_idx` (`IdModelaNamjestaja` ASC) VISIBLE,
  CONSTRAINT `fk_FISKALNI_RAČUN_STAVKA_FISKALNI_RAČUN1`
    FOREIGN KEY (`IdRacuna`)
    REFERENCES `McMilan`.`FISKALNI_RACUN` (`IdRacuna`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_FISKALNI_RAČUN_STAVKA_MODEL_NAMJEŠTAJA1`
    FOREIGN KEY (`IdModelaNamjestaja`)
    REFERENCES `McMilan`.`MODEL_NAMJESTAJA` (`IdModelaNamjestaja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`MODEL_NAMJESTAJA_SE_CUVA_U_SKLADISTU`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`MODEL_NAMJESTAJA_SE_CUVA_U_SKLADISTU` (
  `IdModelaNamjestaja` INT NOT NULL,
  `IdSkladista` INT NOT NULL,
  `Kolicina` INT NOT NULL,
  PRIMARY KEY (`IdModelaNamjestaja`, `IdSkladista`),
  INDEX `fk_MODEL_NAMJEŠTAJA_has_SKLADIŠTE_SKLADIŠTE1_idx` (`IdSkladista` ASC) VISIBLE,
  INDEX `fk_MODEL_NAMJEŠTAJA_has_SKLADIŠTE_MODEL_NAMJEŠTAJA1_idx` (`IdModelaNamjestaja` ASC) VISIBLE,
  CONSTRAINT `fk_MODEL_NAMJEŠTAJA_has_SKLADIŠTE_MODEL_NAMJEŠTAJA1`
    FOREIGN KEY (`IdModelaNamjestaja`)
    REFERENCES `McMilan`.`MODEL_NAMJESTAJA` (`IdModelaNamjestaja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MODEL_NAMJEŠTAJA_has_SKLADIŠTE_SKLADIŠTE1`
    FOREIGN KEY (`IdSkladista`)
    REFERENCES `McMilan`.`SKLADISTE` (`IdSkladista`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `McMilan`.`MODEL_NAMJESTAJA_SE_CUVA_U_SALONU_NAMJESTAJA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `McMilan`.`MODEL_NAMJESTAJA_SE_CUVA_U_SALONU_NAMJESTAJA` (
  `IdModelaNamjestaja` INT NOT NULL,
  `IdSalonaNamjestaja` INT NOT NULL,
  `Kolicina` INT NOT NULL,
  PRIMARY KEY (`IdModelaNamjestaja`, `IdSalonaNamjestaja`),
  INDEX `fk_MODEL_NAMJEŠTAJA_has_SALON_NAMJEŠTAJA_SALON_NAMJEŠTAJ_idx` (`IdSalonaNamjestaja` ASC) VISIBLE,
  INDEX `fk_MODEL_NAMJEŠTAJA_has_SALON_NAMJEŠTAJA_MODEL_NAMJEŠTAJ_idx` (`IdModelaNamjestaja` ASC) VISIBLE,
  CONSTRAINT `fk_MODEL_NAMJEŠTAJA_has_SALON_NAMJEŠTAJA_MODEL_NAMJEŠTAJA1`
    FOREIGN KEY (`IdModelaNamjestaja`)
    REFERENCES `McMilan`.`MODEL_NAMJESTAJA` (`IdModelaNamjestaja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MODEL_NAMJEŠTAJA_has_SALON_NAMJEŠTAJA_SALON_NAMJEŠTAJA1`
    FOREIGN KEY (`IdSalonaNamjestaja`)
    REFERENCES `McMilan`.`SALON_NAMJESTAJA` (`IdSalonaNamjestaja`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
