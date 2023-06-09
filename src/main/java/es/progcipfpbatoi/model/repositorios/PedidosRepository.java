package es.progcipfpbatoi.model.repositorios;

import es.progcipfpbatoi.model.entidades.Order;
import es.progcipfpbatoi.model.entidades.producttypes.Product;
import es.progcipfpbatoi.model.entidades.producttypes.types.Sandwich;

import java.util.ArrayList;

public class PedidosRepository implements InmemoryRepository{
    private ArrayList<Order> listaPedidos;

    public PedidosRepository() {
        listaPedidos = new ArrayList<>();
    }

    @Override
    public ArrayList<Order> findAll() {
        return listaPedidos;
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
