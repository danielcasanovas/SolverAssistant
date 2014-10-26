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
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLCompareController implements Initializable {

    @FXML
    private TableView<SolverProperties> allSolversTable, selectedSolversTable;

    @FXML
    private TableColumn<SolverProperties, Boolean> colAllSelect;

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

    @FXML
    private ComboBox solverComboBox, benchmarkComboBox, solverTypeComboBox, timeOutComboBox, memoryComboBox, coresComboBox;

    private List<Solver> solvers; // All solvers without the instances from db
    private List<Solver> solversToPrint; // Solvers selecteds to print 
    private ObservableList<SolverProperties> data; // All solvers without the instances from db to put in first table
    private FilteredList<SolverProperties> filteredData; // Solvers filetered

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadSolversFromDB();
        configTableViewPageUI();
        bindDataToTable();
        fillComboBoxFilters();
    }

    private void loadSolversFromDB() {
        solvers = SolverManager.daoSolver.getAllSolvers();
        List<SolverProperties> solversPropierties = new ArrayList<>();
        for (Solver s : solvers) {
            solversPropierties.add(new SolverProperties(s));
        }
        data = FXCollections.observableArrayList(solversPropierties);
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
        allSolversTable.getItems().addAll(data);
    }

    private void configTableViewPageUI() {
        colAllSolver.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAllBenchmark.setCellValueFactory(new PropertyValueFactory<>("benchmark"));
        colAllSolverType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colAllTimeOut.setCellValueFactory(new PropertyValueFactory<>("timeOut"));
        colAllMemory.setCellValueFactory(new PropertyValueFactory<>("memory"));
        colAllNumberOfCores.setCellValueFactory(new PropertyValueFactory<>("numberOfCores"));
        colAllSelect.setCellFactory((TableColumn<SolverProperties, Boolean> p) -> new CheckBoxTableCell<>());

        filteredData = new FilteredList<>(data, p -> true);
        solverComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                updateFilteredData();
            }
        });
    }

    private void updateFilteredData() {
        filteredData.clear();
        for (SolverProperties solv : data) {
            if (matchesFilter(solv)) {
                filteredData.add(solv);
            }
        }
        fillComboBoxFilters();
    }

    private boolean matchesFilter(SolverProperties solv) {
        String filterString = solverComboBox.getPromptText();
        if (filterString == null || filterString.isEmpty()) { // No filter --> Add all.
            return true;
        }
        if (solv.getName().toLowerCase().indexOf(filterString.toLowerCase()) != -1) {
            return true;
        }
        return false; // Does not match
    }

    private void fillComboBoxFilters() {
        solverComboBox.getItems().clear();
        benchmarkComboBox.getItems().clear();
        solverTypeComboBox.getItems().clear();
        timeOutComboBox.getItems().clear();
        memoryComboBox.getItems().clear();
        coresComboBox.getItems().clear();

        if (!filteredData.isEmpty()) {
            for (SolverProperties solv : filteredData) {
                if (!solverComboBox.getItems().contains(solv.getName())) {
                    solverComboBox.getItems().add(solv.getName());
                }
                if (!benchmarkComboBox.getItems().contains(solv.getBenchmark())) {
                    benchmarkComboBox.getItems().add(solv.getBenchmark());
                }
                if (!solverTypeComboBox.getItems().contains(solv.getType())) {
                    solverTypeComboBox.getItems().add(solv.getType());
                }
                if (!timeOutComboBox.getItems().contains(solv.getTimeOut())) {
                    timeOutComboBox.getItems().add(solv.getTimeOut());
                }
                if (!memoryComboBox.getItems().contains(solv.getMemory())) {
                    memoryComboBox.getItems().add(solv.getMemory());
                }
                if (!coresComboBox.getItems().contains(solv.getNumberOfCores())) {
                    coresComboBox.getItems().add(solv.getNumberOfCores());
                }
            }
            if (solverComboBox.getItems().size() == 1) {
                solverComboBox.getSelectionModel().select(0);
                solverComboBox.setDisable(true);
            }
            if (benchmarkComboBox.getItems().size() == 1) {
                benchmarkComboBox.getSelectionModel().select(0);
                benchmarkComboBox.setDisable(true);
            }
            if (solverTypeComboBox.getItems().size() == 1) {
                solverTypeComboBox.getSelectionModel().select(0);
                solverTypeComboBox.setDisable(true);
            }
            if (timeOutComboBox.getItems().size() == 1) {
                timeOutComboBox.getSelectionModel().select(0);
                timeOutComboBox.setDisable(true);
            }
            if (memoryComboBox.getItems().size() == 1) {
                memoryComboBox.getSelectionModel().select(0);
                memoryComboBox.setDisable(true);
            }
            if (coresComboBox.getItems().size() == 1) {
                coresComboBox.getSelectionModel().select(0);
                coresComboBox.setDisable(true);
            }
        }
    }

    public static class CheckBoxTableCell<S, T> extends TableCell<S, T> {

        private final CheckBox checkBox;
        private ObservableValue<T> ov;

        public CheckBoxTableCell() {
            this.checkBox = new CheckBox();
            this.checkBox.setAlignment(Pos.CENTER);

            setAlignment(Pos.CENTER);
            setGraphic(checkBox);
        }

        @Override
        public void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                setGraphic(checkBox);
                if (ov instanceof BooleanProperty) {
                    checkBox.selectedProperty().unbindBidirectional((BooleanProperty) ov);
                }
                ov = getTableColumn().getCellObservableValue(getIndex());
                if (ov instanceof BooleanProperty) {
                    checkBox.selectedProperty().bindBidirectional((BooleanProperty) ov);
                }
            }
        }
    }
}
