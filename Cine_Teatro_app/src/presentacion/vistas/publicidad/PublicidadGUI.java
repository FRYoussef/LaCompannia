package presentacion.vistas.publicidad;

import presentacion.vistas.GUI;

public abstract class PublicidadGUI implements GUI
{
    public static final int CODE = 1;
    public static PublicidadGUI instance = null;

    public static PublicidadGUI getInstance()
    {
		if(instance == null) instance = new PublicidadGUIImp();
		return instance;
    }
    
    public static void deleteInstance()
    {
    	instance = null;
    }
}
