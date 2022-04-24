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
public class Cls_Producto {

    private final String SQL_INSERT = "INSERT INTO Producto ("
            + "Nombre, Marca, Precio, Stock) VALUES (?, ?, ?, ?)";
    private final String SQL_SELECT = "SELECT * FROM Producto";
    private PreparedStatement PrepStat;
    private DefaultTableModel DefaultTM;
    private ResultSet RSet;
    private final Cls_Conexion Connect;

    public Cls_Producto() {
        PrepStat = null;
        Connect = new Cls_Conexion();
    }

    private DefaultTableModel setTitulos() {
        DefaultTM = new DefaultTableModel();
        DefaultTM.addColumn("ID");
        DefaultTM.addColumn("Nombre");
        DefaultTM.addColumn("Marca");
        DefaultTM.addColumn("Precio");
        DefaultTM.addColumn("Stock");

        return DefaultTM;
    }

    /* Método para insertar datos */
    public int insertarDatos(String nombre, String marca, float precio, int stock) {
        int resp = 0;
        try {
            PrepStat = Connect.getConnection().prepareStatement(SQL_INSERT);
            PrepStat.setString(1, nombre);
            PrepStat.setString(2, marca);
            PrepStat.setFloat(3, precio);
            PrepStat.setInt(4, stock);
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
    public int updateDatos(String id, String nombre, String marca, float precio, int stock) {
        /* La consulta sirve para pasarle los datos de cada campo */
        String SQL = "UPDATE Producto SET nombre= '" + nombre + "', marca= '" + marca
                + "', precio= '" + precio + "', stock= " + stock + " WHERE id= " + id;
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
    public int deleteDatos(String id) {
        String SQL = "DELETE from producto WHERE id = " + id;
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
                fila[3] = RSet.getFloat(4);
                fila[4] = RSet.getInt(5);
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
                SQL = "SELECT * FROM Producto WHERE id LIKE '" + prm + "%'";
                break;
            case 1:
                SQL = "SELECT * FROM Producto WHERE Nombre LIKE '" + prm + "%'";
                break;
            case 2:
                SQL = "SELECT * FROM Producto WHERE Marca LIKE '" + prm + "%'";
                break;
            case 3:
                SQL = "SELECT * FROM Producto WHERE Precio LIKE '" + prm + "%'";
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
                fila[3] = RSet.getFloat(4);
                fila[4] = RSet.getInt(5);
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
                SQL = "SELECT * FROM Producto ORDER BY id";
                break;
            case 1:
                SQL = "SELECT * FROM Producto ORDER BY Nombre";
                break;
            case 2:
                SQL = "SELECT * FROM Producto ORDER BY Marca";
                break;
            case 3:
                SQL = "SELECT * FROM Producto ORDER BY Precio";
                break;
            case 4:
                SQL = "SELECT * FROM Producto ORDER BY Stock";
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
                fila[3] = RSet.getFloat(4);
                fila[4] = RSet.getInt(5);
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
