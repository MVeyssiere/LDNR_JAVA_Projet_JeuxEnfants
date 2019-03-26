package enfants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Marine Veyssiere
 */
public class Dessin extends JFrame {

    int x = 0, y = 0;
    JPanel container = new JPanel();
    JLabel label = new JLabel();
    JPanel dessin = new JPanel();
    JPanel buttons = new JPanel();
    JPanel colorButtons = new JPanel();
    JPanel formButtons = new JPanel();
    JButton rouge, vert, bleu, carre, cercle, effacer;
    Color couleur = Color.BLACK; //initialisation de la couleur du stylo en noir.
    int shape = 0; // pour choisir la forme du stylo

    public Dessin() {
        dessin.setBackground(Color.white);

        container.addMouseListener(new MyMouseMotionListener());
        container.addMouseMotionListener(new MyMouseMotionListener());

        // Création des bouttons
//        JPanel buttons = new JPanel(new GridLayout(8, 1, 5, 5));
        BoxLayout buttonsLayout = new BoxLayout(buttons, BoxLayout.Y_AXIS);
        buttons.setLayout(buttonsLayout);

        GridLayout gridcolorButtons = new GridLayout(8, 1, 10, 10);
//        BoxLayout colorButtonsLayout = new BoxLayout(colorButtons, BoxLayout.Y_AXIS);
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

        colorButtons.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
        colorButtons.setBorder(BorderFactory.createTitledBorder("Couleurs"));

//        BoxLayout formButtonsLayout = new BoxLayout(formButtons, BoxLayout.Y_AXIS);
        GridLayout gridformButtons = new GridLayout(8, 1, 10, 10);
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

        JPanel effaceButtons = new JPanel(new GridLayout(8, 1, 10, 10));

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

        // integration des trois panels de boutons au panel boutons général
        buttons.add(colorButtons);
        buttons.add(formButtons);
        buttons.add(effaceButtons);
        //Container pour placer les elements: la feuille de dessin et les boutons de choix de couleur du stylo
        setLabel(x, y);
        container.setLayout(new BorderLayout());
        container.add(label, BorderLayout.SOUTH);
        container.add(dessin, BorderLayout.CENTER);
        container.add(buttons, BorderLayout.EAST);

        this.setTitle("Dessinons");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(container);
        this.setVisible(true);
    }

    private void setLabel(int x, int y) {
        label.setText("x=" + x + "y=" + y);
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
        switch (shape) {
            case 1:
                line.drawRect(x, y, 6, 6); //forme carrée
                break;
            case 2:
                line.drawOval(x, y, 6, 6); //forme circulaire
                break;
            default:
                break;
        }
    }

}
