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
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
       JLabel soluce = new JLabel();
       
       Box content= new Box(BoxLayout.PAGE_AXIS);
        // Gestion de la reponse
        
        JLabel question = new JLabel(a);
        
                // Creation des boutons
        
        JButton verifier = new JButton("Verifier");
        JButton solution = new JButton("Solution");
        JButton autre = new JButton("Autre Question");
////      
        JTextField rep = new JTextField(30);
        JLabel ti = new JLabel("Réponse : ");
        
    /**
     * Constructor
    
     */
    public Visuel() {
        
        initGUI();
           // Gestion de la reponse
        reponse.setLayout(new GridLayout(3,0));
        reponse.add(soluce);
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

        global.add(content,BorderLayout.CENTER);
        global.add(bouton,BorderLayout.SOUTH);
        jf.add(global);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermeture
        //jf.setResizable(false); // Fenêtre non-redimensionnable
        jf.pack(); // Ajustement de la taille au contenu
        // On positionne la fenêtre au milieu de l'écran
////        jf.setLocationRelativeTo(null);
        jf.setVisible(true); // Affichage de la fenêtre
       
    }
    private void initGUI(){
        
       
        Question f = new Question();

        System.out.println("f = " + f.toString());
        
       
       
          // ajout de la question a un JLabel pour l'afficher ensuite
         String quest = f.getQuestion();
         System.out.println("quest = " + quest);
         
         question.setText(quest);
         
     
        
        // ajout des listener pour chaque bouton
        verifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String verif= rep.getText();
                
                String reponsedb = f.getReponse();
                String repo ;
                
                if(isANumber(reponsedb)==true && isANumber(verif)==true)
                {   verif = verif;
                    repo = reponsedb;
                }
                else{
                    repo = reponsedb.toLowerCase();
                    verif  = verif.toLowerCase();
                }
                System.out.println("repo = " + repo);
                System.out.println("f.getReponse() = " + f.getReponse());
                
                if(verif.equals(repo))
                {
                    soluce.setText("Bien Joué !!!! "+ f.getReponse());
                    rep.setText(null);
                }
                else{
                    soluce.setText("Quel dommage !!!!  la solution était " + f.getReponse());
                   // f.setReponse(null);
                    rep.setText(null);
                }
            }
        });
        
        solution.addActionListener((ActionEvent e) -> {
            String reponsedb = f.getReponse();
            soluce.setText("T'aurais pu repondre la reponse etait "+ reponsedb);
           // f.setReponse(null);
            rep.setText(null);
            
        });
        
        
        autre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rep.setText(null);
//                f.setReponse(null);
                soluce.setText(null);
                initGUI();
            }
        });
        
   
        
    }
    public static boolean isANumber(String chaine){
         try{              
               Integer.parseInt(chaine);
               return true;
         }catch(NumberFormatException nfe){
               return false;
         }
    }
}
