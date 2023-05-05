package es.progcipfpbatoi;

import es.progcipfpbatoi.controller.ChangeScene;
import es.progcipfpbatoi.controller.InicioController;
import es.progcipfpbatoi.model.repositorios.ProductRepository;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }



    @Override
    public void start(Stage stage) throws Exception {
        InicioController inicioController = new InicioController();
        ChangeScene.change(stage, inicioController, "/vistas/vista_principal.fxml");
    }

}
