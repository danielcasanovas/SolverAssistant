/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 */
package solverassistant;

import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SolverAssistant extends Application {

    public static ResourceBundle messages;
    public static Utils utils = new Utils();
    public static FXMLMainController mainController;
    public static FXMLLoadLogController loadController;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        // Load Controllers
        FXMLLoader loader;
        loader = new FXMLLoader(SolverAssistant.class.getResource("FXMLMain.fxml"));
        mainController = loader.getController();
//        loader = new FXMLLoader(SolverAssistant.class.getResource("FXMLLoadLog.fxml"));
//        loadController = loader.getController();
        System.out.println(">>>>>>> "+mainController);
        this.chargeI18nValues();
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMain.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
     public void chargeI18nValues() {
       String defaultLanguage = utils.fileReader(new File("lang.txt"));
       this.chargeResourceBundleLanguage(defaultLanguage);
    }

    public void chargeResourceBundleLanguage(String defaultLanguage) {
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
    public void refreshCurrentLanguageInViews(){
//        mainController.chargeI18nValues();
//        loadController.chargeI18nValues();
    }
}
