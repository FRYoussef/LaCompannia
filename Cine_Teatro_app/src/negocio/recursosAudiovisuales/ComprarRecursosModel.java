package negocio.recursosAudiovisuales;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import integracion.recursosAudiovisuales.*;
import presentacion.recursosAudiovisuales.*;

public class ComprarRecursosModel extends AbstractTableModel implements Model {
	
	private String[] colNames = {"Nombre", "Tipo", "Unidades", "montable", "fecha Adquisicion", "coste"};
	
	public Event onItemAdded = new Event();
	public Event onItemRemoved = new Event();
	public Event onItemChanged = new Event();
	public Event onModelChanged = new Event();
	

	private List<RecursoAudioVisual> arrRecursos;
	private List<RecursoAudioVisual> arrRecursosFiltrados;
	private boolean filtroQuitarAgotados = false;

	public ComprarRecursosModel(List<RecursoAudioVisual> listaRecursos) {
		this.arrRecursos = listaRecursos;
		this.arrRecursosFiltrados = listaRecursos;
	}


	public List<RecursoAudioVisual> getListaRecursos() {
		return arrRecursos;
	}

	public void setListaRecursos(List<RecursoAudioVisual> listaRecursos) {
		this.arrRecursos = listaRecursos;
		this.arrRecursosFiltrados = listaRecursos;
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return arrRecursosFiltrados.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object toreturn = new Object();
		switch (col) {
		case 0:
			toreturn = arrRecursosFiltrados.get(row).getNombre(); 
			break;
		case 1:
			toreturn = arrRecursosFiltrados.get(row).getTipo();
			break;
		case 2:
			toreturn = arrRecursosFiltrados.get(row).getUnidades();
			break;
		case 3:
			toreturn = arrRecursosFiltrados.get(row).isMontable() ? "SI" : "NO";
			break;
		case 4:
			toreturn = arrRecursosFiltrados.get(row).getFechaAdquisicion();
			break;
		case 5:
			toreturn = arrRecursosFiltrados.get(row).getPrecio();
		default:
			break;
		}
		return toreturn;
	}
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public void filtrarNoAgotados() {
		arrRecursosFiltrados = new ArrayList<RecursoAudioVisual>();
		for(RecursoAudioVisual recurso : arrRecursos) {
			if(recurso.getUnidades() > 0) arrRecursosFiltrados.add(recurso);
		}
		fireTableDataChanged();
	}
	
	public void quitarFiltros() {
		arrRecursosFiltrados = arrRecursos;
		fireTableDataChanged();
	}
	
	public void remove(int index) {
		arrRecursos.remove(index);
		onItemRemoved.notifyAllObservers();
		onModelChanged.notifyAllObservers();
		fireTableDataChanged();
		fireTableRowsDeleted(index, index);
	}


	public boolean isFiltroQuitarAgotados() {
		return filtroQuitarAgotados;
	}


	public void setFiltroQuitarAgotados(boolean filtroQuitarAgotados) {
		this.filtroQuitarAgotados = filtroQuitarAgotados;
		if(filtroQuitarAgotados) filtrarNoAgotados();
		else quitarFiltros();
		onModelChanged.notifyAllObservers();
		fireTableDataChanged();
	}

	@Override
	public boolean isValid() {
		return true;
	}
	
}
