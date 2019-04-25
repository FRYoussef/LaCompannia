package negocio.recursosAudiovisuales;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import integracion.recursosAudiovisuales.*;
import presentacion.recursosAudiovisuales.*;

public class ConsultarRecursosModel extends AbstractTableModel implements Model {
	
	private String[] colNames = {"Nombre", "Tipo", "montable", "fecha Adquisicion", "coste"};
	
	public Event onItemAdded = new Event();
	public Event onItemRemoved = new Event();
	public Event onModelChanged = new Event();
	

	private List<RecursoAudioVisual> arrRecursos;
	private boolean detailsEnabled;

	public ConsultarRecursosModel(List<RecursoAudioVisual> listaRecursos) {
		this.setListaRecursos(listaRecursos);
		detailsEnabled = false;
	}

	public List<RecursoAudioVisual> getListaRecursos() {
		return arrRecursos;
	}

	public void setListaRecursos(List<RecursoAudioVisual> listaRecursos) {
		this.arrRecursos = listaRecursos;
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return arrRecursos.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object toreturn = new Object();
		switch (col) {
		case 0:
			toreturn = arrRecursos.get(row).getNombre(); 
			break;
		case 1:
			toreturn = arrRecursos.get(row).getTipo();
			break;
		case 2:
			toreturn = arrRecursos.get(row).isMontable() ? "SI" : "NO";
			break;
		case 3:
			toreturn = arrRecursos.get(row).getFechaAdquisicion();
			break;
		case 4:
			toreturn = arrRecursos.get(row).getPrecio();
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
	
	public void remove(int index) {
		if(index < 0) return;
		arrRecursos.remove(index);
		onItemRemoved.notifyAllObservers();
		onModelChanged.notifyAllObservers();
		fireTableDataChanged();
		fireTableRowsDeleted(index, index);
	}

	public boolean getDetailsState() {
		return detailsEnabled;
	}

	public void setDetailsState(boolean state) {
		detailsEnabled = state;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	public void addRecurso(RecursoAudioVisual recurso) {
		arrRecursos.add(recurso);
	}
	
}
