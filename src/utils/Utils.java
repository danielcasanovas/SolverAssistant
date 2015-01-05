/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Utils
 */
package utils;

import entities.CompareSolver;
import entities.SolverInstance;
import entities.Solver;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import solverassistant.SolverManager;

public class Utils {

    public Utils() {
    }

    /**
     * Read a file
     *
     * @param file File to read
     * @return String with the content
     */
    public String fileReader(File file) {
        String content = "";
        FileReader fr = null;
        BufferedReader br = null;
        try {
            // Opening File
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            // Reading File
            String linea;
            while ((linea = br.readLine()) != null) {
                content += linea;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return content;
    }

    /**
     * Write content in a file
     *
     * @param path Name of the file
     * @param content Content of the file
     */
    public static void fileWriter(String path, String content) {
        Writer writer = null;
        try {
            File dir = new File("exports/");
            dir.mkdir();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("exports/" + path), "utf-8"));
            writer.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Write content of language in a file
     *
     * @param path Name of the file
     * @param content Content of the file
     */
    public static void fileWriterLanguage(String path, String content) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));
            writer.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Create Solver with all his instances from content
     *
     * @param logName Solver info
     * @param log SolverInstances info
     * @return Solver created
     */
    public static Solver createSolverFromData(String logName, String log) {
        Solver solverCharged = new Solver();
        try {
            List<String> solverInfoList = Arrays.asList(logName.split("-"));
            Collections.reverse(solverInfoList);
            solverCharged.setNumberOfCores(Integer.parseInt((solverInfoList.get(0)).substring(0, (solverInfoList.get(0)).lastIndexOf("."))));
            solverCharged.setMemory(Integer.parseInt(solverInfoList.get(1)));
            solverCharged.setTimeOut(Integer.parseInt(solverInfoList.get(2)));
            solverCharged.setType(solverInfoList.get(3));
            solverCharged.setBenchmark(solverInfoList.get(4));
            solverCharged.setName(solverInfoList.get(solverInfoList.size() - 1));
        } catch (Exception e) {
            System.err.println("[ERROR-INFO] Solver has incomplete or incorrect name.");
            return null;
        }
        try {
            StringTokenizer st = new StringTokenizer(log, "\t");
            SolverInstance instance = null;
            boolean firstElement = true;
            while (st.hasMoreTokens()) {
                if (firstElement) {
                    instance = new SolverInstance();
                    instance.setFileName(st.nextToken());
                    firstElement = false;
                }
                instance.setTime(Double.parseDouble(st.nextToken()));
                instance.setSolution(st.nextToken());
                instance.setOptimum(Integer.parseInt(st.nextToken()));
                instance.setInfo(Integer.parseInt(st.nextToken()));
                instance.setTimeOut(Integer.parseInt(st.nextToken()));
                instance.setBuggy(Integer.parseInt(st.nextToken()));
                instance.setSegmentationFault(Integer.parseInt(st.nextToken()));
                instance.setOutOfMemory(Integer.parseInt(st.nextToken()));
                instance.setLog(st.nextToken());
                instance.setNumberOfVariables(Integer.parseInt(st.nextToken()));
                instance.setNumberOfClause(Integer.parseInt(st.nextToken()));
                instance.setNumberOfHardClause(Integer.parseInt(st.nextToken()));
                instance.setNumberOfSoftClause(Integer.parseInt(st.nextToken()));
                instance.setNumberOfUnsatClause(Integer.parseInt(st.nextToken()));

                String unSatClauseWeigthString = st.nextToken();
                int limit = unSatClauseWeigthString.indexOf("/", 0);
                if (limit != -1) {
                    instance.setUnsatClauseWeigth(Integer.parseInt(unSatClauseWeigthString.substring(0, limit)));
                } else {
                    instance.setUnsatClauseWeigth(Integer.parseInt(unSatClauseWeigthString));
                }

                solverCharged.addInstanceToList(instance);

                if (st.hasMoreTokens()) {
                    instance = new SolverInstance();
                    instance.setFileName(unSatClauseWeigthString.substring(limit));
                }
            }
        } catch (Exception e) {
            System.err.println("[WARNING-INFO] There is incomplete or incorrect instances in the log.");
        } finally {
            return solverCharged;
        }
    }

    public static Map<String, CompareSolver> getComparisonData(List<Solver> solvers) {

        loadInstancesLists(solvers); // Charge all instances into solvers from DAO
        Map<String, CompareSolver> map = new LinkedHashMap<>();

        for (Solver solv : solvers) {
            int instancesCount = 0;
            List<Double> timeValues = new ArrayList<Double>();
            String folder = getFolder(solv.getInstancesList().get(0));
            for (SolverInstance instance : solv.getInstancesList()) {
                if (folder.equals(getFolder(instance))) { // While we don't change the folder increment counters
                    instancesCount++; // Increment instances count
                    if (isSolved(instance)) { // If the instance is solved save the time and increment de solved count
                        timeValues.add(instance.getTime());
                    }
                } else { // If not save the data and reset the counters
                    // Save Data
                    ArrayList<Double> info = new ArrayList<>(); // Object 0 is the number of solved instances, 1 the mean , 2 the median
                    info.add((double) timeValues.size());
                    info.add(mean(timeValues));
                    info.add(median(timeValues));

                    CompareSolver solverToAdd;
                    if (map.containsKey(folder)) {
                        solverToAdd = map.get(folder);
                    } else {
                        solverToAdd = new CompareSolver(folder);
                    }

                    solverToAdd.addToHashMap(solv.getName(), info); // Add to CompareSolver object hashmap the data with the solver name as key
                    solverToAdd.setNumberOfInstances(instancesCount);

                    map.put(folder, solverToAdd); // Add to the map to return this object with the folder as key

                    // Reset Data and check the first instance of the new solver
                    folder = getFolder(instance);
                    instancesCount = 1;
                    timeValues = new ArrayList<>();
                    if (isSolved(instance)) { // If the instance is solved save the time and increment de solved count
                        timeValues.add(instance.getTime());
                    }
                }
            }
            // Save Last Folder Data
            ArrayList<Double> info = new ArrayList<>();
            info.add((double) timeValues.size());
            info.add(mean(timeValues));
            info.add(median(timeValues));
            CompareSolver solverToAdd;
            if (map.containsKey(folder)) {
                solverToAdd = map.get(folder);
            } else {
                solverToAdd = new CompareSolver(folder);
            }

            solverToAdd.addToHashMap(solv.getName(), info);
            solverToAdd.setNumberOfInstances(instancesCount);

            map.put(folder, solverToAdd);
        }
        return map;
    }

    /**
     * Load all the instances into Solvers
     *
     * @param solvers Solvers to load
     */
    private static void loadInstancesLists(List<Solver> solvers) {
        for (Solver solv : solvers) {
            solv.setInstancesList(SolverManager.daoSolver.getAllInstancesBySolver(solv.getId()));
        }
    }

    /**
     * Return the folder of an instance
     *
     * @param instance Instance to know the folder
     * @return folder
     */
    private static String getFolder(SolverInstance instance) {
        StringTokenizer st;
        st = new StringTokenizer(instance.getFileName(), "/");
        for (int i = 1; i <= st.countTokens(); i++) {
            st.nextToken();
        }
        return st.nextToken() + "/" + st.nextToken();
    }

    /**
     * Return the node of a grid pane giving the position
     *
     * @param gridPane GridPane to extract the node
     * @param col Column of the node
     * @param row Row of the node
     * @return Node to return
     */
    public static Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    /**
     * Return if an instance is Solved or not
     *
     * @param instance instance to check
     * @return true if its solved , false if not
     */
    private static boolean isSolved(SolverInstance instance) {
        if (instance.getTimeOut() != 0 && instance.getBuggy() != 0 && instance.getSegmentationFault() != 0 && instance.getOutOfMemory() != 0) {
            return false;
        }
        try {
            if (Integer.parseInt(instance.getSolution()) == -1) {
                return false;
            }
        } catch (NumberFormatException e) {
        }
        if (instance.getSolution().equals("UNKNOW")) {
            return false;
        }
        if (!(instance.getOptimum() == -1 || instance.getSolution().equals("OPTIMUM_FOUND") || instance.getSolution().equals("UNSAT") || instance.getSolution().equals("INCOMPLETE"))) {
            return false;
        }
        if (instance.getOptimum() == -1) {
            if (!instance.getSolution().equals("UNSAT")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return the mean
     *
     * @param values List of all the values
     * @return the mean
     */
    private static Double mean(List<Double> values) {
        if (values.isEmpty()) {
            return 0.0;
        } else {
            double total = 0;
            for (double d : values) {
                total += d;
            }
            return total / values.size();
        }
    }

    /**
     * Return the median
     *
     * @param values List of all the values
     * @return the median
     */
    private static Double median(List<Double> values) {
        if (values.isEmpty()) {
            return 0.0;
        } else {
            Collections.sort(values);
            int middle = values.size() / 2;
            if (values.size() % 2 == 1) {
                return values.get(middle);
            } else {
                return (values.get(middle - 1) + values.get(middle)) / 2.0;
            }
        }
    }

    /**
     * Export as Plot
     */
    public static void exportAsPlot() {
        List<Solver> sortedSolversByInstancesTime = getSolversSortedByInstancesTime(SolverManager.solversToCompare);
        String text = "#Inst\t";
        for (Solver solv : sortedSolversByInstancesTime) {
            text += solv.getName() + "\t";
        }
        text += "\n";
        int i = 0;
        List<List<SolverInstance>> listOfSolverInstancesList = new ArrayList<>();
        for (Solver solv : sortedSolversByInstancesTime) {
            List<SolverInstance> instances = solv.getInstancesList();
            instances.sort(new InstanceComparator());
            listOfSolverInstancesList.add(instances);
        }
        boolean next;
        do {
            next = false;
            boolean begginin = true;
            for (List<SolverInstance> list : listOfSolverInstancesList) {
                if (i < list.size()) {
                    if (begginin) {
                        text += (i + 1) + "\t";
                        begginin = false;
                    }
                    if (isSolved(list.get(i))) {
                        text += list.get(i).getTime() + "\t";
                    } else {
                        text += "-" + "\t";
                    }
                    next = true;
                }
            }
            text += "\n";
            i++;
        } while (next);
        fileWriter("complete-" + SolverManager.solversToCompare.get(0).getBenchmark() + "-table-graph.txt", text);
    }

    public static class InstanceComparator implements Comparator<SolverInstance> {

        @Override
        public int compare(SolverInstance i1, SolverInstance i2) {
            if (i1.getTime() <= i2.getTime()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    private static List<Solver> getSolversSortedByInstancesTime(List<Solver> unSortedList) {
        Map<Solver, ArrayList<Double>> sortedSolvers = new LinkedHashMap<>();
        for (Solver solv : unSortedList) {
            int totalInstances = 0;
            double totalTime = 0;
            for (SolverInstance instance : solv.getInstancesList()) {
                if (isSolved(instance)) {
                    totalInstances++;
                    totalTime += instance.getTime();
                }
            }
            ArrayList<Double> data = new ArrayList<>();
            data.add((double) totalInstances);
            data.add(totalTime);
            sortedSolvers.put(solv, data);
            totalTime = 0;
        }
        List list = new LinkedList(sortedSolvers.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Solver, ArrayList<Double>>>() {
            @Override
            public int compare(Map.Entry<Solver, ArrayList<Double>> o1, Map.Entry<Solver, ArrayList<Double>> o2) {
                if (o1.getValue().get(0) > o2.getValue().get(0)) {
                    return -1;
                } else if (o1.getValue().get(0).equals(o2.getValue().get(0))) {
                    if (o1.getValue().get(1) >= o2.getValue().get(1)) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return 1;
                }
            }
        });
        Map result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return new ArrayList<Solver>(result.keySet());
    }

    /**
     * Export as HTML
     *
     * @param table table to export
     * @param columns number of table columns
     * @param rows number of table rows
     */
    public static void exportAsHTML(GridPane table, int columns, int rows) {
        String html = "<html><body><table><tr>";
        for (int i = 0; i < columns; i++) {
            String aux = getNodeFromGridPane(table, i, 0).toString();
            html += "<th>" + aux.substring(aux.indexOf("'") + 1, aux.lastIndexOf("'")) + "</th>";
        }
        html += "</tr>";
        for (int x = 1; x < rows; x++) {
            html += "<tr>";
            for (int i = 0; i < columns; i++) {
                String aux = getNodeFromGridPane(table, i, x).toString();
                html += "<td>";
                String value = aux.substring(aux.indexOf("'") + 1, aux.lastIndexOf("'"));
                value = value.replace("/", "\\/");
                value = value.replace("_", "\\_");
                if (value.contains("*")) {
                    html += "<font color='#1EAA46'>" + aux.substring(aux.indexOf("'") + 1, aux.lastIndexOf("'"));
                } else {
                    html += aux.substring(aux.indexOf("'") + 1, aux.lastIndexOf("'"));
                }
                html += "</td>";
            }
            html += "</tr>";
        }
        html += "</table></body></html>";
        fileWriter("table.html", html);
    }

    /**
     * Export as Latex
     *
     * @param table table to export
     * @param columns number of table columns
     * @param rows number of table rows
     */
    public static void exportAsLatex(GridPane table, int columns, int rows) {
        String latex = "\\documentclass{article}\n"
                + "\\begin{document}\n"
                + "\n"
                + "\\begin{table}[ht]\n"
                + "\\begin{center}\n"
                + "\\begin{tabular}{|l|r||r|r|r|r|}\n"
                + "\\hline\n";

        for (int i = 0; i < columns; i++) {
            String aux = getNodeFromGridPane(table, i, 0).toString();
            latex += aux.substring(aux.indexOf("'") + 1, aux.lastIndexOf("'"));
            if (i < columns - 1) {
                latex += " & ";
            }
        }
        latex += "\\\\ \\hline \n";

        for (int x = 1; x < rows; x++) {
            for (int i = 0; i < columns; i++) {
                String aux = getNodeFromGridPane(table, i, x).toString();
                if (aux.substring(aux.indexOf("'") + 1, aux.lastIndexOf("'")).contains("*")) {
                    latex += "\\textbf{" + aux.substring(aux.indexOf("'") + 1, aux.lastIndexOf("'")) + "}";
                } else {
                    latex += aux.substring(aux.indexOf("'") + 1, aux.lastIndexOf("'"));
                }
                latex += " & ";
            }
            latex += "\\\\ \\hline \n";
        }
        latex += "\\end{tabular}\n"
                + "\\end{center}\n"
                + "\\caption{Time in seconds.}\n"
                + "\\label{tag}\n"
                + "\\end{table}\n";
        latex += "\\end{document}\n";
        fileWriter("table.tex", latex);
    }
}
