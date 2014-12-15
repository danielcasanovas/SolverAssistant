/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Class to manage all the controllers and the data between them
 */
package solverassistant;

import database.DAOSolver;
import utils.Utils;
import entities.Solver;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SolverManager {

    private final Scene scene;
    private static FXMLMainController mainController;
    public static DAOSolver daoSolver = null;
    public static Solver solverCharged = null;
    public static List<Solver> solversToCompare = null;

    public SolverManager(Scene scene) {
        this.scene = scene;
    }

    public void showMainView() throws IOException {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("FXMLMain.fxml"));
        scene.setRoot((Parent) mainLoader.load());
        mainController = mainLoader.<FXMLMainController>getController();
    }

    public static void initDB() {
        daoSolver = new DAOSolver();
    }

    public static void showStatus(String message) {
        mainController.showStatus(message);
    }

    public static void loadSolver(String logName, String log) {
        Solver load = Utils.createSolverFromData(logName, log);
        if (load != null) {
            solverCharged = Utils.createSolverFromData(logName, log);
            mainController.loadSolver();
            SolverManager.showStatus(SolverAssistant.messages.getString("SolverLoadedInfo"));
        } else {
            showStatus(SolverAssistant.messages.getString("ErrorLoadLog"));
        }
    }

    public static void compareSolvers(Boolean option) {
        mainController.compareSolvers(option);
        //showStatus(SolverAssistant.messages.getString("ErrorLoadLog"));
    }
}
