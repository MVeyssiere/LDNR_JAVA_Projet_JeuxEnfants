package enfants;

import Beans.Question;
import DAO.DAOQuestion;
import java.util.ArrayList;

/**
 * @author Julien Modena
 */
public class QuestionNiveauUn {

    DAOQuestion daoQ = new DAOQuestion();
    // ajout des questions à une liste trié par rapport au niveau

    ArrayList<Question> questnivun = (ArrayList<Question>) daoQ.findNiveau1();
    int MAX = questnivun.size();

    // fonction permettant de tiré aleatoirement une question
    public Question Triniveauun() {
        Question ques = null;
        int compteur = 0;
        int MIN = 0;

        int nombreAleatoire = MIN + (int) (Math.random() * ((MAX - MIN)));
        System.out.println("nombreAleatoire = " + nombreAleatoire);
        for (Question questUn : questnivun) {
            if (nombreAleatoire == compteur) {
                ques = (Question) questUn;
                questnivun.remove(questUn);
                break;
            }
            compteur = compteur + 1;
        }
        System.out.println("MAX = " + MAX);
        MAX = MAX - 1;
        return ques;

    }

    public boolean FindesQuestions() {
        if (MAX == 0) {
            return true;
        } else {
            return false;
        }
    }
}
