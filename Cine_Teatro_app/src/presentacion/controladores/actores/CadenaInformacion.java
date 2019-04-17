package presentacion.controladores.actores;

/**
 * Esta interfaz será usada para pasar información a los controladores,
 * esto es, para saber de donde procede la pantalla actual, actor director, obra
 */
public interface CadenaInformacion {
    int ACTOR = 0;
    int DIRECTOR = 1;
    int PRODICTOR = 2;
    int OBRA = 3;

    void setActuacion(int tipo);
}
