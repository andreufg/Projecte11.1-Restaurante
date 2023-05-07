package es.progcipfpbatoi.model.repositorios;

import es.progcipfpbatoi.model.entidades.Order;

import java.util.ArrayList;

public interface InmemoryPedidosRepository {
    ArrayList<Order> findAll();
    int size();
    void add(Order order);
    void remove();
    void remove(Order order);
    boolean save(Order pedidos);
    ArrayList<Order> findAll(String text);
    Order findByCod(String code);

}
