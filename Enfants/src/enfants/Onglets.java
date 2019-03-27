package enfants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;

/**
 * @author Amine Semmoud
 */
public class Onglets extends JFrame {

    JFrame jf;
    JTabbedPane tp;
    JPanel activite;
    JPanel niveau;
    JPanel admin;

    public Onglets() {

        jf = new JFrame();
        tp = new JTabbedPane();

        jf.setTitle("JEUX D'ENFANTS");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(1300, 700);  // taille de la fenetre

        /////////////////////////////////////////////////////////////////////
        //Activités
        activite = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon m = new ImageIcon(getClass().getResource("/Ressource/fond_accueil.jpg"));
                Image monImage = m.getImage();
//                Image img = Toolkit.getDefaultToolkit().getImage("/Ressource/fond_accueil.jpg");
                g.drawImage(monImage, 0, 0, null);
            }
        };
        activite.setBackground(Color.PINK); // lui donner une couleur

        // creation de trois boutons pour les activités
        JButton jb1 = new JButton("DESSIN");
        JButton jb2 = new JButton("CALCUL");
        JButton jb3 = new JButton("QUESTION");
        //placement des boutons dans le JPanel
        jb1.setBounds(170, 100, 150, 40);
        jb2.setBounds(10, 200, 150, 40);
        jb3.setBounds(330, 200, 150, 40);

        // Ajout des boutons selon le gestionnaire de placement
        activite.add(jb1);
        activite.add(jb2);
        activite.add(jb3);

        ///////////////////////////////////////////////////////
        //Niveaux
        niveau = new JPanel();
        niveau.setBackground(Color.YELLOW);
        JButton niv1 = new JButton("NIVEAU1");
        JButton niv2 = new JButton("NIVEAU2");
        //Ajout des boutons selon le gestionnaire de placement pour les niveaux
        niveau.add(niv1);
        niveau.add(niv2);
        // emplacement des boutons
        niv1.setBounds(10, 200, 150, 40);
        niv2.setBounds(330, 200, 150, 40);

        admin = new JPanel();
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

        tp.setBackgroundAt(0, Color.GREEN);
        tp.setForegroundAt(0, Color.WHITE);
        tp.setBackgroundAt(1, Color.ORANGE);
        tp.setForegroundAt(1, Color.WHITE);
        tp.setBackgroundAt(2, Color.PINK);
        tp.setForegroundAt(2, Color.WHITE);
        tp.setFont(new Font("New Times Roman", Font.BOLD, 18));

        // action sur le bouton dessin : lance le dessin
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activite.setVisible(false);
                Dessin monDessin = new Dessin();
                BorderLayout layoutActivite = new BorderLayout();
                activite.setLayout(layoutActivite);
                activite.add(monDessin);
            }
        });

        jf.setContentPane(tp);
        jf.setResizable(false);
        jf.setBackground(Color.WHITE);
        jf.setVisible(true);


    }

}
