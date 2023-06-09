package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.entidades.producttypes.Product;
import es.progcipfpbatoi.model.repositorios.ProductRepository;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


public class ProductosListaController extends ListCell<Product>  {
    @FXML
    private AnchorPane root;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ImageView categoriaImagen;


    private Product product;

    private ProductRepository productRepository;

    public ProductosListaController(ProductRepository productRepository) {
        this.productRepository = productRepository;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/vistas/list_item.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void setCategoryImage(Product product) {
        try {
            switch (product.getPrefixCode()) {
                case "m" -> categoriaImagen.setImage(new Image(getPathImage("/imagen/bocata.png")));
                case "b" -> categoriaImagen.setImage(new Image(getPathImage("/imagen/bebida.png")));
                case "e" -> categoriaImagen.setImage(new Image(getPathImage("/imagen/entrante.png")));
                case "p" -> categoriaImagen.setImage(new Image(getPathImage("/imagen/postre.png")));

            }
           }catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
    private String getPathImage(String fileName) throws URISyntaxException {

        return getClass().getResource(fileName).toURI().toString();
    }
    @Override
    protected void updateItem(Product product, boolean empty) {

        super.updateItem(product, empty);

        if (empty) {
            setGraphic(null);
        } else {
            descriptionLabel.setText(product.toString());
            this.product = productRepository.findProduct(descriptionLabel.getText());
            setCategoryImage(product);
            setGraphic(root);
        }
    }
}
