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
    private final String[] op = {"+", "-"};
    private final String[] op2 = {"+", "-", "x"};
    private String signe;
    private int nombreQuestion = 0;
  //  private int bonneReponse = 0;
    JLabel operation;
    JPanel panel;
    JPanel allPanel;
    Random rm = new Random();

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
        panel.setOpaque(false);//fait en sorte de voir le fond d'écran
      
        panel.setPreferredSize(new Dimension(1300, 650));

        //Déclaration des variables
        num1 = rm.nextInt(MAX_NUM);
        num2 = rm.nextInt(MAX_NUM);
        signe = op[rm.nextInt(op.length)];

        Result();
//***********************************************************************************************************
//***********************************************************************************************************
        //titre
        JLabel reponse = new JLabel("CALCUL MENTAL", SwingConstants.CENTER);
        reponse.setFont(new Font("Serif", Font.ITALIC, 40));
        GridBagConstraints tailleCase = new GridBagConstraints();
        tailleCase.fill = GridBagConstraints.BOTH;
        tailleCase.weightx = 1;
        tailleCase.weighty = 1;
        tailleCase.gridwidth = 1;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 1;
        panel.add(reponse, tailleCase);
//***********************************************************************************************************
//***********************************************************************************************************
        //calcul
        operation.setFont(new Font("Serif", Font.ITALIC, 38));
        tailleCase.fill = GridBagConstraints.BOTH;
        tailleCase.gridwidth = 1;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 2;
        panel.add(operation, tailleCase);

//***********************************************************************************************************
//***********************************************************************************************************
// verification
        correction.setFont(new Font("Serif", Font.ITALIC, 30));
        tailleCase.fill = GridBagConstraints.BOTH;
        tailleCase.gridwidth = 1;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 3;
        panel.add(correction, tailleCase);

//***********************************************************************************************************
//***********************************************************************************************************
// reponse
        JTextField rep = new JTextField();
        
        rep.setFont(new Font("Serif", Font.PLAIN, 20));
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
        tailleCase.fill = GridBagConstraints.RELATIVE;
        tailleCase.gridwidth = 1;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 7;
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
        JButton niveau1 = new JButton("Autre Calcul Niveau 1");
        tailleCase.fill = GridBagConstraints.REMAINDER;
        tailleCase.gridwidth = 1;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 8;
        panel.add(niveau1, tailleCase);
        niveau1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                num1 = rm.nextInt(MAX_NUM);
                num2 = rm.nextInt(MAX_NUM);
                signe = op[rm.nextInt(op.length)];
                Result();
                operation.setText(num1 + signe + num2);
                correction.setText(null);
                rep.setText(null);
               
            }
        });
        JButton niveau2 = new JButton("Autre Calcul Niveau 2");
        tailleCase.fill = GridBagConstraints.ABOVE_BASELINE;
        tailleCase.gridwidth = 1;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 11;
        panel.add(niveau2, tailleCase);
        niveau2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                num1 = rm.nextInt(999);
                num2 = rm.nextInt(999);
                signe = op2[rm.nextInt(op2.length)];
                Result2();
                operation.setText(num1 + signe + num2);
                correction.setText(null);
                rep.setText(null);
               
            }
        });
        
        JButton quitter = new JButton("Quitter");
        tailleCase.fill = GridBagConstraints.ABOVE_BASELINE;
        tailleCase.gridwidth = 1;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 12;
        panel.add(quitter, tailleCase);
        quitter.addActionListener(new ActionListener() {
            @Override
                    public void actionPerformed(ActionEvent ae) {
           Onglets onglet = new Onglets();
               
            }
        });


//***********************************************************************************************************
//***********************************************************************************************************
//bouton vérifier
        JButton verif = new JButton("Vérifier");
        tailleCase.fill = GridBagConstraints.WEST;
        tailleCase.gridwidth = 1;
        tailleCase.gridheight = 1;
        tailleCase.gridx = 1;
        tailleCase.gridy = 6;
        panel.add(verif, tailleCase);
        verif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           //     Result();
           //     Result2();

                if (rep.getText().equals(String.valueOf(resultat))) {
                   
                    correction.setForeground(Color.green);
                    correction.setText("Bonne Réponse!!!");
                    rep.setText("");
                    nombreQuestion ++;
                //    bonneReponse ++;

                    rep.requestFocus();
                    if (nombreQuestion == 5) {
 //                       System.out.println(bonneReponse);
//                        System.exit(0);
//                        Onglets onglet = new Onglets();
                    }
                } else {
                    correction.setForeground(Color.red);
                    correction.setText("Dommage: Mauvaise Réponse!");
                    rep.setText("");
                    rep.requestFocus();
                    nombreQuestion ++;
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
                if (num2 > num1) {
                    int tampon = num1;
                    num1 = num2;
                    num2 = tampon;
                } else {
                    resultat = num1 - num2;
                }
                break;
        }
    }

    private void Result2() {
        operation.setText(num1 + signe + num2);

        switch (signe) {
            case "+":
                resultat = num1 + num2;
                break;
            case "-":
                resultat = num1 - num2;
                break;
            case "x":
                num1 = rm.nextInt(MAX_NUM);
                num2 = rm.nextInt(MAX_NUM);
                resultat = num1 * num2;
                break;
        }
    }
}
