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
        this.chargeLanguageComboBox(SolverAssistant.messages.getLocale());
        comboLanguage.getSelectionModel().selectedItemProperty().addListener(this.languageComboBoxListener());
    }

    public void chargeI18nValues() {
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
                SolverAssistant.manager.refreshI18nResources();
            }
        };
    }

}
