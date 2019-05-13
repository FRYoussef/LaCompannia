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

import negocio.reservas.IntervaloTiempo;
import negocio.transfers.ReservaLugar;
import negocio.transfers.TipoCompra;

public class DAOReservaLugarImp implements DAOReservaLugar {

	private static final String ruta = new File("src/integracion/reservas/ficheros/resLugares.txt").getAbsolutePath();
	
	@Override
	public boolean altaReserva(ReservaLugar tRes) {
		ArrayList<ReservaLugar> in = loadData();
		if(in != null) {
			in.add(tRes);
			return writeData(in);
		}
		return false;
	}

	@Override
	public boolean elimReserva(String idCompra) {
		ArrayList<ReservaLugar> in = loadData();
		if(in != null) {
			for(int i = 0; i < in.size(); ++i) {
				if(in.get(i).getIdCompra().equals(idCompra)) {
					in.remove(i);
					return writeData(in);
				}
			}
		}
		return false;
	}

	@Override
	public ReservaLugar consultaReserva(String idCompra) {
		ArrayList<ReservaLugar> in = loadData();
		if(in != null) {
			for(ReservaLugar tRes : in) {
				if(tRes.getIdCompra().equals(idCompra)) return tRes;
			}
		}
		return null;
	}

	
	private boolean writeData(ArrayList<ReservaLugar> tResArray) {
		try(BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ruta), "UTF-8"))){
			out.write("ReservaLugares_Cine/Teatro" + System.lineSeparator());
			for(ReservaLugar tRes : tResArray) {
				out.write("$" + System.lineSeparator());
				writeTransfer(out, tRes);
			}
			out.write("#" + System.lineSeparator());
			out.flush();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	
	private ArrayList<ReservaLugar> loadData(){
		ArrayList<ReservaLugar> inData = new ArrayList<ReservaLugar>();
		try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(ruta), "UTF-8"))){
			if(!in.readLine().trim().equals("ReservaLugares_Cine/Teatro")) throw new IOException("Fichero mal formado");
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
	
	private ReservaLugar loadTransfer(BufferedReader in) throws IOException, NumberFormatException {
		ReservaLugar tRes = new ReservaLugar();
		tRes.setIdCompra(in.readLine().trim());
		tRes.setTipoCompra(TipoCompra.getValue(in.readLine().trim()));
		tRes.setIdLugar(in.readLine().trim());
		tRes.setFechaIni(IntervaloTiempo.parseDate(in.readLine().trim()));
		tRes.setFechaFin(IntervaloTiempo.parseDate(in.readLine().trim()));
		if(tRes.getFechaIni() == null || tRes.getFechaFin() == null) throw new NumberFormatException("Intervalo mal definido");
		return tRes;
	}
	
	private void writeTransfer(BufferedWriter out, ReservaLugar tRes) throws IOException {
		out.write(tRes.getIdCompra() + System.lineSeparator());
		out.write(tRes.getTipoCompra() + System.lineSeparator());
		out.write(tRes.getIdLugar() + System.lineSeparator());
		out.write(IntervaloTiempo.formatDate(tRes.getFechaIni()) + System.lineSeparator());
		out.write(IntervaloTiempo.formatDate(tRes.getFechaFin()) + System.lineSeparator());
	}
	
}
