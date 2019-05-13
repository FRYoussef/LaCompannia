package negocio.obras;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import integracion.obras.DAOObras;
import negocio.transfers.Obra;

public class ObrasSA {
	
	private List<Obra> obras;
	private DAOObras dao;
	
	public ObrasSA(){
		this.obras = new ArrayList<Obra>();		
		this.dao = new DAOObras();
	}
	
	public List<Obra> loadObras() throws FileNotFoundException {
		this.obras = dao.load();
		return this.obras;
	}
	
	public List<Obra> getObras(){
		return this.obras;
	}
	
	public void addObra(Obra o) {
		obras.add(o);
		dao.save(o);
	}
	
}
