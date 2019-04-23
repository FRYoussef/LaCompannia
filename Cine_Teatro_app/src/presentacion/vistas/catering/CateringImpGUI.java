package presentacion.vistas.catering;

import presentacion.vistas.GUI;

public class CateringImpGUI implements GUI {
    public static final int CODE = 3;

    @Override
    public void ejecutar() {
    	GUIPedido_GC.getInstancia();
    }
}
