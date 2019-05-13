package integracion.obras;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import negocio.transfers.Obra;
import presentacion.vistas.obras.ObrasImpGUI;

public class DAOObras {

	private ObrasImpGUI vista;
	
	public DAOObras() {
		
	}
	
	
	
	public boolean save(Obra o) {
		boolean s = false;
		FileWriter archivo = null;
		PrintWriter pw = null;
		try {
			archivo = new FileWriter ("src/integracion/obras/bbdd_obras.txt", true);
			
			pw = new PrintWriter(archivo);
			pw.print(o.toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		finally {	
				try {
					if(archivo != null)	{		
						archivo.close();
						pw.close();
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		}		
		return s;
	}
	
	public List<Obra> load() throws FileNotFoundException{
		List<Obra> o = new ArrayList<Obra>();
		
			InputStream is = new FileInputStream("obras/bbdd_obras.txt");	
		
		
		return null;		
	}
	
	
}
