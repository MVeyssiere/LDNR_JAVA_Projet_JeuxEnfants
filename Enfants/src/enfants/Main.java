/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enfants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Amine Semmoud
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        JFrame jf = new JFrame();
        jf.setTitle("ACCUEIL");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.setSize(500, 500);
//        jf.setSize(500, 500);
//
//        // création d'un panneau d'affichege
//        JTabbedPane content = new JTabbedPane();
//
//        content.setLayout(new FlowLayout());
//
//        jf.add(content);
////        jf.pack();
        // creation des ongelets
        JTabbedPane tp = new JTabbedPane();

        JPanel activite = new JPanel();
        // lui donner une couleur
        activite.setBackground(Color.PINK);
        // ajout d'un gestionnaire de placement
//        BoxLayout box = new BoxLayout(activite, BoxLayout.Y_AXIS);
//        activite.setLayout(box);
//       creation d'un JPANEL CENTRE
//        JPanel centre = new JPanel();
//        centre.setLayout(new BorderLayout(10, 10));
//        activite.add(centre, BorderLayout.CENTER);
        activite.setLayout(null);
        // creation de trois boutons
        JButton jb1 = new JButton("DESSIN");
        JButton jb2 = new JButton("CALCUL");
        JButton jb3 = new JButton("QUESTION");

        jb1.setBounds(170, 100, 150, 40);
        jb2.setBounds(10, 200, 150, 40);
        jb3.setBounds(330, 200, 150, 40);

//      Ajout des boutons selon le gestionnaire de placement
        activite.add(jb1);
        activite.add(jb2);
        activite.add(jb3);

        JPanel niveau = new JPanel();
        niveau.setBackground(Color.YELLOW);
        JButton niv1 = new JButton("NIVEAU1");
        JButton niv2 = new JButton("NIVEAU2");
        niveau.setLayout(null);
        //Ajout des boutons selon le gestionnaire de placement
        niveau.add(niv1);
        niveau.add(niv2);
        // emplacement des boutons
        niv1.setBounds(10, 200, 150, 40);
        niv2.setBounds(330, 200, 150, 40);

        JPanel admin = new JPanel();
        admin.setBackground(Color.yellow);

        admin.setLayout(new FlowLayout());
        JLabel label = new JLabel("mot de passe");
        admin.add(label);
        JTextField textField = new JTextField();
        admin.add(textField);
        textField.setColumns(10); //On lui donne un nombre de colonnes à afficher

        tp.setBounds(0, 0, jf.getWidth(), jf.getHeight());
        tp.addTab("ACTIVITE", activite);
        tp.addTab("NIVEAU", niveau);
        tp.addTab("ADMIN", admin);
//        tp.add(jif);
        // on ajoute des items
//        JMenu file = new JMenu("Fichier");
//        menubar.add(file);
//        JMenu help = new JMenu("Aide");
//        menubar.add(help);
        // Voire même une arborescence de menu
//        JMenuItem newFile = new JMenuItem("Nouveau");
//        file.add(newFile);
////        // Un sous-menu est un menu dans un menu
//        JMenu save = new JMenu("Sauver");
//        file.add(save);
//        JMenuItem recent = new JMenuItem("Fichiers récents");
//        save.add(recent);
//        JMenuItem load = new JMenuItem("Charger");
//        file.add(load);
//        JMenuItem helpFile = new JMenuItem("Contenu de l'aide");
//        help.add(helpFile);
//        help.addSeparator();
//        JMenuItem about = new JMenuItem("A propos");
//        help.add(about);
//        JPanel general = new JPanel();
//        general.add(tp);
//        general.setBackground(Color.red);

        jf.add(tp);
        jf.setBackground(Color.yellow);

//        jf.setBackground(Color.RED);
        jf.setLayout(null);
        jf.setVisible(true);

    }

}
