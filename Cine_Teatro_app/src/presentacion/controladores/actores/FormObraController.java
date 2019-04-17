package presentacion.controladores.actores;

public class FormObraController implements Inicializador {
    private int contexto = -1;

    public FormObraController(Bundle bundle) {
        this.contexto = bundle.getContexto();
    }

    @Override
    public void iniciar() {

    }
}
