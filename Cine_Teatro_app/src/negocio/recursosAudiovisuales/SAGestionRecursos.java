package negocio.recursosAudiovisuales;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import integracion.recursosAudiovisuales.*;
import integracion.recursosAudiovisuales.*;

public class SAGestionRecursos implements ServicioDeAplicacion{
	
	private FactoriaDAO daoFactory;
	private DaoRecurso daoRecursoAudiovisual;
	private DaoAveria daoAveria;
	private DaoCompra daoCompra;
	private DaoMontaje daoMontaje;

	public SAGestionRecursos() {
		daoFactory = FactoriaDAO.crearFactoria(TipoFactoria.H2DBFactory);
		daoRecursoAudiovisual = daoFactory.getDaoRecurso();
		daoCompra = daoFactory.getDaoCompra();
		daoAveria = daoFactory.getDaoAveria();
		daoMontaje = daoFactory.getDaoMontaje();
	}
	
	public List<RecursoAudioVisual> getListaRecursos() {
		ArrayList<RecursoAudioVisual> arr = new ArrayList<RecursoAudioVisual>();
		List<Compra> compras = daoCompra.getCompras();
		for(Compra c : compras) arr.add(getRecurso(c.getRecurso().getId()));
		return arr;
	}
	
	public List<Averia> getListaAverias() {
		return daoAveria.getAverias();
	}
	
	public List<Montaje> getListaMontajes(){
		List<Montaje> montaje = new ArrayList<>();
		try {
			montaje = daoMontaje.queryForAll();
		} catch (SQLException e) { e.printStackTrace(); }
		return montaje;
	}

	public List<RecursoAudioVisual> getListaRecursosVenta() {
		return daoRecursoAudiovisual.getRecursosAudiovisuales();
	}
	
	public RecursoAudioVisual getRecurso(int idRecurso) {
		try {
			return daoRecursoAudiovisual.queryForId(idRecurso);
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}
	
	public void comprarRecurso(int idRecurso) {
		RecursoAudioVisual recursoAudioVisual = new RecursoAudioVisual();
		recursoAudioVisual.setId(idRecurso);
		Compra c = new Compra();
		c.setRecurso(recursoAudioVisual);
		c.setFechaAdquisicion(new Timestamp(System.currentTimeMillis()));
		try {
			daoCompra.create(c);
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public RecursoAudioVisual consultarRecursoAudiovisual(int idRecurso){
		try {
			return daoRecursoAudiovisual.queryForId(idRecurso);
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}
	
	public void informarAveria(Averia averia) {
		try {
			daoAveria.create(averia);
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public void solicitarMontaje(int idRecurso) {
		//TODO: implementar
		throw new UnsupportedOperationException();
	}
	
	public void asignarRecurso(int idRecurso) {
		//TODO: implementar
		throw new UnsupportedOperationException();
	}

	public void actualizarRecurso(RecursoAudioVisual recurso) {
		try {
			daoRecursoAudiovisual.update(recurso);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
