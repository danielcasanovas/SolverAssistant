/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Controller for the Edit Log View
 */
package solverassistant;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.table.TableColumn;

public class FXMLEditSolverController implements Initializable {

    @FXML
    private TableView<SolverInstance> instancesTable;

    @FXML
    private TableColumn instancesTableColumn;

    @FXML
    private Label solverLabel, benchmarkLabel, solverTypeLabel, timeOutLabel, moreSolversLabel, memoryLabel, coresLabel;

    @FXML
    private TextField solverTextField, benchmarkTextField, solverTypeTextField, timeOutTextField, memoryTextField, coresTextField;

    @FXML
    private Button sendToDBButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.chargeI18nValues();
    }

    /**
     * Set the properly i18n data to all the components in the view
     */
    public void chargeI18nValues() {
        solverLabel.setText(SolverAssistant.messages.getString("Solver"));
        benchmarkLabel.setText(SolverAssistant.messages.getString("Benchmark"));
        solverTypeLabel.setText(SolverAssistant.messages.getString("SolverType"));
        timeOutLabel.setText(SolverAssistant.messages.getString("TimeOut"));
        memoryLabel.setText(SolverAssistant.messages.getString("Memory"));
        coresLabel.setText(SolverAssistant.messages.getString("NumberOfCores"));
        moreSolversLabel.setText(SolverAssistant.messages.getString("MoreSolvers") + " \"http://maxsat.ia.udl.cat/results\"");
        sendToDBButton.setText(SolverAssistant.messages.getString("SendToDB"));
    }

    public void loadSolver() {
        solverTextField.setText(SolverManager.solverCharged.getName());
        benchmarkTextField.setText(String.valueOf(SolverManager.solverCharged.getBenchmark()));
        solverTypeTextField.setText(SolverManager.solverCharged.getType());
        timeOutTextField.setText(String.valueOf(SolverManager.solverCharged.getTimeOut()));
        memoryTextField.setText(String.valueOf(SolverManager.solverCharged.getMemory()));
        coresTextField.setText(String.valueOf(SolverManager.solverCharged.getNumberOfCores()));
        this.bindDataToTable();
    }

    private void bindDataToTable() {

        final ObservableList<SolverInstance> data = FXCollections.observableArrayList(SolverManager.solverCharged.getInstancesList());

        for (SolverInstance s : SolverManager.solverCharged.getInstancesList()) {
            System.out.println("File Name:" + s.getFileName());
        }

//        final TableView<SolverInstance> instancesTabel = new TableView<>(data);
//        TableColumn<SolverInstance,String> firstNameCol = new TableColumn<SolverInstance,String>("First Name");
//
//        instancesTable.setEditable(true);
//        instancesTable.setItems(data);
//
//        System.out.println(">>>>>" + instancesTable.getItems().size());
    }
}
