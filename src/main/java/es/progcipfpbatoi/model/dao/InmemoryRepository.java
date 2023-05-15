package es.progcipfpbatoi.model.dao;

import es.progcipfpbatoi.model.dto.Order;

import java.util.ArrayList;

public interface InmemoryRepository {
    ArrayList<Order> findAll();
    int size();
    void add(Order order);
    void remove();
    boolean save(Order pedidos);
    ArrayList<Order> findAll(String text);
    Order findByCod(String code);
    Order findByText(String text);
}