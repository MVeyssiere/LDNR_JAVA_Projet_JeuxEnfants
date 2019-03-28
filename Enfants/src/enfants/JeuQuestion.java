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

public final class JeuQuestion {
    public final int MIN = 0;
     DAOQuestion daniv = new DAOQuestion();

public Question poserInter(int niv) {
    
      int MAX =0;
      ArrayList<Question>  listQuest =  (ArrayList<Question>) daniv.findWithNiveau(niv);
       MAX = (listQuest.size())-1;
       Question q;
     q =  poserquestion(MAX, listQuest);
       
     return q;
       
}
public Question poserquestion(int MAX,ArrayList list)
{
      
        Question ques = null;
       
       
        int compteur= 0;
        int aleat = verifierdoublon(MAX);
          System.out.println("MAXze = " + MAX);
                  
     for(Object q : list)
     {
         if(aleat == compteur)
         {
             ques = (Question) q;
            list.remove(q);
            
             
            

             break;
         }
        compteur = compteur+1;
        
         
     }
             System.out.println("MAXze = " + MAX);

              MAX = MAX-1;

          return ques;
          
}

public int verifierdoublon(int MAX)
{
   
    
    
     System.out.println("MAX = " + MAX);
     int alea= 0;
     int nombreAleatoire = 0;
     nombreAleatoire = MIN + (int)(Math.random() * ((MAX - MIN) ));
     alea = nombreAleatoire;
     System.out.println("alea = " + alea);
        
    
        return alea;
}

    


   
}

