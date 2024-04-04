package com.miapp.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nombre;
    private String cuit;
    private List<Libro> inventario;

    public Biblioteca(String nombre, String cuit) {
        this.nombre = nombre;
        this.cuit = cuit;
        this.inventario = new ArrayList<>();
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public List<Libro> getInventario() {
        return inventario;
    }

    public void setInventario(List<Libro> inventario) {
        this.inventario = inventario;
    }

    // Método para verificar la disponibilidad de un libro en el inventario
    public boolean verificarDisponibilidad(Libro libro) {
        return inventario.contains(libro) && libro.isDisponible();
    }
}