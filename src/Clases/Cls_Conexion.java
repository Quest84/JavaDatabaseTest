/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Manuel
 */
public class Cls_Conexion {
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "QuestionSleep";
    private static final String PASSWORD = "Parasitedreams1984";
    private static final String URL = "jdbc:mysql://localhost:3306/tap_db_alumnos";
    private static Connection Connect;
    
    public Cls_Conexion(){
        Connect = null;
    }
    
    public Connection getConnection(){
        try{
            Class.forName(DRIVER);
            Connect = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al conectar la DB", JOptionPane.ERROR_MESSAGE);
            System.exit(0);       
        }
        return Connect;
    }
    
    public void Desconectar() {
        try {
            Connect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al desconectar DB", JOptionPane.ERROR_MESSAGE);
        }
    }
}
