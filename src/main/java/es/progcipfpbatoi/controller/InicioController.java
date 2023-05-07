package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.repositorios.PedidosRepository;
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
            PedidosRepository pedidosRepository = new PedidosRepository();
            PendientesController pendientesController = new PendientesController(pedidosRepository);
            ChangeScene.change(stage, pendientesController, "/vistas/vista_pendientes.fxml");
        } catch (IOException ex) {
            System.out.println("Malo");
            ex.printStackTrace();
        }
    }
    @FXML
    private void cambiarAHistorial(ActionEvent event) {

        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            HistorialController historialController = new HistorialController();
            ChangeScene.change(stage, historialController, "/vistas/vista_historial_pedidos.fxml");
        } catch (IOException ex) {
            System.out.println("Malo");
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
