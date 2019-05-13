package integracion.reservas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import negocio.reservas.FiltroBusquedaLugar;
import negocio.reservas.IntervaloTiempo;
import negocio.transfers.Lugar;

public class DAOLugarImp implements DAOLugar {
	
	private static final String ruta = new File("src/integracion/reservas/ficheros/lugares.txt").getAbsolutePath();

	@Override
	public boolean altaLugar(Lugar tLugar) {
		ArrayList<Lugar> in = loadData();
		if(in != null) {
			in.add(tLugar);
			return writeData(in);
		}
		return false;
	}

	@Override
	public boolean modLugar(Lugar tLugar) {
		ArrayList<Lugar> in = loadData();
		if(in != null) {
			for(int i = 0; i < in.size(); ++i) {
				if(in.get(i).getIdLugar().equals(tLugar.getIdLugar())) {
					in.remove(i);
					in.add(tLugar);
					return writeData(in);
				}
			}
		}
		return false;
	}

	@Override
	public boolean elimLugar(String idLugar) {
		ArrayList<Lugar> in = loadData();
		if(in != null) {
			for(int i = 0; i < in.size(); ++i) {
				if(in.get(i).getIdLugar().equals(idLugar)) {
					in.remove(i);
					return writeData(in);
				}
			}
		}
		return false;
	}

	@Override
	public ArrayList<Lugar> consultaLugar(FiltroBusquedaLugar filtro) {
		ArrayList<Lugar> in = loadData();
		if(in == null) return null;
		ArrayList<Lugar> out = new ArrayList<Lugar>();
		for(Lugar tLugar : in) {
			if(filtro.isInFilter(tLugar)) out.add(tLugar);
		}
		return out;
	}

	@Override
	public Lugar consultaLugar(String idLugar) {
		ArrayList<Lugar> in = loadData();
		if(in != null) {
			for(Lugar tLugar : in) {
				if(tLugar.getIdLugar().equals(idLugar)) return tLugar;
			}
		}
		return null;
	}
	
	private boolean writeData(ArrayList<Lugar> tLugarArray) {
		try(BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta), "UTF-8"))){
			out.write("Lugares_Cine/Teatro" + System.lineSeparator());
			for(Lugar tLugar : tLugarArray) {
				out.write("$" + System.lineSeparator());
				writeTransfer(out, tLugar);
			}
			out.write("#" + System.lineSeparator());
			out.flush();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	
	private ArrayList<Lugar> loadData(){
		ArrayList<Lugar> inData = new ArrayList<Lugar>();
		try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "UTF-8"))){
			if(!in.readLine().trim().equals("Lugares_Cine/Teatro")) throw new IOException("Fichero mal formado");
			String line = in.readLine().trim();
			while(!line.equals("#")) {
				inData.add(loadTransfer(in));
				line = in.readLine().trim();
			}
		} catch (IOException | NumberFormatException e) {
			return null;
		}
		return inData;
	}
	
	private Lugar loadTransfer(BufferedReader in) throws IOException, NumberFormatException {
		Lugar tLugar = new Lugar();
		tLugar.setIdLugar(in.readLine().trim());
		tLugar.setDniPropietario(in.readLine().trim());
		tLugar.setNombre(in.readLine().trim());
		tLugar.setDescripcion(in.readLine().trim());
		tLugar.setComAutonoma(in.readLine().trim());
		tLugar.setCiudad(in.readLine().trim());
		tLugar.setCalle(in.readLine().trim());
		tLugar.setAforo(Integer.parseInt(in.readLine().trim()));
		tLugar.setTarifa(Float.parseFloat(in.readLine().trim()));
		tLugar.setPathFoto(in.readLine().trim());
		String[] fechas = in.readLine().trim().split(" ");
		ArrayList<IntervaloTiempo> ocupacion = new ArrayList<IntervaloTiempo>();
		if(!fechas[0].equals("")) {
			for(String str : fechas) {
				String[] strFecha = str.split("-");
				if(strFecha.length != 2) throw new NumberFormatException("Intervalo mal definido " + str);
				IntervaloTiempo interval = IntervaloTiempo.parseInterval(strFecha[0], strFecha[1]);
				if(interval == null) throw new NumberFormatException("Intervalo mal definido " + str);
				ocupacion.add(interval);
			}
		}
		tLugar.setOcupacion(ocupacion);
		return tLugar;
	}
	
	private void writeTransfer(BufferedWriter out, Lugar tLugar) throws IOException {
		out.write(tLugar.getIdLugar() + System.lineSeparator());
		out.write(tLugar.getDniPropietario() + System.lineSeparator());
		out.write(tLugar.getNombre() + System.lineSeparator());
		out.write(tLugar.getDescripcion() + System.lineSeparator());
		out.write(tLugar.getComAutonoma() + System.lineSeparator());
		out.write(tLugar.getCiudad() + System.lineSeparator());
		out.write(tLugar.getCalle() + System.lineSeparator());
		out.write(tLugar.getAforo() + System.lineSeparator());
		out.write(tLugar.getTarifa() + System.lineSeparator());
		out.write(tLugar.getPathFoto() + System.lineSeparator());
		ArrayList<IntervaloTiempo> ocupacion = tLugar.getOcupacion();
		for(IntervaloTiempo inter : ocupacion) {
			out.write(inter + " ");
		}
		out.write(System.lineSeparator());
	}

	

}
