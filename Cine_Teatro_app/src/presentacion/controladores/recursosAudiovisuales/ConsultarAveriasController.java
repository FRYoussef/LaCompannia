package presentacion.recursosAudiovisuales;

import negocio.recursosAudiovisuales.*;

public class ConsultarAveriasController implements Controller {

	private ConsultarRecursosView view;
	private ConsultarAveriasModel model;

	public ConsultarAveriasController(ConsultarRecursosView view, ConsultarAveriasModel model) {
		this.view = view;
		this.model = model;
		
		setupHandlers();
	}
	
	private void setupHandlers() {
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

}
