package es.progcipfpbatoi.model.dao;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;
import es.progcipfpbatoi.model.dto.producttypes.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductosDAO {
    /**
     *  Obtiene todas las tareas
     */
    ArrayList<Product> findAll();

    /**
     *  Obtiene todas las tareas creadas por el usuario
     */
    ArrayList<Product> findAllCreadas();

    /**
     * Obtiene todas las tareas que comiencen por @text
     * @param cod
     * @return
     */
    Product findAll(int cod) throws SQLException;

    /**
     * Obtiene la tarea cuyo id sea @id
     * @param cod
     * @return
     * @throws NotFoundException
     */
    Product getById(int cod) throws NotFoundException, DatabaseErrorException;

    /**
     * Almacena la tarea o la actualiza en caso de existir
     * @param product
     * @return
     * @throws DatabaseErrorException
     */
    boolean save(Product product) throws DatabaseErrorException, NotFoundException, SQLException;

    boolean update(Product product) throws DatabaseErrorException;
    void eliminar(Product product) throws DatabaseErrorException;
}
