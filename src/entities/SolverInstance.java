/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Solver Instance Entity
 */
package entities;

public class SolverInstance {

    private String fileName;
    private double time;
    private int optimum;
    private String solution;
    private int info;
    private int timeOut;
    private int buggy;
    private int segmentationFault;
    private int outOfMemory;
    private String log;
    private int numberOfVariables;
    private int numberOfClause;
    private int numberOfHardClause;
    private int numberOfSoftClause;
    private int numberOfUnsatClause;
    private int unsatClauseWeigth;

    public SolverInstance() {
        this.fileName = "";
        this.time = 0;
        this.optimum = 0;
        this.solution = "";
        this.info = 0;
        this.timeOut = 0;
        this.buggy = 0;
        this.segmentationFault = 0;
        this.outOfMemory = 0;
        this.log = "";
        this.numberOfVariables = 0;
        this.numberOfClause = 0;
        this.numberOfHardClause = 0;
        this.numberOfSoftClause = 0;
        this.numberOfUnsatClause = 0;
        this.unsatClauseWeigth = 0;
    }

    public SolverInstance(String fileName, double time, int optimum, String solution, int info, int timeOut, int buggy, int segmentationFault, int outOfMemory, String log, int numberOfVariables, int numberOfClause, int numberOfHardClause, int numberOfSoftClause, int numberOfUnsatClause, int unsatClauseWeigth) {
        this.fileName = fileName;
        this.time = time;
        this.optimum = optimum;
        this.solution = solution;
        this.info = info;
        this.timeOut = timeOut;
        this.buggy = buggy;
        this.segmentationFault = segmentationFault;
        this.outOfMemory = outOfMemory;
        this.log = log;
        this.numberOfVariables = numberOfVariables;
        this.numberOfClause = numberOfClause;
        this.numberOfHardClause = numberOfHardClause;
        this.numberOfSoftClause = numberOfSoftClause;
        this.numberOfUnsatClause = numberOfUnsatClause;
        this.unsatClauseWeigth = unsatClauseWeigth;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the time
     */
    public double getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(double time) {
        this.time = time;
    }

    /**
     * @return the optimum
     */
    public int getOptimum() {
        return optimum;
    }

    /**
     * @param optimum the optimum to set
     */
    public void setOptimum(int optimum) {
        this.optimum = optimum;
    }

    /**
     * @return the solution
     */
    public String getSolution() {
        return solution;
    }

    /**
     * @param solution the solution to set
     */
    public void setSolution(String solution) {
        this.solution = solution;
    }

    /**
     * @return the info
     */
    public int getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(int info) {
        this.info = info;
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
     * @return the buggy
     */
    public int getBuggy() {
        return buggy;
    }

    /**
     * @param buggy the buggy to set
     */
    public void setBuggy(int buggy) {
        this.buggy = buggy;
    }

    /**
     * @return the segmentationFault
     */
    public int getSegmentationFault() {
        return segmentationFault;
    }

    /**
     * @param segmentationFault the segmentationFault to set
     */
    public void setSegmentationFault(int segmentationFault) {
        this.segmentationFault = segmentationFault;
    }

    /**
     * @return the outOfMemory
     */
    public int getOutOfMemory() {
        return outOfMemory;
    }

    /**
     * @param outOfMemory the outOfMemory to set
     */
    public void setOutOfMemory(int outOfMemory) {
        this.outOfMemory = outOfMemory;
    }

    /**
     * @return the log
     */
    public String getLog() {
        return log;
    }

    /**
     * @param log the log to set
     */
    public void setLog(String log) {
        this.log = log;
    }

    /**
     * @return the numberOfVariables
     */
    public int getNumberOfVariables() {
        return numberOfVariables;
    }

    /**
     * @param numberOfVariables the numberOfVariables to set
     */
    public void setNumberOfVariables(int numberOfVariables) {
        this.numberOfVariables = numberOfVariables;
    }

    /**
     * @return the numberOfClause
     */
    public int getNumberOfClause() {
        return numberOfClause;
    }

    /**
     * @param numberOfClause the numberOfClause to set
     */
    public void setNumberOfClause(int numberOfClause) {
        this.numberOfClause = numberOfClause;
    }

    /**
     * @return the numberOfHardClause
     */
    public int getNumberOfHardClause() {
        return numberOfHardClause;
    }

    /**
     * @param numberOfHardClause the numberOfHardClause to set
     */
    public void setNumberOfHardClause(int numberOfHardClause) {
        this.numberOfHardClause = numberOfHardClause;
    }

    /**
     * @return the numberOfSoftClause
     */
    public int getNumberOfSoftClause() {
        return numberOfSoftClause;
    }

    /**
     * @param numberOfSoftClause the numberOfSoftClause to set
     */
    public void setNumberOfSoftClause(int numberOfSoftClause) {
        this.numberOfSoftClause = numberOfSoftClause;
    }

    /**
     * @return the numberOfUnsatClause
     */
    public int getNumberOfUnsatClause() {
        return numberOfUnsatClause;
    }

    /**
     * @param numberOfUnsatClause the numberOfUnsatClause to set
     */
    public void setNumberOfUnsatClause(int numberOfUnsatClause) {
        this.numberOfUnsatClause = numberOfUnsatClause;
    }

    /**
     * @return the unsatClauseWeigth
     */
    public int getUnsatClauseWeigth() {
        return unsatClauseWeigth;
    }

    /**
     * @param unsatClauseWeigth the unsatClauseWeigth to set
     */
    public void setUnsatClauseWeigth(int unsatClauseWeigth) {
        this.unsatClauseWeigth = unsatClauseWeigth;
    }
}
