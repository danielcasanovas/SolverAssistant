/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Controller for the Edit Log View
 */
package solverassistant;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FXMLEditLogController implements Initializable {

    @FXML
    private Label solverLabel, benchmarkLabel, solverTypeLabel, timeOutLabel;

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
        sendToDBButton.setText(SolverAssistant.messages.getString("SendToDB"));
    }
}
