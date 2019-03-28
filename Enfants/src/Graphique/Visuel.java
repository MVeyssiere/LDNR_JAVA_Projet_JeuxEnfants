/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphique;

import Beans.Question;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Julien Modena
 */
public class Visuel extends JPanel {
        JFrame frame = new JFrame();
       QuestionNiveauUn questionNiveauUn = new QuestionNiveauUn();
       QuestionNiveauDeux questionNiveauDeux = new QuestionNiveauDeux();
       int niveauSelec =1;
       JPanel global;
       JTextField rep;
       JLabel soluce ;
       JLabel question;
       Question f;
       
    public Visuel() {
        
        //panel général avec nord/sud/est/ouest
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g)
                {
                    super.paintComponent(g);
                    ImageIcon m = new ImageIcon(getClass().getResource("/Ressource/fond_jeu2.jpg"));
                    Image monImage = m.getImage();
                    g.drawImage(monImage, 0, 0, 1390, 600, null);
                 }
            };
         initGUI();
////         global.setPreferredSize(new Dimension(600,330));
         panel.add(global);
       frame.setTitle("question");
      frame.setSize(1500, 500);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);
      frame.setContentPane(panel);
//        frame.getContentPane().add(scroll);
      frame.setVisible(true);
       
    }
  
    private void initGUI(){
        JPanel bouton = new JPanel();
       JPanel reponse = new JPanel();
       this.soluce = new JLabel();
       Box cont = new Box(BoxLayout.Y_AXIS);
       Box niveau= new Box(BoxLayout.X_AXIS);
       Box content= new Box(BoxLayout.PAGE_AXIS);
        // Gestion de la reponse
        
        this.question = new JLabel();
        
                // Creation des boutons
        
        JButton verifier = new JButton("Verifier");
        JButton solution = new JButton("Solution");
        JButton autre = new JButton("Autre Question");
////      
        this.rep = new JTextField(30);
        JLabel ti = new JLabel("Réponse : ");
        this.global = new JPanel();
       
         // bouton qui modifie le niveau
        
        
        JButton niv1 = new JButton("niveau 1");
        
        
        niv1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
               niveauSelec =1;
                Question f = questionNiveauUn.Triniveauun();
                Afficher(f);
               
            }
        });
        JButton niv2 = new JButton("niveau 2");
        niv2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
                niveauSelec =2;
                Question f = questionNiveauDeux.triNivDeux();
                Afficher(f);
               
            }
        });
        niveau.add(niv1);
        niveau.add(niv2);
      //gestion reponse
        reponse.setLayout(new GridLayout(3,0));
        
        reponse.add(soluce);
        reponse.add(ti);
        reponse.add(rep);
        

        // ajout de la question et de la réponse en notre composant
        content.add(question);
        content.add(reponse);
          // ajout des listener pour chaque bouton
        verifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Verifier();
            }
        });
        
        solution.addActionListener((ActionEvent e) -> {
                Solution();
        });
        
        
        autre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AutreQuestion();
        }
        });
        //ajout des bouton au panel regroupant les boutons
        bouton.add(verifier);
        bouton.add(solution);
        bouton.add(autre);
        cont.add(content);
        cont.add(bouton);
        cont.add(niveau);
        // ajout des différents panel au panel général

        global.add(cont,BorderLayout.CENTER);

       
  
        
       
       
    }
    public static final String clean(String toClean) {
        return toClean.replaceAll("[éèêë]", "e")
                .replaceAll("[ÉÈÊË]", "E")
                .replaceAll("[àâä]", "a")
                .replaceAll("[ÀÂÄ]", "A")
                .replaceAll("[îï]", "i")
                .replaceAll("[ÎÏ]", "I")
                .replaceAll("[ôö]", "o")
                .replaceAll("[ÔÖ]", "O")
                .replaceAll("[ùûü]", "u")
                .replaceAll("[ÙÛÜ]", "U")
                .replaceAll("ÿ", "y")
                .replaceAll("Ÿ", "Y")
                .replaceAll("æ", "ae")
                .replaceAll("Æ", "AE")
                .replaceAll("œ", "oe")
                .replaceAll("Œ", "OE")
                .replaceAll("ç", "c")
                .replaceAll("Ç", "C");
    }
   public void Verifier(){
      
        String verif= rep.getText();
        String reponsedb = f.getReponse();
        String repo ;
         if(isANumber(reponsedb)==true && isANumber(verif)==true)
            {
              repo = reponsedb;
            }
             else
                {
                    repo = reponsedb.toLowerCase();
                    repo =  clean(repo);
                    verif  = verif.toLowerCase();
                    verif = clean(verif);
                }
         System.out.println("repo = " + repo);
                System.out.println("f.getReponse() = " + f.getReponse());
                
                if(verif.equals(repo))
                {
                    soluce.setText("Bien Joué !!!! "+ f.getReponse());
                    
                }
                else{
                    soluce.setText("Quel dommage !!!!  la solution était " + f.getReponse());
                  
                   
                }
   }
   public void Solution(){
       
       String reponsedb = f.getReponse();
       soluce.setText("T'aurais pu repondre la reponse etait "+ reponsedb);
       
   }
   
   public void Afficher(Question f){
        String quest = f.getQuestion();
        System.out.println("quest = " + quest);
        question.setText(quest);
           
       
   }
   public void Nettoyer(){
       
       soluce.setText(null);
       rep.setText(null);
       question.setText(null);
       
       
   }
   public void AutreQuestion(){
       Nettoyer();
         switch(niveauSelec){
                    case 1: f = questionNiveauUn.Triniveauun();
                            
                            Afficher(f);
                        
                    case 2: f = questionNiveauDeux.triNivDeux();
                            
                            Afficher(f);
                }
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
       