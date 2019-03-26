package jeu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class CalculMental extends JFrame implements ActionListener, KeyListener {

    private final JTextField result;

    private final JLabel operation;

    private final JLabel message;

    private final JLabel title;

    private final Bouton addition = new Bouton(this, "Niveau 1 ");
    private final Bouton soustraction = new Bouton(this, "Niveau 2 ");
    private final Bouton multiplication = new Bouton(this, "Niveau 3 ");
    private final Bouton division = new Bouton(this, "Niveau 4 ");
    private final Bouton ok;

    private final Bouton solution = new Bouton(this, "Solution");
    private final Bouton autreCalcul = new Bouton(this, "Autre Calcul");

    private final JMenuBar menuBar;
    private final JMenuItem menuNiveau;

    private Operation op = new Addition(addition, soustraction, multiplication, division);

    /**
     * Creation de la fenetre principale
     */
    public CalculMental() {

        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// fermeture du programme à l'issue

        Panneau contentPane = new Panneau(this, new BorderLayout());// Panneau pour calibrer la fenetre
        contentPane.setBorder(new EmptyBorder(100, 50, 100, 50));

//****************************************************************
        menuBar = new JMenuBar();

        JMenu menuFichier = new JMenu("Fichier");

        menuNiveau = new JMenuItem("Niveau");

        menuNiveau.addActionListener(this);

        menuFichier.add(menuNiveau);
        menuFichier.addSeparator();

        menuBar.add(menuFichier);
        setJMenuBar(menuBar);

//**********************************************************
//***container calcul**********************************************************
        Panneau calcul = new Panneau(contentPane, new GridLayout(3, 1), BorderLayout.NORTH);

        // head 
        Panneau head = new Panneau(calcul, new GridLayout(1, 3));

        Panneau sco = new Panneau(head, new FlowLayout(FlowLayout.CENTER));
        sco.add(title = new JLabel("Calcul mental"));
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 45));

        // operation
        Panneau oper = new Panneau(calcul, new FlowLayout(FlowLayout.RIGHT));
        oper.setPreferredSize(new Dimension(300, 60));
        oper.setBackground(new Color(190, 230, 255));

        oper.add(operation = new JLabel(op.getOperation()));
        operation.setFont(new Font("Helvetica", Font.PLAIN, 40));

        oper.add(result = new JTextField());
        result.setFont(new Font("Helvetica", Font.PLAIN, 40));
        result.setColumns(3);//largeur de la case pour rentrer la réponse

        oper.add(ok = new Bouton(this, "OK"));
        ok.setPreferredSize(new Dimension(100, 50));

        // message
        Panneau mess = new Panneau(calcul, new FlowLayout(FlowLayout.CENTER));
        mess.add(message = new JLabel(""));

//****************************************************<fin> container calcul***
//***container levels**********************************************************	
        Panneau levels = new Panneau(contentPane, new BorderLayout(), BorderLayout.CENTER);
        levels.setPreferredSize(new Dimension(240, 230));

        Panneau boutNiv = new Panneau(levels, new GridLayout(1, 4), BorderLayout.WEST);
        boutNiv.add(addition);
        boutNiv.add(soustraction);
        boutNiv.add(multiplication);
        boutNiv.add(division);

        Panneau options = new Panneau(contentPane, new BorderLayout(), BorderLayout.CENTER);
        options.setPreferredSize(new Dimension(300, 300));

        Panneau boutOpt = new Panneau(options, new GridLayout(1, 4), BorderLayout.SOUTH);

        boutOpt.add(solution);
        solution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                okAction(true);

            }
        });
        boutOpt.add(autreCalcul);
        autreCalcul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               autreCalcul();
            }
        });
    }

// Implementation des methodes ActionListener
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == menuNiveau) {
            restart();
        } else if (source == addition) {
            op = new Addition(addition, soustraction, multiplication, division);
            this.okAction(false);
        } else if (source == soustraction) {
            op = new Soustraction(addition, soustraction, multiplication, division);
            this.okAction(false);
        } else if (source == multiplication) {
            op = new Multiplication(addition, soustraction, multiplication, division);
            this.okAction(false);
        } else if (source == division) {
            op = new Division(addition, soustraction, multiplication, division);
            this.okAction(false);
        } else if (source == ok) {
            this.okAction(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_ENTER)) {
            this.okAction(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void okAction(boolean t) {  // Opérations par défaut lorsqu'un bouton est pressé
        if (t) {
            try {
                int res = Integer.parseInt(result.getText());
                String txt = new String();
                if (op.checkResult(res)) {
                    txt = "Bravo! ta réponse est juste.";
                } else {
                    txt = "Désolé, mais ta réponse est fausse, recommence.";
                }
                message.setText(txt);
            } catch (Exception e) {
                message.setText("");
            }
        }
    }

    public void autreCalcul() {

        // Propose une nouvelle équation
        if (op.getStatut()) {
            op.setOperation();
        } else {
            swapOperations();
        }

        operation.setText(op.getOperation());				// affiche la nouvelle opération
        result.setText(null);								// vide la zone de résultat
        result.grabFocus();									// place le curseur dans la zone de résultat

    }

    public void swapOperations() {
        if (op.getAddStatut()) {
            op = new Addition(addition, soustraction, multiplication, division);
            this.okAction(false);
        } else if (op.getSousStatut()) {
            op = new Soustraction(addition, soustraction, multiplication, division);
            this.okAction(false);
        } else if (op.getMultStatut()) {
            op = new Multiplication(addition, soustraction, multiplication, division);
            this.okAction(false);
        } else if (op.getDivStatut()) {
            op = new Division(addition, soustraction, multiplication, division);
            this.okAction(false);
        } else {
            addition.setButtonInactive();
            soustraction.setButtonInactive();
            multiplication.setButtonInactive();
            division.setButtonInactive();
            operation.setText("");
            message.setText("Partie terminée, bravo");
        }
    }

    public void restart() {
        op.reset();
        okAction(false);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CalculMental frame = new CalculMental();
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
