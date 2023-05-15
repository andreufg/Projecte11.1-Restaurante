package es.progcipfpbatoi;

import es.progcipfpbatoi.controller.*;
import es.progcipfpbatoi.model.repositorios.HistorialRepository;
import es.progcipfpbatoi.model.repositorios.PedidosRepository;
import es.progcipfpbatoi.model.repositorios.ProductRepository;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }



    @Override
    public void start(Stage stage) throws Exception {
        InicioController inicioController;
        HistorialRepository historialRepository = new HistorialRepository();
        PedidosRepository pedidosRepository = new PedidosRepository();
        ProductRepository productRepository = new ProductRepository();
        inicioController = new InicioController(historialRepository,pedidosRepository, productRepository);
        ChangeScene.change(stage, inicioController, "/vistas/vista_principal.fxml");
    }

}
