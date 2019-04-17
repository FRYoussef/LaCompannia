package presentacion.controladores.actores;

public class Bundle {
    public static final int ACTOR = 0;
    public static final int DIRECTOR = 1;
    public static final int PRODUCTOR = 2;
    public static final int OBRA = 3;

    private int contexto = -1;

    public Bundle(int contexto) {
        this.contexto = contexto;
    }

    public int getContexto() {
        return contexto;
    }
}
