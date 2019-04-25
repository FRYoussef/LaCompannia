package integracion.recursosAudiovisuales;

public abstract class FactoriaDAO {
	
	public static FactoriaDAO crearFactoria(TipoFactoria tipo) {
		FactoriaDAO factoria = null;
		switch(tipo) {
		case H2DBFactory:
			factoria = new H2FactoriaDAO();
			break;
		case SQLiteFactory:
			break;
		case FileFactory:
			break;
		}
		return factoria;
	}
	
	public abstract DaoRecurso getDaoRecurso();
	
	public abstract DaoCompra getDaoCompra();
	
	public abstract DaoAveria getDaoAveria();
	
	public abstract DaoMontaje getDaoMontaje();

}
