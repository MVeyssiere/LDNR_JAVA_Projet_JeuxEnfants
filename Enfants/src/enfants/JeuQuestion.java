/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enfants;

import Beans.Question;
import DAO.DAOQuestion;
import java.util.ArrayList;

/**
 *
 * @author Julien Modena
 */

public class JeuQuestion {
  
     


public Question poserquestion(int niv)
{
      
        Question ques = null;
        DAOQuestion daniv = new DAOQuestion();
        ArrayList<Question> listQuest = new ArrayList<>();
        listQuest =  (ArrayList<Question>) daniv.findWithNiveau(niv);
        int compteur= 0;
        int aleat = verifierdoublon(niv);
         
        
     for(Question q : listQuest)
     {
         if(aleat == compteur)
         {
             ques = q;
            listQuest.remove(q);
             break;
         }
        compteur = compteur+1;
         
     }
          return ques;
          
}

public int verifierdoublon(int niv)
{
    int MIN = 0;
    int MAX = 0;
    DAOQuestion daniv = new DAOQuestion();
     ArrayList<Question> listQuest = new ArrayList<>();
     listQuest =  (ArrayList<Question>) daniv.findWithNiveau(niv);
     MAX = (listQuest.size())-1;
     System.out.println("MAX = " + MAX);
     int alea= 0;
     int nombreAleatoire = 0;
         if(listQuest.isEmpty())
         {
                  String infoList = "La liste de question est vide";
                  System.out.println("La liste de question est vide ");
         }  
      
   else{
                  System.out.println("coucou");
                    nombreAleatoire = MIN + (int)(Math.random() * ((MAX - MIN) + 1));
                    alea = nombreAleatoire;
                    System.out.println("alea = " + alea);
                   

        }
   
    
        return alea;
}

    


   
}

