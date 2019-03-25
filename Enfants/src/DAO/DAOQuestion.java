/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Beans.Question;
import static DAO.DAO.connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julien Modena
 */
public class DAOQuestion implements DAO<Question>{

    private final String table = "Question";
    
    @Override
    public Question find(Integer id) {
         Question retObj = null;
        // faut faire attention aux espaces qui doivent entouré le nom de la table
       String sql = "SELECT * FROM "+table +" WHERE id_question=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            // permet de trouver dans la base de données tous les lignes ayant l'id
            pstmt.setInt(1, id);
            // cette ensemble permet de récuperer tous les objets ayant le bon pstmt
            ResultSet rs = pstmt.executeQuery();
            
                if(rs.first()){
                  retObj = new Question (id, 
                            rs.getString("question"), 
                            rs.getString("reponse"),
                            rs.getInt("niveau")
                                );
                }
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retObj;
    
    }

    @Override
    public Question create(Question obj) {
        
          Question rtObj = null;
        String sql = "INSERT INTO "+table+" (question, reponse, niveau)"+" VALUES (?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,obj.getQuestion());
            pstmt.setString(2,obj.getReponse());
            pstmt.setInt(3,obj.getNiveau());
            pstmt.executeUpdate();
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if(generatedKeys.first()){

                    rtObj = this.find(generatedKeys.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rtObj;
    }

    @Override
    public void delete(Question obj) {
           String sql = "DELETE FROM "+table+" WHERE id_question=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1,obj.getId());
            int nbligneimpacter = pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Question update(Question obj) {
       Question rtObj = null;
        String sql = "UPDATE "+table+" SET nom=?,"
                + "question = ?,"
                +"reponse = ?,"
                + "niveau = ?,";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
             pstmt.setString(1, obj.getQuestion());
             pstmt.setString(2,obj.getReponse());
             pstmt.setInt(3,obj.getNiveau());
             pstmt.setInt(4, obj.getId());
             pstmt.executeUpdate();
             //réhydrate l'objet a partir de ces nouvelles données
             rtObj = find(obj.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rtObj;
        
    }

    @Override
    public List<Question> findAll() {
           ArrayList<Question> retObj = new ArrayList<>();
        // faut faire attention aux espaces qui doivent entouré le nom de la table
       String sql = "SELECT * FROM "+table ;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            // cette ensemble permet de récuperer tous les objets ayant le bon pstmt
            ResultSet rs = pstmt.executeQuery();
            
                while(rs.next()){
                  retObj.add(new Question(rs.getInt("id_question"), 
                            rs.getString("question"), 
                            rs.getString("reponse"),
                            rs.getInt("niveau")
                                ));
                }
        } catch (SQLException ex) {
            Logger.getLogger(DAOQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retObj;
    
       
    }
    

}
