package presentacion.recursosAudiovisuales;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.LocalLog;
import com.j256.ormlite.support.ConnectionSource;

import negocio.recursosAudiovisuales.*;
import presentacion.controladores.recursosAudiovisuales.RecursosAudiovisualesController;
import presentacion.vistas.*;

public class RecursosAudiovisuales implements GUI{
	public static String databaseUrl = "jdbc:h2:file:./database";
	public static ConnectionSource connectionSource;
	private static RecursosAudiovisuales instance;
	
	static {
		try {
			connectionSource = new JdbcConnectionSource(databaseUrl);
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) { e.printStackTrace(); }
		System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");
	}

	private RecursosAudiovisualesModel mainModel;
	private RecursosAudiovisualesView mainView;
	private RecursosAudiovisualesController controlador;
	private boolean state=true;
	
	private RecursosAudiovisuales() {
		mainModel = new RecursosAudiovisualesModel();
		mainView = new RecursosAudiovisualesView(mainModel);
		controlador = new RecursosAudiovisualesController(mainView, mainModel);
		mainView.setEnabled(state);
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override public void run() {
				controlador.run();
			}
		});
	}

	@Override
	public void ejecutar() {
		mainView.setVisible(state);
		state=!state;
	}

	public static GUI getInstance() {
		if(instance == null) instance = new RecursosAudiovisuales();
		return instance;
	}
	
}


