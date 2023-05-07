package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.repositorios.HistorialRepository;
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
    private HistorialController historialController;
    private PendientesController pendientesController;
    private HistorialRepository historialRepository;
    private PedidosRepository pedidosRepository;

    public InicioController(HistorialRepository historialRepository, PedidosRepository pedidosRepository) {
        this.historialRepository = historialRepository;
        this.pedidosRepository = pedidosRepository;
        this.historialController = new HistorialController(this,historialRepository);
        this.pendientesController = new PendientesController(this,pedidosRepository,historialRepository);
    }

    @FXML
    private void cambiarAPedidos(ActionEvent event) {

        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
