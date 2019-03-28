/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Graphique;

import Beans.Question;
import DAO.DAOQuestion;
import java.util.ArrayList;

/**
 *
 * @author Julien Modena
 */
public class QuestionNiveauUn {
        DAOQuestion daoQ = new DAOQuestion();
        ArrayList<Question> questnivun = (ArrayList<Question>) daoQ.findNiveau1();
        
        
   public Question Triniveauun()
   {
            Question ques = null;
            int compteur =0;
             int MIN =0;
        int MAX = questnivun.size();
        
        int  nombreAleatoire = MIN + (int)(Math.random() * ((MAX - MIN) ));
        System.out.println("nombreAleatoire = " + nombreAleatoire);
    for(Question questUn : questnivun)
     {
         if(nombreAleatoire == compteur)
         {
             ques = (Question) questUn;
            questnivun.remove(questUn);
    
             break;
         }
        compteur = compteur+1;
        
        
         
     }
       
       return ques;
   }
   
}
