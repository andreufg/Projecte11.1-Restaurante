package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.dto.Order;
import es.progcipfpbatoi.model.repositorios.HistorialRepository;
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

public class HistorialController implements Initializable {
    @FXML
    private ListView<Order> listViewHistorialPedidos;
    private ArrayList<Order> listaPedidos;
    private HistorialRepository historialRepository;
    private Initializable controladorPadre;

    public HistorialController(Initializable controladorPadre,HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
        this.controladorPadre = controladorPadre;
        this.listaPedidos = this.historialRepository.findAll();
        System.out.println(listaPedidos.size());
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
    private ObservableList<Order> getData() {
        return FXCollections.observableArrayList(listaPedidos);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (listaPedidos.size() > 0) {
            this.listViewHistorialPedidos.setItems(getData());
        }
    }
}
