package enfants;

import Beans.Question;
import DAO.DAOQuestion;
import java.util.HashSet;

/**
 *
 * @author Julien Modena
 */
public class JeuQuestion {

    final static int MIN = 1;
    final static int MAX = 10;

//    public Question poserquestion(int niv) {
//
//        Question ques = null;
//        DAOQuestion daniv = new DAOQuestion();
//        int compteur = 0;
//        int aleat = verifierdoublon();
//
//        for (Question q : daniv.findWithNiveau(niv)) {
//            if (aleat == compteur) {
//                ques = q;
//
//                break;
//            }
//            compteur = compteur + 1;
//
//        }
//        return ques;
//
//    }

    public int verifierdoublon() {

        int alea = 0;
        HashSet<Integer> tirage = new HashSet<>();
        int nombreAleatoire = 0;

        if (tirage.isEmpty()) {
            nombreAleatoire = MIN + (int) (Math.random() * ((MAX - MIN) + 1));
            System.out.println(nombreAleatoire);
            System.out.println("zob");

            alea = nombreAleatoire;
            tirage.add(alea);

        } else {
            for (Integer i : tirage) {

                while (i == nombreAleatoire) {
                    nombreAleatoire = MIN + (int) (Math.random() * ((MAX - MIN) + 1));
                    System.out.println("salut");
                    System.out.println(nombreAleatoire);
                }

                alea = nombreAleatoire;
                System.out.println("al = " + alea);
                tirage.add(alea);

            }

        }

        System.out.println("alea = " + alea);
        return alea;
    }
}
