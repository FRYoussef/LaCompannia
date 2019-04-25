package integracion.recursosAudiovisuales;

import java.sql.Timestamp;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Compra {
	
	@DatabaseField(generatedId = true)
	private Integer idCompra;
	
	@DatabaseField(foreign = true)
	private RecursoAudioVisual idRecurso;
	
	@DatabaseField
	private Timestamp fechaAdquisicion;

	public Integer getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}

	public Timestamp getFechaAdquisicion() {
		return fechaAdquisicion;
	}

	public void setFechaAdquisicion(Timestamp fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public RecursoAudioVisual getRecurso() {
		return idRecurso;
	}

	public void setRecurso(RecursoAudioVisual idRecurso) {
		this.idRecurso = idRecurso;
	}
}
