package es.progcipfpbatoi.model.repositorios;

import es.progcipfpbatoi.model.dto.Order;
import es.progcipfpbatoi.model.repositorios.InmemoryRepository;

import java.util.ArrayList;

public class PedidosRepository implements InmemoryRepository {
    private ArrayList<Order> listaPedidos;

    public PedidosRepository() {
        listaPedidos = new ArrayList<>();
    }

    @Override
    public ArrayList<Order> findAll() {
        return listaPedidos;
    }

    public ArrayList<Order> listaOrdenada(){
        ArrayList<Order> orders = new ArrayList<>();
        int n = orders.size();
        boolean swapped;

        do {
            swapped = false;

            for (int i = 0; i < orders.size(); i++) {
                Order order1 = orders.get(i);
                Order order2 = orders.get(i + 1);

                if (order1.getCreado().compareTo(order2.getCreado()) > 0) {
                    // Swap orders
                    orders.set(i, order2);
                    orders.set(i + 1, order1);
                    swapped = true;
                }
            }

            n--;
        } while (swapped);

        return orders;
    }

    @Override
    public int size() {
        return listaPedidos.size();
    }

    @Override
    public void add(Order pedidos) {
        listaPedidos.add(pedidos);
    }

    @Override
    public void remove() {
        listaPedidos.remove(0);
    }


    public void remove(Order order) {
        listaPedidos.remove(order);
    }

    @Override
    public boolean save(Order pedidos) {
        for (Order pedido: listaPedidos) {
            if (pedido.getCode().equals(pedidos.getCode())){
                return true;
            }
        }
        return false;
    }
    @Override
    public Order findByCod(String code) {
        Order orderBuscada = new Order(code);
        if (listaPedidos.contains(orderBuscada)) {
            return listaPedidos.get(listaPedidos.indexOf(orderBuscada));
        }

        return null;
    }

    @Override
    public Order findByText(String text) {
        for (Order order:listaPedidos) {
            if (order.empiezaPor(text)) {
                return order;
            }
        }


        return null;
    }


    @Override
    public ArrayList<Order> findAll(String text) {
        ArrayList<Order> listaPedidosFiltrados = new ArrayList<>();
        for (Order pedido: listaPedidos) {
            if (pedido.empiezaPor(text)){
                listaPedidosFiltrados.add(pedido);
            }
        }
        return listaPedidosFiltrados;
    }
}
