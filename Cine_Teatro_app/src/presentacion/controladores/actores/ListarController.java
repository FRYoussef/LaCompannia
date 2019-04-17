package presentacion.controladores.actores;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class ListarController implements Inicializador{

    @FXML
    private ListView _lvEntidades;
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
        actualizaLista();
        onClickListItem();
    }

    private void actualizaLista(){
        _lvEntidades.setItems(datos);
    }

    public void onClickBuscar(MouseEvent event) {

    }

    public void onClickNuevo(MouseEvent event) {

    }

    private void onClickListItem(){
        _lvEntidades.getSelectionModel().selectedItemProperty().addListener(
                (ChangeListener<String>) (ov, old_val, new_val) -> {
                    System.out.println(new_val);
                });
    }
}
