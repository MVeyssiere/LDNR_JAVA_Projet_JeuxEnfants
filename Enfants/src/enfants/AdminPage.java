package enfants;

import Beans.Question;
import DAO.DAOQuestion;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * @author Marine V
 */
public class AdminPage extends JFrame {

    public AdminPage() {

        //panel général avec nord/sud/est/ouest
//        JPanel container = new JPanel();
        BorderLayout containerLayout = new BorderLayout();
        this.setLayout(containerLayout);

        //scrollbar
        JScrollPane scroll = new JScrollPane(this);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //panel des infos qu'on placera dans le centre du panel général et qui place chaque ligne de la BDD en vertical
        JPanel entrees = new JPanel();
        BoxLayout layoutEntrees = new BoxLayout(entrees, BoxLayout.PAGE_AXIS);
        entrees.setLayout(layoutEntrees);

        DAOQuestion quest = new DAOQuestion();

        // chaque colonne de la BDD est affichée en horizontal
        JPanel panelQuestions = new JPanel();
        FlowLayout layoutQuestions = new FlowLayout();
        panelQuestions.setLayout(layoutQuestions);
//        List<Question> listQuestions = new ArrayList<>();

        for (Question q : quest.findAll()) {
//            listQuestions.add(q);
            panelQuestions.add(new JLabel(q.getNiveau().toString()));
            panelQuestions.add(new JTextField(q.getQuestion()));
        }
        entrees.add(panelQuestions);
        this.add(entrees, BorderLayout.CENTER);

        this.setTitle("Administration");
        this.setSize(1200, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(this);
//        this.getContentPane().add(scroll);
        this.setVisible(true);
    }
}
