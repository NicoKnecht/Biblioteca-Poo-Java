package com.miapp.biblioteca.interfaz;

import com.miapp.biblioteca.Biblioteca;
import com.miapp.biblioteca.Libro;
import com.miapp.biblioteca.Usuario;
import com.miapp.biblioteca.servicios.BibliotecaServicios;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BibliotecaInterfazUsuario {

    public static void main(String[] args) {
        // Crear una biblioteca
        Biblioteca biblioteca = new Biblioteca("Biblioteca Municipal", "123456789");
        BibliotecaServicios servicios = new BibliotecaServicios(biblioteca);

        // Agregar libros
        servicios.agregarLibro(new Libro("El Hobbit", "J.R.R. Tolkien", "1234567891", "Fantasía"));
        servicios.agregarLibro(new Libro("1984", "George Orwell", "1234567892", "Ciencia ficción"));
        servicios.agregarLibro(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "1234567893", "Novela"));
        servicios.agregarLibro(new Libro("Orgullo y prejuicio", "Jane Austen", "1234567894", "Romance"));
        servicios.agregarLibro(new Libro("Matar un ruiseñor", "Harper Lee", "1234567895", "Ficción"));
        servicios.agregarLibro(new Libro("El señor de los anillos", "J.R.R. Tolkien", "1234567896", "Fantasía"));
        servicios.agregarLibro(new Libro("Crimen y castigo", "Fyodor Dostoevsky", "1234567897", "Novela"));
        servicios.agregarLibro(new Libro("Las aventuras de Sherlock Holmes", "Arthur Conan Doyle", "1234567898", "Misterio"));
        servicios.agregarLibro(new Libro("Rayuela", "Julio Cortázar", "1234567899", "Ficción"));
        servicios.agregarLibro(new Libro("Cien años de soledad", "Gabriel García Márquez", "1234567800", "Realismo mágico"));

        // Crear usuarios
        Usuario usuario1 = new Usuario("Guillermo", 1, "12345678A");
        Usuario usuario2 = new Usuario("Nicolas", 2, "23456789B");
        Usuario usuario3 = new Usuario("Ana", 3, "34567890C");
        Usuario usuario4 = new Usuario("Laura", 4, "45678901D");

        // Agregar usuarios
        servicios.getUsuarios().add(usuario1);
        servicios.getUsuarios().add(usuario2);
        servicios.getUsuarios().add(usuario3);
        servicios.getUsuarios().add(usuario4);

        Scanner scanner = new Scanner(System.in);

        // Menú de opciones
        int opcion;
        do {
            System.out.println("\nBienvenido a la Biblioteca " + biblioteca.getNombre());
            System.out.println("1. Crear libro");
            System.out.println("2. Modificar libro");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Buscar libro");
            System.out.println("6. Eliminar libro");
            System.out.println("7. Ver listado completo de libros");
            System.out.println("8. Ver listado de libros prestados");
            System.out.println("9. Crear usuario");
            System.out.println("10. Ver listado de usuarios");
            System.out.println("11. Salir");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar
                opcion = -1; // Establecer una opción inválida para que el bucle continúe
                continue; // Volver a la parte superior del bucle
            }

            switch (opcion) {
                case 1:
                    // Crear libro
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese el autor del libro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Ingrese el ISBN del libro: ");
                    String ISBN = scanner.nextLine();
                    System.out.print("Ingrese el género del libro: ");
                    String genero = scanner.nextLine();
                    servicios.agregarLibro(new Libro(titulo, autor, ISBN, genero));
                    break;
                case 2:
                    // Modificar libro
                    System.out.print("Ingrese el título del libro que desea modificar: ");
                    String tituloModificar = scanner.nextLine();
                    List<Libro> resultadosModificar = servicios.buscarLibros("titulo", tituloModificar);
                    if (!resultadosModificar.isEmpty()) {
                        Libro libroModificar = resultadosModificar.get(0); // Suponiendo que el primer resultado es el deseado
                        System.out.println("Seleccione el atributo que desea modificar:");
                        System.out.println("1. Título");
                        System.out.println("2. Autor");
                        System.out.println("3. ISBN");
                        System.out.println("4. Género");
                        int opcionModificar = scanner.nextInt();
                        scanner.nextLine(); // Limpia
                        switch (opcionModificar) {
                            case 1:
                                System.out.print("Ingrese el nuevo título: ");
                                String nuevoTitulo = scanner.nextLine();
                                libroModificar.setTitulo(nuevoTitulo);
                                break;
                            case 2:
                                System.out.print("Ingrese el nuevo autor: ");
                                String nuevoAutor = scanner.nextLine();
                                libroModificar.setAutor(nuevoAutor);
                                break;
                            case 3:
                                System.out.print("Ingrese el nuevo ISBN: ");
                                String nuevoISBN = scanner.nextLine();
                                libroModificar.setISBN(nuevoISBN);
                                break;
                            case 4:
                                System.out.print("Ingrese el nuevo género: ");
                                String nuevoGenero = scanner.nextLine();
                                libroModificar.setGenero(nuevoGenero);
                                break;
                            default:
                                System.out.println("Opción no válida.");
                                break;
                        }
                    } else {
                        System.out.println("No se encontró el libro especificado.");
                    }
                    break;
                case 3:
                    // Prestar libro
                    System.out.print("Ingrese el título del libro que desea prestar: ");
                    String tituloPrestamo = scanner.nextLine();
                    List<Libro> resultadosPrestamo = servicios.buscarLibros("titulo", tituloPrestamo);
                    if (!resultadosPrestamo.isEmpty()) {
                        Libro libroPrestamo = resultadosPrestamo.get(0); // Suponiendo que el primer resultado es el deseado
                        System.out.print("Ingrese el número de identificación del usuario: ");
                        int numeroIdentificacionUsuarioPrestamo = scanner.nextInt();
                        scanner.nextLine(); // Limpriar
                        Usuario usuarioPrestamo = null;
                        for (Usuario usuario : servicios.getUsuarios()) {
                            if (usuario.getNumeroIdentificacion() == numeroIdentificacionUsuarioPrestamo) {
                                usuarioPrestamo = usuario;
                                break;
                            }
                        }
                        if (usuarioPrestamo != null) {
                            servicios.prestarLibro(libroPrestamo, usuarioPrestamo);
                        } else {
                            System.out.println("Usuario no encontrado.");
                        }
                    } else {
                        System.out.println("No se encontró el libro especificado.");
                    }
                    break;

                case 4:
                    // Devolver libro
                    System.out.print("Ingrese el título del libro que desea devolver: ");
                    String tituloDevolucion = scanner.nextLine();
                    List<Libro> resultadosDevolucion = servicios.buscarLibros("titulo", tituloDevolucion);
                    if (!resultadosDevolucion.isEmpty()) {
                        Libro libroDevolucion = resultadosDevolucion.get(0); // Suponiendo que el primer resultado es el deseado
                        System.out.print("Ingrese el número de identificación del usuario: ");
                        int numeroIdentificacionUsuarioDevolucion = scanner.nextInt();
                        scanner.nextLine();
                        Usuario usuarioDevolucion = null;
                        for (Usuario usuario : servicios.getUsuarios()) {
                            if (usuario.getNumeroIdentificacion() == numeroIdentificacionUsuarioDevolucion) {
                                usuarioDevolucion = usuario;
                                break;
                            }
                        }
                        if (usuarioDevolucion != null) {
                            servicios.devolverLibro(libroDevolucion, usuarioDevolucion);
                        } else {
                            System.out.println("Usuario no encontrado.");
                        }
                    } else {
                        System.out.println("No se encontró el libro especificado.");
                    }
                    break;
                case 5:
                    // Buscar libro
                    System.out.print("Ingrese el criterio de búsqueda (titulo, autor o género): ");
                    String criterioBusqueda = scanner.nextLine();
                    System.out.print("Ingrese el valor de búsqueda: ");
                    String valorBusqueda = scanner.nextLine();
                    List<Libro> resultadosBusqueda = servicios.buscarLibros(criterioBusqueda, valorBusqueda);
                    if (!resultadosBusqueda.isEmpty()) {
                        System.out.println("Resultados de la búsqueda:");
                        for (Libro libro : resultadosBusqueda) {
                            System.out.println(libro.getTitulo() + " - " + libro.getAutor() + " - " + libro.getGenero());
                        }
                    } else {
                        System.out.println("No se encontraron resultados para la búsqueda especificada.");
                    }
                    break;
                case 6:
                    // Eliminar libro
                    System.out.print("Ingrese el título del libro que desea eliminar: ");
                    String tituloEliminar = scanner.nextLine();
                    List<Libro> resultadosEliminar = servicios.buscarLibros("titulo", tituloEliminar);
                    if (!resultadosEliminar.isEmpty()) {
                        Libro libroEliminar = resultadosEliminar.get(0); // Suponiendo que el primer resultado es el deseado
                        servicios.eliminarLibro(libroEliminar);
                    } else {
                        System.out.println("No se encontró el libro especificado.");
                    }
                    break;
                case 7:
                    // Ver listado completo de libros
                    System.out.println("Listado completo de libros:");
                    for (Libro libro : biblioteca.getInventario()) {
                        System.out.println(libro.getTitulo() + " - " + libro.getAutor() + " - " + libro.getGenero());
                    }
                    break;
                case 8:
                    // Ver listado de libros prestados
                    List<Libro> librosPrestados = servicios.obtenerLibrosPrestados();
                    if (!librosPrestados.isEmpty()) {
                        System.out.println("Listado de libros prestados:");
                        for (Libro libro : librosPrestados) {
                            System.out.println(libro.getTitulo() + " - " + libro.getAutor() + " - " + libro.getGenero());
                        }
                    } else {
                        System.out.println("No hay libros prestados en este momento.");
                    }
                    break;
                case 9:
                    // Crear usuario
                    System.out.print("Ingrese el nombre del usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    System.out.print("Ingrese el número de identificación del usuario: ");
                    int numeroIdentificacionUsuarioCrear = scanner.nextInt();
                    scanner.nextLine(); // Limpiar
                    System.out.print("Ingrese el DNI del usuario: ");
                    String dniUsuario = scanner.nextLine();
                    servicios.getUsuarios().add(new Usuario(nombreUsuario, numeroIdentificacionUsuarioCrear, dniUsuario));
                    break;

                    case 10:
                    // Ver listado completo de Usuarios
                    System.out.println("Listado completo de Usuarios:");
                    for (Usuario usuario : servicios.getUsuarios()) {
                        System.out.println(usuario.getNombre() + " - " + "id " + usuario.getNumeroIdentificacion());
                        System.out.println("Libros Prestados:");
                        for (Libro libro : usuario.getLibrosPrestados()) {
                            System.out.println(libro); //  metodo toString de Libro
                        }
                    }
                    break;
                case 11:
                    // Salir
                    System.out.println("Gracias por usar la Biblioteca. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");
                    break;
            }
        } while (opcion != 11);
        scanner.close();
    }
}


