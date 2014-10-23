/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Solver entity
 */
package entities;

import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SolverPropierties {

    private Solver solver;
    private StringProperty name = new SimpleStringProperty() {
        @Override
        public void set(String name) {
            super.set(name);
            solver.setName(name);
        }
    };
    private StringProperty benchmark = new SimpleStringProperty() {
        @Override
        public void set(String benchmark) {
            super.set(benchmark);
            solver.setBenchmark(benchmark);
        }
    };
    private StringProperty type = new SimpleStringProperty() {
        @Override
        public void set(String type) {
            super.set(type);
            solver.setType(type);
        }
    };
    private IntegerProperty timeOut = new SimpleIntegerProperty() {
        @Override
        public void set(int timeout) {
            super.set(timeout);
            solver.setTimeOut(timeout);
        }
    };
    private IntegerProperty memory = new SimpleIntegerProperty() {
        @Override
        public void set(int memory) {
            super.set(memory);
            solver.setMemory(memory);
        }
    };
    private IntegerProperty numberOfCores = new SimpleIntegerProperty() {
        @Override
        public void set(int numberOfCores) {
            super.set(numberOfCores);
            solver.setNumberOfCores(numberOfCores);
        }
    };
    private List<SolverInstance> instancesList;

    public SolverPropierties(Solver solver) {
        this.setSolver(solver);
    }

    private void setSolver(Solver solver) {
        if (solver != null) {
            this.solver = solver;
            getNameProperty().set(this.solver.getName());
            getBenchmarkProperty().set(this.solver.getBenchmark());
            getTypeProperty().set(this.solver.getType());
            getTimeOutProperty().set(this.solver.getTimeOut());
            getMemoryProperty().set(this.solver.getMemory());
            getNumberOfCoresProperty().set(this.solver.getNumberOfCores());
            solver.setInstancesList(this.solver.getInstancesList());
        }
    }

    public Solver getSolver() {
        return solver;
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public String getName() {
        return name.getValue();
    }

    public StringProperty getBenchmarkProperty() {
        return benchmark;
    }

    public String getBenchmark() {
        return benchmark.getValue();
    }

    public StringProperty getTypeProperty() {
        return type;
    }

    public String getType() {
        return type.getValue();
    }

    public IntegerProperty getTimeOutProperty() {
        return timeOut;
    }

    public int getTimeOut() {
        return timeOut.getValue();
    }

    public IntegerProperty getMemoryProperty() {
        return memory;
    }

    public int getMemory() {
        return memory.getValue();
    }

    public IntegerProperty getNumberOfCoresProperty() {
        return numberOfCores;
    }

    public int getNumberOfCores() {
        return numberOfCores.getValue();
    }

    public List<SolverInstance> getInstancesList() {
        return instancesList;
    }

}
