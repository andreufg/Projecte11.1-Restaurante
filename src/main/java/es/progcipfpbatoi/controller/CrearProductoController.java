package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;
import es.progcipfpbatoi.model.dto.producttypes.Product;
import es.progcipfpbatoi.model.dto.producttypes.types.*;
import es.progcipfpbatoi.model.repositorios.ProductRepository;
import es.progcipfpbatoi.util.AlertMessages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    @FXML
    private AnchorPane bebida;
    @FXML
    private AnchorPane postre;
    @FXML
    private AnchorPane entrante;
    @FXML
    private CheckBox rellenable;
    @FXML
    private ComboBox<String> tamanyo;
    @FXML
    private Text extras;
    @FXML
    private Spinner<Integer> cantidad;
    @FXML
    private RadioButton celiaco;
    @FXML
    private RadioButton diabetico;


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
    private void mostrarExtras(ActionEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (tipo.getValue().equals("Bebida")) {
            this.bebida.setVisible(true);
            this.postre.setVisible(false);
            this.entrante.setVisible(false);
        } else if (tipo.getValue().equals("Postre")) {
            this.postre.setVisible(true);
            this.entrante.setVisible(false);
            this.bebida.setVisible(false);
        } else if (tipo.getValue().equals("Entrante")) {
            this.entrante.setVisible(true);
            this.bebida.setVisible(false);
            this.postre.setVisible(false);
        } else {
            AlertMessages.mostrarAlertInformacion("Este producto no tiene extras");
        }
    }

    @FXML
    private void confirmar(ActionEvent event) throws DatabaseErrorException, NotFoundException, SQLException {
        if (tipo.getValue() == null || nombre.getText() == null || iva.getText() == null || precio.getText() == null || descuento.getText() == null) {
            System.out.println("No se han seleccionado productos.");
        } else if (!esNumero(iva.getText()) || !esNumeroConDecimal(precio.getText()) || !esNumero(descuento.getText())) {
            System.out.println("El iva, el precio y el descuento deben ser numericos o estar dentro del rango permitido");
        } else {
            if (product != null) {
                product.setName(nombre.getText());
                product.setVat(retornarValorNumerico(iva.getText()));
                product.setPrize(retornarValorNumerico(precio.getText()));
                product.setDiscount(retornarValorNumerico(descuento.getText()));
                if (tipo.getValue().equals("Bebida")) {
                    if (rellenable.isSelected()) {
                        ((Drink) product).setRefillable(true);
                    } else {
                        ((Drink) product).setRefillable(false);
                    }
                    if (tamanyo.getValue().equals("Pequeño")) {
                        ((Drink) product).setSize(Size.SMALL);
                    } else if (tamanyo.getValue().equals("Grande")) {
                        ((Drink) product).setSize(Size.BIG);
                    } else {
                        ((Drink) product).setSize(Size.NORMAL);
                    }
                    productRepository.update(product);
                    AlertMessages.mostrarAlertInformacion("Cambios guardados con éxito");

                    try {
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        ChangeScene.change(stage, controladorPadre, ruta);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            } else {
                Product product;
                if (tipo.getValue().equals("Bebida")) {
                    if (rellenable.isSelected()) {
                        if (tamanyo.getValue().equals("Pequeño")) {
                            product = new Drink(productRepository.codigo(), nombre.getText(), retornarValorNumerico(precio.getText()), retornarValorNumerico(descuento.getText()), retornarValorNumerico(iva.getText()), Size.SMALL, true);
                        } else if (tamanyo.getValue().equals("Grande")) {
                            product = new Drink(productRepository.codigo(), nombre.getText(), retornarValorNumerico(precio.getText()), retornarValorNumerico(descuento.getText()), retornarValorNumerico(iva.getText()), Size.BIG, true);
                        } else {
                            product = new Drink(productRepository.codigo(), nombre.getText(), retornarValorNumerico(precio.getText()), retornarValorNumerico(descuento.getText()), retornarValorNumerico(iva.getText()), Size.NORMAL, true);
                        }
                    } else {
                        if (tamanyo.getValue().equals("Pequeño")) {
                            product = new Drink(productRepository.codigo(), nombre.getText(), retornarValorNumerico(precio.getText()), retornarValorNumerico(descuento.getText()), retornarValorNumerico(iva.getText()), Size.SMALL, false);
                        } else if (tamanyo.getValue().equals("Grande")) {
                            product = new Drink(productRepository.codigo(), nombre.getText(), retornarValorNumerico(precio.getText()), retornarValorNumerico(descuento.getText()), retornarValorNumerico(iva.getText()), Size.BIG, false);
                        } else {
                            product = new Drink(productRepository.codigo(), nombre.getText(), retornarValorNumerico(precio.getText()), retornarValorNumerico(descuento.getText()), retornarValorNumerico(iva.getText()), Size.NORMAL, false);
                        }
                    }

                } else if (tipo.getValue().equals("Montadito")) {
                    product = new Sandwich(productRepository.codigo(), nombre.getText(), retornarValorNumerico(precio.getText()), retornarValorNumerico(descuento.getText()), retornarValorNumerico(iva.getText()));
                } else if (tipo.getValue().equals("Postre")) {
                    if (celiaco.isSelected() && diabetico.isSelected()) {
                        product = new Desert(productRepository.codigo(), nombre.getText(), retornarValorNumerico(precio.getText()), retornarValorNumerico(descuento.getText()), retornarValorNumerico(iva.getText()), Characteristic.CELIAC_SUITABLE, Characteristic.DIABETIC_SUITABLE, Characteristic.CELIAC_SUITABLE);
                    } else if (celiaco.isSelected()) {
                        product = new Desert(productRepository.codigo(), nombre.getText(), retornarValorNumerico(precio.getText()), retornarValorNumerico(descuento.getText()), retornarValorNumerico(iva.getText()), Characteristic.CELIAC_SUITABLE);

                    } else if (diabetico.isSelected()) {
                        product = new Desert(productRepository.codigo(), nombre.getText(), retornarValorNumerico(precio.getText()), retornarValorNumerico(descuento.getText()), retornarValorNumerico(iva.getText()), Characteristic.CELIAC_SUITABLE);
                    } else {
                        product = new Desert(productRepository.codigo(), nombre.getText(), retornarValorNumerico(precio.getText()), retornarValorNumerico(descuento.getText()), retornarValorNumerico(iva.getText()));
                    }

                } else {
                    product = new Starter(productRepository.codigo(), nombre.getText(), retornarValorNumerico(precio.getText()), retornarValorNumerico(descuento.getText()), retornarValorNumerico(iva.getText()));
                    ((Starter) product).setRation(cantidad.getValue());
                }

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


        private float retornarValorNumerico (String text){
            boolean tieneComa = text.contains(",");
            if (tieneComa) {
                text = text.replace(",", ".");
                return Float.parseFloat(text);
            }
            if (esNumero(text)) {
                return Float.parseFloat(text);
            }
            return 0;
        }

        private boolean esNumeroConDecimal (String text){
            try {
                boolean tieneComa = text.contains(",");
                if (tieneComa) {
                    text = text.replace(",", ".");
                }
                Float.valueOf(text);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        private boolean esNumero (String text){
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
        private void volverAtras (ActionEvent event){

            try {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                ChangeScene.change(stage, controladorPadre, ruta);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            if (product != null) {
                this.iva.setText(product.getVat());
                this.nombre.setText(product.getName());
                this.precio.setText(product.getPrecio());
                this.descuento.setText(product.getDiscount());
                if (product instanceof Drink) {
                    this.bebida.setVisible(true);
                    this.postre.setVisible(false);
                    this.entrante.setVisible(false);
                    this.tipo.getSelectionModel().select("Bebida");
                    if (((Drink) product).isRefillable()) {
                        rellenable.selectedProperty().set(true);
                    }
                    tamanyo.getSelectionModel().select(((Drink) product).getTamanyo());
                } else if (product instanceof Desert) {
                    this.bebida.setVisible(false);
                    this.postre.setVisible(true);
                    this.entrante.setVisible(false);
                    this.tipo.getSelectionModel().select("Postre");
                } else if (product instanceof Sandwich) {
                    this.tipo.getSelectionModel().select("Montadito");
                } else {
                    this.tipo.getSelectionModel().select("Entrante");
                    this.bebida.setVisible(false);
                    this.postre.setVisible(false);
                    this.entrante.setVisible(true);
                }
            } else {
                this.bebida.setVisible(false);
                this.postre.setVisible(false);
                this.entrante.setVisible(false);
            }
            ObservableList<String> tipos =
                    FXCollections.observableArrayList(
                            "Bebida",
                            "Postre",
                            "Montadito",
                            "Entrante"
                    );
            tipo.setItems(tipos);

            ObservableList<String> tamanyo =
                    FXCollections.observableArrayList(
                            "Pequeño",
                            "Mediano",
                            "Grande"
                    );
            this.tamanyo.setItems(tamanyo);

        }
    }
