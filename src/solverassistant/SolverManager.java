/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Class to manage all the controllers and the data between them
 */
package solverassistant;

import entities.Solver;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SolverManager {

    private final Scene scene;
    private static FXMLMainController mainController;
    public static Solver solverCharged = null;
    public static DataBase database = null;
    private final static String dataBaseName = "SolverAssistant";

    public SolverManager(Scene scene) {
        this.scene = scene;
        try {
            SolverManager.initDataBase();
        } catch (Exception ex) {
            System.err.println("[ERROR-INFO] - " + ex);
        }
    }

    public void showMainView() throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("FXMLMain.fxml"));
        scene.setRoot((Parent) mainLoader.load());
        mainController = mainLoader.<FXMLMainController>getController();
    }

    public static void loadSolver(String logName, String log) {
        solverCharged = Utils.createSolverFromData(logName, log);
        mainController.loadSolver();
    }

    private static void initDataBase() throws Exception {
       // database = new DataBase(dataBaseName);
    }
}
