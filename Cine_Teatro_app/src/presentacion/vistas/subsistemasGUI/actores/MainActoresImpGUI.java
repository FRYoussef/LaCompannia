package presentacion.vistas.subsistemasGUI.actores;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentacion.vistas.subsistemasGUI.GUI;

import java.io.IOException;

public class MainActoresImpGUI implements GUI {
    public static final int CODE = 6;
    private static Stage primaryStage = null;

    /**
     * Método para implementar el patron singelton
     * @return stage
     */
    private Stage getStage() {
        if(primaryStage == null)
            primaryStage = new Stage();

        return primaryStage;
    }

    @Override
    public void ejecutar() {
        Stage primaryStage = getStage();
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("main_actores_GUI.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Módulo actores, directores, obras, ...");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cambia la pantalla actual a otra que se le pase
     * @param pantalla ruta del fxml
     */
    public void cambiaPantalla(String pantalla) {
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(pantalla));
            Scene scene = new Scene(root);
            primaryStage.setTitle("");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
