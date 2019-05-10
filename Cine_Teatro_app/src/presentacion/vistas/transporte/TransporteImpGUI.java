package presentacion.vistas.transporte;

import presentacion.vistas.GUI;

public class TransporteImpGUI implements GUI {
    public static final int CODE = 2;

    @Override
    public void ejecutar() {
    	GUITransporte.getInstancia();
    }
}
