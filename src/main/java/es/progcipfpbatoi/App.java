package es.progcipfpbatoi;

import es.progcipfpbatoi.controller.*;
import es.progcipfpbatoi.model.dao.FileProductsDao;
import es.progcipfpbatoi.model.dao.InmemoryProductDAO;
import es.progcipfpbatoi.model.dao.ProductosDAO;
import es.progcipfpbatoi.model.dto.producttypes.Product;
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
        FileProductsDao fileProductsDao = new FileProductsDao();
        ProductRepository productRepository = new ProductRepository(fileProductsDao);

        inicioController = new InicioController(historialRepository,pedidosRepository, productRepository);
        ChangeScene.change(stage, inicioController, "/vistas/vista_principal.fxml");
    }

}
