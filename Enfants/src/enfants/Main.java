/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enfants;

import Beans.Question;

/**
 *
 * @author Amine Semmoud
 */
public class Main {
    
  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JeuQuestion test = new JeuQuestion();
        java.util.Scanner sc = new java.util.Scanner(System.in);
//        System.out.println("saisir le niveau que vous souhaitez");
//        int niveau = sc.nextInt();
        for(int i =0; i<11;i++)
        {
            Question f = test.poserquestion(1);
        System.out.println("f = " + f);
            
        }
        
        
        
        
        
        
}
}
