package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CompareSolver {
    
    private String folder;
    private int numberOfInstances;
    private Map<String, ArrayList<Double>> solverMap;
    
    public CompareSolver(String type) {
        this.folder = type;
        solverMap = new HashMap<>();
    }

    /**
     * @return the folder
     */
    public String getFolder() {
        return folder;
    }

    /**
     * @param folder the folder to set
     */
    public void setFolder(String folder) {
        this.folder = folder;
    }

    /**
     * @return the numberOfInstances
     */
    public int getNumberOfInstances() {
        return numberOfInstances;
    }

    /**
     * @param numberOfInstances the numberOfInstances to set
     */
    public void setNumberOfInstances(int numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    /**
     * @return the solverMap
     */
    public Map<String, ArrayList<Double>> getMultiMap() {
        return solverMap;
    }

    /**
     * @param multiMap the solverMap to set
     */
    public void setMultiMap(Map<String, ArrayList<Double>> multiMap) {
        this.solverMap = multiMap;
    }

    /**
     *
     * @param solver
     * @param solutionInfo
     */
    public void addToHashMap(String solver, ArrayList solutionInfo) {
        this.solverMap.put(solver, solutionInfo);
    }
    
    @Override
    public boolean equals(Object object) {
        return (object instanceof CompareSolver) && ((CompareSolver) object).getFolder().equals(this.getFolder());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.folder);
        hash = 29 * hash + this.numberOfInstances;
        hash = 29 * hash + Objects.hashCode(this.solverMap);
        return hash;
    }
}
