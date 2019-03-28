package enfants;

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

    public Question triNivDeux() {

        int MIN = 0;
        int MAX = questnivdeux.size();
        int nombreAleatoire = MIN + (int) (Math.random() * ((MAX - MIN)));
        Question questDeux = daoQ.find(nombreAleatoire);

        return questDeux;
    }
}
