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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CrearPedidoController implements Initializable {
    @FXML
    private ListView<Product> listaProductos;
    private Initializable controladorPadre;
    private ArrayList<Product> lista;
    private String vistaPadre;

    public CrearPedidoController(Initializable controladorPadre,String vistaPadre) {
        this.controladorPadre = controladorPadre;
        this.vistaPadre = vistaPadre;
        ProductRepository productRepository = new ProductRepository();
        this.lista = productRepository.findAll();
    }

    @FXML
    private void volverAtras(ActionEvent event) {

        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            InicioController inicioController = new InicioController();
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

    }
}
