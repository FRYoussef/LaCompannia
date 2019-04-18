package negocio.transfers;

import java.io.Serializable;

public class PersonaEspectaculo extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    private String premios;
    private String bibliografia;
    private PersonaEspectaculoRol rol;

    public PersonaEspectaculo(int id, String nombre, String apellidos, String dni, int telefono, String email, String premios, String bibliografia, PersonaEspectaculoRol rol) {
        super(id, nombre, apellidos, dni, telefono, email);
        this.premios = premios;
        this.bibliografia = bibliografia;
        this.rol = rol;
    }

    public PersonaEspectaculo(String nombre, String apellidos, String dni, int telefono, String email, String premios, String bibliografia, PersonaEspectaculoRol rol) {
        super(nombre, apellidos, dni, telefono, email);
        this.premios = premios;
        this.bibliografia = bibliografia;
        this.rol = rol;
    }

    public PersonaEspectaculo(int id, String nombre, String apellidos, String dni, int telefono, String email, PersonaEspectaculoRol rol) {
        super(id, nombre, apellidos, dni, telefono, email);
        this.rol = rol;
    }

    public PersonaEspectaculo(String nombre, String apellidos, String dni, int telefono, String email, PersonaEspectaculoRol rol) {
        super(nombre, apellidos, dni, telefono, email);
        this.rol = rol;
    }

    public String getPremios() {
        return premios;
    }

    public void setPremios(String premios) {
        this.premios = premios;
    }

    public String getBibliografia() {
        return bibliografia;
    }

    public void setBibliografia(String bibliografia) {
        this.bibliografia = bibliografia;
    }

    public PersonaEspectaculoRol getRol() {
        return rol;
    }

    public void setRol(PersonaEspectaculoRol rol) {
        this.rol = rol;
    }
}
