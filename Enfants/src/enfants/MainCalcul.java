/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enfants;

import java.awt.FlowLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Thomas
 */
public class MainCalcul {

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("CALCUL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        JPanel panelGeneral = new JPanel();
        BoxLayout boxGeneral = new BoxLayout(panelGeneral, BoxLayout.PAGE_AXIS);
        panelGeneral.setLayout(boxGeneral);

        JPanel operation = new JPanel(new FlowLayout());
//        BoxLayout boxlayout = new BoxLayout(operation, BoxLayout.LINE_AXIS);
//        operation.setLayout(boxlayout);
//        operation.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));

        Random r = new Random();
        int n = r.nextInt(10);
        int p = r.nextInt(10);
        String[]operande = {"+","-"};
       
        JLabel label = new JLabel(Integer.toString(n));
        String op = operande[r.nextInt(operande.length)];
        JLabel label2 = new JLabel(op);
        JLabel label3= new JLabel(Integer.toString(p));       
		
        operation.add(label);
        operation.add(label2);
        operation.add(label3);
     
        
        JPanel solution = new JPanel(new FlowLayout());
        JTextField textField = new JTextField();
        textField.setColumns(10); //On lui donne un nombre de colonnes à afficher
        solution.add(textField);   
        
        
        JPanel choix = new JPanel(new FlowLayout());
        BoxLayout boxlayout3 = new BoxLayout(choix, BoxLayout.LINE_AXIS);
        choix.setLayout(boxlayout3);
        choix.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));

      
        
        JButton jb5 = new JButton("Verifier");
        JButton jb6 = new JButton("Solution");
        JButton jb7 = new JButton("Autre calcul");

      
        
        choix.add(jb5);
        choix.add(jb6);
        choix.add(jb7);

        panelGeneral.add(operation);
        panelGeneral.add(solution);
        panelGeneral.add(choix);
//        panelGeneral.add(solution);
        frame.add(panelGeneral);
//        frame.add(operation);
//        frame.add(choix);
        frame.pack();
        frame.setVisible(true);

    }

    private static void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
