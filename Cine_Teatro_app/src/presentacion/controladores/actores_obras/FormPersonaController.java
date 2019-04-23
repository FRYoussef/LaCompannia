package presentacion.controladores.actores_obras;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import negocio.actores_obras.sistemas_aplicacion.ActoresObrasSA;
import negocio.transfers.PersonaEspectaculo;
import negocio.transfers.PersonaEspectaculoRol;
import presentacion.vistas.actores_obras.MainActoresObrasImpGUI;

import java.util.Optional;

public class FormPersonaController extends FormController implements Inicializador {

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
        onClickCancelar(_btCancelar, contexto);
        onKeyPressedTitulo(_tfNombre, _lbTitulo);
    }

    void annadeDatosTransfer() {
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

    void onClickAceptar(){
        _btAceptar.setOnMouseClicked(event -> Platform.runLater(()->{
            boolean relleno = true;
            String nombre = _tfNombre.getText();
            relleno = relleno && !nombre.isEmpty();
            String apellidos = _tfApellidos.getText();
            relleno = relleno && !apellidos.isEmpty();
            String dni = _tfDni.getText();
            relleno = relleno && !dni.isEmpty();
            int telefono = 0;
            if(!_tfTelefono.getText().isEmpty())
                telefono = Integer.parseInt(_tfTelefono.getText());
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
            ActoresObrasSA sa = ActoresObrasSA.getInstancia();
            sa.modificaTransfer(contexto, persona);
            cambiaPantalla(contexto);
        }));
    }
}
