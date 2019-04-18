package presentacion.controladores.actores;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import negocio.sistemas_aplicacion.ActoresSA;
import negocio.transfers.PersonaEspectaculo;
import negocio.transfers.PersonaEspectaculoRol;
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
    private PersonaEspectaculo persona = null;

    public FormPersonaController(Bundle bundle) {
        contexto = bundle.getContexto();
        if(bundle.getObj() != null)
            persona = (PersonaEspectaculo) bundle.getObj();
    }

    @Override
    public void iniciar() {
        annadeDatosTransfer();
        onClickAceptar();
        onClickCancelar();
        onBlurName();
    }

    private void annadeDatosTransfer() {
        if(persona != null){
            Platform.runLater(()->{
                _lbTitulo.setText("Datos de " + persona.getNombre());
                _tfNombre.setText(persona.getNombre());
                _tfApellidos.setText(persona.getApellidos());
                _tfDni.setText(persona.getDni());
                _tfEmail.setText(persona.getEmail());
                _tfTelefono.setText(Integer.toString(persona.getTelefono()));
                _taBibliografia.setText(persona.getBibliografia());
                _taPremios.setText(persona.getPremios());
            });
        }
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
            int telefono = Integer.parseInt(_tfTelefono.getText());
            relleno = relleno && !_tfTelefono.getText().isEmpty();
            String email = _tfEmail.getText();
            relleno = relleno && !email.isEmpty();
            String biblio = _taBibliografia.getText();
            String premios = _taPremios.getText();

            if(!relleno){
                alertInfo();
                return;
            }
            PersonaEspectaculoRol rol = PersonaEspectaculoRol.ACTOR;
            if(contexto == Bundle.DIRECTOR)
                rol = PersonaEspectaculoRol.DIRECTOR;
            else if (contexto == Bundle.PRODUCTOR)
                rol = PersonaEspectaculoRol.PRODUCTOR;

            if(persona == null)
                persona = new PersonaEspectaculo(nombre, apellidos, dni, telefono, email, premios, biblio, rol);
            else{
                persona.setRol(rol);
                persona.setNombre(nombre);
                persona.setApellidos(apellidos);
                persona.setDni(dni);
                persona.setEmail(email);
                persona.setTelefono(telefono);
                persona.setPremios(premios);
                persona.setBibliografia(biblio);
            }
            if(!alertConfirmarCambios()){
                return;
            }
            ActoresSA sa = ActoresSA.getInstancia();
            sa.modificaTransfer(contexto, persona);
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
