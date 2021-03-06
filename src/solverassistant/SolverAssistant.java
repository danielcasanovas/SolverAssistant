/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 */
package solverassistant;

import utils.Utils;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SolverAssistant extends Application {

    public static ResourceBundle messages;
    public static Utils utils = new Utils();
    public static SolverManager manager;

    @Override
    public void start(Stage stage) throws Exception {
        // Load the resource bundle
        this.chargeResourceBundleLanguage();

        // Load Controllers
        Scene scene = new Scene(new StackPane());
        manager = new SolverManager(scene);
        manager.initDB();
        manager.showMainView();
        stage.setScene(scene);
        stage.setTitle("Solver Assistant 0.1");
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void chargeResourceBundleLanguage() {
        String defaultLanguage = utils.fileReader(new File("lang.txt"));
        switch (defaultLanguage) {
            case "en":
                messages = ResourceBundle.getBundle("bundles.bundle", Locale.forLanguageTag("en"));
                break;
            case "es":
                messages = ResourceBundle.getBundle("bundles.bundle", Locale.forLanguageTag("es"));
                break;
            case "cat":
                messages = ResourceBundle.getBundle("bundles.bundle", Locale.forLanguageTag("cat"));
                break;
            default:
                messages = ResourceBundle.getBundle("bundles.bundle", Locale.forLanguageTag("en"));
                break;
        }
    }
}
