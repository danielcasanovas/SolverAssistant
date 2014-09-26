/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Solver entity
 */
package solverassistant;

import java.util.ArrayList;
import java.util.List;

public class Solver {

    private String name;
    private String benchmark;
    private String type;
    private int timeOut;
    private int memory;
    private int numberOfCores;
    private List<SolverInstance> instancesList;

    public Solver() {
        this.name = "";
        this.benchmark = "";
        this.type = "";
        this.timeOut = 0;
        this.memory = 0;
        this.numberOfCores = 0;
        this.instancesList = new ArrayList<>();
    }

    public Solver(String name, String benchmark, String type, int timeOut, int memory, int numberOfCores, List<SolverInstance> instancesList) {
        this.name = name;
        this.benchmark = benchmark;
        this.type = type;
        this.timeOut = timeOut;
        this.memory = memory;
        this.numberOfCores = numberOfCores;
        this.instancesList = instancesList;
    }

    /**
     * @return the name of the solver
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the benchmark
     */
    public String getBenchmark() {
        return benchmark;
    }

    /**
     * @param benchmark the benchmark to set
     */
    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the timeOut
     */
    public int getTimeOut() {
        return timeOut;
    }

    /**
     * @param timeOut the timeOut to set
     */
    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * @return the memory
     */
    public int getMemory() {
        return memory;
    }

    /**
     * @param memory the memory to set
     */
    public void setMemory(int memory) {
        this.memory = memory;
    }

    /**
     * @return the numberOfCores
     */
    public int getNumberOfCores() {
        return numberOfCores;
    }

    /**
     * @param numberOfCores the numberOfCores to set
     */
    public void setNumberOfCores(int numberOfCores) {
        this.numberOfCores = numberOfCores;
    }

    /**
     * @return the instancesList
     */
    public List<SolverInstance> getInstancesList() {
        return instancesList;
    }

    /**
     * @param instancesList the instancesList to set
     */
    public void setInstancesList(List<SolverInstance> instancesList) {
        this.instancesList = instancesList;
    }
    
    /**
     * @param instance instance to add
     */
    public void addInstanceToList(SolverInstance instance){
        this.instancesList.add(instance);
    }
}
