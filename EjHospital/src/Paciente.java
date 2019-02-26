import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private String Nombre;

    private String Direccion;

    private int NumHistoriaClinica;

    public List<Análisis> análisis = new ArrayList<Análisis> ();

    public List<Médico>  = new ArrayList<Médico> ();

    public List<Enfermero>  = new ArrayList<Enfermero> ();

    String getDireccion() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.Direccion;
    }

    void setDireccion(String value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.Direccion = value;
    }

    int getNumHistoriaClinica() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.NumHistoriaClinica;
    }

    String getNombre() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.Nombre;
    }

}
