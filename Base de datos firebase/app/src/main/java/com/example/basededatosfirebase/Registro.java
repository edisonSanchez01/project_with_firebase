package com.example.basededatosfirebase;

public class Registro {

    private String identificacion;
    private String nombre;
    private String direccion;
    private String celular;

    public Registro( String identificacion, String nombre, String direccion, String celular) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.direccion = direccion;
        this.celular = celular;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
