package enfants;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author Marine Veyssiere
 */
public class Dessin extends JPanel {

    int x = 0, y = 0;

    JLabel label = new JLabel();
    JPanel dessin = new JPanel();
    JPanel buttons = new JPanel();
    JPanel colorButtons = new JPanel();
    JPanel formButtons = new JPanel();
    JButton rouge, vert, bleu, autre, carre, cercle, effacer, quitter;

    int tailleCurseur = 3;
    Color couleur = Color.BLACK; //initialisation de la couleur du stylo en noir.
    int shape = 0; // pour choisir la forme du stylo

    public Dessin() {
//
//        dessin = new JPanel() {
//            Toolkit toolkit = Toolkit.getDefaultToolkit();
//            Image image = toolkit.getImage("/Ressource/Pencil.png");
//            Cursor c = toolkit.createCustomCursor(image, new Point(dessin.getX(),dessin.getY()), "");
//            setCursor(c);
//        };
        dessin.setSize(1300, 700);
        dessin.setBackground(Color.white);

        this.addMouseListener(new MyMouseMotionListener());
        this.addMouseMotionListener(new MyMouseMotionListener());

        //couleurs
        JPanel colors = new JPanel();
        JColorChooser chooseColor = new JColorChooser();
        colors.add(chooseColor);

        // Création des bouttons
        BoxLayout buttonsLayout = new BoxLayout(buttons, BoxLayout.Y_AXIS);
        buttons.setLayout(buttonsLayout);

        GridLayout gridcolorButtons = new GridLayout(5, 1, 10, 10);
        colorButtons.setLayout(gridcolorButtons);

        rouge = new JButton("Rouge");
        rouge.setBackground(Color.red);
        rouge.setFont(new Font("New Times Roman", Font.BOLD, 16));
        //Event: modification de la couleur du stylo après avoir cliqué sur le bouton
        rouge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                couleur = Color.red;
            }
        });
        colorButtons.add(rouge);

        vert = new JButton("Vert");
        vert.setBackground(Color.green);
        vert.setFont(new Font("New Times Roman", Font.BOLD, 16));
        vert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                couleur = Color.green;
            }
        });
        colorButtons.add(vert);

        bleu = new JButton("Bleu");
        bleu.setBackground(Color.cyan);
        bleu.setFont(new Font("New Times Roman", Font.BOLD, 16));
        bleu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                couleur = Color.cyan;
            }
        });
        colorButtons.add(bleu);

        autre = new JButton("+ de couleurs");
        autre.setFont(new Font("New Times Roman", Font.BOLD, 16));
        autre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(dessin, "Choisissez une couleur", couleur);
                if (newColor != null) {
                    couleur = newColor;
                }
            }
        });
        colorButtons.add(autre);

        //slider taille crayon
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 1, 30, 10); //slide horizontale
        slider.setMajorTickSpacing(5); // traits d'espacement sur la slide
        slider.setMinorTickSpacing(1); // petits traits d'espacement sur la slide

        slider.setPaintTicks(true);
        slider.setPaintLabels(true); //afficher les labels sur la slide
        //label pour les positions
        Hashtable position = new Hashtable();
        position.put(1, new JLabel("0"));
        position.put(5, new JLabel("5"));
        position.put(10, new JLabel("10"));
        position.put(15, new JLabel("15"));
        position.put(20, new JLabel("20"));
        position.put(25, new JLabel("25"));
        position.put(29, new JLabel("30"));
        slider.setLabelTable(position);  //affichage des labels sur la slide
        //creation de l'action sur la slide
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                tailleCurseur = slider.getValue();
            }
        });

        colorButtons.add(slider);


        // bordure pour distinger les boutons couleurs des boutons formes ...
        colorButtons.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        colorButtons.setBorder(BorderFactory.createTitledBorder("Couleurs"));

        GridLayout gridformButtons = new GridLayout(3, 1, 4, 4);
        formButtons.setLayout(gridformButtons);

        carre = new JButton("Carré");
        carre.setBackground(Color.PINK);
        carre.setFont(new Font("New Times Roman", Font.BOLD, 16));
        carre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = 1; // mettre la forme du stylo en carré
            }
        });

        formButtons.add(carre);

        cercle = new JButton("Cercle");
        cercle.setBackground(Color.PINK);
        cercle.setFont(new Font("New Times Roman", Font.BOLD, 16));
        cercle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = 2; // mettre la forme du stylo en carré
            }
        });
        formButtons.add(cercle);

        formButtons.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        formButtons.setBorder(BorderFactory.createTitledBorder("Formes"));

        JPanel effaceButtons = new JPanel(new GridLayout(2, 1, 4, 4));

        effacer = new JButton("Effacer");
        effacer.setBackground(Color.lightGray);
        effacer.setFont(new Font("New Times Roman", Font.BOLD, 16));
        effacer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                couleur = Color.BLACK;
                shape = 0; // forme du stylo par défaut
            }
        });

        effaceButtons.add(effacer);

        quitter = new JButton("Quitter");
        quitter.setBackground(Color.lightGray);
        quitter.setFont(new Font("New Times Roman", Font.BOLD, 16));
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Onglets onglet = new Onglets();
            }
        });

        effaceButtons.add(quitter);

        // integration des trois panels de boutons au panel boutons général
        buttons.add(colorButtons);
        buttons.add(formButtons);
        buttons.add(effaceButtons);
//        buttons.add(colors);
        //Container pour placer les elements: la feuille de dessin et les boutons de choix de couleur du stylo
        setLabel(x, y);
        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.SOUTH);
        this.add(dessin, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.EAST);
    }

    private void setLabel(int x, int y) {
        label.setText("x = " + x + " y = " + y);
    }

    public class MyMouseMotionListener extends MouseAdapter {

        @Override
        public void mouseDragged(MouseEvent e) {
            Graphics g = dessin.getGraphics();
            paintComponent(g);
            g.drawLine(x, y, e.getX(), e.getY());
            x = e.getX();
            y = e.getY();
            setLabel(x, y);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();
            setLabel(x, y);
        }
    }

    //utilisé pour changer la couleur/forme du stylo pour dessiner
    public void paintComponent(Graphics g) {
        Graphics2D line = (Graphics2D) g;
        line.setColor(couleur);
//        Cursor curseur = new Cursor(Cursor.HAND_CURSOR);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        ImageIcon m = new ImageIcon(getClass().getResource("/Ressource/Pencil.png"));
        Image image = m.getImage();
        Image scaledImage = image.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        Cursor c2 = toolkit.createCustomCursor(scaledImage, new Point(0, 30), "");
        line.setStroke(new BasicStroke(tailleCurseur)); // appliquer la taille du brush guidé par le slider sur le pinceau

        dessin.setCursor(c2);

        switch (shape) {
            case 1:
                line.drawRect(x, y, 15, 15); //forme carrée
                break;
            case 2:
                line.drawOval(x, y, 15, 15); //forme circulaire
                break;
            default:
                break;
        }
    }
}
