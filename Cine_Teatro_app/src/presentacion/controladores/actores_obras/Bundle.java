package presentacion.controladores.actores_obras;

public class Bundle {
    public static final int ACTOR = 0;
    public static final int DIRECTOR = 1;
    public static final int PRODUCTOR = 2;
    public static final int OBRA = 3;

    private int contexto = -1;
    private Object obj = null;

    public Bundle(int contexto) {
        this.contexto = contexto;
    }

    public Bundle(int contexto, Object obj) {
        this.contexto = contexto;
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public int getContexto() {
        return contexto;
    }
}
