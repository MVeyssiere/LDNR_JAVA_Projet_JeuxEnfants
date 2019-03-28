package enfants;

import Beans.Question;
import DAO.DAOQuestion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon m = new ImageIcon(getClass().getResource("/Ressource/fond_admin.jpg"));
                Image monImage = m.getImage();
                g.drawImage(monImage, 0, 0, 1290, 700, null);
            }
        };

        JTable table = new JTable();
//        JScrollPane scrollpane = new JScrollPane(table);
//        table.setPreferredScrollableViewportSize(table.getPreferredSize());
//        table.setFillsViewportHeight(true);

        DefaultTableModel model = new DefaultTableModel(new String[]{"Id", "Question", "Réponse", "Niveau", "", ""}, 0);

        DAOQuestion quest = new DAOQuestion();
        for (Question q : quest.findAll()) {
            String d = q.getId().toString();
            String e = q.getQuestion();
            String f = q.getReponse();
            String g = q.getNiveau().toString();
            String update = "Modifier";
            String supprimer = "Supprimer";
            model.addRow(new Object[]{d, e, f, g, update, supprimer});
        }

        // taille de la table
        table.setModel(model);
        table.setRowHeight(20); //hauteur des cellules de la table
        table.getColumnModel().getColumn(1).setPreferredWidth(800); // largeur de la colonne questions
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // largeur de la colonne réponse
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true); // permettre à la table de prendre la taille de la fenetre
        JTableHeader header = table.getTableHeader(); // afficher les colonnes headers
        header.setBackground(Color.yellow); // fond jaune pour les headers

        JScrollPane pane = new JScrollPane(table);
        panel.add(pane);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int id_table, niveau_table;
                String question_table, reponse_table;
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());

                //recuperer les valeurs de la ligne
                id_table = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                question_table = table.getValueAt(table.getSelectedRow(), 1).toString();
                reponse_table = table.getValueAt(table.getSelectedRow(), 2).toString();
                niveau_table = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString());
                DAOQuestion daoq = new DAOQuestion();
                Question updatedQuestion = new Question(id_table, question_table, reponse_table, niveau_table);
                // modifier la ligne
                if (row >= 0 && col == 4) {
                    //integrer try catch pour ne pas afficher le message si une erreur s'est produite
                    daoq.update(updatedQuestion);
                    JOptionPane.showMessageDialog(panel, "La modification a bien été effectuée");
                }
                // supprimer la ligne
                if (row >= 0 && col == 5) {
                    //integrer try catch pour ne pas afficher le message si une erreur s'est produite
                    daoq.delete(updatedQuestion);
                    JOptionPane.showMessageDialog(panel, "La suppression a bien été effectuée");
                }

            }
        });
        frame.setTitle("Administration");
        frame.setSize(1500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(panel);
//        frame.getContentPane().add(scroll);
        frame.setVisible(true);
    }
}
