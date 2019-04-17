package negocio.transfers;

public class PersonaEspectaculo extends Persona {
    private String premios;
    private String bibliografia;

    public PersonaEspectaculo(String nombre, String apellidos, String dni, int telefono, String email, String premios, String bibliografia) {
        super(nombre, apellidos, dni, telefono, email);
        this.premios = premios;
        this.bibliografia = bibliografia;
    }

    public String getPremios() {
        return premios;
    }

    public String getBibliografia() {
        return bibliografia;
    }
}
