package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.dto.producttypes.Product;
import es.progcipfpbatoi.model.dto.producttypes.types.Desert;
import es.progcipfpbatoi.model.dto.producttypes.types.Drink;
import es.progcipfpbatoi.model.dto.producttypes.types.Sandwich;
import es.progcipfpbatoi.model.dto.producttypes.types.Starter;
import es.progcipfpbatoi.model.repositorios.ProductRepository;
import es.progcipfpbatoi.util.AlertMessages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CrearProductoController implements Initializable {
    private Initializable controladorPadre;
    private ArrayList<Product> productosCreados;
    @FXML
    private ComboBox<String> tipo;
    @FXML
    private TextField nombre;
    @FXML
    private TextField iva;
    @FXML
    private TextField precio;
    @FXML
    private TextField descuento;

    private ProductRepository productRepository;
    private String ruta;
    private Product product;

    public CrearProductoController(Initializable initializable, String ruta, ProductRepository productRepository) {
        this.controladorPadre = initializable;
        this.ruta = ruta;
        tipo = new ComboBox<>();
        this.productosCreados = new ArrayList<>();
        this.productRepository = productRepository;
    }

    public CrearProductoController(Initializable initializable, ProductRepository productRepository, String ruta, Product product) {
        this.controladorPadre = initializable;
        tipo = new ComboBox<>();
        this.product = product;
        this.ruta = ruta;
        this.productosCreados = new ArrayList<>();
        this.productRepository = productRepository;
    }

    @FXML
    private void confirmar(ActionEvent event) {
        if (tipo.getValue() == null || nombre.getText() == null || iva.getText() == null || precio.getText() == null || descuento.getText() == null) {
            System.out.println("No se han seleccionado productos.");
        } else if (!esNumero(iva.getText()) || !esNumeroConDecimal(precio.getText()) || !esNumero(descuento.getText())) {
            System.out.println("El iva, el precio y el descuento deben ser numericos o estar dentro del rango permitido");
        } else {
            if (product != null) {
                product.setName(nombre.getText());
                product.setVat(retornarValorNumerico(iva.getText()) / 100);
                product.setPrize(retornarValorNumerico(precio.getText()));
                product.setDiscount(retornarValorNumerico(descuento.getText()) / 100);
                AlertMessages.mostrarAlertInformacion("Cambios guardados con éxito");
                try {
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    ChangeScene.change(stage, controladorPadre, ruta);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                Product product;
                if (tipo.getValue().equals("Bebida")) {
                    product = new Drink(productRepository.codigo(), nombre.getText(), retornarValorNumerico(precio.getText()), retornarValorNumerico(descuento.getText()) / 100, retornarValorNumerico(iva.getText()) / 100);
                } else if (tipo.getValue().equals("Montadito")) {
                    product = new Sandwich(productRepository.codigo(), nombre.getText(), retornarValorNumerico(precio.getText()), retornarValorNumerico(descuento.getText()) / 100, retornarValorNumerico(iva.getText()) / 100);
                } else if (tipo.getValue().equals("Postre")) {
                    product = new Desert(productRepository.codigo(), nombre.getText(), retornarValorNumerico(precio.getText()), retornarValorNumerico(descuento.getText()) / 100, retornarValorNumerico(iva.getText()) / 100);
                } else {
                    product = new Starter(productRepository.codigo(), nombre.getText(), retornarValorNumerico(precio.getText()), retornarValorNumerico(descuento.getText()) / 100, retornarValorNumerico(iva.getText()) / 100);
                }
                productRepository.anyadirProducto(product);
                if (productRepository.save(product)) {
                    AlertMessages.mostrarAlertInformacion("Producto guardado con éxito");
                    try {
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        ChangeScene.change(stage, controladorPadre, ruta);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

    }


    private float retornarValorNumerico(String text) {
        boolean tieneComa = text.contains(",");
        if (tieneComa){
            text = text.replace(",",".");
            return Float.parseFloat(text);
        }
        if (esNumero(text)) {
            return Float.parseFloat(text);
        }
        return 0;
    }

    private boolean esNumeroConDecimal(String text) {
        try {
            boolean tieneComa = text.contains(",");
            if (tieneComa){
                text = text.replace(",",".");
            }
            Float.valueOf(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean esNumero(String text) {
        try {
            Integer.valueOf(text);
            if (Integer.parseInt(text) >= 0 && Integer.parseInt(text) <= 100) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @FXML
    private void volverAtras(ActionEvent event) {

        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ChangeScene.change(stage, controladorPadre, ruta);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (product != null) {
            this.iva.setText(product.getVat());
            this.nombre.setText(product.getName());
            this.precio.setText(product.getPrecio());
            this.descuento.setText(product.getDiscount());
            if (product instanceof Drink) {
                this.tipo.getSelectionModel().select("Bebida");
            } else if (product instanceof Desert) {
                this.tipo.getSelectionModel().select("Postre");
            } else if (product instanceof Sandwich) {
                this.tipo.getSelectionModel().select("Montadito");
            } else {
                this.tipo.getSelectionModel().select("Entrante");
            }
        }
        ObservableList<String> tipos =
                FXCollections.observableArrayList(
                        "Bebida",
                        "Postre",
                        "Montadito",
                        "Entrante"
                );
        tipo.setItems(tipos);

    }
}
