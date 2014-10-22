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
        this.setInstance(solverInstance);
    }

    public final void setInstance(SolverInstance solverInstance) {
        if (solverInstance != null) {
            this.solverInstance = solverInstance;
            getFileNameProperty().set(solverInstance.getFileName());
            getTimeProperty().set(solverInstance.getTime());
            getOptimumProperty().set(solverInstance.isOptimum());
            getSolutionProperty().set(solverInstance.getSolution());
            getInfoProperty().set(solverInstance.getInfo());
            getTimeOutProperty().set(solverInstance.getTimeOut());
            getBuggyProperty().set(solverInstance.getBuggy());
            getSegmentationFaultProperty().set(solverInstance.getSegmentationFault());
            getOutOfMemoryProperty().set(solverInstance.getOutOfMemory());
            getLogProperty().set(solverInstance.getLog());
            getNumberOfVariablesProperty().set(solverInstance.getNumberOfVariables());
            getNumberOfClauseProperty().set(solverInstance.getNumberOfClause());
            getNumberOfHardClauseProperty().set(solverInstance.getNumberOfHardClause());
            getNumberOfSoftClauseProperty().set(solverInstance.getNumberOfSoftClause());
            getNumberOfUnsatClauseProperty().set(solverInstance.getNumberOfUnsatClause());
            getUnsatClauseWeigthProperty().set(solverInstance.getUnsatClauseWeigth());
        }
    }

    /**
     * @return the solverInstance
     */
    public SolverInstance getSolverInstance() {
        return solverInstance;
    }

    public StringProperty getFileNameProperty() {
        return fileName;
    }

    public String getFileName() {
        return fileName.getValue();
    }

    public DoubleProperty getTimeProperty() {
        return time;
    }

    public double getTime() {
        return time.getValue();
    }

    public BooleanProperty getOptimumProperty() {
        return optimum;
    }

    public boolean getOptimum() {
        return optimum.getValue();
    }

    public IntegerProperty getSolutionProperty() {
        return solution;
    }

    public int getSolution() {
        return solution.getValue();
    }

    public IntegerProperty getInfoProperty() {
        return info;
    }

    public int getInfo() {
        return info.getValue();
    }

    public IntegerProperty getTimeOutProperty() {
        return timeOut;
    }

    public int getTimeOut() {
        return timeOut.getValue();
    }

    public IntegerProperty getBuggyProperty() {
        return buggy;
    }

    public int getBuggy() {
        return buggy.getValue();
    }

    public IntegerProperty getSegmentationFaultProperty() {
        return segmentationFault;
    }

    public int getSegmentationFault() {
        return segmentationFault.getValue();
    }

    public IntegerProperty getOutOfMemoryProperty() {
        return outOfMemory;
    }

    public int getOutOfMemory() {
        return outOfMemory.getValue();
    }

    public StringProperty getLogProperty() {
        return log;
    }

    public String getLog() {
        return log.getValue();
    }

    public IntegerProperty getNumberOfVariablesProperty() {
        return numberOfVariables;
    }

    public int getNumberOfVariables() {
        return numberOfVariables.getValue();
    }

    public IntegerProperty getNumberOfClauseProperty() {
        return numberOfClause;
    }

    public int getNumberOfClause() {
        return numberOfClause.getValue();
    }

    public IntegerProperty getNumberOfHardClauseProperty() {
        return numberOfHardClause;
    }

    public int getNumberOfHardClause() {
        return numberOfHardClause.getValue();
    }

    public IntegerProperty getNumberOfSoftClauseProperty() {
        return numberOfSoftClause;
    }

    public int getNumberOfSoftClause() {
        return numberOfSoftClause.getValue();
    }

    public IntegerProperty getNumberOfUnsatClauseProperty() {
        return numberOfUnsatClause;
    }

    public int getNumberOfUnsatClause() {
        return numberOfUnsatClause.getValue();
    }

    public IntegerProperty getUnsatClauseWeigthProperty() {
        return unsatClauseWeigth;
    }

    public int getUnsatClauseWeigth() {
        return unsatClauseWeigth.getValue();
    }
}
