/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 */
package solverassistant;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javax.swing.JFileChooser;

public class FXMLLoadLogController implements Initializable {

    @FXML
    private Label logNameLabel;

    @FXML
    private TextArea logTextArea;

    @FXML
    private Button loadLogButton;

    //private String logName;
    private String logName = "C:\\Users\\Daniel\\Desktop\\ahmaxsat-ls-ms_crafted-COMPLETE-1800-3500-2.log";
    private String log;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.chargeI18nValues();
    }

    public void chargeI18nValues() {
        loadLogButton.setText(SolverAssistant.messages.getString("OpenNewLog"));
    }

    // -------- Actions
    @FXML
    private void openLog(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();

        // Selecting File
        // if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        if (true) {
            File file = fileChooser.getSelectedFile();
            log = SolverAssistant.utils.fileReader(new File(logName));
            logNameLabel.setText(logName);
            logTextArea.setText(log);
        }
    }
}
