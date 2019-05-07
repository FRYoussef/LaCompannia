package presentacion.controladores.recursosAudiovisuales;

import negocio.recursosAudiovisuales.*;
import presentacion.recursosAudiovisuales.ConsultarRecursosView;
import presentacion.recursosAudiovisuales.Controller;

public class ConsultarRecursosController implements Controller {

	private ConsultarRecursosView view;
	private ConsultarRecursosModel model;

	public ConsultarRecursosController(ConsultarRecursosView view, ConsultarRecursosModel model) {
		this.view = view;
		this.model = model;
		
		setupHandlers();
	}
	
	private void setupHandlers() {
		view.btnDetallesPressed.addObserver(()->model.setDetailsState(!model.getDetailsState()));
		view.btnDetallesPressed.addObserver(()->view.updateView());
		view.btnRetirarPressed.addObserver(()->model.remove(view.getSelectedItemIndex()));
		model.onItemRemoved.addObserver(()->view.updateView());
	}

	@Override
	public void run() {

	}

}
