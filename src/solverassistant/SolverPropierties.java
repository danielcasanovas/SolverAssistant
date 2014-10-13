/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Solver entity
 */
package solverassistant;

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
        if (solver != null) {
            this.solver = solver;

            solver.setName(this.solver.getName());
            solver.setBenchmark(this.solver.getBenchmark());
            solver.setType(this.solver.getType());
            solver.setTimeOut(this.solver.getTimeOut());
            solver.setMemory(solver.getMemory());
            solver.setNumberOfCores(solver.getNumberOfCores());
            solver.setInstancesList(solver.getInstancesList());
        }
    }

    /**
     * @return the solver
     */
    public Solver getSolver() {
        return solver;
    }

    /**
     * @return the name
     */
    public StringProperty getName() {
        return name;
    }

    /**
     * @return the benchmark
     */
    public StringProperty getBenchmark() {
        return benchmark;
    }

    /**
     * @return the type
     */
    public StringProperty getType() {
        return type;
    }

    /**
     * @return the timeOut
     */
    public IntegerProperty getTimeOut() {
        return timeOut;
    }

    /**
     * @return the memory
     */
    public IntegerProperty getMemory() {
        return memory;
    }

    /**
     * @return the numberOfCores
     */
    public IntegerProperty getNumberOfCores() {
        return numberOfCores;
    }

    /**
     * @return the instancesList
     */
    public List<SolverInstance> getInstancesList() {
        return instancesList;
    }
    
    
}
