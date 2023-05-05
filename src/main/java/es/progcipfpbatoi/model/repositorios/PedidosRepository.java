package es.progcipfpbatoi.model.repositorios;

import es.progcipfpbatoi.model.entidades.producttypes.Product;

import java.util.ArrayList;

public class PedidosRepository implements InmemoryRepository{
    @Override
    public ArrayList<Product> findAll() {
        return null;
    }

    @Override
    public boolean save(Product usuario) {
        return false;
    }

    @Override
    public ArrayList<Product> findAll(String text) {
        return null;
    }
}
