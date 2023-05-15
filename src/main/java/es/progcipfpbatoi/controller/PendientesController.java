package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.dto.Order;
import es.progcipfpbatoi.model.repositorios.HistorialRepository;
import es.progcipfpbatoi.model.repositorios.PedidosRepository;
import es.progcipfpbatoi.model.repositorios.ProductRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class PendientesController implements Initializable {
    @FXML
    private ListView<Order> listViewPedidos;
    private ArrayList<Order> listaPedidos;
    private PedidosRepository pedidosRepository;
    private HistorialRepository historialRepository;
    private ObservableList<Order> pedidosSeleccionados;
    private Initializable controladorPadre;
    private ProductRepository productRepository;

    public PendientesController(Initializable initializable,PedidosRepository pedidosRepository, HistorialRepository historialRepository, ProductRepository productRepository) {
        this.pedidosRepository = pedidosRepository;
        this.controladorPadre = initializable;
        this.productRepository = productRepository;
        this.historialRepository = historialRepository;
        this.listaPedidos = pedidosRepository.findAll();
    }
    @FXML
    private void verDetalle(ActionEvent event) {
        pedidosSeleccionados = listViewPedidos.getSelectionModel().getSelectedItems();
        if (pedidosSeleccionados.isEmpty()) {
        }else {
            try {

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Order order = pedidosSeleccionados.get(0);
                DetalleController detalleController = new DetalleController(this, order);

                ChangeScene.change(stage, detalleController,"/vistas/list_detalle_pedido.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void volverAtras(ActionEvent event) {

        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ChangeScene.change(stage, controladorPadre, "/vistas/vista_principal.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void cambiarNuevoPedido(ActionEvent event) {

        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            CrearPedidoController crearPedidoController = new CrearPedidoController(this, "/vistas/vista_pendientes.fxml", pedidosRepository, productRepository);
            ChangeScene.change(stage, crearPedidoController, "/vistas/vista_crear_pedido.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void cancelarPedido() {
        pedidosSeleccionados = listViewPedidos.getSelectionModel().getSelectedItems();
        if (!pedidosSeleccionados.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            String respuesta;
            boolean salir = false;
            do {
                System.out.println("¿Deseas cancelar el pedido?");
                respuesta = scanner.next();
                if (respuesta.equals("si") || respuesta.equals("no") || respuesta.equals("Si") || respuesta.equals("No")) {
                    salir = true;
                }
            } while (!salir);
            if (respuesta.equals("si") || respuesta.equals("Si")) {
                for (Order order : pedidosSeleccionados) {
                    pedidosRepository.remove(order);
                    actualizarListaPedidos();
                }
            }
        }
    }

    @FXML
    private void prepararPedido(ActionEvent event) {
        if (listaPedidos.size() > 0) {
            historialRepository.add(listaPedidos.get(0));
            if (historialRepository.save(listaPedidos.get(0))) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Preparar Pedido");
                alert.setHeaderText(null);
                alert.setContentText("Has preparado el predido " + listaPedidos.get(0).toString());
                alert.showAndWait();
                System.out.println("Preparado con exito");
                listaPedidos.remove(listaPedidos.get(0));
                actualizarListaPedidos();

            }
        }
    }

    private void actualizarListaPedidos() {
        this.listaPedidos = pedidosRepository.findAll();
        listViewPedidos.setItems(getData());
    }

    private ObservableList<Order> getData() {
        return FXCollections.observableArrayList(listaPedidos);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (listaPedidos.size() > 0) {
            this.listViewPedidos.setItems(getData());
            this.listViewPedidos.setCellFactory((ListView<Order> l) -> new PedidosPendientesListaController(pedidosRepository));
            this.listViewPedidos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }
    }
}


