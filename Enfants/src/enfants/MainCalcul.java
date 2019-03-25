/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enfants;

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

        JPanel panel = new JPanel();

        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

        panel.setLayout(boxlayout);

        panel.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));

        JButton jb1 = new JButton("0-9");
        JButton jb2 = new JButton("0-9");
        JButton jb3 = new JButton("+ -");
        JButton jb4 = new JButton("Verifier");
        JButton jb5 = new JButton("Solution");
        JButton jb6 = new JButton("Autre calcul");

        panel.add(jb1);
        panel.add(jb2);
        panel.add(jb3);
        panel.add(jb4);
        panel.add(jb5);
        panel.add(jb6);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

    }

}
