package integracion.reservas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

import negocio.reservas.IntervaloTiempo;
import negocio.transfers.Pago;
import negocio.transfers.TipoPago;

public class DAOPagoImp implements DAOPago {

	@Override
	public boolean altaPago(Pago tPago) {
		ArrayList<Pago> in = loadData();
		if(in != null) {
			in.add(tPago);
			return writeData(in);
		}
		return false;
	}

	@Override
	public boolean modPago(Pago tPago) {
		ArrayList<Pago> in = loadData();
		if(in != null) {
			for(int i = 0; i < in.size(); ++i) {
				if(in.get(i).getIdCompra().equals(tPago.getIdCompra())) {
					in.remove(i);
					in.add(tPago);
					return writeData(in);
				}
			}
		}
		return false;
	}

	@Override
	public boolean elimPago(String idCompra) {
		ArrayList<Pago> in = loadData();
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
	public Pago consultaPago(String idCompra) {
		ArrayList<Pago> in = loadData();
		if(in != null) {
			for(Pago tPago : in) {
				if(tPago.getIdCompra().equals(idCompra)) return tPago;
			}
		}
		return null;
	}
	
	
	private boolean writeData(ArrayList<Pago> tPagoArray) {
		try(BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("pagos.txt"), "ISO-8859-15"))){
			out.write("Pagos_Cine/Teatro" + System.lineSeparator());
			for(Pago tPago : tPagoArray) {
				out.write("$" + System.lineSeparator());
				writeTransfer(out, tPago);
			}
			out.write("#" + System.lineSeparator());
			out.flush();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	
	private ArrayList<Pago> loadData(){
		ArrayList<Pago> inData = new ArrayList<Pago>();
		try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("pagos.txt"), "ISO-8859-15"))){
			if(!in.readLine().trim().equals("Pagos_Cine/Teatro")) throw new IOException("Fichero mal formado");
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
	
	private Pago loadTransfer(BufferedReader in) throws IOException, NumberFormatException {
		Pago tPago = new Pago();
		tPago.setIdCompra(in.readLine().trim());
		tPago.setDniCliente(in.readLine().trim());
		tPago.setDescripcion(in.readLine().trim());
		tPago.setDineroCobrado(Float.parseFloat(in.readLine().trim()));
		tPago.setDineroAdeudado(Float.parseFloat(in.readLine().trim()));
		tPago.setTipoPago(TipoPago.getValue(in.readLine().trim()));
		if(tPago.getTipoPago() == null) throw new NumberFormatException("Tipo pago mal definido");
		tPago.setFechaIni(IntervaloTiempo.parseDate(in.readLine().trim()));
		if(tPago.getFechaIni() == null) throw new NumberFormatException("Fecha de inicio mal definida");
		String[] fechas = in.readLine().trim().split(" ");
		ArrayList<Date> fechasCobro = new ArrayList<Date>();
		for(String str : fechas) 
			fechasCobro.add(IntervaloTiempo.parseDate(str));
		tPago.setFechasCobro(fechasCobro);
		return tPago;
	}
	
	private void writeTransfer(BufferedWriter out, Pago tPago) throws IOException {
		out.write(tPago.getIdCompra() + System.lineSeparator());
		out.write(tPago.getDniCliente() + System.lineSeparator());
		out.write(tPago.getDescripcion() + System.lineSeparator());
		out.write(tPago.getDineroCobrado() + System.lineSeparator());
		out.write(tPago.getDineroAdeudado() + System.lineSeparator());
		out.write(tPago.getTipoPago().name() + System.lineSeparator());
		out.write(IntervaloTiempo.formatDate(tPago.getFechaIni()) + System.lineSeparator());
		ArrayList<Date> fechasCobro = tPago.getFechasCobro();
		for(Date fecha : fechasCobro) {
			out.write(IntervaloTiempo.formatDate(fecha) + " ");
		}
		out.write(System.lineSeparator());
	}

}
