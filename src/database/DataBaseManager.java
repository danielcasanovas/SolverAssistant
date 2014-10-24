/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * DataBaseManager Manager
 */
package database;

import com.ibatis.common.jdbc.ScriptRunner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseManager {

    private final String sDriver = "org.sqlite.JDBC";
    private final String sUrl = "jdbc:sqlite:SolverAssistant";
    private String databaseName = null;
    private final int timeout = 30;
    private Connection conn = null;
    private PreparedStatement statement = null;

    // To use, use empty constructor and after call init function with the db name or use the second constructor with the db name
    // After that execute executeUp or executeQr
    public DataBaseManager() {
        init();
    }

    private void init() {
        try {
            runInitialScript();
        } catch (Exception ex) {
            System.err.println("[ERROR-INFO] Failed to initiate DB: " + ex.getMessage());
        }

    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void openConnection() throws Exception {
        Class.forName(sDriver);
        conn = DriverManager.getConnection(getCompleteUrl());
    }

    public String getCompleteUrl() {
        return sUrl + databaseName;
    }

    public Connection getConnection() {
        return conn;
    }

    public PreparedStatement getStatement(String sql) throws SQLException {
        statement = conn.prepareStatement(sql);
        statement.setQueryTimeout(timeout);
        return statement;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (Exception ignore) {
            System.err.println("[ERROR-INFO] - " + ignore);
        }
    }

    private void runInitialScript() throws Exception {
        String aSQLScriptFilePath = "src\\utils\\CreationDataBase.sql";
        openConnection();
        try {
            // Initialize object for ScripRunner
            ScriptRunner sr = new ScriptRunner(getConnection(), false, false);

            // Give the input file to Reader
            Reader reader = new BufferedReader(new FileReader(aSQLScriptFilePath));

            // Exctute script
            sr.runScript(reader);

            System.out.println("[INFO] Initial script executed");

        } catch (IOException | SQLException e) {
            System.err.println("[ERROR-INFO] Failed to Execute " + aSQLScriptFilePath + ". Message: " + e.getMessage());
        } finally {
            closeConnection();
        }
    }
}
