package negocio.recursosAudiovisuales;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import integracion.recursosAudiovisuales.*;

public class ConsultarAveriasModel extends AbstractTableModel implements Model {

	private List<Averia> arrAverias;
	private String[] colNames = {"ID", "Fecha", "Tipo", "Descripcion"};

	public ConsultarAveriasModel(List<Averia> list) {
		this.setListaAverias(list);
	}
	
	public ConsultarAveriasModel() {
		this.setListaAverias(new ArrayList<>());
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public int getColumnCount() {
		return colNames.length;
	}

	@Override
	public int getRowCount() {
		return arrAverias.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object ret = new Object();
		switch(col) {
		case 0:
			ret = getArrAverias().get(row).getIdAveria();
			break;
		case 1:
			ret = getArrAverias().get(row).getFechaAveria();
			break;
		case 2:
			ret = getArrAverias().get(row).getTipo();
			break;
		case 3:
			ret = getArrAverias().get(row).getDescripcion();
		}
		return ret;
	}
	
	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	public List<Averia> getArrAverias() {
		return arrAverias;
	}

	public void setListaAverias(List<Averia> arrAverias) {
		this.arrAverias = arrAverias;
		fireTableDataChanged();
	}

	public void addAveria(Averia averia) {
		arrAverias.add(averia);
		fireTableDataChanged();
	}

}
