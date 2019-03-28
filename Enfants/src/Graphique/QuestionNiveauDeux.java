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
public class QuestionNiveauDeux {

  DAOQuestion daoQ = new DAOQuestion();
    ArrayList<Question> questnivdeux = (ArrayList<Question>) daoQ.findAll();
    
    
    public Question triNivDeux(){
        
        int MIN =0;
        int MAX = questnivdeux.size();
        int  nombreAleatoire = MIN + (int)(Math.random() * ((MAX - MIN) ));
        Question questDeux = daoQ.find(nombreAleatoire);
        
        return questDeux;
    }

}
