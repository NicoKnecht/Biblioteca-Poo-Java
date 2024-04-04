package com.miapp.biblioteca.servicios;

import com.miapp.biblioteca.Biblioteca;
import com.miapp.biblioteca.Libro;
import com.miapp.biblioteca.Usuario;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaServicios {
    private Biblioteca biblioteca;
    private List<Usuario> usuarios;

    public BibliotecaServicios(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        this.usuarios = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        biblioteca.getInventario().add(libro);
        System.out.println("Libro agregado al inventario.");
    }

    public void prestarLibro(Libro libro, Usuario usuario) {
        if (biblioteca.verificarDisponibilidad(libro)) {
            usuario.getLibrosPrestados().add(libro);
            libro.setDisponible(false);
            System.out.println("Libro prestado correctamente.");
        } else {
            System.out.println("El libro no está disponible para préstamo.");
        }
    }

    public void devolverLibro(Libro libro, Usuario usuario) {
        if (usuario.getLibrosPrestados().contains(libro)) {
            usuario.getLibrosPrestados().remove(libro);
            libro.setDisponible(true);
            System.out.println("Libro devuelto correctamente.");
        } else {
            System.out.println("El usuario no tiene este libro prestado.");
        }
    }

    public List<Libro> buscarLibros(String criterio, String valor) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : biblioteca.getInventario()) {
            String valorLibro = null;
            switch (criterio.toLowerCase()) {
                case "titulo":
                    valorLibro = libro.getTitulo().toLowerCase();
                    break;
                case "autor":
                    valorLibro = libro.getAutor().toLowerCase();
                    break;
                case "genero":
                    valorLibro = libro.getGenero().toLowerCase();
                    break;
                default:
                    System.out.println("Criterio de búsqueda no válido.");
                    return resultados;
            }
            // agregué para buscar coincidencias parciales (mejorar)
            if (valorLibro.contains(valor.toLowerCase())) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    // Método para cambiar la disponibilidad de libro
    public void cambiarDisponibilidad(Libro libro, boolean disponibilidad) {
        libro.setDisponible(disponibilidad);
    }

    // Método para eliminar un libro del inventario
    public void eliminarLibro(Libro libro) {
        List<Libro> inventario = biblioteca.getInventario();

        // Se recorre la lista de libros del inventario
        for (int i = 0; i < inventario.size(); i++) {
            Libro libroActual = inventario.get(i);

            if (libroActual.equals(libro)) {
                inventario.remove(i);
                System.out.println("Libro eliminado correctamente.");
                return;
            }
        }
        // Si no se encontró el libro en el inventario
        System.out.println("El libro no está en el inventario.");
    }

    // Método para obtener la lista de libros prestados
    public List<Libro> obtenerLibrosPrestados() {
        List<Libro> librosPrestados = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            librosPrestados.addAll(usuario.getLibrosPrestados());
        }
        return librosPrestados;
    }

    // Método para obtener la lista de usuarios
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
