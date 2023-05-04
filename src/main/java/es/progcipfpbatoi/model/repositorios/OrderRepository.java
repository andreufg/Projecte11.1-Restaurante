package es.progcipfpbatoi.model.repositorios;


import es.progcipfpbatoi.model.entidades.Order;

import java.util.ArrayList;

public class OrderRepository  {

    private ArrayList<Order> orderList;

    public OrderRepository() {
        this.orderList = new ArrayList<>();
    }

    public void add(Order order) {
        this.orderList.add(order);
    }

    public int size() {
        return this.orderList.size();
    }

    public ArrayList<Order> findAll() {
        return orderList;
    }

    public Order findByCod(String code) {
        Order orderBuscada = new Order(code);
        if (orderList.contains(orderBuscada)) {
            return orderList.get(orderList.indexOf(orderBuscada));
        }

        return null;
    }
}
