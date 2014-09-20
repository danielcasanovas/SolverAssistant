/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
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
    private Tab loadTab;

    @FXML
    private Tab editTab;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.chargeI18nValues();
        comboLanguage.getSelectionModel().selectedItemProperty().addListener(this.languageComboBoxListener());
    }

    public void chargeI18nValues() {
        // Charge i18n values to the elements of this controller
        this.chargeLanguageComboBox(SolverAssistant.messages.getLocale());
        loadTab.setText(SolverAssistant.messages.getString("LoadLog"));
        editTab.setText(SolverAssistant.messages.getString("EditLog"));
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

    // -------- Listeners
    private ChangeListener languageComboBoxListener() {
        return new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {

            }

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                switch ((String) newValue) {
                    case "Català":
                        SolverAssistant.messages = ResourceBundle.getBundle("bundles.bundle", Locale.forLanguageTag("cat"));
                        SolverAssistant.utils.fileWriter("lang.txt", "cat");
                        SolverAssistant.loadController.chargeI18nValues();
                        break;
                    case "Español":
                        SolverAssistant.messages = ResourceBundle.getBundle("bundles.bundle", Locale.forLanguageTag("es"));
                        SolverAssistant.utils.fileWriter("lang.txt", "es");
                        SolverAssistant.loadController.chargeI18nValues();
                        break;
                    case "English":
                        SolverAssistant.messages = ResourceBundle.getBundle("bundles.bundle", Locale.forLanguageTag("en"));
                        SolverAssistant.utils.fileWriter("lang.txt", "en");
                        SolverAssistant.loadController.chargeI18nValues();
                        break;
                }
                chargeI18nValues();
            }
        };
    }

}
