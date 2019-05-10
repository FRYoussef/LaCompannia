package presentacion.vistas.transporte;

import presentacion.controladores.transporte.ControladorTransporte;

public abstract class GUITransporte {

	static GUITransporte instancia= null;
	
	public static GUITransporte getInstancia()	{
		if(instancia == null) instancia= new GUITransporteImp();
		return instancia;
	}
	
	static public void eliminarInstancia() {
		instancia = null;
	}
	

}
