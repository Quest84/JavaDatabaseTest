/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lógica;

import Clases.Cls_Conexion;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Manuel
 */
public class Cls_Alumno {

    private final String SQL_INSERT = "INSERT INTO Alumno ("
            + "Nombre, Parcial, Grupo, Calificacion) VALUES (?, ?, ?, ?)";
    private final String SQL_SELECT = "SELECT * FROM Alumno";
    private PreparedStatement PrepStat;
    private DefaultTableModel DefaultTM;
    private ResultSet RSet;
    private final Cls_Conexion Connect;

    public Cls_Alumno() {
        PrepStat = null;
        Connect = new Cls_Conexion();
    }

    private DefaultTableModel setTitulos() {
        DefaultTM = new DefaultTableModel();
        DefaultTM.addColumn("Matricula");
        DefaultTM.addColumn("Nombre");
        DefaultTM.addColumn("Parcial");
        DefaultTM.addColumn("Grupo");
        DefaultTM.addColumn("Calificacion");

        return DefaultTM;
    }

    /* Método para insertar datos */
    public int insertarDatos(String nombre, String parcial, String grupo, float calificacion) {
        int resp = 0;
        try {
            PrepStat = Connect.getConnection().prepareStatement(SQL_INSERT);
            PrepStat.setString(1, nombre);
            PrepStat.setString(2, parcial);
            PrepStat.setString(3, grupo);
            PrepStat.setFloat(4, calificacion);
            resp = PrepStat.executeUpdate();

            if (resp > 0) {
                JOptionPane.showMessageDialog(null, "Registro Guardado");
            }

        } catch (SQLException ex) {
            System.err.println("Error al guardar los datos en la DB: " + ex.getMessage());
        } finally {
            PrepStat = null;
            Connect.Desconectar();
        }
        return resp;
    }

    /* Método para modificar los datos */
    public int updateDatos(String matricula, String nombre, String parcial, String grupo, float calificacion) {
        /* La consulta sirve para pasarle los datos de cada campo */
        String SQL = "UPDATE Alumno SET nombre = '" + nombre + "', parcial = '" + parcial
                + "', grupo = '" + grupo + "', calificacion = " + calificacion + " WHERE matricula = " + matricula;
        int resp = 0;

        try {
            PrepStat = Connect.getConnection().prepareStatement(SQL);
            resp = PrepStat.executeUpdate();
            if (resp > 0) {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
            }
        } catch (SQLException ex) {
            System.err.println("Error al modifcar los datos");
        } finally {
            PrepStat = null;
            Connect.Desconectar();
        }
        return resp;
    }

    /* Eliminar datos */
    public int deleteDatos(String matricula) {
        String SQL = "DELETE from alumno WHERE matricula = " + matricula;
        int resp = 0;
        try {
            PrepStat = Connect.getConnection().prepareStatement(SQL);
            resp = PrepStat.executeUpdate();
            if (resp > 0) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
            }
        } catch (SQLException ex) {
            System.err.println("Error al eliminar los datos:" + ex.getMessage());
        } finally {
            PrepStat = null;
            Connect.Desconectar();
        }
        return resp;
    }

    /* Método para mostrar los datos */
    public DefaultTableModel getDatos() {
        try {
            setTitulos();
            PrepStat = Connect.getConnection().prepareStatement(SQL_SELECT);
            RSet = PrepStat.executeQuery();

            /* Crear el arreglo para recorrer las filas de la tabla */
            Object[] fila = new Object[5];
            while (RSet.next()) {
                fila[0] = RSet.getInt(1);
                fila[1] = RSet.getString(2);
                fila[2] = RSet.getString(3);
                fila[3] = RSet.getString(4);
                fila[4] = RSet.getFloat(5);
                DefaultTM.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar los datos: " + ex.getMessage());
        } finally {
            PrepStat = null;
            RSet = null;
            Connect.Desconectar();
        }
        return DefaultTM;
    }

    /* Método para buscar los datos */
    public DefaultTableModel getDato(int index, String prm) {
        String SQL = null;
        switch (index) {
            case 0:
                SQL = "SELECT * FROM Alumno WHERE Matricula LIKE '" + prm + "%'";
                break;
            case 1:
                SQL = "SELECT * FROM Alumno WHERE Nombre LIKE '" + prm + "%'";
                break;
            case 2:
                SQL = "SELECT * FROM Alumno WHERE Parcial LIKE '" + prm + "%'";
                break;
            case 3:
                SQL = "SELECT * FROM Alumno WHERE Grupo LIKE '" + prm + "%'";
            default:
                break;
        }

        try {
            setTitulos();
            PrepStat = Connect.getConnection().prepareStatement(SQL);
            RSet = PrepStat.executeQuery();

            // Crear un arreglo para recorrer las filas de la tabla
            Object[] fila = new Object[5];
            while (RSet.next()) {
                fila[0] = RSet.getInt(1);
                fila[1] = RSet.getString(2);
                fila[2] = RSet.getString(3);
                fila[3] = RSet.getString(4);
                fila[4] = RSet.getFloat(5);
                DefaultTM.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar los datos: " + ex.getMessage());
        } finally {
            PrepStat = null;
            RSet = null;
            Connect.Desconectar();
        }

        return DefaultTM;
    }

    public DefaultTableModel orderDato(int index) {
        String SQL = null;
        switch (index) {
            case 0:
                SQL = "SELECT * FROM Alumno ORDER BY Matricula";
                break;
            case 1:
                SQL = "SELECT * FROM Alumno ORDER BY Nombre";
                break;
            case 2:
                SQL = "SELECT * FROM Alumno ORDER BY Parcial";
                break;
            case 3:
                SQL = "SELECT * FROM Alumno ORDER BY Grupo";
                break;
            case 4:
                SQL = "SELECT * FROM Alumno ORDER BY Calificacion";
                break;
            default:
                break;
        } 
        try {
            setTitulos();
            PrepStat = Connect.getConnection().prepareStatement(SQL);
            RSet = PrepStat.executeQuery();
            
            // Crear el arreglo para recorrer las dilas de la tabla
            Object[]fila = new Object[5];
            while (RSet.next()) {
                fila[0] = RSet.getInt(1);
                fila[1] = RSet.getString(2);
                fila[2] = RSet.getString(3);
                fila[3] = RSet.getString(4);
                fila[4] = RSet.getFloat(5);
                DefaultTM.addRow(fila);
            }
        } catch (SQLException ex) {
            System.out.println("Error al ordenar los datos: " + ex.getMessage());
        } finally {
            PrepStat = null;
            RSet = null;
            Connect.Desconectar();
        }
        return DefaultTM;
    }

}
