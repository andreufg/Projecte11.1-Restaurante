package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.entidades.Order;
import es.progcipfpbatoi.model.entidades.producttypes.Product;
import es.progcipfpbatoi.model.entidades.producttypes.types.Desert;
import es.progcipfpbatoi.model.entidades.producttypes.types.Drink;
import es.progcipfpbatoi.model.entidades.producttypes.types.Sandwich;
import es.progcipfpbatoi.model.repositorios.ProductRepository;
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
import java.util.ResourceBundle;
import java.util.stream.Collector;

public class CrearProductoController implements Initializable {
    private Initializable controladorPadre;
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

    public CrearProductoController(Initializable initializable, ProductRepository productRepository) {
        this.controladorPadre = initializable;
        tipo = new ComboBox<>();

    }

    @FXML
    private void confirmar(ActionEvent event) {
        if (tipo.getValue() == null||nombre.getText()==null||iva.getText()==null||precio.getText()==null||descuento.getText()==null) {
            System.out.println("No se han seleccionado productos.");
        } else if (!esNumero(iva.getText())||!esNumeroConDecimal(precio.getText())||!esNumero(descuento.getText())) {
            System.out.println("El iva, el precio y el descuento deben ser numericos o estar dentro del rango permitido");
        } else {
            if (tipo.getValue().equals("Bebida")){
                Drink bebida = new Drink("2",nombre.getText(),retornarValorNumerico(precio.getText()),retornarValorNumerico(descuento.getText()),retornarValorNumerico(iva.getText()));
            } else if (tipo.getValue().equals("Montadito")){
                Sandwich sandwich = new Sandwich("2",nombre.getText(),retornarValorNumerico(precio.getText()),retornarValorNumerico(descuento.getText()),retornarValorNumerico(iva.getText()));
            }else if (tipo.getValue().equals("Postre")){
                Desert desert = new Desert("2",nombre.getText(),retornarValorNumerico(precio.getText()),retornarValorNumerico(descuento.getText()),retornarValorNumerico(iva.getText()));
            }else {

            }

        }
//            if (!checkBox.isSelected()) {
//                if (pedidosRepository.size() == 0) {
//                    order = new Order("c1");
//                } else {
//                    int numero = pedidosRepository.size() + 1;
//                    order = new Order("c" + numero);
//                }
//            } else {
//                if (pedidosRepository.size() == 0) {
//                    order = new Order("c1", nombre.getText(), fecha.getAccessibleText());
//                } else {
//                    int numero = pedidosRepository.size() + 1;
//                    order = new Order("c" + numero, nombre.getText(), fecha.getAccessibleText());
//                }
//            }
//        }
//        for (Product producto : productosSeleccionados) {
//            order.addNewProduct(producto);
//        }
//
//        System.out.println(order.getProducts());
//        pedidosRepository.add(order);
//        if (pedidosRepository.save(order)) {
//            System.out.println("Pedido guardado con exito");
//            try {
//                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                ChangeScene.change(stage, controladorPadre, vistaPadre);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
    }

    private float retornarValorNumerico(String text){
        if (esNumero(text)) {
            return Float.parseFloat(text);
        }
        return 0;
    }
    private boolean esNumeroConDecimal(String text){
        try {
            Float.valueOf(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean esNumero(String text){
        try {
            Integer.valueOf(text);
            if (Integer.parseInt(text)>=0&&Integer.parseInt(text)<=100){
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
            ChangeScene.change(stage, controladorPadre, "/vistas/vista_productos.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
