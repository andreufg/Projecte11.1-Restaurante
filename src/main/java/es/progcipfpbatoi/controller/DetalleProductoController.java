package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.entidades.producttypes.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
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

    public DetalleProductoController(Initializable initializable, Product product) {
        this.initializable = initializable;
        this.product = product;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
