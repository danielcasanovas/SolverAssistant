/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Controller for the Edit Log View
 */
package solverassistant;

import entities.SolverInstance;
import entities.SolverInstanceProperties;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLEditSolverController implements Initializable {

    @FXML
    private TableView<SolverInstanceProperties> instancesTable;

    @FXML
    private TableColumn<SolverInstance, String> colInstance;

    @FXML
    private TableColumn<SolverInstance, Double> colTime;

    @FXML
    private TableColumn<SolverInstance, Boolean> colOptimal;

    @FXML
    private TableColumn<SolverInstance, Integer> colSolution;

    @FXML
    private TableColumn<SolverInstance, Integer> colInfo;

    @FXML
    private TableColumn<SolverInstance, Integer> colTimeOut;

    @FXML
    private TableColumn<SolverInstance, Integer> colBuggy;

    @FXML
    private TableColumn<SolverInstance, Integer> colSegmentation;

    @FXML
    private TableColumn<SolverInstance, Integer> colOutOfMemory;

    @FXML
    private TableColumn<SolverInstance, String> colLog;

    @FXML
    private TableColumn<SolverInstance, Integer> colVariables;

    @FXML
    private TableColumn<SolverInstance, Integer> colClauses;

    @FXML
    private TableColumn<SolverInstance, Integer> colHardClauses;

    @FXML
    private TableColumn<SolverInstance, Integer> colSoftClauses;

    @FXML
    private TableColumn<SolverInstance, Integer> colUnsatClauses;

    @FXML
    private TableColumn<SolverInstance, Integer> colWeigthUnsatClauses;

    @FXML
    private Label solverLabel, benchmarkLabel, solverTypeLabel, timeOutLabel, moreSolversLabel, memoryLabel, coresLabel;

    @FXML
    private TextField solverTextField, benchmarkTextField, timeOutTextField, memoryTextField, coresTextField;

    @FXML
    private ComboBox comboSolverType;

    @FXML
    private Button sendToDBButton;

    private List<SolverInstanceProperties> solvPropList;
    private ObservableList<SolverInstanceProperties> data;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        solvPropList = new ArrayList<>();
        this.configTableViewPageUI();
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

        colInstance.setText(SolverAssistant.messages.getString("Instance"));
        colTime.setText(SolverAssistant.messages.getString("Time"));
        colOptimal.setText(SolverAssistant.messages.getString("Optimal"));
        colSolution.setText(SolverAssistant.messages.getString("Solution"));
        colInfo.setText(SolverAssistant.messages.getString("Info"));
        colTimeOut.setText(SolverAssistant.messages.getString("TimeOut"));
        colBuggy.setText(SolverAssistant.messages.getString("Buggy"));
        colSegmentation.setText(SolverAssistant.messages.getString("SegmentationFault"));
        colOutOfMemory.setText(SolverAssistant.messages.getString("OutOfMemory"));
        colLog.setText(SolverAssistant.messages.getString("Log"));
        colVariables.setText(SolverAssistant.messages.getString("Variables"));
        colClauses.setText(SolverAssistant.messages.getString("Clauses"));
        colHardClauses.setText(SolverAssistant.messages.getString("HardClauses"));
        colSoftClauses.setText(SolverAssistant.messages.getString("SoftClauses"));
        colUnsatClauses.setText(SolverAssistant.messages.getString("UnSatClauses"));
        colWeigthUnsatClauses.setText(SolverAssistant.messages.getString("WeigthUnsatClauses"));
        instancesTable.setPlaceholder(new Label(SolverAssistant.messages.getString("EmptyTable")));
//        instancesTable.requestFocus();
    }

    public void loadSolver() {
        solverTextField.setText(SolverManager.solverCharged.getName());
        benchmarkTextField.setText(String.valueOf(SolverManager.solverCharged.getBenchmark()));
        if (SolverManager.solverCharged.getType().equals("COMPLETE")) {
            comboSolverType.setValue("COMPLETE");
        } else {
            comboSolverType.setValue("UNCOMPLETE");
        }
        timeOutTextField.setText(String.valueOf(SolverManager.solverCharged.getTimeOut()));
        memoryTextField.setText(String.valueOf(SolverManager.solverCharged.getMemory()));
        coresTextField.setText(String.valueOf(SolverManager.solverCharged.getNumberOfCores()));
        this.bindDataToTable();
    }

    private void bindDataToTable() {
        for (SolverInstance s : SolverManager.solverCharged.getInstancesList()) {
            solvPropList.add(new SolverInstanceProperties(s));
        }
        data = FXCollections.observableArrayList(solvPropList);
        instancesTable.getItems().addAll(data);
    }

    private void configTableViewPageUI() {
        comboSolverType.getItems().addAll(
                "COMPLETE",
                "UNCOMPLETE"
        );
        timeOutTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int value = Integer.parseInt(newValue);
                timeOutTextField.setText(String.valueOf(value));
            } catch (Exception e) {
                timeOutTextField.setText(oldValue);
            }
        });
        memoryTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int value = Integer.parseInt(newValue);
                memoryTextField.setText(String.valueOf(value));
            } catch (Exception e) {
                memoryTextField.setText(oldValue);
            }
        });
        coresTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int value = Integer.parseInt(newValue);
                coresTextField.setText(String.valueOf(value));
            } catch (Exception e) {
                coresTextField.setText(oldValue);
            }
        });

        colInstance.setCellValueFactory(new PropertyValueFactory<>("fileName"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colOptimal.setCellValueFactory(new PropertyValueFactory<>("optimum"));
        colSolution.setCellValueFactory(new PropertyValueFactory<>("solution"));
        colInfo.setCellValueFactory(new PropertyValueFactory<>("info"));
        colTimeOut.setCellValueFactory(new PropertyValueFactory<>("timeOut"));
        colBuggy.setCellValueFactory(new PropertyValueFactory<>("buggy"));
        colSegmentation.setCellValueFactory(new PropertyValueFactory<>("segmentationFault"));
        colOutOfMemory.setCellValueFactory(new PropertyValueFactory<>("outOfMemory"));
        colLog.setCellValueFactory(new PropertyValueFactory<>("log"));
        colVariables.setCellValueFactory(new PropertyValueFactory<>("numberOfVariables"));
        colClauses.setCellValueFactory(new PropertyValueFactory<>("numberOfClause"));
        colHardClauses.setCellValueFactory(new PropertyValueFactory<>("numberOfHardClause"));
        colSoftClauses.setCellValueFactory(new PropertyValueFactory<>("numberOfSoftClause"));
        colUnsatClauses.setCellValueFactory(new PropertyValueFactory<>("numberOfUnsatClause"));
        colWeigthUnsatClauses.setCellValueFactory(new PropertyValueFactory<>("unsatClauseWeigth"));
        instancesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void saveSolverData() {
        SolverManager.solverCharged.setName(solverTextField.getText());
        SolverManager.solverCharged.setBenchmark(benchmarkTextField.getText());
        SolverManager.solverCharged.setType(comboSolverType.getValue().toString());
        SolverManager.solverCharged.setTimeOut(Integer.parseInt(timeOutTextField.getText()));
        SolverManager.solverCharged.setMemory(Integer.parseInt(memoryTextField.getText()));
        SolverManager.solverCharged.setNumberOfCores(Integer.parseInt(coresTextField.getText()));
    }

    // -------- Actions
    @FXML
    private void saveSolverAction(ActionEvent event) {
        this.saveSolverData();
        SolverManager.daoSolver.addSolver(SolverManager.solverCharged);
    }
}
