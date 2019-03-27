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
//        JScrollPane scrollpane = new JScrollPane(table);
//        table.setPreferredScrollableViewportSize(table.getPreferredSize());
//        table.setFillsViewportHeight(true);

        DefaultTableModel model = new DefaultTableModel(new String[]{"Id", "Question", "Réponse", "Niveau", "", ""}, 0);

        DAOQuestion quest = new DAOQuestion();
        for (Question q : quest.findAll()) {
//            listQuestions.add(q);
            String d = q.getId().toString();
            String e = q.getQuestion();
            String f = q.getReponse();
            String g = q.getNiveau().toString();
            String update = "Modifier";
            String supprimer = "Supprimer";
            model.addRow(new Object[]{d, e, f, g, update, supprimer});
        }
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setModel(model);
        table.getColumnModel().getColumn(0).setPreferredWidth(75);
        table.getColumnModel().getColumn(1).setPreferredWidth(800);
        table.getColumnModel().getColumn(2).setPreferredWidth(300);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.yellow);

        JScrollPane pane = new JScrollPane(table);
        panel.add(pane);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col == 4) {
                    System.out.println(table.getValueAt(row, 1));
//                    Question modifierQuestion = new Question(col, question, reponse, NORMAL);
//                    modifierQuestion.add(table.getValueAt(row, 0));
//                    update(g);
//                    //update

                }
                if (row >= 0 && col == 5) {
                    //delete
                }
            }
        });

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
        frame.setSize(1500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(panel);
//        frame.getContentPane().add(scroll);
        frame.setVisible(true);
    }
}
