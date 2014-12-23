/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Controller for the Compare View
 */
package solverassistant;

import entities.CompareSolver;
import static java.lang.Double.isNaN;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FXMLCompareController implements Initializable {

    @FXML
    private HBox compareContainer;

    @FXML
    private Label exportLabel;

    private GridPane gridPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.chargeI18nValues();
    }

    //Set the properly i18n data to all the components in the view
    public void chargeI18nValues() {
        exportLabel.setText(SolverAssistant.messages.getString("Export"));
    }

    public void generateTable(boolean option) {
        Map<String, CompareSolver> rawResults = utils.Utils.getComparisonData(SolverManager.solversToCompare);
        Map<String, Integer> totalSolvedInTime = new HashMap<>();
        Map<String, Double> totalSolvedTimes = new HashMap<>();
        int totalInstances = 0;
        int row = 0;
        int column = 0;

        if (compareContainer.getChildren().contains(gridPane)) {
            compareContainer.getChildren().remove(gridPane);
        }
        gridPane = new GridPane();

        // HEADERS
        gridPane.add(new Label("Solver"), column, 0);
        column++;

        gridPane.add(new Label("Instances"), column, 0);
        column++;

        CompareSolver solverComparedHeader = rawResults.get(rawResults.keySet().iterator().next());
        for (String solverValue : solverComparedHeader.getMultiMap().keySet()) {
            gridPane.add(new Label(solverValue), column, 0);
            column++;
        }
        column = 0;
        row++;

        // DATA
        for (String folderValue : rawResults.keySet()) {

            // FOLDER NAMES
            gridPane.add(new Label(folderValue), column, row);
            column++;

            CompareSolver solverCompared = rawResults.get(folderValue);
            totalInstances += solverCompared.getNumberOfInstances();

            // NUMBER OF INSTANCES
            gridPane.add(new Label(String.valueOf(solverCompared.getNumberOfInstances())), column, row);
            column++;

            // VALUES
            for (String solverValue : solverCompared.getMultiMap().keySet()) {
                ArrayList<Double> data = solverCompared.getMultiMap().get(solverValue);
                String result = "";
                if (option) {
                    result += isNaN(data.get(1)) ? "0" : String.format("%.2f", data.get(1));
                } else {
                    result += isNaN(data.get(2)) ? "0" : String.format("%.2f", data.get(2));
                }
                result += " (" + data.get(0).intValue() + ")";
                if (totalSolvedInTime.containsKey(solverValue)) {
                    totalSolvedInTime.put(solverValue, (totalSolvedInTime.get(solverValue) + data.get(0).intValue()));
                } else {
                    totalSolvedInTime.put(solverValue, data.get(0).intValue());
                }
                if (totalSolvedTimes.containsKey(solverValue)) {
                    if (option) {
                        totalSolvedTimes.put(solverValue, (totalSolvedTimes.get(solverValue) + data.get(1)));
                    } else {
                        totalSolvedTimes.put(solverValue, (totalSolvedTimes.get(solverValue) + data.get(2)));
                    }
                } else {
                    if (option) {
                        totalSolvedTimes.put(solverValue, data.get(1));
                    } else {
                        totalSolvedTimes.put(solverValue, data.get(2));
                    }
                }

                gridPane.add(new Label(result), column, row);
                column++;
            }
            column = 0;
            row++;
        }
        // TOTALS

        gridPane.add(new Label("Total"), column, row);
        column++;

        gridPane.add(new Label(String.valueOf(totalInstances)), column, row);
        column++;

        Map<Integer, ArrayList<Double>> totalWithSolverMap = new HashMap<>();
        for (String solver : totalSolvedInTime.keySet()) {
            gridPane.add(new Label(String.valueOf(totalSolvedInTime.get(solver))), column, row);
            ArrayList<Double> data = new ArrayList<>();
            data.add(totalSolvedInTime.get(solver).doubleValue());
            data.add(totalSolvedTimes.get(solver));
            totalWithSolverMap.put(column, data);
            column++;
        }
        Map<Integer, ArrayList<Double>> sortedTotalsMapAsc = sortByComparator(totalWithSolverMap);
        gridPane = sortGridPaneByMap(gridPane, sortedTotalsMapAsc, rawResults);

        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        compareContainer.getChildren().add(gridPane);
    }

    private static Map<Integer, ArrayList<Double>> sortByComparator(Map<Integer, ArrayList<Double>> unsortMap) {

        List<Entry<Integer, ArrayList<Double>>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<Integer, ArrayList<Double>>>() {
            @Override
            public int compare(Entry<Integer, ArrayList<Double>> o1,
                    Entry<Integer, ArrayList<Double>> o2) {
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

        // Maintaining insertion order with the help of LinkedList
        Map<Integer, ArrayList<Double>> sortedMap = new LinkedHashMap<Integer, ArrayList<Double>>();
        for (Entry<Integer, ArrayList<Double>> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    private GridPane sortGridPaneByMap(GridPane gridPane, Map<Integer, ArrayList<Double>> sortedTotalMapAsc, Map<String, CompareSolver> rawResults) {

        GridPane gridPaneSorted = new GridPane();

        int column = 0;
        // HEADERS
        gridPaneSorted.add(new Label("Solver"), 0, 0);
        column++;

        gridPaneSorted.add(new Label("Instances"), 1, 0);
        column++;

        // FOLDERS AND INSTANCES
        int row = 1;
        for (String folderValue : rawResults.keySet()) {
            gridPaneSorted.add(new Label(folderValue), 0, row);
            gridPaneSorted.add(getNodeFromGridPane(gridPane, 1, row), 1, row);
            row++;
        }

        // SOLVERS
        column = 2;
        row = 0;
        for (Integer position : sortedTotalMapAsc.keySet()) {
            gridPaneSorted.add(getNodeFromGridPane(gridPane, position, 0), column, row);
            column++;
        }

        // DATA
        column = 2;
        row = 1;
        for (Integer position : sortedTotalMapAsc.keySet()) {
            for (int i = 1; i <= rawResults.keySet().size(); i++) {
                gridPaneSorted.add(getNodeFromGridPane(gridPane, position, i), column, row);
                row++;
            }
            row = 1;
            column++;
        }

        // TOTALS
        row = rawResults.keySet().size() + 1;

        gridPaneSorted.add(getNodeFromGridPane(gridPane, 0, row), 0, row);
        gridPaneSorted.add(getNodeFromGridPane(gridPane, 1, row), 1, row);

        column = 2;
        for (Integer position : sortedTotalMapAsc.keySet()) {
            gridPaneSorted.add(getNodeFromGridPane(gridPane, position, row), column, row);
            column++;
        }
        return gridPaneSorted;
    }
}
