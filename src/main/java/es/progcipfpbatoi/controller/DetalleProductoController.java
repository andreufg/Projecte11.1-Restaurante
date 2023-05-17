package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.dto.producttypes.Product;
import es.progcipfpbatoi.model.repositorios.ProductRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetalleProductoController implements Initializable {
    @FXML
    private Text cod;
    @FXML
    private Text coste;
    @FXML
    private TextField nombre;
    @FXML
    private TextField precio;
    @FXML
    private TextField descuento;
    @FXML
    private TextField iva;
    @FXML
    private ImageView imagen;
    private Initializable initializable;
    private Product product;
    private CrearProductoController crearProductoController;
    private ProductRepository productRepository;

    public DetalleProductoController(Initializable initializable, Product product, ProductRepository productRepository) {
        this.initializable = initializable;
        this.product = product;
        this.productRepository = productRepository;
    }

    private String getPathImage(String fileName) throws URISyntaxException {
        return getClass().getResource(fileName).toURI().toString();
    }

    private void setImagen(Product product) {
        try {
            switch (product.getPrefixCode()) {
                case "m" -> imagen.setImage(new Image(getPathImage("/imagen/bocata.png")));
                case "b" -> imagen.setImage(new Image(getPathImage("/imagen/bebida.png")));
                case "e" -> imagen.setImage(new Image(getPathImage("/imagen/entrante.png")));
                case "p" -> imagen.setImage(new Image(getPathImage("/imagen/postre.png")));

            }
        }catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void volverAtras(ActionEvent event) {

        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ChangeScene.change(stage, initializable, "/vistas/vista_productos.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void modificar(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            crearProductoController = new CrearProductoController(this,productRepository,"/vistas/vista_detalle_producto.fxml",product);
            ChangeScene.change(stage, crearProductoController, "/vistas/vista_crear_producto.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void eliminar(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            productRepository.eliminarProducto(product);
            ChangeScene.change(stage, initializable, "/vistas/vista_productos.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.iva.setText(product.getVat());
        this.nombre.setText(product.getName());
        this.cod.setText(product.getPrefixCod());
        this.precio.setText(product.getPrecio());
        this.descuento.setText(product.getDiscount());
        this.coste.setText(product.getPrizeString());
        setImagen(product);
    }
}
