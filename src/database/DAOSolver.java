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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOSolver {

    public static DataBaseManager databaseManager = null;
    public final static String sqlInsertSolver = "INSERT INTO `Solver` (`Name`,`Benchmark`,`Type`,`TimeOut`,`Memory`,`NumberOfCores`) VALUES (?,?,?,?,?,?);";
    public final static String sqlInsertSolverInstance = "INSERT INTO `SolverInstance` (`Instance`,`Time`,`Optimum`,`Solution`,`Info`,`TimeOut`,`Buggy`,`SegmentationFault`,`Log`,`OutOfMemory`,`NumberOfVariables`,`NumberOfClause`,`NumberOfHardClause`,`NumberOfSoftClause`,`NumberOfUnsatClause`,`NumberOfUnsatClauseWeigth`,`SolverId`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    public final static String sqlSetSolvers = "UPDATE `Solver` SET `Name`=?,`Benchmark`=?,`Type`=?,`TimeOut`=?,`Memory`=?,`NumberOfCores`=? WHERE SolverId=?;";
    public final static String sqlGetSolvers = "SELECT * FROM `Solver`";
    public final static String sqlDeleteSolvers = "DELETE FROM `Solver` WHERE SolverId=?;";

    public DAOSolver() {
        try {
            initDataBase();
        } catch (Exception ex) {
            System.err.println("[ERROR-INFO (DAO)] - " + ex);
        }
    }

    private static void initDataBase() throws Exception {
        databaseManager = new DataBaseManager();
    }

    public void addSolverFULL(Solver solv) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            databaseManager.openConnection();
            Connection conn = databaseManager.getConnection();
            conn.setAutoCommit(false);
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
            conn.commit();
            if (solverId != null && !solv.getInstancesList().isEmpty()) {
                for (SolverInstance instance : solv.getInstancesList()) {
                    pst = databaseManager.getStatement(sqlInsertSolverInstance);
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
                conn.commit();
            }
        } catch (SQLException e) {
            System.err.println("[ERROR-INFO (DAO)] - " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                System.err.println("[ERROR-INFO (DAO)] - " + ex);
            }
        }
        databaseManager.closeConnection();
    }

    public void setSolver(Solver solv) {
        PreparedStatement pst = null;
        try {
            databaseManager.openConnection();
            pst = databaseManager.getStatement(sqlSetSolvers);
            int i = 1;
            pst.setString(i++, solv.getName());
            pst.setString(i++, solv.getBenchmark());
            pst.setString(i++, solv.getType());
            pst.setInt(i++, solv.getTimeOut());
            pst.setInt(i++, solv.getMemory());
            pst.setInt(i++, solv.getNumberOfCores());
            pst.setInt(i++, solv.getId());
            pst.execute();
        } catch (SQLException e) {
            System.err.println("[ERROR-INFO (DAO)] - " + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                System.err.println("[ERROR-INFO (DAO)] - " + ex);
            }
        }
        databaseManager.closeConnection();
    }
    
     public void deleteSolver(Solver solv) {
        PreparedStatement pst = null;
        try {
            databaseManager.openConnection();
            pst = databaseManager.getStatement(sqlDeleteSolvers);
            int i = 1;
            pst.setInt(i++, solv.getId());
            pst.execute();
        } catch (SQLException e) {
            System.err.println("[ERROR-INFO (DAO)] - " + e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                System.err.println("[ERROR-INFO (DAO)] - " + ex);
            }
        }
        databaseManager.closeConnection();
    }

    public List<Solver> getAllSolvers() {
        List<Solver> solvers = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            databaseManager.openConnection();
            pst = databaseManager.getStatement(sqlGetSolvers);
            pst.execute();
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                solvers.add(new Solver(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), res.getInt(7)));
            }
        } catch (SQLException e) {
            System.err.println("[ERROR-INFO (DAO)] - " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                System.err.println("[ERROR-INFO (DAO)] - " + ex);
            }
        }
        databaseManager.closeConnection();
        return solvers;
    }
}
