package integracion.recursosAudiovisuales;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class H2DaoAveria extends BaseDaoImpl<Averia, Integer> implements DaoAveria {

	protected H2DaoAveria(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Averia.class);
	}

	@Override
	public List<Averia> getAverias() {
		List<Averia> lista = new ArrayList<>();
		try {
			lista = queryForAll();
		} catch (SQLException e) { e.printStackTrace(); }
		return lista;
	}
	
	

}
