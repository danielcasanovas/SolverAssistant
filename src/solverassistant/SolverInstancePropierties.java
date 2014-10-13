/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Solver entity
 */
package solverassistant;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;

public class SolverInstancePropierties {

    private SolverInstance solverInstance;
    private StringProperty fileName = new SimpleStringProperty() {
        @Override
        public void set(String fileName) {
            super.set(fileName);
            solverInstance.setFileName(fileName);
        }
    };
    private DoubleProperty time = new SimpleDoubleProperty() {
        @Override
        public void set(double time) {
            super.set(time);
            solverInstance.setTime(time);
        }
    };
    private BooleanProperty optimum = new SimpleBooleanProperty() {
        @Override
        public void set(boolean optimum) {
            super.set(optimum);
            solverInstance.setOptimum(optimum);
        }
    };
    private IntegerProperty solution = new SimpleIntegerProperty() {
        @Override
        public void set(int solution) {
            super.set(solution);
            solverInstance.setSolution(solution);
        }
    };
    private IntegerProperty info = new SimpleIntegerProperty() {
        @Override
        public void set(int info) {
            super.set(info);
            solverInstance.setInfo(info);
        }
    };
    private IntegerProperty timeOut = new SimpleIntegerProperty() {
        @Override
        public void set(int timeOut) {
            super.set(timeOut);
            solverInstance.setTimeOut(timeOut);
        }
    };
    private IntegerProperty buggy = new SimpleIntegerProperty() {
        @Override
        public void set(int buggy) {
            super.set(buggy);
            solverInstance.setBuggy(buggy);
        }
    };
    private IntegerProperty segmentationFault = new SimpleIntegerProperty() {
        @Override
        public void set(int segmentationFault) {
            super.set(segmentationFault);
            solverInstance.setSegmentationFault(segmentationFault);
        }
    };
    private IntegerProperty outOfMemory = new SimpleIntegerProperty() {
        @Override
        public void set(int outOfMemory) {
            super.set(outOfMemory);
            solverInstance.setOutOfMemory(outOfMemory);
        }
    };

    private StringProperty log = new SimpleStringProperty() {
        @Override
        public void set(String log) {
            super.set(log);
            solverInstance.setLog(log);
        }
    };
    private IntegerProperty numberOfVariables = new SimpleIntegerProperty() {
        @Override
        public void set(int numberOfVariables) {
            super.set(numberOfVariables);
            solverInstance.setNumberOfVariables(numberOfVariables);
        }
    };
    private IntegerProperty numberOfClause = new SimpleIntegerProperty() {
        @Override
        public void set(int numberOfClause) {
            super.set(numberOfClause);
            solverInstance.setNumberOfClause(numberOfClause);
        }
    };
    private IntegerProperty numberOfHardClause = new SimpleIntegerProperty() {
        @Override
        public void set(int numberOfHardClause) {
            super.set(numberOfHardClause);
            solverInstance.setNumberOfHardClause(numberOfHardClause);
        }
    };
    private IntegerProperty numberOfSoftClause = new SimpleIntegerProperty() {
        @Override
        public void set(int numberOfSoftClause) {
            super.set(numberOfSoftClause);
            solverInstance.setNumberOfSoftClause(numberOfSoftClause);
        }
    };
    private IntegerProperty numberOfUnsatClause = new SimpleIntegerProperty() {
        @Override
        public void set(int numberOfUnsatClause) {
            super.set(numberOfUnsatClause);
            solverInstance.setNumberOfUnsatClause(numberOfUnsatClause);
        }
    };
    private IntegerProperty unsatClauseWeigth = new SimpleIntegerProperty() {
        @Override
        public void set(int unsatClauseWeigth) {
            super.set(unsatClauseWeigth);
            solverInstance.setNumberOfUnsatClause(unsatClauseWeigth);
        }
    };

    public SolverInstancePropierties(SolverInstance solverInstance) {
        if (solverInstance != null) {
            this.solverInstance = solverInstance;

            solverInstance.setFileName(this.solverInstance.getFileName());
            solverInstance.setTime(this.solverInstance.getTime());
            solverInstance.setOptimum(this.solverInstance.isOptimum());
            solverInstance.setSolution(this.solverInstance.getSolution());
            solverInstance.setInfo(this.solverInstance.getInfo());
            solverInstance.setTimeOut(this.solverInstance.getTimeOut());
            solverInstance.setBuggy(this.solverInstance.getBuggy());
            solverInstance.setSegmentationFault(this.solverInstance.getSegmentationFault());
            solverInstance.setOutOfMemory(this.solverInstance.getOutOfMemory());
            solverInstance.setLog(this.solverInstance.getLog());
            solverInstance.setNumberOfVariables(this.solverInstance.getNumberOfVariables());
            solverInstance.setNumberOfClause(this.solverInstance.getNumberOfClause());
            solverInstance.setNumberOfHardClause(this.solverInstance.getNumberOfHardClause());
            solverInstance.setNumberOfSoftClause(this.solverInstance.getNumberOfSoftClause());
            solverInstance.setNumberOfUnsatClause(this.solverInstance.getNumberOfUnsatClause());
            solverInstance.setUnsatClauseWeigth(this.solverInstance.getUnsatClauseWeigth());

        }
    }

    /**
     * @return the solverInstance
     */
    public SolverInstance getSolverInstance() {
        return solverInstance;
    }

    /**
     * @return the fileName
     */
    public StringProperty getFileName() {
        return fileName;
    }

    /**
     * @return the time
     */
    public DoubleProperty getTime() {
        return time;
    }

    /**
     * @return the optimum
     */
    public BooleanProperty getOptimum() {
        return optimum;
    }

    /**
     * @return the solution
     */
    public IntegerProperty getSolution() {
        return solution;
    }

    /**
     * @return the info
     */
    public IntegerProperty getInfo() {
        return info;
    }

    /**
     * @return the timeOut
     */
    public IntegerProperty getTimeOut() {
        return timeOut;
    }

    /**
     * @return the buggy
     */
    public IntegerProperty getBuggy() {
        return buggy;
    }

    /**
     * @return the segmentationFault
     */
    public IntegerProperty getSegmentationFault() {
        return segmentationFault;
    }

    /**
     * @return the outOfMemory
     */
    public IntegerProperty getOutOfMemory() {
        return outOfMemory;
    }

    /**
     * @return the log
     */
    public StringProperty getLog() {
        return log;
    }

    /**
     * @return the numberOfVariables
     */
    public IntegerProperty getNumberOfVariables() {
        return numberOfVariables;
    }

    /**
     * @return the numberOfClause
     */
    public IntegerProperty getNumberOfClause() {
        return numberOfClause;
    }

    /**
     * @return the numberOfHardClause
     */
    public IntegerProperty getNumberOfHardClause() {
        return numberOfHardClause;
    }

    /**
     * @return the numberOfSoftClause
     */
    public IntegerProperty getNumberOfSoftClause() {
        return numberOfSoftClause;
    }

    /**
     * @return the numberOfUnsatClause
     */
    public IntegerProperty getNumberOfUnsatClause() {
        return numberOfUnsatClause;
    }

    /**
     * @return the unsatClauseWeigth
     */
    public IntegerProperty getUnsatClauseWeigth() {
        return unsatClauseWeigth;
    }
    
}
