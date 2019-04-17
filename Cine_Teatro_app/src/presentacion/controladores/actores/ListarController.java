package presentacion.controladores.actores;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import presentacion.vistas.subsistemasGUI.actores.MainActoresImpGUI;

import java.util.Optional;

public class ListarController implements Inicializador{
    private static final int EDITAR = 0;
    private static final int BORRAR = 1;

    @FXML
    private ListView _lvEntidades;
    @FXML
    private Label _lbSubtitulo;
    @FXML
    private Button _btNuevo;

    private ButtonType _btAlertEditar = new ButtonType("Editar");
    private ButtonType _btAlertBorrar = new ButtonType("Borrar");
    private ObservableList<String> datos = FXCollections.observableArrayList(
            "chocolate", "salmon", "gold", "coral", "darkorchid",
            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
            "blueviolet", "brown");

    private int contexto = -1;

    public ListarController(Bundle bundle) {
        contexto = bundle.getContexto();
    }

    @Override
    public void iniciar() {
        Platform.runLater(()->{
            actualizaLista();
            onClickListItem();
            onClickNuevo();
            String texto = _lbSubtitulo.getText();
            if(contexto == Bundle.ACTOR)
                texto = "Actores";
            else if(contexto == Bundle.DIRECTOR)
                texto = "Directores";
            else if(contexto == Bundle.PRODUCTOR)
                texto = "Productores";
            else if(contexto == Bundle.OBRA)
                texto = "Obras u Películas";

            _lbSubtitulo.setText(texto);
        });
    }

    private void actualizaLista(){
        _lvEntidades.setItems(datos);
    }

    public void onClickBuscar() {
        //sin implementar
    }

    public void onClickNuevo() {
        _btNuevo.setOnMouseClicked(event -> siguientePantalla());
    }

    private void onClickListItem(){
        Platform.runLater(()-> _lvEntidades.setOnMouseClicked(event -> {
            int index = _lvEntidades.getSelectionModel().getSelectedIndex();
            String seleccion = datos.get(index);

            int accion = dialogoAccion(seleccion);
            if (accion == EDITAR) {
                // TODO Falta recibir de SA el transfer
                siguientePantalla();

            } else if (accion == BORRAR) {
                if (dialogoConfirmarBorrar(seleccion)) {
                    // TODO llamar al servicio de app y actualizar lista
                }
            }
        }));
    }

    private int dialogoAccion(String text){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de acción");
        alert.setHeaderText("¿Qué quieres hacer con " + text + "?");
        alert.getButtonTypes().setAll(_btAlertEditar, _btAlertBorrar);
        Optional<ButtonType> result = alert.showAndWait();
        int accion = -1;
        if(result.get() == _btAlertEditar)
            accion = EDITAR;
        else if(result.get() == _btAlertBorrar)
            accion = BORRAR;

        return accion;
    }

    private boolean dialogoConfirmarBorrar(String text){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación de acción");
        alert.setHeaderText("¿Estas seguro que quieres borrar a " + text + "?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
            return true;
        else
            return false;
    }

    private void siguientePantalla(){
        MainActoresImpGUI app = new MainActoresImpGUI();
        Bundle bundle = new Bundle(contexto);
        Inicializador controller;
        String pantalla;
        if(contexto == Bundle.OBRA) {
            pantalla = "form_obra.fxml";
            controller = new FormObraController(bundle);
        }
        else if(contexto == Bundle.PRODUCTOR | contexto == Bundle.ACTOR | contexto == Bundle.DIRECTOR){
            pantalla = "form_persona.fxml";
            controller = new FormPersonaController(bundle);
        }
        else
            return;

        app.cambiaPantalla(pantalla, controller);
    }
}

