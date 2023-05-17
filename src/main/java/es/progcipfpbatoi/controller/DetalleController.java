package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.dto.Order;
import es.progcipfpbatoi.model.dto.producttypes.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DetalleController implements Initializable {
    @FXML
    private Label cod;
    @FXML
    private Label fecha;
    @FXML
    private Label cliente;
    @FXML
    private Label total;
    @FXML
    private ListView<Product> listViewProductos;
    private Initializable initializable;
    private Order order;
    private ArrayList<Product> listaProductos;

    public DetalleController(Initializable initializable, Order order) {
        this.initializable = initializable;
        this.order = order;

    }
    @FXML
    private void volverAtras(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ChangeScene.change(stage, initializable, "/vistas/vista_pendientes.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private ObservableList<Product> getData() {
        return FXCollections.observableArrayList(listaProductos);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.listaProductos = order.getProducts();
        this.total.setText(order.getOrderPrize() + "€");
        this.cod.setText(order.getCode());
        this.fecha.setText(order.getCreatedOn());
        this.cliente.setText(order.getClientName());
        listViewProductos.setItems(getData());
    }
}
