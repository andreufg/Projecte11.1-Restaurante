package es.progcipfpbatoi.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicioController implements Initializable {
    @FXML
    private void cambiarAPedidos(ActionEvent event) {

        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            PendientesController pendientesController = new PendientesController();
            ChangeScene.change(stage, pendientesController, "/vistas/vista_pendientes.fxml");
        } catch (IOException ex) {
            System.out.println("Malo");
            ex.printStackTrace();
        }
    }
    @FXML
    private void cambiarAHistorialPedidos(ActionEvent event) {

        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            CrearPedidoController crearPedidoController = new CrearPedidoController();
            ChangeScene.change(stage, crearPedidoController, "/vistas/vista_crear_pedido.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
