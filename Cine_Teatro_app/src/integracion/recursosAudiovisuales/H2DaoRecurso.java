package integracion.recursosAudiovisuales;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class H2DaoRecurso extends BaseDaoImpl<RecursoAudioVisual, Integer> implements DaoRecurso{

	protected H2DaoRecurso(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, RecursoAudioVisual.class);
	}

	@Override
	public List<RecursoAudioVisual> getRecursosAudiovisuales() {
		List<RecursoAudioVisual> arrayList = new ArrayList<RecursoAudioVisual>();
		try {
			arrayList = queryForAll();
		} catch (SQLException e) { e.printStackTrace(); }
		return arrayList;
	}
	

}
