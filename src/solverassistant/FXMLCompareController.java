/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Controller for the Compare View
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
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private Label filterLabel, filterByLabel;

    @FXML
    private TextField filterTextField;

    @FXML
    private Button compareButton, reloadButton;

    @FXML
    private CheckBox wholeWordCheckBox, solverCheckBox, benchmarkCheckBox, typeCheckBox, memoryCheckBox, timeOutCheckBox, coresCheckBox;

    private ObservableList<SolverProperties> data; // All solvers without the instances from db to put in first table
    private ObservableList<SolverProperties> filteredData; // Solvers filetered
    private ObservableList<SolverProperties> selectedData; // Solvers selected

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filteredData = FXCollections.observableArrayList();
        selectedData = FXCollections.observableArrayList();
        configTableViewPageUI();
        loadSolversFromDB();
        bindDataToTable(data, false);
        chargeI18nValues();
    }

    private void loadSolversFromDB() {
        List<Solver> solvers = SolverManager.daoSolver.getAllSolvers();
        List<SolverProperties> solversPropierties = new ArrayList<>();
        for (Solver s : solvers) {
            solversPropierties.add(new SolverProperties(s));
        }
        data = FXCollections.observableArrayList(solversPropierties);
        selectedData.addListener((ListChangeListener.Change<? extends SolverProperties> change) -> {
            if (selectedData.isEmpty()) {
                compareButton.setDisable(true);
            } else {
                compareButton.setDisable(false);
            }
        });
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

        filterLabel.setText(SolverAssistant.messages.getString("Filter"));
        filterByLabel.setText(SolverAssistant.messages.getString("Filter") + " " + SolverAssistant.messages.getString("By") + ":");
        solverCheckBox.setText(SolverAssistant.messages.getString("Solver"));
        benchmarkCheckBox.setText(SolverAssistant.messages.getString("Benchmark"));
        typeCheckBox.setText(SolverAssistant.messages.getString("SolverType"));
        memoryCheckBox.setText(SolverAssistant.messages.getString("TimeOut"));
        timeOutCheckBox.setText(SolverAssistant.messages.getString("Memory"));
        coresCheckBox.setText(SolverAssistant.messages.getString("NumberOfCores"));

        reloadButton.setText(SolverAssistant.messages.getString("ResetAndReloadData"));
        compareButton.setText(SolverAssistant.messages.getString("Compare"));

        allSolversTable.setPlaceholder(new Label(SolverAssistant.messages.getString("EmptyTable")));
        selectedSolversTable.setPlaceholder(new Label(SolverAssistant.messages.getString("EmptyTable")));
    }

    private void configTableViewPageUI() {
        colAllSelect.setCellFactory((TableColumn<SolverProperties, Boolean> p) -> new CheckBoxTableCell<>());
        colAllSolver.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAllBenchmark.setCellValueFactory(new PropertyValueFactory<>("benchmark"));
        colAllSolverType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colAllTimeOut.setCellValueFactory(new PropertyValueFactory<>("timeOut"));
        colAllMemory.setCellValueFactory(new PropertyValueFactory<>("memory"));
        colAllNumberOfCores.setCellValueFactory(new PropertyValueFactory<>("numberOfCores"));

        colSelectedSolver.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSelectedBenchmark.setCellValueFactory(new PropertyValueFactory<>("benchmark"));
        colSelectedSolverType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colSelectedTimeOut.setCellValueFactory(new PropertyValueFactory<>("timeOut"));
        colSelectedMemory.setCellValueFactory(new PropertyValueFactory<>("memory"));
        colSelectedNumberOfCores.setCellValueFactory(new PropertyValueFactory<>("numberOfCores"));

        filterTextField.textProperty().addListener(getTextFieldListener());
        solverCheckBox.selectedProperty().addListener(getComboBoxListener());
        benchmarkCheckBox.selectedProperty().addListener(getComboBoxListener());
        typeCheckBox.selectedProperty().addListener(getComboBoxListener());
        memoryCheckBox.selectedProperty().addListener(getComboBoxListener());
        timeOutCheckBox.selectedProperty().addListener(getComboBoxListener());
        coresCheckBox.selectedProperty().addListener(getComboBoxListener());
        wholeWordCheckBox.selectedProperty().addListener(getComboBoxListener());
    }

    private void bindDataToTable(ObservableList<SolverProperties> data, boolean selectedTable) {
        if (selectedTable) {
            selectedSolversTable.getItems().clear();
            selectedSolversTable.getItems().addAll(data);
        } else {
            allSolversTable.getItems().clear();
            allSolversTable.getItems().addAll(data);
        }
    }

    private void updateFilteredData() {
        filteredData.clear();
        for (SolverProperties p : data) {
            if (matchesFilter(p)) {
                filteredData.add(p);
            }
        }
    }

    private boolean matchesFilter(SolverProperties solv) {
        String filterString = filterTextField.getText();
        // No filter --> Add all.
        if (filterString == null || filterString.isEmpty()) {
            return true;
        }
        if (wholeWordCheckBox.isSelected()) {
            if (solverCheckBox.isSelected() && solv.getName().toLowerCase().equals(filterString.toLowerCase())) {
                return true;
            } else if (benchmarkCheckBox.isSelected() && solv.getBenchmark().toLowerCase().equals(filterString.toLowerCase())) {
                return true;
            } else if (typeCheckBox.isSelected() && solv.getType().toLowerCase().equals(filterString.toLowerCase())) {
                return true;
            } else if (memoryCheckBox.isSelected() && String.valueOf(solv.getTimeOut()).equals(filterString.toLowerCase())) {
                return true;
            } else if (timeOutCheckBox.isSelected() && String.valueOf(solv.getMemory()).equals(filterString.toLowerCase())) {
                return true;
            } else if (coresCheckBox.isSelected() && String.valueOf(solv.getNumberOfCores()).equals(filterString.toLowerCase())) {
                return true;
            } else {
                return false;
            }
        } else {
            if (solverCheckBox.isSelected() && solv.getName().toLowerCase().contains(filterString.toLowerCase())) {
                return true;
            } else if (benchmarkCheckBox.isSelected() && solv.getBenchmark().toLowerCase().contains(filterString.toLowerCase())) {
                return true;
            } else if (typeCheckBox.isSelected() && solv.getType().toLowerCase().contains(filterString.toLowerCase())) {
                return true;
            } else if (memoryCheckBox.isSelected() && String.valueOf(solv.getTimeOut()).contains(filterString.toLowerCase())) {
                return true;
            } else if (timeOutCheckBox.isSelected() && String.valueOf(solv.getMemory()).contains(filterString.toLowerCase())) {
                return true;
            } else if (coresCheckBox.isSelected() && String.valueOf(solv.getNumberOfCores()).contains(filterString.toLowerCase())) {
                return true;
            } else {
                return false;
            }
        }
    }

    public class CheckBoxTableCell<S, T> extends TableCell<S, T> {

        private final CheckBox checkBox;
        private ObservableValue<T> ov;

        public CheckBoxTableCell() {
            this.checkBox = new CheckBox();
            this.checkBox.setAlignment(Pos.CENTER);
            this.checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                    if (ov.getValue()) {
                        getTableView().getSelectionModel().select(getTableRow().getIndex());
                        addToSelectedsList(getTableView().getSelectionModel().getSelectedItem());
                    } else {
                        getTableView().getSelectionModel().select(getTableRow().getIndex());
                        removeFromSelectedsList(getTableView().getSelectionModel().getSelectedItem());
                    }
                    bindDataToTable(selectedData, true);
                }
            });
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

    public void addToSelectedsList(Object obj) {
        selectedData.add((SolverProperties) obj);
    }

    public void removeFromSelectedsList(Object obj) {
        selectedData.remove((SolverProperties) obj);
    }

    // -------- Listeners
    private ChangeListener<Boolean> getComboBoxListener() {
        return (ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            updateFilteredData();
            bindDataToTable(filteredData, false);
        };
    }

    private ChangeListener<String> getTextFieldListener() {
        return ((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            updateFilteredData();
            bindDataToTable(filteredData, false);
        });
    }

    // -------- Actions
    @FXML
    private void resetAndReloadSolvers(ActionEvent event) {
        loadSolversFromDB();
        updateFilteredData();
        selectedData.clear();
        bindDataToTable(filteredData, false);
        bindDataToTable(selectedData, true);
        colAllSelect.setCellFactory(null);
        colAllSelect.setCellFactory((TableColumn<SolverProperties, Boolean> p) -> new CheckBoxTableCell<>());

    }
}
