package integracion.reservas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import negocio.transfers.Cliente;
import negocio.transfers.DatosBancarios;

public class DAOClienteImp implements DAOCliente {

	@Override
	public boolean altaCliente(Cliente tCli) {
		ArrayList<Cliente> in = loadData();
		if(in != null) {
			in.add(tCli);
			return writeData(in);
		}
		return false;
	}

	@Override
	public boolean modCliente(Cliente tCli) {
		ArrayList<Cliente> in = loadData();
		if(in != null) {
			for(int i = 0; i < in.size(); ++i) {
				if(in.get(i).getDni().equals(tCli.getDni())) {
					in.remove(i);
					in.add(tCli);
					return writeData(in);
				}
			}
		}
		return false;
	}

	@Override
	public boolean elimCliente(String dniCliente) {
		ArrayList<Cliente> in = loadData();
		if(in != null) {
			for(int i = 0; i < in.size(); ++i) {
				if(in.get(i).getDni().equals(dniCliente)) {
					in.remove(i);
					return writeData(in);
				}
			}
		}
		return false;
	}

	@Override
	public Cliente consultaCliente(String dniCliente) {
		ArrayList<Cliente> in = loadData();
		if(in != null) {
			for(Cliente tCli : in) {
				if(tCli.getDni().equals(dniCliente)) return tCli;
			}
		}
		return null;
	}
	
	private boolean writeData(ArrayList<Cliente> tCliArray) {
		try(BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("clientes.txt"), "ISO-8859-15"))){
			out.write("Clientes_Cine/Teatro" + System.lineSeparator());
			for(Cliente tCli : tCliArray) {
				out.write("$" + System.lineSeparator());
				writeTransfer(out, tCli);
			}
			out.write("#" + System.lineSeparator());
			out.flush();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
	
	private ArrayList<Cliente> loadData(){
		ArrayList<Cliente> inData = new ArrayList<Cliente>();
		try(BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("clientes.txt"), "ISO-8859-15"))){
			if(!in.readLine().trim().equals("Clientes_Cine/Teatro")) throw new IOException("Fichero mal formado");
			String line = in.readLine().trim();
			while(!line.equals("#")) {
				inData.add(loadTransfer(in));
				line = in.readLine().trim();
			}
		} catch (IOException e) {
			return null;
		}
		return inData;
	}
	
	private Cliente loadTransfer(BufferedReader in) throws IOException {
		Cliente tCli = new Cliente();
		tCli.setDatosBancarios(null);
		tCli.setDni(in.readLine().trim());
		tCli.setNombre(in.readLine().trim());
		tCli.setApellidos(in.readLine().trim());
		tCli.setPais(in.readLine().trim());
		tCli.setCiudad(in.readLine().trim());
		tCli.setCalle(in.readLine().trim());
		tCli.setCodPostal(in.readLine().trim());
		tCli.setTelefono(Integer.parseInt(in.readLine().trim()));
		tCli.setEmail(in.readLine().trim());
		tCli.setComensal(Boolean.parseBoolean(in.readLine().trim()));
		String numCuenta = in.readLine().trim();
		if(numCuenta.equals("")) return tCli;
		DatosBancarios tBank = new DatosBancarios();
		tBank.setNumCuenta(numCuenta);
		tBank.setNombreCuenta(in.readLine().trim());
		tBank.setPaisCuenta(in.readLine().trim());
		tCli.setDatosBancarios(tBank);
		return tCli;
	}
	
	private void writeTransfer(BufferedWriter out, Cliente tCli) throws IOException {
		out.write(tCli.getDni() + System.lineSeparator());
		out.write(tCli.getNombre() + System.lineSeparator());
		out.write(tCli.getApellidos() + System.lineSeparator());
		out.write(tCli.getPais() + System.lineSeparator());
		out.write(tCli.getCiudad() + System.lineSeparator());
		out.write(tCli.getCalle() + System.lineSeparator());
		out.write(tCli.getCodPostal() + System.lineSeparator());
		out.write(tCli.getTelefono() + System.lineSeparator());
		out.write(tCli.getEmail() + System.lineSeparator());
		out.write(tCli.isComensal() + System.lineSeparator());
		if(tCli.getDatosBancarios() != null) {
			out.write(tCli.getDatosBancarios().getNumCuenta() + System.lineSeparator());
			out.write(tCli.getDatosBancarios().getNombreCuenta() + System.lineSeparator());
			out.write(tCli.getDatosBancarios().getPaisCuenta());
		}
		out.write(System.lineSeparator());
	}

}
