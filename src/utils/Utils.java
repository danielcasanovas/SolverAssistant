/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Utils
 */
package utils;

import database.DAOSolver;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import solverassistant.SolverManager;

public class Utils {

    public Utils() {
    }

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

    public void fileWriter(String path, String content) {
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
                if (st.nextToken().equals("OPTIMUM_FOUND")) {
                    instance.setOptimum(true);
                } else {
                    instance.setOptimum(false);
                }
                instance.setSolution(Integer.parseInt(st.nextToken()));
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
                instance.setUnsatClauseWeigth(Integer.parseInt(unSatClauseWeigthString.substring(0, limit)));

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
        Map<String, CompareSolver> map = new HashMap<>();

        for (Solver solv : solvers) {
            int instancesCount = 0;
            double time = 0;
            int solvedCount = 0;
            String folder = getFolder(solv.getInstancesList().get(0));
            for (SolverInstance instance : solv.getInstancesList()) {
                if (folder.equals(getFolder(instance))) {
                    instancesCount++;
                    if (instance.getTimeOut() == 0) {
                        time += instance.getTime();
                        solvedCount++;
                    }
                } else {
                    // Save Data
                    ArrayList<Double> info = new ArrayList<>();
                    info.add(time);
                    info.add((double) solvedCount);
                    System.out.println("+-+: " + solv.getName());
                    CompareSolver solverToAdd;
                    if (map.containsKey(folder)) {
                        solverToAdd = map.get(folder);
                    } else {
                        solverToAdd = new CompareSolver(folder);
                    }

                    solverToAdd.addToHashMap(solv.getName(), info);
                    solverToAdd.setNumberOfInstances(instancesCount);
                    map.put(folder, solverToAdd);

                    // Reset Data
                    folder = getFolder(instance);
                    instancesCount = 1;
                    if (instance.getTimeOut() == 0) {
                        time = instance.getTime();
                        solvedCount = 1;
                    } else {
                        time = 0;
                        solvedCount = 0;
                    }
                }
            }
//            System.out.println("Check-----" + map.size());
//            CompareSolver aux = map.get("bipartite/maxcut-140-630-0.7");
//            System.out.println("Check------");
//            System.out.println(aux.getFolder());
//            for (double d : aux.getMultiMap().get("SAT4J")) {
//                System.out.println(d + " |");
//            }
        }
        System.out.println("Total folders------" + map.size());
        CompareSolver aux = map.get("bipartite/maxcut-140-630-0.7");
        ArrayList<Double> get = aux.getMultiMap().get("Test");
        System.out.println("Values: " + get.toString());
        return map;
    }

    private static void loadInstancesLists(List<Solver> solvers) {
        for (Solver solv : solvers) {
            solv.setInstancesList(SolverManager.daoSolver.getAllInstancesBySolver(solv.getId()));
        }
    }

    private static String getFolder(SolverInstance instance) {
        StringTokenizer st;
        st = new StringTokenizer(instance.getFileName(), "/");
        for (int i = 1; i <= st.countTokens(); i++) {
            st.nextToken();
        }
        return st.nextToken() + "/" + st.nextToken();
    }
}
