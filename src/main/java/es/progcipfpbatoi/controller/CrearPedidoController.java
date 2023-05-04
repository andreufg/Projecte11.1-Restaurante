package es.progcipfpbatoi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CrearPedidoController implements Initializable {
    private Initializable controladorPadre;
    private String vistaPadre;

    public CrearPedidoController(Initializable controladorPadre,String vistaPadre) {
        this.controladorPadre = controladorPadre;
        this.vistaPadre = vistaPadre;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
