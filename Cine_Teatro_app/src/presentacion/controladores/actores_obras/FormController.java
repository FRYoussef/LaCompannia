package presentacion.controladores.actores_obras;

import javafx.application.Platform;
import javafx.scene.control.*;
import presentacion.vistas.subsistemasGUI.actores_obras.MainActoresObrasImpGUI;

import java.util.Optional;

public abstract class FormController {
    abstract void annadeDatosTransfer();
    abstract void onClickAceptar();

    void onKeyPressedTitulo(TextField tf, Label lb) {
        tf.setOnKeyReleased(event ->
                Platform.runLater(()->
                        lb.setText("Datos de " + tf.getText())));
    }

    void onClickCancelar(Button _btCancelar, int contexto){
        _btCancelar.setOnMouseClicked(event -> cambiaPantalla(contexto));
    }

    void cambiaPantalla(int contexto){
        MainActoresObrasImpGUI app = new MainActoresObrasImpGUI();
        Bundle bundle = new Bundle(contexto);
        Inicializador controller = new ListarController(bundle);
        String pantalla = "listar.fxml";
        app.cambiaPantalla(pantalla, controller);
    }

    void alertInfo(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Error en los campos");
        alert.setContentText("Por favor, rellena todos los campos de la sección de información");
        alert.showAndWait();
    }

    boolean alertConfirmarCambios(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de acción");
        alert.setHeaderText("¿Estas seguro del cambio?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
            return true;
        else
            return false;
    }
}
