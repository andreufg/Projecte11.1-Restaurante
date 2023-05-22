package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.dto.Order;
import es.progcipfpbatoi.model.dto.producttypes.Product;
import es.progcipfpbatoi.model.repositorios.PedidosRepository;
import es.progcipfpbatoi.model.repositorios.ProductRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CrearPedidoController implements Initializable {
    @FXML
    private ListView<Product> listaProductos;

    @FXML
    private Button confirmar;

    @FXML
    private TextField nombre;

    private Initializable controladorPadre;
    private ArrayList<Product> lista;
    private String vistaPadre;
    private ProductRepository productRepository;
    private PedidosRepository pedidosRepository;
    private ObservableList<Product> productosSeleccionados;
    private Order order;

    public CrearPedidoController(Initializable controladorPadre, String vistaPadre, PedidosRepository pedidosRepository, ProductRepository productRepository) {
        this.controladorPadre = controladorPadre;
        this.pedidosRepository = pedidosRepository;
        this.vistaPadre = vistaPadre;
        this.productRepository = productRepository;
        this.lista = productRepository.findAll();
    }

    @FXML
    private void confirmar(ActionEvent event) {
        productosSeleccionados = listaProductos.getSelectionModel().getSelectedItems();
        if (productosSeleccionados.isEmpty()) {
            System.out.println("No se han seleccionado productos.");
        } else {
            if (nombre.getText().isBlank()) {
                if (pedidosRepository.size() == 0) {
                    order = new Order("c1", "anonimo");
                } else {
                    int numero = pedidosRepository.size() + 1;
                    order = new Order("c" + numero, "anonimo");
                }
            } else {
                if (pedidosRepository.size() == 0) {
                    order = new Order("c1", nombre.getText());
                } else {
                    int numero = pedidosRepository.size() + 1;
                    order = new Order("c" + numero, nombre.getText());
                }
            }
        }
        for (Product producto : productosSeleccionados) {
            order.addNewProduct(producto);
        }

        System.out.println(order.getProducts());
        pedidosRepository.add(order);
        if (pedidosRepository.save(order)) {
            System.out.println("Pedido guardado con exito");
            try {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                ChangeScene.change(stage, controladorPadre, vistaPadre);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }



    @FXML
    private void volverAtras(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ChangeScene.change(stage, controladorPadre, vistaPadre);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private ObservableList<Product> getData() {
        return FXCollections.observableArrayList(lista);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listaProductos.setItems(getData());
        listaProductos.setCellFactory((ListView<Product> l) -> new ProductosListaController(productRepository));
        listaProductos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }
}
