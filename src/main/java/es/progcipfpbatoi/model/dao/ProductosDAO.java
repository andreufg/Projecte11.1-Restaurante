package es.progcipfpbatoi.model.dao;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;
import es.progcipfpbatoi.model.dto.producttypes.Product;

import java.util.ArrayList;

public interface ProductosDAO {
    /**
     *  Obtiene todas las tareas
     */
    ArrayList<Product> findAll();

    /**
     * Obtiene todas las tareas que comiencen por @text
     * @param text
     * @return
     */
    ArrayList<Product> findAll(String text);

    /**
     * Obtiene la tarea cuyo id sea @id
     * @param id
     * @return
     * @throws NotFoundException
     */
    Product getById(int id) throws NotFoundException, DatabaseErrorException;

    /**
     * Almacena la tarea o la actualiza en caso de existir
     * @param tarea
     * @return
     * @throws DatabaseErrorException
     */
    boolean save(Product tarea) throws DatabaseErrorException;
}
