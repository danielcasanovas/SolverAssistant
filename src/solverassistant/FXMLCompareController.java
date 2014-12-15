/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Controller for the Compare View
 */
package solverassistant;

import entities.CompareSolver;
import entities.Solver;
import java.net.URL;
import java.util.ArrayList;
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

    public void generateTable() {

        Map<String, CompareSolver> results = utils.Utils.getComparisonData(SolverManager.solversToCompare);
        int row = 0;
        int column = 0;

        GridPane gridPane = new GridPane();
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

        for (Solver solv : SolverManager.solversToCompare) {
            Label solverNameLabel = new Label(solv.getName());
            GridPane.setHalignment(solverNameLabel, HPos.CENTER);
            gridPane.add(solverNameLabel, column, 0);
            column++;
        }
        column = 0;
        row++;

        System.out.println("CHECK" + results.keySet());
        // DATA
        for (String folderValue : results.keySet()) {

            Label folderLabel = new Label(folderValue);
            GridPane.setHalignment(folderLabel, HPos.LEFT);
            gridPane.add(folderLabel, column, row);
            column++;

            CompareSolver solverCompared = results.get(folderValue);

            Label instancesLabelCount = instancesLabelCount = new Label(String.valueOf(solverCompared.getNumberOfInstances()));
            GridPane.setHalignment(instancesLabelCount, HPos.CENTER);
            gridPane.add(instancesLabelCount, column, row);
            column++;

            for (String solverValue : solverCompared.getMultiMap().keySet()) {
                ArrayList<Double> data = solverCompared.getMultiMap().get(solverValue);
                String result = data.get(0) + " (" + data.get(1).intValue() + ")";

                Label resultLabel = new Label(result);
                GridPane.setHalignment(resultLabel, HPos.CENTER);
                gridPane.add(resultLabel, column, row);
                column++;
            }
            column = 0;
            row++;
        }

        comparePane.getChildren().add(gridPane);
    }
}
