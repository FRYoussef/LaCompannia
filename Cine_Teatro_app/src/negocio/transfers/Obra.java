package negocio.transfers;

import java.io.Serializable;
import java.util.ArrayList;

public class Obra implements Serializable {
    private int id;
    private String titulo;
    private String pais;
    private int anno;
    private ObraGenero obraGenero;
    private String premios;
    private String sinopsis;
    private ArrayList<PersonaEspectaculo> actores;
    private ArrayList<PersonaEspectaculo> directores;
    private ArrayList<PersonaEspectaculo> productores;
    private boolean esDestacada;
    
    public Obra(int id, String titulo, String pais, int anno, ObraGenero obraGenero, String premios, String sinopsis, ArrayList<PersonaEspectaculo> actores, ArrayList<PersonaEspectaculo> directores, ArrayList<PersonaEspectaculo> productores) {
        this.id = id;
        this.titulo = titulo;
        this.pais = pais;
        this.anno = anno;
        this.obraGenero = obraGenero;
        this.premios = premios;
        this.sinopsis = sinopsis;
        this.actores = actores;
        this.directores = directores;
        this.productores = productores;
        this.esDestacada = false;
    }

    public Obra(String titulo, String pais, int anno, ObraGenero obraGenero, String premios, String sinopsis, ArrayList<PersonaEspectaculo> actores, ArrayList<PersonaEspectaculo> directores, ArrayList<PersonaEspectaculo> productores) {
        this.id = (int) Math.floor(Math.random()*1000000);
        this.titulo = titulo;
        this.pais = pais;
        this.anno = anno;
        this.obraGenero = obraGenero;
        this.premios = premios;
        this.sinopsis = sinopsis;
        this.actores = actores;
        this.directores = directores;
        this.productores = productores;
        this.esDestacada = false;
    }

    public Obra(String titulo, String pais, int anno, ObraGenero obraGenero) {
        this.id = (int) Math.floor(Math.random()*1000000);
        this.titulo = titulo;
        this.pais = pais;
        this.anno = anno;
        this.obraGenero = obraGenero;
        this.esDestacada = false;
    }

    public Obra(String titulo, String pais, int anno, ObraGenero obraGenero, String sinopsis, boolean destacada) {
    	this.id = (int) Math.floor(Math.random()*1000000);
    	this.titulo = titulo;
    	this.pais = pais;
    	this.anno = anno;
    	this.obraGenero = obraGenero;
    	this.sinopsis = sinopsis;
    	this.esDestacada = destacada;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public ObraGenero getObraGenero() {
        return obraGenero;
    }

    public void setObraGenero(ObraGenero obraGenero) {
        this.obraGenero = obraGenero;
    }

    public String getPremios() {
        return premios;
    }

    public void setPremios(String premios) {
        this.premios = premios;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public ArrayList<PersonaEspectaculo> getActores() {
        return actores;
    }

    public void setActores(ArrayList<PersonaEspectaculo> actores) {
        this.actores = actores;
    }

    public ArrayList<PersonaEspectaculo> getDirectores() {
        return directores;
    }

    public void setDirectores(ArrayList<PersonaEspectaculo> directores) {
        this.directores = directores;
    }

    public ArrayList<PersonaEspectaculo> getProductores() {
        return productores;
    }

    public void setProductores(ArrayList<PersonaEspectaculo> productores) {
        this.productores = productores;
    }
    
    public boolean getDestacada() {
        return this.esDestacada;
    }

    public void setDestacada(boolean d) {
        this.esDestacada = d;
    }
    
    public String toString() {
    	String a = "";
    	a += this.titulo + "\n";
    	a += this.pais + "\n";
    	a += this.anno + "\n";
    	a += this.sinopsis + "\n";
    	
    	return a;
    }
}
