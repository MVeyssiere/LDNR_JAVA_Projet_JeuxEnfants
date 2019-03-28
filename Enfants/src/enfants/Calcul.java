package enfants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author Thomas Géhant
 */
public class Calcul extends JPanel {

    private static final int MAX_NUM = 10;
    private int num1, num2, resultat;
    private final String[] op = {"+", "-", "x"};
    private String signe;
    private int nombreQuestion = 0;
    JLabel operation;
    JPanel panel;
    JPanel allPanel;

    public Calcul() {
        allPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon m = new ImageIcon(getClass().getResource("/Ressource/fond_jeu1.jpg"));
                Image monImage = m.getImage();
//                monImage.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
                g.drawImage(monImage, 0, 0, 1300, 900, null);
            }
        };

        operation = new JLabel("", SwingConstants.CENTER);
        JLabel correction = new JLabel("", SwingConstants.CENTER);

        panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
//        panel.setBackground(Color.green);
        panel.setPreferredSize(new Dimension(500, 600));

        Random rm = new Random();
        //Déclaration des variables
        num1 = rm.nextInt(MAX_NUM);
        num2 = rm.nextInt(MAX_NUM);
        signe = op[rm.nextInt(op.length)];

        Result();
//***********************************************************************************************************
//***********************************************************************************************************
        //titre
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
        panel.add(reponse, tailleCase);
//***********************************************************************************************************
//***********************************************************************************************************
        //calcul
        operation.setFont(new Font("Serif", Font.PLAIN, 28));
        tailleCase.fill = GridBagConstraints.BOTH;
        tailleCase.gridwidth = 3;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 2;
        panel.add(operation, tailleCase);

//***********************************************************************************************************
//***********************************************************************************************************
// verification
        correction.setFont(new Font("Serif", Font.PLAIN, 20));
        tailleCase.fill = GridBagConstraints.BOTH;
        tailleCase.gridwidth = 3;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 3;
        panel.add(correction, tailleCase);

//***********************************************************************************************************
//***********************************************************************************************************
// reponse
        JTextField rep = new JTextField();
        rep.setHorizontalAlignment(JTextField.CENTER);
        rep.requestFocus();
        tailleCase.fill = GridBagConstraints.BOTH;
        tailleCase.gridwidth = 1;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 4;
        panel.add(rep, tailleCase);

//***********************************************************************************************************
//***********************************************************************************************************
//solution
        JButton solu = new JButton("Solution");
        tailleCase.fill = GridBagConstraints.BOTH;
        tailleCase.gridwidth = 1;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 6;
        panel.add(solu, tailleCase);
        solu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                rep.setText(Integer.toString(resultat));
            }
        });

//***********************************************************************************************************
//***********************************************************************************************************
//bouton autre calcul
        JButton autre = new JButton("Autre Calcul");
        tailleCase.fill = GridBagConstraints.BOTH;
        tailleCase.gridwidth = 1;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 7;
        panel.add(autre, tailleCase);
        autre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                num1 = rm.nextInt(MAX_NUM);
                num2 = rm.nextInt(MAX_NUM);
                signe = op[rm.nextInt(op.length)];
//                String.valueOf(num1);
//                String.valueOf(num2);
                Result();
                operation.setText(num1 + signe + num2);
                correction.setText(null);
                rep.setText(null); //pour vider le textfield de la réponse
            }
        });

//***********************************************************************************************************
//***********************************************************************************************************
//boutton vérifier
        JButton verif = new JButton("Vérifier");
        verif.setPreferredSize(new Dimension(100, 80));
        tailleCase.fill = GridBagConstraints.BOTH;
        tailleCase.gridwidth = 1;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 5;
        panel.add(verif, tailleCase);
        verif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (signe) {
                    case "+":
                        resultat = num1 + num2;
                        break;
                    case "-":
                        resultat = num1 - num2;
                        break;
                    case "x":
                        resultat = num1 * num2;
                        break;
                }

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

        allPanel.add(panel, BorderLayout.CENTER);
        this.add(allPanel);
    }

    //Initialisation des opérations
    private void Result() {
        operation.setText(num1 + " " + signe + " " + num2);

        switch (signe) {
            case "+":
                resultat = num1 + num2;
                break;
            case "-":
                resultat = num1 - num2;
                break;
            case "x":
                resultat = num1 * num2;
                break;
        }
    }
}
