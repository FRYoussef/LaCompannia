package presentacion.controladores.actores;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ActoresMainController {


    @FXML
    public void onClickShow(MouseEvent event) {
        Platform.runLater(()->{
            // No está implementado
        });
    }

    @FXML
    public void onClickEdit(MouseEvent event) {
        Platform.runLater(()->{
            ImageView iv = (ImageView) event.getSource();
            switch (iv.getId()){
                case "iv_editActor":
                    break;
                case "iv_editDirector":
                    break;
                case "iv_editProductor":
                    break;
                case "iv_editObra":
                    break;
                 default:
                    System.out.println("No reconocido");
            }
        });
    }
}
