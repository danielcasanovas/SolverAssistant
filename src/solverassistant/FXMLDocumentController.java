/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 */
package solverassistant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javax.swing.JFileChooser;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label logNameLabel;

    @FXML
    private TextArea logTextArea;

    @FXML
    private ComboBox comboLanguage;
    
    @FXML
    private Button loadLogButton;
    
    @FXML
    private Tab loadTab;
    
    @FXML
    private Tab editTab;

    private ResourceBundle messages;

    //private String logName;
    private String logName = "C:\\Users\\Daniel\\Desktop\\ahmaxsat-ls-ms_crafted-COMPLETE-1800-3500-2.log";
    private String log;

    @FXML
    private void openLog(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();

        // Selecting File
        // if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        if (true) {
            File file = fileChooser.getSelectedFile();
            log = this.fileReader(new File(logName));
            //logName = String.valueOf(file);
            logNameLabel.setText(logName);
            // logNameLabel.setText(messages.getString("OpenNewLog"));
            logTextArea.setText(log);
        }
    }

    private String fileReader(File file) {
        String content = "";
        FileReader fr = null;
        BufferedReader br = null;
        try {
            // Opening File
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            // Reading File
            String linea;
            while ((linea = br.readLine()) != null) {
                content += linea;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return content;
    }

    private void chargeResourceBundleLanguage() {
        comboLanguage.getItems().addAll(
                "English",
                "Español",
                "Català"
        );
        String defaultLanguage = this.fileReader(new File("lang.txt"));
        switch (defaultLanguage) {
            case "en":
                messages = ResourceBundle.getBundle("bundles.bundle", Locale.forLanguageTag("en"));
                comboLanguage.setValue("English");
                break;
            case "es":
                messages = ResourceBundle.getBundle("bundles.bundle", Locale.forLanguageTag("es"));
                comboLanguage.setValue("Español");
                break;
            case "cat":
                messages = ResourceBundle.getBundle("bundles.bundle", Locale.forLanguageTag("cat"));
                comboLanguage.setValue("Català");
                break;
            default:
                messages = ResourceBundle.getBundle("bundles.bundle", Locale.forLanguageTag("en"));
                comboLanguage.setValue("English");
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.chargeResourceBundleLanguage();
        loadLogButton.setText(messages.getString("OpenNewLog"));
        loadTab.setText(messages.getString("LoadLog"));
        editTab.setText(messages.getString("EditLog"));
    }

}
