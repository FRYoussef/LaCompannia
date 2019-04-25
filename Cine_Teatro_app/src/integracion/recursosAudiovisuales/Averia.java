package integracion.recursosAudiovisuales;

import java.sql.Timestamp;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Averia {
	
	@DatabaseField(generatedId = true)
	private Integer idAveria;
	
	@DatabaseField(foreign = true)
	private RecursoAudioVisual idRecurso;
	
	@DatabaseField
	private Timestamp fechaAveria;
	
	@DatabaseField
	private TipoAveria tipo;
	
	@DatabaseField(width=2000)
	private String descripcion;
	
	public Timestamp getFechaAveria() {
		return fechaAveria;
	}
	public void setFechaAveria(Timestamp fechaAveria) {
		this.fechaAveria = fechaAveria;
	}
	public TipoAveria getTipo() {
		return tipo;
	}
	public void setTipo(TipoAveria tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getIdAveria() {
		return idAveria;
	}
	public void setIdAveria(Integer idAveria) {
		this.idAveria = idAveria;
	}
	public RecursoAudioVisual getIdRecurso() {
		return idRecurso;
	}
	public void setIdRecurso(RecursoAudioVisual idRecurso) {
		this.idRecurso = idRecurso;
	}
}
