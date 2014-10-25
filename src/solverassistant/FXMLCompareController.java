/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solverassistant;

import entities.Solver;
import entities.SolverProperties;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLCompareController implements Initializable {

    @FXML
    private TableView<SolverProperties> allSolversTable, selectedSolversTable;

    @FXML
    private TableColumn<SolverProperties, Integer> colAllSelect;

    @FXML
    private TableColumn<SolverProperties, String> colAllSolver;

    @FXML
    private TableColumn<SolverProperties, String> colAllBenchmark;

    @FXML
    private TableColumn<SolverProperties, String> colAllSolverType;

    @FXML
    private TableColumn<SolverProperties, Integer> colAllTimeOut;

    @FXML
    private TableColumn<SolverProperties, Integer> colAllMemory;

    @FXML
    private TableColumn<SolverProperties, Integer> colAllNumberOfCores;

    @FXML
    private TableColumn<SolverProperties, String> colSelectedSolver;

    @FXML
    private TableColumn<SolverProperties, String> colSelectedBenchmark;

    @FXML
    private TableColumn<SolverProperties, String> colSelectedSolverType;

    @FXML
    private TableColumn<SolverProperties, Integer> colSelectedTimeOut;

    @FXML
    private TableColumn<SolverProperties, Integer> colSelectedMemory;

    @FXML
    private TableColumn<SolverProperties, Integer> colSelectedNumberOfCores;

    @FXML
    private Label solverLabel, benchmarkLabel, solverTypeLabel, timeOutLabel, memoryLabel, coresLabel;

    @FXML
    private Button compareButton;

    public List<Solver> solvers;

    public List<Solver> solversToPrint;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        solvers = SolverManager.daoSolver.getAllSolvers();
        solversToPrint = solvers;
        configTableViewPageUI();
        bindDataToTable();
    }

    public void chargeI18nValues() {
        colAllSolver.setText(SolverAssistant.messages.getString("Solver"));
        colAllBenchmark.setText(SolverAssistant.messages.getString("Benchmark"));
        colAllSolverType.setText(SolverAssistant.messages.getString("SolverType"));
        colAllTimeOut.setText(SolverAssistant.messages.getString("TimeOut"));
        colAllMemory.setText(SolverAssistant.messages.getString("Memory"));
        colAllNumberOfCores.setText(SolverAssistant.messages.getString("NumberOfCores"));

        colSelectedSolver.setText(SolverAssistant.messages.getString("Solver"));
        colSelectedBenchmark.setText(SolverAssistant.messages.getString("Benchmark"));
        colSelectedSolverType.setText(SolverAssistant.messages.getString("SolverType"));
        colSelectedTimeOut.setText(SolverAssistant.messages.getString("TimeOut"));
        colSelectedMemory.setText(SolverAssistant.messages.getString("Memory"));
        colSelectedNumberOfCores.setText(SolverAssistant.messages.getString("NumberOfCores"));

        solverLabel.setText(SolverAssistant.messages.getString("Solver"));
        benchmarkLabel.setText(SolverAssistant.messages.getString("Benchmark"));
        solverTypeLabel.setText(SolverAssistant.messages.getString("SolverType"));
        timeOutLabel.setText(SolverAssistant.messages.getString("TimeOut"));
        memoryLabel.setText(SolverAssistant.messages.getString("Memory"));
        coresLabel.setText(SolverAssistant.messages.getString("NumberOfCores"));

        compareButton.setText(SolverAssistant.messages.getString("Compare"));
    }

    private void bindDataToTable() {
        List<SolverProperties> solversPropierties = new ArrayList<>();
        for (Solver s : solversToPrint) {
            solversPropierties.add(new SolverProperties(s));
        }
        ObservableList<SolverProperties> data = FXCollections.observableArrayList(solversPropierties);
        allSolversTable.getItems().addAll(data);
    }

    private void configTableViewPageUI() {
        colAllSolver.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAllBenchmark.setCellValueFactory(new PropertyValueFactory<>("benchmark"));
        colAllSolverType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colAllTimeOut.setCellValueFactory(new PropertyValueFactory<>("timeOut"));
        colAllMemory.setCellValueFactory(new PropertyValueFactory<>("memory"));
        colAllNumberOfCores.setCellValueFactory(new PropertyValueFactory<>("numberOfCores"));
    }
}
