package integracion.recursosAudiovisuales;

import java.sql.Timestamp;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class RecursoAudioVisual {
	
	@DatabaseField(generatedId = true)
	private Integer id;
	
	@DatabaseField
	private Integer unidades;
	
	@DatabaseField
	private String nombre;
	
	@DatabaseField(width=2000)
	private String descripcion;
	
	@DatabaseField
	private TipoRecurso tipo;
	
	@DatabaseField
	private Timestamp fechaAdquisicion;
	
	@DatabaseField
	private Timestamp fechaDevolucion;
	
	@DatabaseField
	private Integer precio;
	
	@DatabaseField
	private boolean montable;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public TipoRecurso getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoRecurso tipo) {
		this.tipo = tipo;
	}

	public Integer getUnidades() {
		return unidades;
	}

	public void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	public Timestamp getFechaAdquisicion() {
		return fechaAdquisicion;
	}

	public void setFechaAdquisicion(Timestamp fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public Timestamp getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Timestamp fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public boolean isMontable() {
		return montable;
	}

	public void setMontable(boolean esMontable) {
		this.montable = esMontable;
	}
}
