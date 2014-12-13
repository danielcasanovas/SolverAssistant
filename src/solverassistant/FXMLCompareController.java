/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Controller for the Compare View
 */
package solverassistant;

import entities.Solver;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class FXMLCompareController implements Initializable {

    @FXML
    private TableView comparisonTable;

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

        TableColumn col = new TableColumn("Solver");
        col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().toString());
            }
        });
        comparisonTable.getColumns().addAll(col);

        col = new TableColumn("Instances");
        col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().toString());
            }
        });
        comparisonTable.getColumns().addAll(col);

        for (Solver solv : SolverManager.solversToCompare) {
            col = new TableColumn(solv.getBenchmark());
            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().toString());
                }
            });
            comparisonTable.getColumns().addAll(col);
        }

//        ObservableList<String> row = FXCollections.observableArrayList();
//        for (Solver solv : SolverManager.solversToCompare) {
//            row.add(solv.getBenchmark());
//        }
//        data.add(row);
//        comparisonTable.setItems(data);
    }
}
