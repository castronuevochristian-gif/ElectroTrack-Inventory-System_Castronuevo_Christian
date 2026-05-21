package com.electrotrack.dao;

import java.util.List;

/**
 * A generic Data Access Object (DAO) interface.
 * Fulfills the project requirement for structural interface utilization.
 * @param <T> The model entity type (e.g., Component, User)
 */
public interface BaseDAO<T> {
    
    // CREATE 
    boolean create(T object);
    
    // READ 
    List<T> readAll();
    
    // UPDATE 
    boolean update(T object);
    
    // DELETE 
    boolean delete(int id);
    
    // SEARCH 
    List<T> search(String keyword);
}