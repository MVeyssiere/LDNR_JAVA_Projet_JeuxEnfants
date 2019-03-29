package enfants;

import Beans.Question;
import DAO.DAOQuestion;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * @author Marine Veyssiere
 */
public class AdminPage extends JPanel {

    JPanel admin = new JPanel();

    public AdminPage() {

        admin.setLayout(new GridLayout(2, 1));

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
        table.setBackground(new Color(230, 242, 255));
        JTableHeader header = table.getTableHeader(); // afficher les colonnes headers
        header.setBackground(Color.yellow); // fond jaune pour les headers
//
//        JScrollPane pane = new JScrollPane(table);
//        this.add(pane);

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
                    JOptionPane.showMessageDialog(getParent(), "La modification a bien été effectuée");
                }
                // supprimer la ligne
                if (row >= 0 && col == 5) {
                    //integrer try catch pour ne pas afficher le message si une erreur s'est produite
                    daoq.delete(updatedQuestion);
                    model.removeRow(row);
                    JOptionPane.showMessageDialog(getParent(), "La suppression a bien été effectuée");
                }
            }
        });

        JTable tableNew = new JTable();

        DefaultTableModel modelNew = new DefaultTableModel(new String[]{"Votre question", "Votre réponse", "Niveau", ""}, 0);
        modelNew.addRow(new Object[]{"", "", 0, "Ajouter"});

        // taille de la table
        tableNew.setModel(modelNew);
        tableNew.setRowHeight(20); //hauteur des cellules de la table
        tableNew.getColumnModel().getColumn(0).setPreferredWidth(800); // largeur de la colonne questions
        tableNew.getColumnModel().getColumn(1).setPreferredWidth(300); // largeur de la colonne réponse
//        tableNew.setPreferredScrollableViewportSize(table.getPreferredSize());
//        tableNew.setFillsViewportHeight(true); // permettre à la table de prendre la taille de la fenetre
        JTableHeader headerNew = tableNew.getTableHeader(); // afficher les colonnes headers
        headerNew.setBackground(Color.GREEN); // fond jaune pour les headers

        tableNew.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int new_niveau;
                String new_question, new_reponse;

                int row = tableNew.rowAtPoint(evt.getPoint());
                int col = tableNew.columnAtPoint(evt.getPoint());

                //recuperer les valeurs de la ligne
                new_question = tableNew.getValueAt(tableNew.getSelectedRow(), 0).toString();
                new_reponse = tableNew.getValueAt(tableNew.getSelectedRow(), 1).toString();
                new_niveau = Integer.parseInt(tableNew.getValueAt(tableNew.getSelectedRow(), 2).toString());

                DAOQuestion daoadd = new DAOQuestion();
                Question addQuestion = new Question(0, new_question, new_reponse, new_niveau);
                // ajouter la ligne
                if (row >= 0 && col == 3) {
                    //integrer try catch pour ne pas afficher le message si une erreur s'est produite
                    daoadd.create(addQuestion);
                    JOptionPane.showMessageDialog(getParent(), "Une nouvelle question et sa réponse ont été ajoutés.");
                }
            }
        });

        JScrollPane sp = new JScrollPane(table);
        JScrollPane sp2 = new JScrollPane(tableNew);
        admin.add(sp);
        admin.add(sp2);

        admin.validate();
        this.add(admin);
    }
}
