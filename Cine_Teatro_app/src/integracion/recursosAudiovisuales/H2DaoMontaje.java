package integracion.recursosAudiovisuales;

import java.sql.SQLException;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class H2DaoMontaje extends BaseDaoImpl<Montaje, Integer> implements DaoMontaje {

	protected H2DaoMontaje(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Montaje.class);
	}

}
