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
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

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
    private Button compareButton, refreshButton;

    @FXML
    private CheckBox solverCheckBox, benchmarkCheckBox, typeCheckBox, memoryCheckBox, timeOutCheckBox, coresCheckBox;

    private List<Solver> solvers; // All solvers without the instances from db
    private List<Solver> solversToPrint; // Solvers selecteds to print 
    private ObservableList<SolverProperties> data; // All solvers without the instances from db to put in first table
    private ObservableList<SolverProperties> filteredData; // Solvers filetered

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadSolversFromDB();
        configTableViewPageUI();
        bindDataToTable(data);
        filteredData = FXCollections.observableArrayList();
        chargeI18nValues();
    }

    private void loadSolversFromDB() {
        solvers = SolverManager.daoSolver.getAllSolvers();
        List<SolverProperties> solversPropierties = new ArrayList<>();
        for (Solver s : solvers) {
            solversPropierties.add(new SolverProperties(s));
        }
        data = FXCollections.observableArrayList(solversPropierties);
        data.addListener(new ListChangeListener<SolverProperties>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends SolverProperties> change) {
                updateFilteredData();
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

        refreshButton.setText(SolverAssistant.messages.getString("Refresh"));
        compareButton.setText(SolverAssistant.messages.getString("Compare"));
        
        allSolversTable.setPlaceholder(new Label(SolverAssistant.messages.getString("EmptyTable")));
        selectedSolversTable.setPlaceholder(new Label(SolverAssistant.messages.getString("EmptyTable")));
    }

    private void bindDataToTable(ObservableList<SolverProperties> data) {
        allSolversTable.getItems().clear();
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

        solverCheckBox.selectedProperty().addListener(getComboBoxListener());
        benchmarkCheckBox.selectedProperty().addListener(getComboBoxListener());
        typeCheckBox.selectedProperty().addListener(getComboBoxListener());
        memoryCheckBox.selectedProperty().addListener(getComboBoxListener());
        timeOutCheckBox.selectedProperty().addListener(getComboBoxListener());
        coresCheckBox.selectedProperty().addListener(getComboBoxListener());

        filterTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                updateFilteredData();
                bindDataToTable(filteredData);
            }
        });

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
        // If not check if check box is selected and filter by filterstring
        if (solverCheckBox.isSelected()) {
            if (solv.getName().toLowerCase().contains(filterString.toLowerCase())) {
                return true;
            }
        }
        if (benchmarkCheckBox.isSelected()) {
            if (solv.getBenchmark().toLowerCase().contains(filterString.toLowerCase())) {
                return true;
            }
        }
        if (typeCheckBox.isSelected()) {
            if (solv.getType().toLowerCase().contains(filterString.toLowerCase())) {
                return true;
            }
        }
        if (memoryCheckBox.isSelected()) {
            if (String.valueOf(solv.getTimeOut()).contains(filterString.toLowerCase())) {
                return true;
            }
        }
        if (timeOutCheckBox.isSelected()) {
            if (String.valueOf(solv.getMemory()).contains(filterString.toLowerCase())) {
                return true;
            }
        }
        if (coresCheckBox.isSelected()) {
            if (String.valueOf(solv.getNumberOfCores()).contains(filterString.toLowerCase())) {
                return true;
            }
        }
        return false;
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

    private ChangeListener<Boolean> getComboBoxListener() {
        return new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                updateFilteredData();
                bindDataToTable(filteredData);
            }
        };
    }

    // -------- Actions
    @FXML
    private void refreshSolvers(ActionEvent event) {
        loadSolversFromDB();
        updateFilteredData();
        bindDataToTable(filteredData);
    }

}
