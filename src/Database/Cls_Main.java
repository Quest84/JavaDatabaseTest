/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import Formularios.Frm_Alumnos;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Manuel
 */
public class Cls_Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error al aplicar el estilo al sistema", JOptionPane.ERROR_MESSAGE);
        }

        JOptionPane.showMessageDialog(null, "Test");
        Frm_Alumnos FA = new Frm_Alumnos();
        FA.setVisible(true);
    }
    
}
