package es.progcipfpbatoi.model.repositorios;

import es.progcipfpbatoi.model.entidades.producttypes.Product;

import java.util.ArrayList;

public interface InmemoryRepository {
    ArrayList<Product> findAll();
    ArrayList<Product> findAll(String text);
}
