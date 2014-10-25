-- -----------------------------------------------------
-- ------------------ MAIN ENTITIES --------------------
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `Solver` (
  `SolverId` INTEGER PRIMARY KEY AUTOINCREMENT,
  `Name` TEXT DEFAULT NULL,
  `Benchmark` TEXT DEFAULT NULL,
  `Type` TEXT DEFAULT NULL,
  `TimeOut` INTEGER DEFAULT NULL,
  `Memory` INTEGER DEFAULT NULL,
  `NumberOfCores` INTEGER DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `SolverInstance` (
  `SolverInstanceId` INTEGER PRIMARY KEY AUTOINCREMENT,
  `Instance` TEXT DEFAULT NULL,
  `Time` NUMERIC DEFAULT NULL,
  `Optimum` NUMERIC DEFAULT NULL,
  `Solution` INTEGER DEFAULT NULL,
  `Info` INTEGER DEFAULT NULL,
  `TimeOut` INTEGER DEFAULT NULL,
  `Buggy` INTEGER DEFAULT NULL,
  `SegmentationFault` INTEGER DEFAULT NULL,
  `Log` TEXT DEFAULT NULL,
  `OutOfMemory` INTEGER DEFAULT NULL,
  `NumberOfVariables` INTEGER DEFAULT NULL,
  `NumberOfClause` INTEGER DEFAULT NULL,
  `NumberOfHardClause` INTEGER DEFAULT NULL,
  `NumberOfSoftClause` INTEGER DEFAULT NULL,
  `NumberOfUnsatClause` INTEGER DEFAULT NULL,
  `NumberOfUnsatClauseWeigth` INTEGER DEFAULT NULL,
  `SolverId` INTEGER REFERENCES `Solver`(`SolverId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
