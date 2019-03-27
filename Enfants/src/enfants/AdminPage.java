package enfants;

import Beans.Question;
import DAO.DAOQuestion;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * @author Marine Veyssiere
 */
public class AdminPage extends JFrame {

    JFrame frame = new JFrame();

    public AdminPage() {

        //panel général avec nord/sud/est/ouest
        JPanel panel = new JPanel();

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(new String[]{"Id", "Question", "Réponse", "Niveau"}, 0);

        DAOQuestion quest = new DAOQuestion();
        for (Question q : quest.findAll()) {
//            listQuestions.add(q);
            String d = q.getId().toString();
            String e = q.getQuestion();
            String f = q.getReponse();
            String g = q.getNiveau().toString();
            model.addRow(new Object[]{d, e, f, g});
        }
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setModel(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.yellow);

        JScrollPane pane = new JScrollPane(table);
        panel.add(pane);
//
////        //scrollbar
////        JScrollPane scroll = new JScrollPane(frame);
////        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        //panel des infos qu'on placera dans le centre du panel général et qui place chaque ligne de la BDD en vertical
//        JPanel entrees = new JPanel();
//        BoxLayout layoutEntrees = new BoxLayout(entrees, BoxLayout.Y_AXIS);
//        entrees.setLayout(layoutEntrees);
//
//        DAOQuestion quest = new DAOQuestion();
//
//        // chaque colonne de la BDD est affichée en horizontal
//        JPanel panelQuestions = new JPanel();
//        FlowLayout layoutQuestions = new FlowLayout();
//        panelQuestions.setLayout(layoutQuestions);
////        List<Question> listQuestions = new ArrayList<>();
//
//        for (Question q : quest.findAll()) {
////            listQuestions.add(q);
//            panelQuestions.add(new JLabel(q.getNiveau().toString()));
//            panelQuestions.add(new JTextField(q.getQuestion()));
//            panelQuestions.add(new JTextField(q.getReponse()));
//            panelQuestions.add(new JTextField(q.getNiveau().toString()));
//            entrees.add(panelQuestions);
//        }
//        panel.add(entrees, BorderLayout.CENTER);
//        frame.add(panel);

//        JScrollPane scrollpane = new JScrollPane(table);
//        scrollpane.setPreferredSize(new Dimension(800, 400));
        frame.setTitle("Administration");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(panel);
//        frame.getContentPane().add(scroll);
        frame.setVisible(true);
    }
}
