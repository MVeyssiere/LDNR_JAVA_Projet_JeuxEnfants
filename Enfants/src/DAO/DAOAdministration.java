package DAO;

import Beans.Admin;
import static DAO.DAO.connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Marine Veyssiere
 */
public class DAOAdministration implements DAO<Admin> {

    private final String table = "Administration";

    @Override
    public Admin find(Integer id) {
        Admin retObj = null;
        // faut faire attention aux espaces qui doivent entouré le nom de la table
        String sql = "SELECT * FROM " + table + " WHERE id_admin=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            // permet de trouver dans la base de données tous les lignes ayant l'id
            pstmt.setInt(1, id);
            // cette ensemble permet de récuperer tous les objets ayant le bon pstmt
            ResultSet rs = pstmt.executeQuery();

            if (rs.first()) {
                retObj = new Admin(id,
                        rs.getString("pwd")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retObj;

    }

    @Override
    public Admin create(Admin obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Admin obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Admin update(Admin obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Admin> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
