package presentacion.controladores.actores_obras;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import presentacion.vistas.subsistemasGUI.actores_obras.MainActoresObrasImpGUI;

public class ActoresObrasMainController {


    @FXML
    public void onClickShow(MouseEvent event) {
        Platform.runLater(()->{
            // No está implementado
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Aún no se puede consultar");
            alert.setContentText("Pincha en el botón de editar (el lápiz)");
            alert.showAndWait();
        });
    }

    @FXML
    public void onClickEdit(MouseEvent event) {
        Platform.runLater(()->{
            ImageView iv = (ImageView) event.getSource();
            MainActoresObrasImpGUI mainController = new MainActoresObrasImpGUI();
            String pantalla = "listar.fxml";
            ListarController controller;
            Bundle bundle;
            switch (iv.getId()){
                case "iv_editActor":
                    bundle = new Bundle(Bundle.ACTOR);
                    break;
                case "iv_editDirector":
                    bundle = new Bundle(Bundle.DIRECTOR);
                    break;
                case "iv_editProductor":
                    bundle = new Bundle(Bundle.PRODUCTOR);
                    break;
                case "iv_editObra":
                    bundle = new Bundle(Bundle.OBRA);
                    break;
                 default:
                    System.out.println("No reconocido");
                    return;
            }
            controller = new ListarController(bundle);
            mainController.cambiaPantalla(pantalla, controller);
        });
    }
}
