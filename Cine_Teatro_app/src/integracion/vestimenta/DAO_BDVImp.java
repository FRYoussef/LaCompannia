package integracion.vestimenta;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

import negocio.vestimenta.TransferReserva;
import negocio.vestimenta.TransferVestimenta;

public class DAO_BDVImp implements DAO_BDV {

	private String [][] Vestimentas;
	public static int Num_AtriVestimenta = 7;
	
	
	@Override
	public void crearReserva(TransferReserva reserva) {
		ArrayList<TransferReserva> reservas = null;
			reservas.add(reserva);
			GuardarReserva(reservas);
		
		
	}

	@Override
	public void AnularReserva(String ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ModificarReserva(int Codigo, int Cantidad, int Importe, Date FechaIni, Date FechaFin) {
		// TODO Auto-generated method stub
		
	}

	
	public TransferVestimenta buscarVestimenta(String ID) {
		
		TransferVestimenta vestimenta=new TransferVestimenta();
		for(int i = 0; i < Vestimentas.length; i++) {
			if(ID == Vestimentas[i][0]) {
				vestimenta = new TransferVestimenta();
				vestimenta.setID(ID);
				vestimenta.setNombrPropietario(Vestimentas[i][1]);
				vestimenta.setLocalizacion(Vestimentas[i][2]);
				vestimenta.setDescripcion(Vestimentas[i][3]);
				vestimenta.setDisponible(Vestimentas[i][4]);
				vestimenta.setTarifa(Integer.parseInt(Vestimentas[i][5]));
				vestimenta.setCosteFabricacion(Integer.parseInt(Vestimentas[i][6]));
			}
		}
		if(vestimenta ==null) {
			System.out.println("Codigo no existente en BBDD");
		}
		return vestimenta;
	}
	public void loadVestimenta() {
		String linea;
		try(BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream("src/integracion/catering/BBDD_Vestimentas.txt")))){
			Vestimentas = new String[Integer.parseInt(r.readLine())][4];
			int i = 0;
			linea = r.readLine();
			while(linea != null) {
				if(Num_AtriVestimenta != linea.length() || Integer.parseInt(linea) < 1000) throw new IOException("El fichero contiene un codigo de producto incorrecto");
				Vestimentas[i][0] = linea;
				linea = r.readLine();
				Vestimentas[i][1] = linea;
				linea = r.readLine();
				Vestimentas[i][2] = linea;
				linea = r.readLine();
				Vestimentas[i][3] = linea;
				linea = r.readLine();
				Vestimentas[i][4] = linea;
				linea = r.readLine();
				Vestimentas[i][5] = linea;
				linea = r.readLine();
				Vestimentas[i][6] = linea;
				i++;
				linea = r.readLine();
			}
			
		} catch (IOException | IllegalArgumentException ex) {
			System.err.println(ex.getLocalizedMessage());
			System.exit(1);
		}
		
	}
	
	private void GuardarReserva(ArrayList<TransferReserva> Reservas) {
		try(BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src/integracion/catering/BBDD_Reserva.txt")))){
			for(TransferReserva reserva : Reservas) {
				out.write("Codigo: " + reserva.getCodigo()+"\n");
				out.write("Cantidad: " + reserva.getCantidad()+"\n");
				out.write("Importe: " + reserva.getImporte() + "\n");
				out.write("FechaIni: " + reserva.getFechaIni() + "\n");
				out.write("FechaFin: " + reserva.getFechaFin() + "\n");
			}
			
		
		} catch (IOException e) {
			
		}
	}
	
	
	}
	
