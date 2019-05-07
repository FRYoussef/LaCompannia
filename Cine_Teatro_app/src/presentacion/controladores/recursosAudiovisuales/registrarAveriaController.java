package presentacion.controladores.recursosAudiovisuales;

import javax.swing.JOptionPane;

import negocio.recursosAudiovisuales.*;
import presentacion.recursosAudiovisuales.Controller;
import presentacion.recursosAudiovisuales.RegistrarAveriaView;

public class registrarAveriaController implements Controller {

	private RegistrarAveriaModel model;
	private RegistrarAveriaView view;

	public registrarAveriaController(RegistrarAveriaView registrarAveriaView,
			RegistrarAveriaModel registrarAveriaModel) {
				this.view = registrarAveriaView;
				this.model = registrarAveriaModel;
				
				registrarAveriaView.btnRegistarPressed.addObserver(() -> updateModel());
				model.onModelChanged.addObserver(() -> view.updateView());
	}
	
	public void updateModel(){
		model.setIdRecurso(view.getIdRecurso());
		model.setDescripcion(view.getDescripcion());
		model.setTipoAveria(view.getTipoAveria());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

	public void mostrarMensajeError() {
		JOptionPane.showMessageDialog(view, "debes completar todos los campos", "Campos vacios", JOptionPane.ERROR_MESSAGE);
	}

}
