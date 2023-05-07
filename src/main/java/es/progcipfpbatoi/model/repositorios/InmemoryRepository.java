package es.progcipfpbatoi.model.repositorios;

import es.progcipfpbatoi.model.entidades.Order;
import es.progcipfpbatoi.model.entidades.producttypes.Product;

import java.util.ArrayList;

public interface InmemoryRepository {
    ArrayList<Order> findAll();
    int size();
    void add(Order order);
    boolean save(Order pedidos);
    ArrayList<Order> findAll(String text);
    Order findByCod(String code);
    Order findByText(String text);
}
