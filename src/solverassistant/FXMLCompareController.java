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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class FXMLCompareController implements Initializable {

    @FXML
    private TableView comparisonTable;

    @FXML
    private AnchorPane comparePane;

    private ObservableList<ObservableList> data;
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
    }

    public void generateTable(boolean option) {
        Map<String, CompareSolver> results = utils.Utils.getComparisonData(SolverManager.solversToCompare);
        Map<String, Integer> totalSolvedInTime = new HashMap<>();
        int totalInstances = 0;
        int row = 0;
        int column = 0;

        if (comparePane.getChildren().contains(gridPane)) {
            comparePane.getChildren().remove(gridPane);
        }
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(40, 0, 0, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // HEADERS
        Label solverLabel = new Label("Solver");
        GridPane.setHalignment(solverLabel, HPos.CENTER);
        gridPane.add(solverLabel, column, 0);
        column++;

        Label instancesLabel = new Label("Instances");
        GridPane.setHalignment(instancesLabel, HPos.CENTER);
        gridPane.add(instancesLabel, column, 0);
        column++;

        CompareSolver solverComparedHeader = results.get(results.keySet().iterator().next());
        for (String solverValue : solverComparedHeader.getMultiMap().keySet()) {
            Label solverNameLabel = new Label(solverValue);
            GridPane.setHalignment(solverNameLabel, HPos.CENTER);
            gridPane.add(solverNameLabel, column, 0);
            column++;
        }
        column = 0;
        row++;

        // DATA
        for (String folderValue : results.keySet()) {

            Label folderLabel = new Label(folderValue);
            GridPane.setHalignment(folderLabel, HPos.LEFT);
            gridPane.add(folderLabel, column, row);
            column++;

            CompareSolver solverCompared = results.get(folderValue);
            totalInstances += solverCompared.getNumberOfInstances();

            Label instancesLabelCount = instancesLabelCount = new Label(String.valueOf(solverCompared.getNumberOfInstances()));
            GridPane.setHalignment(instancesLabelCount, HPos.CENTER);
            gridPane.add(instancesLabelCount, column, row);
            column++;

            for (String solverValue : solverCompared.getMultiMap().keySet()) {
                ArrayList<Double> data = solverCompared.getMultiMap().get(solverValue);
                String result = "";
                if (option) {
                    result += isNaN(data.get(0) / data.get(1)) ? "0" : String.format("%.2f", (data.get(0) / data.get(1)));
                } else;
                result += " (" + data.get(1).intValue() + ")";
                if (totalSolvedInTime.containsKey(solverValue)) {
                    totalSolvedInTime.put(solverValue, (totalSolvedInTime.get(solverValue) + data.get(1).intValue()));
                } else {
                    totalSolvedInTime.put(solverValue, data.get(1).intValue());
                }

                Label resultLabel = new Label(result);
                GridPane.setHalignment(resultLabel, HPos.CENTER);
                gridPane.add(resultLabel, column, row);
                column++;
            }
            column = 0;
            row++;
        }
        // TOTALS

        Label totalLabel = new Label("Total");
        GridPane.setHalignment(totalLabel, HPos.CENTER);
        gridPane.add(totalLabel, column, row);
        column++;

        Label totalCountInstances = new Label(String.valueOf(totalInstances));
        GridPane.setHalignment(totalCountInstances, HPos.CENTER);
        gridPane.add(totalCountInstances, column, row);
        column++;

        for (String solver : totalSolvedInTime.keySet()) {
            Label totalSolvedInTimeLabel = new Label(String.valueOf(totalSolvedInTime.get(solver)));
            GridPane.setHalignment(totalSolvedInTimeLabel, HPos.CENTER);
            gridPane.add(totalSolvedInTimeLabel, column, row);
            column++;
        }
        row++;
        comparePane.getChildren().add(gridPane);
    }
}
