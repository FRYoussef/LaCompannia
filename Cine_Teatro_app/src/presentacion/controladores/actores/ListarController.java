package presentacion.controladores.actores;

public class ListarController implements CadenaInformacion {
    private static int actuacion = -1;






    @Override
    public void setActuacion(int tipo) {
        actuacion = tipo;
    }
}
