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
    // ajout des questions à une liste de questions contenant toute les questions
    ArrayList<Question> questnivdeux = (ArrayList<Question>) daoQ.findAll();
    int MIN = 0;
    int MAX = questnivdeux.size();

    public Question triNivDeux() {

        int nombreAleatoire = MIN + (int) (Math.random() * ((MAX - MIN)));

        // tirage d'une question dans la base de données par rapport à un nombre aléatoire
        Question questDeux = daoQ.find(nombreAleatoire);

        MAX = MAX - 1;
        return questDeux;

    }

    public boolean FindesQuestionsDeux() {
        if (MAX == 0) {
            return true;
        } else {
            return false;
        }
    }
}
