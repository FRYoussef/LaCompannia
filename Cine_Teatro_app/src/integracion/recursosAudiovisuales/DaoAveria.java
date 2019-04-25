package integracion.recursosAudiovisuales;

import java.util.List;

import com.j256.ormlite.dao.Dao;

public interface DaoAveria extends Dao<Averia,Integer>{

	List<Averia> getAverias();

}
