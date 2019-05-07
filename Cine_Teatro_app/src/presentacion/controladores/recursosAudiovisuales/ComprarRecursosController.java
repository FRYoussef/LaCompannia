package presentacion.controladores.recursosAudiovisuales;

import negocio.recursosAudiovisuales.*;
import presentacion.recursosAudiovisuales.ComprarRecursosView;
import presentacion.recursosAudiovisuales.Controller;

public class ComprarRecursosController implements Controller {

	private ComprarRecursosView view;
	private ComprarRecursosModel model;

	public ComprarRecursosController(ComprarRecursosView comprarRecursosView,
			ComprarRecursosModel comprarRecursosModel) {
		this.view = comprarRecursosView;
		this.model = comprarRecursosModel;
		setupHandlers();
	}
	
	
	private void setupHandlers() {
		view.btnDetallesPressed.addObserver(() -> {
			if(view.isDetallesEnabled()) 
				view.ocultarDetalles(); 	
			else 
				view.mostrarDetalles();
		});
		model.onItemRemoved.addObserver(() -> {
			view.updateView();
		});
		view.btnOcultarAgotadosPressed.addObserver(() -> {
			if(model.isFiltroQuitarAgotados())
				model.setFiltroQuitarAgotados(false);
			else 
				model.setFiltroQuitarAgotados(true);
			view.updateView();
		});
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
