package integracion.recursosAudiovisuales;

import java.util.List;

import com.j256.ormlite.dao.Dao;

public interface DaoRecurso extends Dao<RecursoAudioVisual, Integer>{
	
	public List<RecursoAudioVisual> getRecursosAudiovisuales();
	
}
