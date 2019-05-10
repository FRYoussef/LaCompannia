package presentacion.vistas.transporte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import presentacion.controladores.transporte.ControladorTransporte;

import negocio.transfers.Viaje;

public class ResTableModel extends AbstractTableModel implements ActionListener{
	private List<Viaje> viajes;

	//Array con los indices de la tabla
	private static String[] columnNames = {"Origen", "Destino", "Hora Salida", "Hora Llegada", "Medio", "Clase", "Precio euros"}; //Podria añadirse compañia
	
	public ResTableModel(ControladorTransporte ctrl, ArrayList<Viaje> listado) { //Hay que pasarle en controlador en el constructor
		this.viajes=new ArrayList<Viaje>();
		this.viajes=listado;
		//ctrl.addObserver(this);
	}

	@Override
	public int getColumnCount() {
		return this.columnNames.length;
	}

	@Override
	public int getRowCount() {
		return this.viajes == null ? 0 :
			this.viajes.size();
	}

	public String getColumnName(int column){
		 return this.columnNames[column]; 
	}
	@Override
	public Object getValueAt(int row, int col) {
		switch (col){
		case 0:
			return this.viajes.get(row).getLugarOrigen();
		case 1:
			return this.viajes.get(row).getLugarDestino();
		case 2:
			return this.viajes.get(row).gethoraSalida();
		case 3:
			return this.viajes.get(row).gethoraLlegada();
		case 4:
			return this.viajes.get(row).getPrecio();
		case 5:
			return this.viajes.get(row).getClase();
		case 6:
			return this.viajes.get(row).getMedioTrans();
		default:
			return null;
		}
	}
	
	public void addRow(ArrayList<Viaje> listado){
		this.viajes.addAll(listado);
		this.fireTableDataChanged();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}	
}