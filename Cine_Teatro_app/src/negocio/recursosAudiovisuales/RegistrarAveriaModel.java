package negocio.recursosAudiovisuales;

import java.sql.Timestamp;

import integracion.recursosAudiovisuales.*;
import presentacion.recursosAudiovisuales.*;

public class RegistrarAveriaModel implements Model {

	private Integer idRecurso;
	private Timestamp fecha;
	private TipoAveria tipoAveria;
	private String descripcion;
	
	public Event onModelChanged = new Event();
	
	public RegistrarAveriaModel() {
		setFecha(new Timestamp(System.currentTimeMillis()));
		idRecurso = 0;
		fecha = new Timestamp(System.currentTimeMillis());
		tipoAveria = TipoAveria.DESCONOCIDO;
		descripcion = "";
	}

	public RegistrarAveriaModel(RecursoAudioVisual recursoAudioVisual) {
		super();
		idRecurso = recursoAudioVisual.getId();
	}

	public boolean isValid() {
		if(getIdRecurso() < 0)
			return false;
		if(getDescripcion().isEmpty()) 
			return false;
		return true;
	}

	public Averia toAveria() {
		Averia averia = new Averia();
		RecursoAudioVisual recurso = new RecursoAudioVisual();
		recurso.setId(getIdRecurso());
		averia.setIdRecurso(recurso);
		averia.setDescripcion(getDescripcion());
		averia.setFechaAveria(getFecha());
		averia.setTipo(getTipoAveria());
		return averia;
	}

	public Integer getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(Integer idRecurso) {
		this.idRecurso = idRecurso;
		onModelChanged.notifyAllObservers();
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
		onModelChanged.notifyAllObservers();
	}

	public TipoAveria getTipoAveria() {
		return tipoAveria;
	}

	public void setTipoAveria(TipoAveria tipoAveria) {
		this.tipoAveria = tipoAveria;
		onModelChanged.notifyAllObservers();
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		onModelChanged.notifyAllObservers();
	}
	
}
