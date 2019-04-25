package integracion.recursosAudiovisuales;

import java.sql.Timestamp;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Montaje {

	@DatabaseField(generatedId = true)
	private Integer idMontaje;
	
	@DatabaseField(foreign = true)
	private RecursoAudioVisual idRecurso;
	
	@DatabaseField
	private Timestamp fechaMontaje;
	
	public Integer getIdMontaje() {
		return idMontaje;
	}

	public void setIdMontaje(Integer idMontaje) {
		this.idMontaje = idMontaje;
	}

	public RecursoAudioVisual getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(RecursoAudioVisual idRecurso) {
		this.idRecurso = idRecurso;
	}

	public Timestamp getFechaMontaje() {
		return fechaMontaje;
	}

	public void setFechaMontaje(Timestamp fechaMontaje) {
		this.fechaMontaje = fechaMontaje;
	}
	
}
