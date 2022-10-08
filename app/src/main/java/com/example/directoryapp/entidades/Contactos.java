package com.example.directoryapp.entidades;

public class Contactos {

    private int id;
    private String nombre;
    private String webpage;
    private String telefono;
    private String email;
    private String proyser;
    private int consultoria;
    private int desarrollo;
    private int fabrica;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProyser() {
        return proyser;
    }

    public void setProyser(String proyser) {
        this.proyser = proyser;
    }

    public int isConsultoria() {
        return consultoria;
    }

    public void setConsultoria(int consultoria) {
        this.consultoria = consultoria;
    }

    public int isDesarrollo() {
        return desarrollo;
    }

    public void setDesarrollo(int desarrollo) {
        this.desarrollo = desarrollo;
    }

    public int isFabrica() {
        return fabrica;
    }

    public void setFabrica(int fabrica) {
        this.fabrica = fabrica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
