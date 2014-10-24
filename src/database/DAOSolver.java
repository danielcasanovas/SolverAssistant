/*
 * TFG: Daniel Casanovas Gayoso 
 * Grau en Enginyeria Informàtica - Escola Politècnica Superior de Lleida
 * 2014/2015
 * Solver DAO
 */
package database;

import entities.Solver;
import entities.SolverInstance;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOSolver {

    public static DataBaseManager databaseManager = null;
    public final static String sqlInsertSolver = "INSERT INTO `Solver` VALUES (?,?,?,?,?,?);";
    public final static String sqlInsertSolverInstance = "INSERT INTO `SolverInstance` VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    public DAOSolver() {
        try {
            initDataBase();
        } catch (Exception ex) {
            System.err.println("[ERROR-INFO] - " + ex);
        }
    }

    private static void initDataBase() throws Exception {
        databaseManager = new DataBaseManager();
    }

    public void addSolver(Solver solv) throws Exception {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            databaseManager.openConnection();
            pst = databaseManager.getStatement(sqlInsertSolver);

            int i = 1;
            pst.setString(i++, solv.getName());
            pst.setString(i++, solv.getBenchmark());
            pst.setString(i++, solv.getType());
            pst.setInt(i++, solv.getTimeOut());
            pst.setInt(i++, solv.getMemory());
            pst.setInt(i++, solv.getNumberOfCores());

            pst.execute();
            BigDecimal solverId = null;
            if (pst.getGeneratedKeys() != null) {
                rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    solverId = new BigDecimal(rs.getInt(1));
                } else {
                    throw new SQLException("Creating solver failed, no ID obtained.");
                }
            }
            pst.close();
            if (solverId!= null && !solv.getInstancesList().isEmpty()) {
                for (SolverInstance instance : solv.getInstancesList()) {
                    pst = databaseManager.getStatement(sqlInsertSolver);
                    i = 1;
                    pst.setString(i++, instance.getFileName());
                    pst.setDouble(i++, instance.getTime());
                    if (instance.isOptimum()) {
                        pst.setInt(i++, 1);
                    } else {
                        pst.setInt(i++, 0);
                    }
                    pst.setInt(i++, instance.getSolution());
                    pst.setInt(i++, instance.getInfo());
                    pst.setInt(i++, instance.getTimeOut());
                    pst.setInt(i++, instance.getBuggy());
                    pst.setInt(i++, instance.getSegmentationFault());
                    pst.setString(i++, instance.getLog());
                    pst.setInt(i++, instance.getOutOfMemory());
                    pst.setInt(i++, instance.getNumberOfVariables());
                    pst.setInt(i++, instance.getNumberOfClause());
                    pst.setInt(i++, instance.getNumberOfHardClause());
                    pst.setInt(i++, instance.getNumberOfSoftClause());
                    pst.setInt(i++, instance.getNumberOfUnsatClause());
                    pst.setInt(i++, instance.getUnsatClauseWeigth());
                    pst.setInt(i++, solverId.intValue());

                    pst.execute();
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            databaseManager.closeConnection();
        }
    }
}
