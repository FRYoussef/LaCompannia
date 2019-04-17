package negocio.sistemas_aplicacion.actores;

public class ActoresSA {

    private static ActoresSA sa = null;

    private ActoresSA(){ }

    static public ActoresSA getInstancia() {
        if (sa == null){
            sa = new ActoresSA();
            return sa;
        }
        else
            return sa;
    }

}
