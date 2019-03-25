/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import Connection.SQLConnection;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Julien Modena
 * @param <T>le type du bean associé
//
// */
public interface DAO <T>{


    Connection connection = SQLConnection.getInstance();
    /**
     * retourne l'objet ayant l'identifiant a recuperer
     * @param id
     * @return l'objet ayant l'identifiant id
     */
    T find (Integer id);
    /**
     * 
     * @param obj l'objet a perenniser en DB
     * @return l'objet stocké en DB
     */
    T create (T obj);
    /**
     * Supprime l'objet en parametre de la database
     * @param obj 
     */
    void delete(T obj);
    /**
     * Modifie la DB pour correspondre l'objet passe en parametre
     * @param obj l'objet a perenniser dans la DB
     * @return l'objet hydraté depuis la DB
     */
    T update (T obj);
    /**
     * Retourne une collection d'objet hydraté(eponge depuis la DB) depuis la DB
     * @return 
     */
    List<T> findAll();
}



