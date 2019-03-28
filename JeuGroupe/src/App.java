
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class App {

    private static final int MAX_NUM = 10;
    private int num1, num2, resultat;
    private final String[] op = {"+", "-", "x"};
    private String signe;
    private int nombreQuestion = 0;

    private final Random rm = new Random();

    private JFrame frame;
    private final JLabel operation = new JLabel("", SwingConstants.CENTER);
    private final JLabel correction = new JLabel("", SwingConstants.CENTER);

    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                App window = new App();
                window.frame.setVisible(true);
            } catch (Exception e) {
            }
        });
    }

    /**
     * Création application
     */
    public App() {
        initialize();
    }

    /**
     * creation fenetre
     */
    private void initialize() {
        //Déclaration des variables

        num1 = rm.nextInt(MAX_NUM);
        num2 = rm.nextInt(MAX_NUM);
        signe = op[rm.nextInt(op.length)];

        //Création de la fenêtre principale
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Fermeture du programme à la fermeture de la fenetre
        GridBagLayout gridBagLayout = new GridBagLayout();//Création d'une grille
        frame.getContentPane().setLayout(gridBagLayout);
        ResultNiv1();
//***********************************************************************************************************
//***********************************************************************************************************
        JLabel reponse = new JLabel("CALCUL MENTAL", SwingConstants.CENTER);
        reponse.setFont(new Font("Serif", Font.PLAIN, 40));
        GridBagConstraints tailleCase = new GridBagConstraints();
        tailleCase.fill = GridBagConstraints.BOTH;
        tailleCase.weightx = 1;
        tailleCase.weighty = 1;
        tailleCase.gridwidth = 3;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 1;
        frame.getContentPane().add(reponse, tailleCase);

//***********************************************************************************************************
//***********************************************************************************************************
        operation.setFont(new Font("Serif", Font.PLAIN, 20));
        tailleCase.fill = GridBagConstraints.BOTH;
        tailleCase.gridwidth = 3;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 2;
        frame.getContentPane().add(operation, tailleCase);

//***********************************************************************************************************
//***********************************************************************************************************        
        correction.setFont(new Font("Serif", Font.PLAIN, 20));
        tailleCase.fill = GridBagConstraints.BOTH;
        tailleCase.gridwidth = 3;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 3;
        frame.getContentPane().add(correction, tailleCase);

//***********************************************************************************************************
//***********************************************************************************************************
        JTextField rep = new JTextField();
        rep.setHorizontalAlignment(JTextField.CENTER);
        rep.requestFocus();
        tailleCase.fill = GridBagConstraints.BOTH;
        tailleCase.gridwidth = 1;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 4;
        frame.getContentPane().add(rep, tailleCase);

//***********************************************************************************************************
//***********************************************************************************************************
        JButton solu = new JButton("Solution");
        tailleCase.fill = GridBagConstraints.BOTH;
        tailleCase.gridwidth = 1;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 6;
        frame.getContentPane().add(solu, tailleCase);
        solu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                rep.setText(Integer.toString(resultat));

            }
        });

//***********************************************************************************************************
//***********************************************************************************************************
        JButton autre = new JButton("Autre Calcul");
        tailleCase.fill = GridBagConstraints.BOTH;
        tailleCase.gridwidth = 1;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 7;
        frame.getContentPane().add(autre, tailleCase);
        autre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                num1 = rm.nextInt(MAX_NUM);
                num2 = rm.nextInt(MAX_NUM);
                signe = op[rm.nextInt(op.length)];
                String.valueOf(num1);
                String.valueOf(num2);
                ResultNiv1();
                operation.setText(num1 + signe + num2);
                correction.setText(null);
                rep.setText(null);

            }
        });

//***********************************************************************************************************
//***********************************************************************************************************
        JButton verif = new JButton("Vérifier");
        tailleCase.fill = GridBagConstraints.BOTH;
        tailleCase.gridwidth = 1;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 5;
        frame.getContentPane().add(verif, tailleCase);
        verif.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ResultNiv1();

                if (rep.getText().equals(String.valueOf(resultat))) {
                    nombreQuestion++;
                    correction.setForeground(Color.green);
                    correction.setText("Bonne Réponse!!!");
                    rep.setText("");

                    rep.requestFocus();
                    if (nombreQuestion == 5) {

                        System.exit(0);
                    }
                } else {
                    correction.setForeground(Color.red);
                    correction.setText("Dommage: Mauvaise Réponse!");
                    rep.setText("");
                    rep.requestFocus();
                }
            }

        });

    }
    //Initialisation des opérations

    private void ResultNiv1() {
        operation.setText(num1 + signe + num2);

        switch (signe) {
            case "+":
                resultat = num1 + num2;
                break;
            case "-":
                if(num2 > num1){
                    int tampon = num1;
                    num1 = num2;
                    num2 = tampon;
                }else
                resultat = num1 - num2;
                break;
            case "x":
                resultat = num1 * num2;
                break;
        }

    }
}
