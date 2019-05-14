package presentacion.vistas.vestimenta;

import presentacion.vistas.GUI;


public abstract class VestimentaGUIImp implements GUI {

	public static final int CODE = 4;
    public static ReservaVestimentaImp instance = null;

   
    
    public static ReservaVestimentaImp getInstance()
    {
    	if(instance == null) instance = new ReservaVestimentaImp();
		return instance;
    }
    
    public static void deleteInstance()
    {
    	instance = null;
    }
}
