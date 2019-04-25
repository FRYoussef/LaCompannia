package integracion.recursosAudiovisuales;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

public class H2DaoCompra extends BaseDaoImpl<Compra, Integer> implements DaoCompra{

	protected H2DaoCompra(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Compra.class);
	}

	@Override
	public List<Compra> getCompras() {
		List<Compra> queryForAll = new ArrayList<>();
		try {
			 queryForAll = queryForAll();
		} catch (SQLException e) { e.printStackTrace(); }
		return queryForAll;
	}

}
