package integracion.catering;

public class FactoriaDAO_GCImp extends FactoriaDAO_GC {

public DAO_GC nuevoDAOPedido()	{
		return new DAO_GCImp();
	}

}
