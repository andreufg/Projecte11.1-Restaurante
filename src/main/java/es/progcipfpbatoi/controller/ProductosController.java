package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.model.dto.producttypes.Product;
import es.progcipfpbatoi.model.repositorios.ProductRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductosController implements Initializable {
    @FXML
    private ListView<Product> listViewProductos;

    private Initializable controladorPadre;
    private CrearProductoController crearProductoController;
    private ProductRepository productRepository;
    private ObservableList<Product> productosSeleccionados;
    private DetalleProductoController detalleProductoController;


    public ProductosController(Initializable initializable, ProductRepository productRepository) {
        this.controladorPadre = initializable;
        this.productRepository = productRepository;
        this.crearProductoController = new CrearProductoController(this,"/vistas/vista_productos.fxml", productRepository);
    }

    @FXML
    private void darDeBaja(ActionEvent event) {
        productosSeleccionados = listViewProductos.getSelectionModel().getSelectedItems();
        if (productosSeleccionados.isEmpty()) {
        } else {
            try {
                Product product = productosSeleccionados.get(0);
                productRepository.eliminarProducto(product);
                listViewProductos.getItems().remove(product);
            } catch (DatabaseErrorException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    private void cambiarCrearProductos(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ChangeScene.change(stage, crearProductoController, "/vistas/vista_crear_producto.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void verDetalle(ActionEvent event) {
        productosSeleccionados = listViewProductos.getSelectionModel().getSelectedItems();
        if (productosSeleccionados.isEmpty()) {
        } else {
            try {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Product product = productosSeleccionados.get(0);
                this.detalleProductoController = new DetalleProductoController(this,product, productRepository);
                ChangeScene.change(stage, detalleProductoController, "/vistas/vista_detalle_producto.fxml");
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

    private ObservableList<Product> getData() {
        return FXCollections.observableArrayList(productRepository.getProductsCreadosLista());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (productRepository.getProductsCreadosLista().size() > 0) {
            this.listViewProductos.setItems(getData());
            this.listViewProductos.setCellFactory((ListView<Product> l) -> new ProductosListaController(productRepository));
        }
    }
}
