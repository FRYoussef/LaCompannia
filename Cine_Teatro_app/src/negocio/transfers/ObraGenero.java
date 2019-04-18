package negocio.transfers;

import java.util.ArrayList;

public enum ObraGenero {
    Comedia, Terror, Aventuras, Policiaca, SiFi, Drama;

    public static ArrayList<String> getArrayNames(){
        ArrayList<String> al = new ArrayList();
        al.add(ObraGenero.Comedia.name());
        al.add(ObraGenero.Terror.name());
        al.add(ObraGenero.Aventuras.name());
        al.add(ObraGenero.Policiaca.name());
        al.add(ObraGenero.SiFi.name());
        al.add(ObraGenero.Drama.name());
        return al;
    }
}
