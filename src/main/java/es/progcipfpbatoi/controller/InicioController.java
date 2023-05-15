package es.progcipfpbatoi.controller;

import es.progcipfpbatoi.model.repositorios.HistorialRepository;
import es.progcipfpbatoi.model.repositorios.PedidosRepository;
import es.progcipfpbatoi.model.repositorios.ProductRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicioController implements Initializable {
    private HistorialController historialController;
    private PendientesController pendientesController;
    private HistorialRepository historialRepository;
    private ProductosController productosController;

    public InicioController(HistorialRepository historialRepository, PedidosRepository pedidosRepository, ProductRepository productRepository) {
        this.productosController = new ProductosController(this,productRepository);
        this.historialController = new HistorialController(this,historialRepository);
        this.pendientesController = new PendientesController(this,pedidosRepository,historialRepository, productRepository);
    }

    @FXML
    private void cambiarAPedidos(ActionEvent event) {

        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ChangeScene.change(stage, pendientesController, "/vistas/vista_pendientes.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void cambiarAHistorial(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ChangeScene.change(stage, historialController, "/vistas/vista_historial_pedidos.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void cambiarAProductos(ActionEvent event) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ChangeScene.change(stage, productosController, "/vistas/vista_productos.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
