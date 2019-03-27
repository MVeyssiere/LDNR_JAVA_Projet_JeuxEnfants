/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphique;

import Beans.Question;
import enfants.JeuQuestion;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Julien Modena
 */
public class Visuel extends JPanel{
       String a = null;
       JFrame jf = new JFrame();
       JPanel global = new JPanel();
       JPanel bouton = new JPanel();
       JPanel reponse = new JPanel();
       Box content= new Box(BoxLayout.PAGE_AXIS);
        // Gestion de la reponse
        
        JLabel question = new JLabel(a);
        
                // Creation des boutons
        JButton verifier = new JButton("Verifier");
        JButton solution = new JButton("Solution");
        JButton autre = new JButton("Autre Question");
        JeuQuestion test = new JeuQuestion();
        JTextField rep = new JTextField(30);
        JLabel ti = new JLabel("Réponse : ");
        
    /**
     * Constructor
    
     */
    public Visuel() {
        
        initGUI();
           // Gestion de la reponse
        reponse.setLayout(new GridLayout(0,2));
       
        reponse.add(ti);
        reponse.add(rep);

        // ajout de la question et de la réponse en notre composant
        content.add(question);
        content.add(reponse);
        //ajout des bouton au panel regroupant les boutons
        bouton.add(verifier);
        bouton.add(solution);
        bouton.add(autre);
        
        // ajout des différents panel au panel général
        global.add(bouton,BorderLayout.SOUTH);
        global.add(content,BorderLayout.CENTER);
        jf.add(global);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermeture
        jf.setResizable(false); // Fenêtre non-redimensionnable
        jf.pack(); // Ajustement de la taille au contenu
        // On positionne la fenêtre au milieu de l'écran
        jf.setLocationRelativeTo(null);
        jf.setVisible(true); // Affichage de la fenêtre
       
    }
    private void initGUI(){
        
       
        Question f = test.poserquestion(1);
        System.out.println("f = " + f);
        System.out.println("f = " + f.getReponse());
       
       
          // ajout de la question a un JLabel pour l'afficher ensuite
         String quest = f.getQuestion();
         System.out.println("quest = " + quest);
         
         question.setText(quest);
         
     
        
        // ajout des listener pour chaque bouton
        verifier.addActionListener((ActionEvent e) -> {
            String verif = rep.getText();
            verif.toLowerCase();
            String reponsedb = f.getReponse();
            String repo = reponsedb.toLowerCase();
            System.out.println("repo = " + repo);
            System.out.println("f.getReponse() = " + f.getReponse());
            
            if(verif.equals(repo))
            {
                JOptionPane.showMessageDialog(null, "Bravo vous avez trouvé la bonne réponse "
                        ,"Verification", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Quel dommage !!! la bonne réponse était "
                        +f.getReponse(),"Verification", JOptionPane.WARNING_MESSAGE);
            }
        });
        
        solution.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(null, "la solution de la reponse est  "
                    +f.getReponse(), "Solution", JOptionPane.INFORMATION_MESSAGE);
        });
        
        
        autre.addActionListener((ActionEvent e) -> {
            initGUI();
        });
        
   
        
    }
}
