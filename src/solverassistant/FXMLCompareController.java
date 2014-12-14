/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Controller for the Compare View
 */
package solverassistant;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(40, 0, 0, 40));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        utils.Utils.getComparisonData(SolverManager.solversToCompare);
//        int columnCounter = 0;
//        Label lbSolver = new Label("Solver");
//        GridPane.setHalignment(lbSolver, HPos.CENTER);
//        columnCounter++;
//        Label lbInstances = new Label("Instances");
//        GridPane.setHalignment(lbInstances, HPos.CENTER);
//        columnCounter++;
//
//        gridPane.add(lbSolver, 0, 0);
//        gridPane.add(lbInstances, 1, 0);
//
//        for (Solver solv : SolverManager.solversToCompare) {
//            Label lbBenchmark = new Label(solv.getBenchmark());
//            GridPane.setHalignment(lbBenchmark, HPos.CENTER);
//            gridPane.add(lbBenchmark, columnCounter++, 0);
//        }

        comparePane.getChildren().add(gridPane);
//        TableColumn col = new TableColumn("Solver");
//        col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
//            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
//                return new SimpleStringProperty(param.getValue().toString());
//            }
//        });
//        comparisonTable.getColumns().addAll(col);
//
//        col = new TableColumn("Instances");
//        col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
//            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
//                return new SimpleStringProperty(param.getValue().toString());
//            }
//        });
//        comparisonTable.getColumns().addAll(col);
//
//        for (Solver solv : SolverManager.solversToCompare) {
//            col = new TableColumn(solv.getBenchmark());
//            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
//                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
//                    return new SimpleStringProperty(param.getValue().toString());
//                }
//            });
//            comparisonTable.getColumns().addAll(col);
//        }
//
////        ObservableList<String> row = FXCollections.observableArrayList();
////        for (Solver solv : SolverManager.solversToCompare) {
////            row.add(solv.getBenchmark());
////        }
////        data.add(row);
////        comparisonTable.setItems(data);
    }
}
