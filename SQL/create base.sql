SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `mydb` ;
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Product` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Product` (
  `idProduct` INT NOT NULL AUTO_INCREMENT ,
  `Name` VARCHAR(45) NOT NULL ,
  `LongName` VARCHAR(75) NOT NULL ,
  `Descryption` VARCHAR(200) NULL ,
  `DefaultPrice` FLOAT NULL ,
  `DefaultTax` FLOAT NULL ,
  `Code` INT NULL ,
  `Picture` VARCHAR(50) NULL ,
  PRIMARY KEY (`idProduct`) ,
  UNIQUE INDEX `idTowar_UNIQUE` (`idProduct` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Warehause`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Warehause` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Warehause` (
  `idWarehause` INT NOT NULL AUTO_INCREMENT ,
  `Name` VARCHAR(45) NOT NULL ,
  `Description` BLOB NULL ,
  `Address` VARCHAR(75) NULL ,
  `City` VARCHAR(45) NULL ,
  `PostCode` INT NULL ,
  `Default?` TINYINT(1) NOT NULL DEFAULT False ,
  PRIMARY KEY (`idWarehause`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Stock`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Stock` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Stock` (
  `idProduct` INT NOT NULL ,
  `idWarehause` INT NOT NULL ,
  `Stock` FLOAT NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`idProduct`, `idWarehause`) ,
  INDEX `fk_Product_idx` (`idProduct` ASC) ,
  INDEX `fk_Warehause_idx` (`idWarehause` ASC) ,
  CONSTRAINT `idProduct`
    FOREIGN KEY (`idProduct` )
    REFERENCES `mydb`.`Product` (`idProduct` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idWarehause`
    FOREIGN KEY (`idWarehause` )
    REFERENCES `mydb`.`Warehause` (`idWarehause` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Contractor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Contractor` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Contractor` (
  `idContractor` INT NOT NULL AUTO_INCREMENT ,
  `Name` VARCHAR(45) NOT NULL ,
  `LongName` VARCHAR(200) NOT NULL ,
  `NIP` INT NOT NULL ,
  `REGON` INT NULL ,
  `Street` VARCHAR(75) NOT NULL ,
  `City` VARCHAR(50) NOT NULL ,
  `PostCode` INT NOT NULL ,
  `Country` VARCHAR(50) NOT NULL DEFAULT 'Poland' ,
  `Region` VARCHAR(50) NULL ,
  `Type` CHAR NOT NULL DEFAULT 'B' ,
  PRIMARY KEY (`idContractor`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Role` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Role` (
  `idRole` INT NOT NULL AUTO_INCREMENT ,
  `Name` VARCHAR(45) NOT NULL ,
  `AddInvoice` TINYINT(1) NOT NULL DEFAULT false ,
  `ViewInvoice` TINYINT(1) NOT NULL DEFAULT false ,
  `AddProduct` TINYINT(1) NOT NULL DEFAULT false ,
  `ViewProduct` TINYINT(1) NOT NULL DEFAULT false ,
  `AddReciept` TINYINT(1) NOT NULL DEFAULT false ,
  `ViewReciept` TINYINT(1) NOT NULL DEFAULT false ,
  `AddBuyInvoice` TINYINT(1) NOT NULL DEFAULT false ,
  `ViewBuyInvoice` TINYINT(1) NOT NULL DEFAULT false ,
  `AddRole` TINYINT(1) NOT NULL DEFAULT false ,
  `ViewRole` TINYINT(1) NOT NULL DEFAULT false ,
  `AddContractor` TINYINT(1) NOT NULL DEFAULT false ,
  `ViewContractor` TINYINT(1) NOT NULL DEFAULT false ,
  `AddUser` TINYINT(1) NOT NULL DEFAULT false ,
  `ViewUser` TINYINT(1) NOT NULL DEFAULT false ,
  `ViewWarehause` TINYINT(1) NOT NULL DEFAULT false ,
  `AddWarehause` TINYINT(1) NOT NULL DEFAULT false ,
  `ViewDelivery` TINYINT(1) NOT NULL DEFAULT false ,
  `AddDelivery` TINYINT(1) NOT NULL DEFAULT false ,
  PRIMARY KEY (`idRole`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`User` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`User` (
  `idUser` VARCHAR(15) NOT NULL ,
  `Name` VARCHAR(45) NOT NULL ,
  `Surname` VARCHAR(45) NOT NULL ,
  `idRole` INT NOT NULL ,
  `Password` VARCHAR(45) NOT NULL ,
  `Active` TINYINT(1) NOT NULL DEFAULT true ,
  `Admin` TINYINT(1) NOT NULL DEFAULT false ,
  PRIMARY KEY (`idUser`) ,
  INDEX `fk_User_Roles1_idx` (`idRole` ASC) ,
  CONSTRAINT `fk_User_Roles1`
    FOREIGN KEY (`idRole` )
    REFERENCES `mydb`.`Role` (`idRole` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ExternalDelivery`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ExternalDelivery` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`ExternalDelivery` (
  `idExternalDelivery` INT NOT NULL AUTO_INCREMENT ,
  `idWarehause` INT NOT NULL ,
  `Auto` TINYINT(1) NOT NULL DEFAULT False ,
  `Date` DATE NOT NULL ,
  `idContractor` INT NULL ,
  `idUser` VARCHAR(15) NOT NULL ,
  PRIMARY KEY (`idExternalDelivery`) ,
  INDEX `fk_Warehause_idx` (`idWarehause` ASC) ,
  INDEX `fk_ExternalDelivery_Contractor1_idx` (`idContractor` ASC) ,
  INDEX `fk_ExternalDelivery_User1_idx` (`idUser` ASC) ,
  CONSTRAINT `fk_Warehause`
    FOREIGN KEY (`idWarehause` )
    REFERENCES `mydb`.`Warehause` (`idWarehause` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ExternalDelivery_Contractor1`
    FOREIGN KEY (`idContractor` )
    REFERENCES `mydb`.`Contractor` (`idContractor` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ExternalDelivery_User1`
    FOREIGN KEY (`idUser` )
    REFERENCES `mydb`.`User` (`idUser` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Invoice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Invoice` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Invoice` (
  `idInvoice` INT NOT NULL ,
  `idExternalDelivery` INT NULL ,
  `idContractor` INT NULL ,
  `User_idUser` VARCHAR(15) NOT NULL ,
  `Amount` FLOAT NOT NULL DEFAULT 0 ,
  `PayDay` DATE NULL ,
  `Date` DATE NOT NULL ,
  `SellDate` DATE NOT NULL ,
  PRIMARY KEY (`idInvoice`) ,
  INDEX `fk_Invoice_ExternalDelivery1_idx` (`idExternalDelivery` ASC) ,
  INDEX `fk_Invoice_Contractor1_idx` (`idContractor` ASC) ,
  INDEX `fk_Invoice_User1_idx` (`User_idUser` ASC) ,
  CONSTRAINT `fk_Invoice_ExternalDelivery1`
    FOREIGN KEY (`idExternalDelivery` )
    REFERENCES `mydb`.`ExternalDelivery` (`idExternalDelivery` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Invoice_Contractor1`
    FOREIGN KEY (`idContractor` )
    REFERENCES `mydb`.`Contractor` (`idContractor` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Invoice_User1`
    FOREIGN KEY (`User_idUser` )
    REFERENCES `mydb`.`User` (`idUser` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DeliveryPosition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`DeliveryPosition` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`DeliveryPosition` (
  `idProduct` INT NOT NULL ,
  `idExternalDelivery` INT NOT NULL ,
  `Count` FLOAT NOT NULL ,
  `Price` FLOAT NOT NULL ,
  `Unit` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`idProduct`, `idExternalDelivery`) ,
  INDEX `fk_Product_idx` (`idProduct` ASC) ,
  INDEX `fk_ExternalDelivery_idx` (`idExternalDelivery` ASC) ,
  CONSTRAINT `fk_Product`
    FOREIGN KEY (`idProduct` )
    REFERENCES `mydb`.`Product` (`idProduct` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ExternalDelivery`
    FOREIGN KEY (`idExternalDelivery` )
    REFERENCES `mydb`.`ExternalDelivery` (`idExternalDelivery` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Receipt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Receipt` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Receipt` (
  `idReceipt` INT NOT NULL AUTO_INCREMENT ,
  `idExternalDelivery` INT NULL ,
  `idUser` VARCHAR(15) NOT NULL ,
  `Date` DATE NOT NULL ,
  `PayDate` DATE NOT NULL ,
  `SellDate` DATE NOT NULL ,
  PRIMARY KEY (`idReceipt`) ,
  INDEX `fk_Receipt_ExternalDelivery1_idx` (`idExternalDelivery` ASC) ,
  INDEX `fk_Receipt_User1_idx` (`idUser` ASC) ,
  CONSTRAINT `fk_Receipt_ExternalDelivery1`
    FOREIGN KEY (`idExternalDelivery` )
    REFERENCES `mydb`.`ExternalDelivery` (`idExternalDelivery` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Receipt_User1`
    FOREIGN KEY (`idUser` )
    REFERENCES `mydb`.`User` (`idUser` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`OneTimeProduct`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`OneTimeProduct` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`OneTimeProduct` (
  `idOneTimeProduct` INT NOT NULL AUTO_INCREMENT ,
  `idInvoice` INT NULL ,
  `idReceipt` INT NULL ,
  `Name` VARCHAR(50) NOT NULL ,
  `Description` VARCHAR(200) NULL ,
  `Unit` VARCHAR(10) NOT NULL ,
  `Price` FLOAT NOT NULL DEFAULT '0.0' ,
  `Count` FLOAT NOT NULL DEFAULT '1' ,
  PRIMARY KEY (`idOneTimeProduct`) ,
  INDEX `fk_OneTimeProduct_Invoice1_idx` (`idInvoice` ASC) ,
  INDEX `fk_OneTimeProduct_Receipt1_idx` (`idReceipt` ASC) ,
  CONSTRAINT `fk_OneTimeProduct_Invoice1`
    FOREIGN KEY (`idInvoice` )
    REFERENCES `mydb`.`Invoice` (`idInvoice` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OneTimeProduct_Receipt1`
    FOREIGN KEY (`idReceipt` )
    REFERENCES `mydb`.`Receipt` (`idReceipt` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ExternalAdoption`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ExternalAdoption` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`ExternalAdoption` (
  `idExternalAdoption` INT NOT NULL AUTO_INCREMENT ,
  `idContractor` INT NULL ,
  `idUser` VARCHAR(15) NOT NULL ,
  `Date` DATE NOT NULL ,
  `PayDate` DATE NOT NULL ,
  `BuyDate` DATE NOT NULL ,
  `Amount` FLOAT NULL ,
  PRIMARY KEY (`idExternalAdoption`) ,
  INDEX `fk_InternalAdoption_Contractor1_idx` (`idContractor` ASC) ,
  INDEX `fk_ExternalAdoption_User1_idx` (`idUser` ASC) ,
  CONSTRAINT `fk_InternalAdoption_Contractor1`
    FOREIGN KEY (`idContractor` )
    REFERENCES `mydb`.`Contractor` (`idContractor` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ExternalAdoption_User1`
    FOREIGN KEY (`idUser` )
    REFERENCES `mydb`.`User` (`idUser` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`AdoptionPosition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`AdoptionPosition` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`AdoptionPosition` (
  `idExternalAdoption` INT NOT NULL ,
  `idProduct` INT NOT NULL ,
  `Count` FLOAT NOT NULL DEFAULT 1.0 ,
  `Price` FLOAT NOT NULL DEFAULT 0.0 ,
  `Unit` VARCHAR(10) NOT NULL DEFAULT 'szt.' ,
  PRIMARY KEY (`idExternalAdoption`, `idProduct`) ,
  INDEX `fk_AdoptionPosition_Product1_idx` (`idProduct` ASC) ,
  CONSTRAINT `fk_AdoptionPosition_ExternalAdoption1`
    FOREIGN KEY (`idExternalAdoption` )
    REFERENCES `mydb`.`ExternalAdoption` (`idExternalAdoption` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AdoptionPosition_Product1`
    FOREIGN KEY (`idProduct` )
    REFERENCES `mydb`.`Product` (`idProduct` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`BuyInvoice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`BuyInvoice` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`BuyInvoice` (
  `idBuyInvoice` INT NOT NULL AUTO_INCREMENT ,
  `idExternalAdoption` INT NOT NULL ,
  PRIMARY KEY (`idBuyInvoice`) ,
  INDEX `fk_BuyInvoice_ExternalAdoption1_idx` (`idExternalAdoption` ASC) ,
  CONSTRAINT `fk_BuyInvoice_ExternalAdoption1`
    FOREIGN KEY (`idExternalAdoption` )
    REFERENCES `mydb`.`ExternalAdoption` (`idExternalAdoption` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Tax`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Tax` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`Tax` (
  `idTax` INT NOT NULL AUTO_INCREMENT ,
  `Amount` VARCHAR(45) NOT NULL ,
  `Name` FLOAT NOT NULL ,
  PRIMARY KEY (`idTax`) )
ENGINE = InnoDB;

USE `mydb` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
