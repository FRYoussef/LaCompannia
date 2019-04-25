package integracion.recursosAudiovisuales;

import java.sql.SQLException;

import presentacion.recursosAudiovisuales.*;

public class H2FactoriaDAO extends FactoriaDAO{
	
	public H2FactoriaDAO() {
		
	}

	@Override
	public DaoRecurso getDaoRecurso() {
		try {
			return new H2DaoRecurso(RecursosAudiovisuales.connectionSource);
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public DaoCompra getDaoCompra() {
		try {
			return new H2DaoCompra(RecursosAudiovisuales.connectionSource);
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public DaoAveria getDaoAveria() {
		try {
			return new H2DaoAveria(RecursosAudiovisuales.connectionSource);
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public DaoMontaje getDaoMontaje() {
		try {
			return new H2DaoMontaje(RecursosAudiovisuales.connectionSource);
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}

}


/* crear tablas
 * TableUtils.createTable(connectionSource, RecursoAudioVisual.class);
 * 
 * RecursoAudioVisual r1 = new RecursoAudioVisual();
 * r1.setNombre("Luces StairVille TRI LED Bundle"); r1.
 * setDescripcion("El Stage TRI LED Bundle completo es muy sencillo de manejar por medio del pedal de control incluido o por DMX 512 (controles desde el pedal: Modo autom�tico, modo Sound-to-Light, Freeze & Blackout)"
 * ); r1.setFechaAdquisicion(new Timestamp(System.currentTimeMillis()));
 * r1.setFechaDevolucion(new Timestamp(System.currentTimeMillis()));
 * r1.setPrecio(248); r1.setMontable(true); r1.setTipo(TipoRecurso.LUCES);
 * r1.setUnidades(1);
 * 
 * RecursoAudioVisual r2 = new RecursoAudioVisual();
 * r2.setNombre("Luces AMZdeal Softbox Focos kit"); r2.
 * setDescripcion("l Amzdeal Softbox proporciona un ambiente brillante para tomar la foto, es extremadamente adecuado para todos los fot�grafos a nivel profesional o amateur.\r\n"
 * +
 * "l La iluminaci�n contua LED 135W 5500K proporciona la luz suave y difusa. El color de la luz es perfectamente adecuado para fotograf�a digital o anal�gica y no es azul o amarillo."
 * ); r2.setFechaAdquisicion(new Timestamp(System.currentTimeMillis()));
 * r2.setFechaDevolucion(new Timestamp(System.currentTimeMillis()));
 * r2.setMontable(true); r2.setPrecio(56); r2.setTipo(TipoRecurso.LUCES);
 * r2.setUnidades(1);
 * 
 * RecursoAudioVisual r3 = new RecursoAudioVisual(); r3.setNombre("Kit Podium");
 * r3.
 * setDescripcion("plataformas de aluminio plegables de madera contrachapada de 18 mm de espesor tratada antideslizamiento. Las placas se fijan a la estructura sin herramientas gracias a un sistema de encajes"
 * ); r3.setFechaAdquisicion(new Timestamp(System.currentTimeMillis()));
 * r3.setFechaDevolucion(new Timestamp(System.currentTimeMillis()));
 * r3.setMontable(true); r3.setPrecio(258); r3.setTipo(TipoRecurso.PLATAFORMA);
 * r3.setUnidades(3);
 * 
 * RecursoAudioVisual r4 = new RecursoAudioVisual();
 * r4.setNombre("Escalera Profesional de Aluminio Triple Tijera"); r4.
 * setDescripcion("N�mero de pelda�os 3x9. Altura alcanzable 4,60 m en tijera y en apoyo 6,0 m. Altura total plegada 2,50 m. Ancho superior frotal escalera 47 cm"
 * ); r4.setFechaAdquisicion(new Timestamp(System.currentTimeMillis()));
 * r4.setFechaDevolucion(new Timestamp(System.currentTimeMillis()));
 * r4.setMontable(true); r4.setPrecio(159); r4.setTipo(TipoRecurso.HERRAMIENTA);
 * r4.setUnidades(1);
 * 
 * RecursoAudioVisual r5 = new RecursoAudioVisual();
 * r5.setNombre("Adobe creative cloud"); r5.
 * setDescripcion("Aplicaciones creativas l�deres del sector con gesti�n de licencias sencilla y de f�cil implementaci�n"
 * ); r5.setFechaAdquisicion(new Timestamp(System.currentTimeMillis()));
 * r5.setFechaDevolucion(new Timestamp(System.currentTimeMillis()));
 * r5.setMontable(true); r5.setPrecio(159); r5.setTipo(TipoRecurso.DIGITAL);
 * r5.setUnidades(1);
 * 
 * getDaoRecurso().create(r1); getDaoRecurso().create(r2);
 * getDaoRecurso().create(r3); getDaoRecurso().create(r4);
 * getDaoRecurso().create(r5);
 * 
 * connectionSource.close();
 */