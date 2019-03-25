/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enfants;

import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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

        JButton jb1 = new JButton("0-9");
        JButton jb2 = new JButton("0-9");
        JButton jb3 = new JButton("+ -");
        
        operation.add(jb1);
        operation.add(jb2);
        operation.add(jb3);
        
       
        
        JPanel choix = new JPanel();
        BoxLayout boxlayout2 = new BoxLayout(choix, BoxLayout.Y_AXIS);
        choix.setLayout(boxlayout2);
        choix.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));

      
        JButton jb4 = new JButton("Résultat");
        JButton jb5 = new JButton("Verifier");
        JButton jb6 = new JButton("Solution");
        JButton jb7 = new JButton("Autre calcul");

      
        choix.add(jb4);
        choix.add(jb5);
        choix.add(jb6);
        choix.add(jb7);

        panelGeneral.add(operation);
        panelGeneral.add(choix);
        frame.add(panelGeneral);
//        frame.add(operation);
//        frame.add(choix);
        frame.pack();
        frame.setVisible(true);

    }

}
