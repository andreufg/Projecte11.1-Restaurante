package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.entidades.producttypes.Product;
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
import java.util.List;
import java.util.ResourceBundle;

public class ProductosController implements Initializable {
    private Initializable controladorPadre;
    private CrearProductoController crearProductoController;
    private ProductRepository productRepository;

    @FXML
    private ListView<Product> listViewProductos;

    public ProductosController(Initializable initializable, ProductRepository productRepository) {
        this.controladorPadre = initializable;
        this.productRepository = productRepository;
        this.crearProductoController = new CrearProductoController(this,productRepository);
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
