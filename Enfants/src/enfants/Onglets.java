package enfants;

import Beans.Admin;
import DAO.DAOAdministration;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;

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
                g.drawImage(monImage, 0, 0, null);
            }
        };

        activite.setLayout(null);
        // creation de trois boutons pour les activités
        JButton jb1 = new JButton("DESSIN");
        JButton jb2 = new JButton("CALCUL");
        JButton jb3 = new JButton("QUESTIONS");
        //placement des boutons dans le JPanel
        jb1.setBounds(570, 200, 200, 60);
        jb2.setBounds(410, 350, 200, 60);
        jb3.setBounds(730, 350, 200, 60);
        jb1.setBackground(Color.magenta);
        jb1.setFont(new Font("New Times Roman", Font.BOLD, 18));
        jb1.setForeground(Color.WHITE);
        jb2.setBackground(Color.magenta);
        jb2.setForeground(Color.WHITE);
        jb2.setFont(new Font("New Times Roman", Font.BOLD, 18));
        jb3.setBackground(Color.magenta);
        jb3.setForeground(Color.WHITE);
        jb3.setFont(new Font("New Times Roman", Font.BOLD, 18));

        // Ajout des boutons selon le gestionnaire de placement
        activite.add(jb1);
        activite.add(jb2);
        activite.add(jb3);

        ///////////////////////////////////////////////////////
        //Niveaux
        niveau = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon m = new ImageIcon(getClass().getResource("/Ressource/fond_niveaux.jpg"));
                Image monImage = m.getImage();
//                monImage.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
                g.drawImage(monImage, 0, 0, 1300, 900, null);
            }
        };
        niveau.setLayout(null);
        niveau.setBackground(Color.YELLOW);
        JButton niv1 = new JButton("NIVEAU 1");
        JButton niv2 = new JButton("NIVEAU 2");
        //Ajout des boutons selon le gestionnaire de placement pour les niveaux
        niveau.add(niv1);
        niveau.add(niv2);
        // emplacement des boutons
        niv1.setBounds(350, 250, 200, 60);
        niv2.setBounds(750, 250, 200, 60);
        niv1.setBackground(Color.magenta);
        niv2.setBackground(Color.magenta);
        niv1.setForeground(Color.WHITE);
        niv1.setFont(new Font("New Times Roman", Font.BOLD, 18));
        niv2.setForeground(Color.WHITE);
        niv2.setFont(new Font("New Times Roman", Font.BOLD, 18));

        admin = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon m = new ImageIcon(getClass().getResource("/Ressource/fond_admin.jpg"));
                Image monImage = m.getImage();
                g.drawImage(monImage, 0, 0, 1290, 700, null);
            }
        };
        admin.setBackground(Color.lightGray);

        admin.setLayout(new FlowLayout());

        JLabel label = new JLabel("mot de passe");
        label.setPreferredSize(new Dimension(150, 50));
        admin.add(label);

        JPasswordField textField = new JPasswordField(10);

//        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 30));
        admin.add(textField);
//        textField.setColumns(10); //On lui donne un nombre de colonnes à afficher

        JButton boutonAdmin = new JButton("Entrer");
        boutonAdmin.setPreferredSize(new Dimension(100, 50));
        boutonAdmin.setFont(new Font("New Times Roman", Font.BOLD, 18));
        boutonAdmin.setBackground(Color.MAGENTA);
        boutonAdmin.setForeground(Color.WHITE);
        admin.add(boutonAdmin);

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
                activite.remove(jb1);
                activite.remove(jb2);
                activite.remove(jb3);
                activite.setVisible(false);
                Dessin monDessin = new Dessin();
                BorderLayout layoutActivite = new BorderLayout();
                activite.setLayout(layoutActivite);
                activite.add(monDessin);
            }
        });

        // action sur le bouton calcul
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activite.remove(jb1);
                activite.remove(jb2);
                activite.remove(jb3);
                activite.setVisible(false);
                Calcul monCalcul = new Calcul();
                BorderLayout layoutCalcul = new BorderLayout();
                activite.setLayout(layoutCalcul);
                activite.add(monCalcul);
            }
        });

        // action sur le bouton Questions Reponses
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activite.remove(jb1);
                activite.remove(jb2);
                activite.remove(jb3);
                activite.setVisible(false);
                Visuel monVisuel = new Visuel();
                BorderLayout layoutVisuel = new BorderLayout();
                activite.setLayout(layoutVisuel);
                activite.add(monVisuel);
            }
        });

        boutonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //utilisation DAO pour la page administration
                DAOAdministration daoad = new DAOAdministration();
                Admin admi = daoad.find(1);
                String mypwd = String.valueOf(textField.getPassword());
                if (encryptThisString(mypwd).equals(admi.getPass())) { // comparaison du mot de passe user et celui en bdd
                    //si ok acces page admin
                    admin.remove(boutonAdmin);
                    admin.remove(textField);
                    admin.remove(label);
                    admin.setVisible(false);
                    AdminPage adminPage = new AdminPage();
                    //                BoxLayout layoutAdmin = new BoxLayout(admin, BoxLayout.Y_AXIS);
                    //                admin.setLayout(layoutAdmin);
                    admin.add(adminPage);
                } else {
                    JOptionPane.showMessageDialog(admin, "Le mot de passe est erroné", "ERREUR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        jf.setContentPane(tp);
        jf.setResizable(false);
        jf.setBackground(Color.WHITE);
        jf.setVisible(true);
    }

    public static String encryptThisString(String input) {
        try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            // return the HashText
            return hashtext;
        } // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
