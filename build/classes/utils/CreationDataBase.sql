
-- -----------------------------------------------------
-- -------------------- DATABASE -----------------------
-- -----------------------------------------------------
USE `SolverAssistant`;

-- -----------------------------------------------------
-- ------------------ MAIN ENTITIES --------------------
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `Solver` (
  `SolverId` INT(11) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Benchmark` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Type` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `TimeOut` INT(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Memory` INT(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NumberOfCores` INT(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`SolverId`)
) ENGINE=InnoDB  
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `SolverInstance` (
  `SolverInstanceId` int(11) NOT NULL AUTO_INCREMENT,
  `Instance` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Time` DECIMAL(11,4) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Optimum` TINYINT(1) NOT NULL DEFAULT 0,
  `Solution` INT(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Info` INT(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `TimeOut` INT(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Buggy` INT(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SegmentationFault` INT(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Log` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `OutOfMemory` INT(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NumberOfVariables` INT(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NumberOfClause` INT(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NumberOfHardClause` INT(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NumberOfSoftClause` INT(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NumberOfUnsatClause` INT(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `NumberOfUnsatClauseWeigth` INT(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `SolverId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`SolverInstanceId`),
  CONSTRAINT `fk_solver`
    FOREIGN KEY (`SolverId`)
    REFERENCES `Solver` (`SolverId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB  
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;
