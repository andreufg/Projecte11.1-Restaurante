package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.entidades.Order;
import es.progcipfpbatoi.model.entidades.producttypes.Product;
import es.progcipfpbatoi.model.repositorios.PedidosRepository;
import es.progcipfpbatoi.model.repositorios.ProductRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PendientesController implements Initializable {
    @FXML
    private ListView<Order> listViewPedidos;
    private ArrayList<Order> listaPedidos;
    private PedidosRepository pedidosRepository;

    public PendientesController(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
        this.listaPedidos = pedidosRepository.findAll();
    }

    @FXML
    private void volverAtras(ActionEvent event) {

        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            InicioController inicioController = new InicioController();
            ChangeScene.change(stage, inicioController, "/vistas/vista_principal.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void cambiarNuevoPedido(ActionEvent event) {

        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            CrearPedidoController crearPedidoController = new CrearPedidoController(this,"/vistas/vista_pendientes.fxml", pedidosRepository);
            ChangeScene.change(stage, crearPedidoController, "/vistas/vista_crear_pedido.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private ObservableList<Order> getData() {
        return FXCollections.observableArrayList(listaPedidos);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (listaPedidos.size()>0){
            this.listViewPedidos.setItems(getData());
            this.listViewPedidos.setCellFactory((ListView<Order> l) -> new PedidosPendientesListaController(pedidosRepository));
            this.listViewPedidos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }
    }
}


