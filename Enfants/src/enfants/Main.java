/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enfants;

import Beans.Question;
import DAO.DAOQuestion;
import java.util.Random;

/**
 *
 * @author Amine Semmoud
 */
public class Main {
    
    final static int MIN =1;
    final static int MAX = 10;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DAOQuestion daop = new DAOQuestion();
        int nombreAleatoire = MIN + (int)(Math.random() * ((MAX - MIN) + 1));
         System.out.println("nombreAleatoire = " + nombreAleatoire);
         int niveau = 1;
           
           
         for(Question p : daop.findWithNiveau(1))
        {
            System.out.println("p = " + p);
      }
    }

}
