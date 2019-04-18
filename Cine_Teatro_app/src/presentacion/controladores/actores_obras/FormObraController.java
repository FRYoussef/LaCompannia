package presentacion.controladores.actores_obras;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import negocio.sistemas_aplicacion.ActoresObrasSA;
import negocio.transfers.Obra;
import negocio.transfers.ObraGenero;
import negocio.transfers.PersonaEspectaculo;
import negocio.transfers.PersonaEspectaculoRol;
import presentacion.vistas.subsistemasGUI.actores_obras.MainActoresObrasImpGUI;

import java.util.ArrayList;

public class FormObraController extends FormController implements Inicializador {

    @FXML
    private Label _lbTitulo;
    @FXML
    private TextField _tfTitulo;
    @FXML
    private TextField _tfPais;
    @FXML
    private TextField _tfAnno;
    @FXML
    private TextField _tfDirectores;
    @FXML
    private TextField _tfActores;
    @FXML
    private TextField _tfProductores;
    @FXML
    private TextField _tfPremios;
    @FXML
    private TextArea _taSinopsis;
    @FXML
    private ChoiceBox _cbGenero;
    @FXML
    private ChoiceBox _cbDirectores;
    @FXML
    private ChoiceBox _cbActores;
    @FXML
    private ChoiceBox _cbProductores;
    @FXML
    private Button _btAceptar;
    @FXML
    private Button _btCancelar;


    private int contexto = -1;
    private Obra obra = null;
    private ArrayList<String> directores;
    private ArrayList<String> actores;
    private ArrayList<String> productores;

    public FormObraController(Bundle bundle) {
        this.contexto = bundle.getContexto();
        if(bundle.getObj() != null)
            obra = (Obra)bundle.getObj();
    }

    @Override
    public void iniciar() {
        inicializaDesplegables();
        annadeDatosTransfer();
        onKeyPressedTitulo(_tfTitulo, _lbTitulo);
        onClickAceptar();
        onClickCancelar(_btCancelar, contexto);
    }

    private void inicializaDesplegables() {
        Platform.runLater(()->{
            ActoresObrasSA sa = ActoresObrasSA.getInstancia();
            directores = sa.getListaPersonas(PersonaEspectaculoRol.DIRECTOR);
            actores = sa.getListaPersonas(PersonaEspectaculoRol.ACTOR);
            productores = sa.getListaPersonas(PersonaEspectaculoRol.PRODUCTOR);
            _cbDirectores.setItems(FXCollections.observableArrayList(directores));
            _cbActores.setItems(FXCollections.observableArrayList(actores));
            _cbProductores.setItems(FXCollections.observableArrayList(productores));
            _cbGenero.setItems(FXCollections.observableArrayList(ObraGenero.getArrayNames()));

            _cbDirectores.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue)
                    -> Platform.runLater(() -> _tfDirectores.appendText(", " + directores.get(newValue.intValue()))));
            _cbActores.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue)
                    -> Platform.runLater(() -> _tfActores.appendText(", " + actores.get(newValue.intValue()))));
            _cbProductores.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue)
                    -> Platform.runLater(() -> _tfProductores.appendText(", " + productores.get(newValue.intValue()))));
        });
    }

    void annadeDatosTransfer() {
        if(obra != null){
            Platform.runLater(()->{
                _lbTitulo.setText("Datos de " + obra.getTitulo());
                _tfTitulo.setText(obra.getTitulo());
                _tfPais.setText(obra.getPais());
                _tfAnno.setText(Integer.toString(obra.getAnno()));
                _tfPremios.setText(obra.getPremios());
                _taSinopsis.setText(obra.getSinopsis());
                _tfActores.setText(concatenaNombres(obra.getActores()));
                _tfDirectores.setText(concatenaNombres(obra.getDirectores()));
                _tfProductores.setText(concatenaNombres(obra.getProductores()));
                _cbGenero.setValue(obra.getObraGenero().name());
            });
        }
    }

    private String concatenaNombres(ArrayList<PersonaEspectaculo> al){
        StringBuilder str = new StringBuilder();
        for (PersonaEspectaculo p: al)
            str.append(p.getNombre() + ", ");

        return str.toString();
    }

    void onClickAceptar(){
        _btAceptar.setOnMouseClicked(event -> Platform.runLater(()->{
            boolean relleno = true;
            String titulo = _tfTitulo.getText();
            relleno = relleno && !titulo.isEmpty();
            String pais = _tfPais.getText();
            relleno = relleno && !pais.isEmpty();
            int anno = 0;
            if(!_tfAnno.getText().isEmpty())
                anno = Integer.parseInt(_tfAnno.getText());
            relleno = relleno && !_tfAnno.getText().isEmpty();
            ObraGenero genero = ObraGenero.Aventuras;
            if(_cbGenero.getValue() != null)
                genero = ObraGenero.valueOf(_cbGenero.getValue().toString());
            String premios = _tfPremios.getText();
            String sinopsis = _taSinopsis.getText();
            String directores = _tfDirectores.getText();
            String actores = _tfActores.getText();
            String productores = _tfProductores.getText();
            if(!relleno){
                alertInfo();
                return;
            }
            ActoresObrasSA sa = ActoresObrasSA.getInstancia();
            // Parsear los nombres de las personas a objetos
            ArrayList<PersonaEspectaculo> alDi = sa.parseaObjetos(directores);
            ArrayList<PersonaEspectaculo> alAct = sa.parseaObjetos(actores);
            ArrayList<PersonaEspectaculo> alPro = sa.parseaObjetos(productores);

            if(obra == null)
                obra = new Obra(titulo, pais, anno, genero, premios, sinopsis, alAct, alDi, alPro);
            else{
                obra.setTitulo(titulo);
                obra.setPais(pais);
                obra.setAnno(anno);
                obra.setObraGenero(genero);
                obra.setPremios(premios);
                obra.setSinopsis(sinopsis);
                obra.setActores(alAct);
                obra.setDirectores(alDi);
                obra.setProductores(alPro);
            }
            if(!alertConfirmarCambios()){
                return;
            }
            sa.modificaTransfer(contexto, obra);
            cambiaPantalla(contexto);
        }));
    }
}
