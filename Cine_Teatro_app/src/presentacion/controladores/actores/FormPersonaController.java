package presentacion.controladores.actores;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import presentacion.vistas.subsistemasGUI.actores.MainActoresImpGUI;

import java.util.Optional;

public class FormPersonaController implements Inicializador {

    @FXML
    private TextField _tfNombre;
    @FXML
    private TextField _tfApellidos;
    @FXML
    private TextField _tfDni;
    @FXML
    private TextField _tfTelefono;
    @FXML
    private TextField _tfEmail;
    @FXML
    private TextArea _taPremios;
    @FXML
    private TextArea _taBibliografia;
    @FXML
    private Button _btAceptar;
    @FXML
    private Button _btCancelar;
    @FXML
    private Label _lbTitulo;

    private int contexto = -1;

    public FormPersonaController(Bundle bundle) {
        contexto = bundle.getContexto();
    }

    @Override
    public void iniciar() {
        onClickAceptar();
        onClickCancelar();
        onBlurName();
    }

    private void onBlurName() {
        _tfNombre.setOnKeyReleased(event ->
                Platform.runLater(()->
                        _lbTitulo.setText("Datos de " + _tfNombre.getText())));
    }

    private void onClickAceptar(){
        _btAceptar.setOnMouseClicked(event -> Platform.runLater(()->{
            boolean relleno = true;
            String nombre = _tfNombre.getText();
            relleno = relleno && !nombre.isEmpty();
            String apellidos = _tfApellidos.getText();
            relleno = relleno && !apellidos.isEmpty();
            String dni = _tfDni.getText();
            relleno = relleno && !dni.isEmpty();
            String telefono = _tfTelefono.getText();
            relleno = relleno && !telefono.isEmpty();
            String email = _tfEmail.getText();
            relleno = relleno && !email.isEmpty();
            if(!relleno){
                alertInfo();
                return;
            }
            String premios = _taPremios.getText();
            String biblio = _taBibliografia.getText();
            if(!alertConfirmarCambios()){
                return;
            }
            //TODO Crear transfer y pasarlo a SA

            cambiaPantalla();
        }));
    }

    private void onClickCancelar(){
        _btCancelar.setOnMouseClicked(event -> cambiaPantalla());
    }

    private void alertInfo(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Error en los campos");
        alert.setContentText("Por favor, rellena todos los campos de la sección de información");
        alert.showAndWait();
    }

    private boolean alertConfirmarCambios(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de acción");
        alert.setHeaderText("¿Estas seguro del cambio?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
            return true;
        else
            return false;
    }

    private void cambiaPantalla(){
        MainActoresImpGUI app = new MainActoresImpGUI();
        Bundle bundle = new Bundle(contexto);
        Inicializador controller = new ListarController(bundle);
        String pantalla = "listar.fxml";
        app.cambiaPantalla(pantalla, controller);
    }
}
