package enfants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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

    Color couleur = Color.BLACK; //initialisation de la couleur du stylo en noir.
    int shape = 0; // pour choisir la forme du stylo

    public Dessin() {

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
                e.getActionCommand();
                Component source = (Component) e.getSource();
                Container parent = source.getParent();// will give the container of the button
                do {
                    parent = parent.getParent();
                } while (!(parent instanceof JTabbedPane));
                JTabbedPane tabbedPane = (JTabbedPane) parent;
                tabbedPane.setSelectedIndex(0);
//                label.removeAll();
//                dessin.removeAll();
//                buttons.removeAll();
//                parent.getClass();
//                parent.getLayout();

//                Onglets onglet = new Onglets();
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
        Cursor curseur = new Cursor(Cursor.HAND_CURSOR);
        this.setCursor(curseur);
        // test pour mettre une image de stylo à la place du curseur
//        Toolkit toolkit = getToolkit();
//        Image cursorImage = new ImageIcon("ressources/Pencil.png").getImage();
//        Point cursorHotSpot = new Point(dessin.getX(), dessin.getY());
//        Cursor lightPenCursor = toolkit.createCustomCursor(cursorImage, cursorHotSpot, "lightPenCursor");
//        this.setCursor(lightPenCursor); //Within a JPanel

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
