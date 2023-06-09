package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.entidades.Order;
import es.progcipfpbatoi.model.entidades.producttypes.Product;
import es.progcipfpbatoi.model.repositorios.PedidosRepository;
import es.progcipfpbatoi.model.repositorios.ProductRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class PedidosPendientesListaController extends ListCell<Order> {
    @FXML
    private AnchorPane root;
    @FXML
    private Label descripcion;
    private PedidosRepository pedidosRepository;
    private Order order;

    public PedidosPendientesListaController(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/vistas/list_pedidos_pendientes.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void updateItem(Order order, boolean empty) {

        super.updateItem(order, empty);

        if (empty) {
            setGraphic(null);
        } else {
            descripcion.setText(order.toString());
            this.order = pedidosRepository.findByText(descripcion.getText());
            setGraphic(root);
        }
    }

}
