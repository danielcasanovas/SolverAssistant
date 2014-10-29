/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Controller for the Main View
 */
package solverassistant;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javax.swing.event.ChangeEvent;

public class FXMLMainController implements Initializable {

    @FXML
    private ComboBox<String> comboLanguage;

    @FXML
    private FXMLLoadLogController barTabPageLoadController;

    @FXML
    private FXMLEditSolverController barTabPageEditController;

    @FXML
    private FXMLCompareController barTabPageCompareController;

    @FXML
    private Tab loadTab, editTab, compareTab;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.chargeI18nValues();
        this.chargeLanguageComboBox(SolverAssistant.messages.getLocale());
        comboLanguage.getSelectionModel().selectedItemProperty().addListener(getLanguageComboBoxListener());
        editTab.setDisable(true);
    }

    /**
     * Set the properly i18n data to all the components in the view
     */
    public void chargeI18nValues() {
        loadTab.setText(SolverAssistant.messages.getString("LoadLog"));
        editTab.setText(SolverAssistant.messages.getString("EditSolver"));
        compareTab.setText(SolverAssistant.messages.getString("CompareFromDatabase"));

        barTabPageLoadController.chargeI18nValues();
        barTabPageEditController.chargeI18nValues();
        barTabPageCompareController.chargeI18nValues();
    }

    private void chargeLanguageComboBox(Locale language) {
        comboLanguage.getItems().addAll(
                "Català",
                "English",
                "Español"
        );
        switch (language.getLanguage()) {
            case "cat":
                comboLanguage.setValue("Català");
                break;
            case "es":
                comboLanguage.setValue("Español");
                break;
            case "en":
                comboLanguage.setValue("English");
                break;
        }
    }

    public void loadSolver() {
        barTabPageEditController.loadSolver();
        editTab.setDisable(false);
    }

    // -------- Listeners
    private ChangeListener getLanguageComboBoxListener() {
        return new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
            }

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                switch ((String) newValue) {
                    case "Català":
                        SolverAssistant.messages = ResourceBundle.getBundle("bundles.bundle", Locale.forLanguageTag("cat"));
                        SolverAssistant.utils.fileWriter("lang.txt", "cat");
                        break;
                    case "Español":
                        SolverAssistant.messages = ResourceBundle.getBundle("bundles.bundle", Locale.forLanguageTag("es"));
                        SolverAssistant.utils.fileWriter("lang.txt", "es");
                        break;
                    case "English":
                        SolverAssistant.messages = ResourceBundle.getBundle("bundles.bundle", Locale.forLanguageTag("en"));
                        SolverAssistant.utils.fileWriter("lang.txt", "en");
                        break;
                }
                chargeI18nValues();
            }
        };
    }
}
