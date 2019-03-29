package enfants;

import Beans.Question;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Julien Modena
 */
public class Visuel extends JPanel {

    QuestionNiveauUn questionNiveauUn = new QuestionNiveauUn();
    QuestionNiveauDeux questionNiveauDeux = new QuestionNiveauDeux();
    private int niveauSelec = 1;
    public int score = 0;
    JPanel global;
    JPanel fin;
    JTextField rep;
    JLabel soluce;
    JLabel question;
    Question f;

    public Visuel() {

        //panel général avec nord/sud/est/ouest
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon m = new ImageIcon(getClass().getResource("/Ressource/fond_jeu2.jpg"));
                Image monImage = m.getImage();
                g.drawImage(monImage, 0, 0, 1390, 800, null);
            }
        };

        initGUI();

        panel.setPreferredSize(new Dimension(1390, 800));

        panel.add(global);
        this.add(panel);
    }

    private void initGUI() {

        JPanel bouton = new JPanel();
        JPanel reponse = new JPanel();
        this.soluce = new JLabel();
        Box cont = new Box(BoxLayout.Y_AXIS);
        Box niveau = new Box(BoxLayout.X_AXIS);
        Box content = new Box(BoxLayout.PAGE_AXIS);
        // Gestion de la reponse

        this.question = new JLabel();

        // Creation des boutons
        JButton verifier = new JButton("Verifier");
        JButton solution = new JButton("Solution");
        JButton autre = new JButton("Autre Question");
////
        this.rep = new JTextField(30);
        JLabel ti = new JLabel("Réponse : ");
        this.global = new JPanel();

        // bouton qui modifie le niveau
        JButton niv1 = new JButton("niveau 1");
// action listener qui va modifier le niveau des questions

        niv1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                setNiveauSelec(1);

            }
        });
        JButton niv2 = new JButton("niveau 2");
        niv2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {

                setNiveauSelec(2);

            }
        });
        // Initialisation du boutton quitter
        // pour quitter la page question
        JButton quitter = new JButton("Quitter");
        quitter.setBackground(Color.lightGray);
        quitter.setFont(new Font("New Times Roman", Font.BOLD, 16));
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Onglets onglet = new Onglets();
//                QuitterPage();
            }
        });
        niveau.add(niv1);
        niveau.add(niv2);
        //gestion reponse
        reponse.setLayout(new GridLayout(3, 0));

        reponse.add(soluce);
        reponse.add(ti);
        reponse.add(rep);

        // ajout de la question et de la réponse en notre composant
        content.add(question);
        content.add(reponse);

        int level = getNiveauSelec();
        triNiveau(level);
        System.out.println("level = " + level);
        // ajout des listener pour chaque bouton
// action listener du bouton verifier
        verifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Verifier();
            }
        });
// action listener du bouton solution

        solution.addActionListener((ActionEvent e) -> {
            Solution();
        });
// action listener du bouton autre question
        autre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AutreQuestion();
                // permet l'affichage du score des que c'est la fin des questions
                if ((questionNiveauUn.FindesQuestions() || questionNiveauDeux.FindesQuestionsDeux()) == true) {

                    JLabel info = new JLabel("votre score est de " + score);
                    global.remove(cont);
                    global.add(info);

                }
            }
        });
        //ajout des bouton au panel regroupant les boutons
        bouton.add(verifier);
        bouton.add(solution);
        bouton.add(autre);
        cont.add(content);
        cont.add(bouton);
        cont.add(niveau);

        // ajout des différents panel au panel général
        global.add(quitter, BorderLayout.NORTH);
        global.add(cont, BorderLayout.CENTER);

    }

    // permet de transformer les caractères spéciaux
    public static final String clean(String toClean) {
        return toClean.replaceAll("[éèêë]", "e")
                .replaceAll("[ÉÈÊË]", "E")
                .replaceAll("[àâä]", "a")
                .replaceAll("[ÀÂÄ]", "A")
                .replaceAll("[îï]", "i")
                .replaceAll("[ÎÏ]", "I")
                .replaceAll("[ôö]", "o")
                .replaceAll("[ÔÖ]", "O")
                .replaceAll("[ùûü]", "u")
                .replaceAll("[ÙÛÜ]", "U")
                .replaceAll("ÿ", "y")
                .replaceAll("Ÿ", "Y")
                .replaceAll("æ", "ae")
                .replaceAll("Æ", "AE")
                .replaceAll("œ", "oe")
                .replaceAll("Œ", "OE")
                .replaceAll("ç", "c")
                .replaceAll("Ç", "C");
    }

// fonction qui permet de verifier si la reponse du textfield correspond à celle de la base de données
    public int Verifier() {

        String verif = rep.getText();
        String reponsedb = f.getReponse();
        String repo;
        if (isANumber(reponsedb) == true && isANumber(verif) == true) {
            repo = reponsedb;
        } else {
            repo = reponsedb.toLowerCase();
            repo = clean(repo);
            verif = verif.toLowerCase();
            verif = clean(verif);
        }
        System.out.println("repo = " + repo);
        System.out.println("f.getReponse() = " + f.getReponse());

        if (verif.equals(repo)) {
            soluce.setText("Bien Joué !!!! " + f.getReponse());
            score = score + 1;
            System.out.println("score = " + score);

        } else {
            soluce.setText("Quel dommage !!!!  la solution était " + f.getReponse());
            System.out.println("score = " + score);
        }
        return score;
    }

    // Affiche dans le label reponse la solution a la question
    public void Solution() {
        String reponsedb = f.getReponse();
        soluce.setText("T'aurais pu repondre la réponse était " + reponsedb);
    }

    // Rempli le label correspondant a la question
    public void Afficher(Question f) {
        String quest = f.getQuestion();
        question.setText(quest);
    }

    //Fonction qui va nettoyer les différents champs
    public void Nettoyer() {
        soluce.setText(null);
        rep.setText(null);
        question.setText(null);
    }

    //Fonction qui va tirer une question
    public void AutreQuestion() {
        Nettoyer();
        int le;
        le = getNiveauSelec();
        System.out.println("le = " + le);
        switch (le) {
            case 1:
                f = questionNiveauUn.Triniveauun();
                Afficher(f);
                break;
            case 2:
                f = questionNiveauDeux.triNivDeux();
                Afficher(f);
                break;
        }
    }

    //Fonction de vérification si la saisie ou la reponse sont des nombres
    public static boolean isANumber(String chaine) {
        try {
            Integer.parseInt(chaine);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    //fonction qui devait permettre de revenir au menu principal
//    public boolean QuitterPage(){
//        return true;

//    }
    //Fonction qui va selon le niveau afficher la premiere question
    public void triNiveau(int niv) {
        switch (niveauSelec) {
            case 1:
                f = questionNiveauUn.Triniveauun();
                Afficher(f);

                break;
            case 2:
                f = questionNiveauDeux.triNivDeux();
                Afficher(f);
                break;
        }

    }

    public int getNiveauSelec() {
        return niveauSelec;
    }

    public void setNiveauSelec(int niveauSelec) {
        this.niveauSelec = niveauSelec;
    }

}
