package main;

import java.util.Scanner;

class Libro {

    String titulo;
    String autor;
    String isbn;

    int stock;
    int prestados;

    double precio;

    double calcularValor() {
        return stock * precio;
    }
}

class Usuario {

    String nombre;
    int clave;
    String rol;
}

public class Bibliostock {

    static Scanner scanner = new Scanner(System.in);

    static Libro[] libros = new Libro[100];
    static Usuario[] usuarios = new Usuario[50];

    static int cantidadLibros = 0;
    static int cantidadUsuarios = 1;

    static Usuario usuarioActual;

    public static void main(String[] args) {

        Usuario admin = new Usuario();

        admin.nombre = "admin";
        admin.clave = 12345;
        admin.rol = "admin";

        usuarios[0] = admin;

        iniciarSesion();
    }

    static void iniciarSesion() {

        boolean acceso = false;

        while (!acceso) {

            System.out.println();
            System.out.println("==============================");
            System.out.println("        INICIO SESION");
            System.out.println("==============================");

            System.out.print("Usuario: ");
            String nombre = scanner.nextLine();

            System.out.print("Clave numerica: ");
            int clave = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < cantidadUsuarios; i++) {

                if (usuarios[i].nombre.equals(nombre)
                        && usuarios[i].clave == clave) {

                    usuarioActual = usuarios[i];

                    acceso = true;

                    System.out.println();
                    System.out.println("Bienvenido " + usuarioActual.nombre);

                    if (usuarioActual.rol.equals("admin")) {

                        menuAdmin();

                    } else {

                        menuEstudiante();
                    }
                }
            }

            if (!acceso) {

                System.out.println();
                System.out.println("Usuario o clave incorrecta");
            }
        }
    }

    static void menuAdmin() {

        int opcion;

        do {

            System.out.println();
            System.out.println("==============================");
            System.out.println("         MENU ADMIN");
            System.out.println("==============================");
            System.out.println("1. Agregar libro");
            System.out.println("2. Editar libro");
            System.out.println("3. Eliminar libro");
            System.out.println("4. Mostrar inventario");
            System.out.println("5. Mostrar resumen");
            System.out.println("6. Crear usuario");
            System.out.println("7. Prestar libro");
            System.out.println("8. Administrar permisos");
            System.out.println("0. Cerrar sesion");

            System.out.print("Opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    agregarLibro();
                    break;

                case 2:
                    editarLibro();
                    break;

                case 3:
                    eliminarLibro();
                    break;

                case 4:
                    mostrarInventario();
                    break;

                case 5:
                    mostrarResumen();
                    break;

                case 6:
                    crearUsuario();
                    break;

                case 7:
                    prestarLibro();
                    break;

                case 8:
                    administrarPermisos();
                    break;

                case 0:
                    System.out.println();
                    System.out.println("Sesion cerrada");
                    iniciarSesion();
                    break;

                default:
                    System.out.println();
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 0);
    }

    static void menuEstudiante() {

        int opcion;

        do {

            System.out.println();
            System.out.println("==============================");
            System.out.println("      MENU ESTUDIANTE");
            System.out.println("==============================");
            System.out.println("1. Mostrar inventario");
            System.out.println("2. Prestar libro");
            System.out.println("0. Cerrar sesion");

            System.out.print("Opcion: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    mostrarInventario();
                    break;

                case 2:
                    prestarLibro();
                    break;

                case 0:
                    System.out.println();
                    System.out.println("Sesion cerrada");
                    iniciarSesion();
                    break;

                default:
                    System.out.println();
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 0);
    }

    static void agregarLibro() {

        System.out.println();
        System.out.println("===== AGREGAR LIBRO =====");

        Libro nuevo = new Libro();

        System.out.print("Titulo: ");
        nuevo.titulo = scanner.nextLine();

        System.out.print("Autor: ");
        nuevo.autor = scanner.nextLine();

        System.out.print("ISBN: ");
        nuevo.isbn = scanner.nextLine();

        for (int i = 0; i < cantidadLibros; i++) {

            if (libros[i].isbn.equals(nuevo.isbn)) {

                System.out.println();
                System.out.println("El ISBN ya existe");
                return;
            }
        }

        System.out.print("Stock: ");
        nuevo.stock = scanner.nextInt();

        System.out.print("Precio: ");
        nuevo.precio = scanner.nextDouble();

        scanner.nextLine();

        nuevo.prestados = 0;

        libros[cantidadLibros] = nuevo;

        cantidadLibros++;

        System.out.println();
        System.out.println("Libro agregado");
    }

    static void editarLibro() {

        System.out.println();
        System.out.print("Ingrese ISBN: ");
        String isbn = scanner.nextLine();

        for (int i = 0; i < cantidadLibros; i++) {

            if (libros[i].isbn.equals(isbn)) {

                System.out.print("Nuevo titulo: ");
                libros[i].titulo = scanner.nextLine();

                System.out.print("Nuevo autor: ");
                libros[i].autor = scanner.nextLine();

                System.out.print("Nuevo stock: ");
                libros[i].stock = scanner.nextInt();

                System.out.print("Nuevo precio: ");
                libros[i].precio = scanner.nextDouble();

                scanner.nextLine();

                System.out.println();
                System.out.println("Libro actualizado");

                return;
            }
        }

        System.out.println();
        System.out.println("Libro no encontrado");
    }

    static void eliminarLibro() {

        System.out.println();
        System.out.print("Ingrese ISBN: ");
        String isbn = scanner.nextLine();

        for (int i = 0; i < cantidadLibros; i++) {

            if (libros[i].isbn.equals(isbn)) {

                for (int j = i; j < cantidadLibros - 1; j++) {

                    libros[j] = libros[j + 1];
                }

                libros[cantidadLibros - 1] = null;

                cantidadLibros--;

                System.out.println();
                System.out.println("Libro eliminado");

                return;
            }
        }

        System.out.println();
        System.out.println("Libro no encontrado");
    }

    static void mostrarInventario() {

        if (cantidadLibros == 0) {

            System.out.println();
            System.out.println("Inventario vacio");
            return;
        }

        System.out.println();
        System.out.println("========== INVENTARIO ==========");

        for (int i = 0; i < cantidadLibros; i++) {

            System.out.println();
            System.out.println("Libro #" + (i + 1));

            System.out.println("Titulo    : " + libros[i].titulo);
            System.out.println("Autor     : " + libros[i].autor);
            System.out.println("ISBN      : " + libros[i].isbn);
            System.out.println("Stock     : " + libros[i].stock);
            System.out.println("Prestados : " + libros[i].prestados);
            System.out.println("Precio    : $" + libros[i].precio);
        }
    }

    static void mostrarResumen() {

        int totalStock = 0;

        double valorTotal = 0;

        for (int i = 0; i < cantidadLibros; i++) {

            totalStock += libros[i].stock;

            valorTotal += libros[i].calcularValor();
        }

        System.out.println();
        System.out.println("========== RESUMEN ==========");

        System.out.println("Total libros     : " + cantidadLibros);
        System.out.println("Total unidades   : " + totalStock);
        System.out.println("Valor inventario : $" + valorTotal);
    }

    static void crearUsuario() {

        System.out.println();
        System.out.println("===== CREAR USUARIO =====");

        Usuario nuevo = new Usuario();

        System.out.print("Nombre usuario: ");
        nuevo.nombre = scanner.nextLine();

        if (!nuevo.nombre.matches("[a-zA-Z]+")) {

            System.out.println();
            System.out.println("El usuario solo debe tener letras");
            return;
        }

        System.out.print("Clave numerica: ");
        nuevo.clave = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Rol (admin/estudiante): ");
        nuevo.rol = scanner.nextLine();

        usuarios[cantidadUsuarios] = nuevo;

        cantidadUsuarios++;

        System.out.println();
        System.out.println("Usuario creado");
    }

    static void administrarPermisos() {

        System.out.println();
        System.out.println("===== ADMINISTRAR PERMISOS =====");

        System.out.print("Ingrese nombre del usuario: ");
        String nombre = scanner.nextLine();

        for (int i = 0; i < cantidadUsuarios; i++) {

            if (usuarios[i].nombre.equals(nombre)) {

                System.out.println();
                System.out.println("Usuario encontrado");
                System.out.println("Rol actual: " + usuarios[i].rol);

                System.out.print("Nuevo rol (admin/estudiante): ");
                String nuevoRol = scanner.nextLine();

                if (nuevoRol.equals("admin")
                        || nuevoRol.equals("estudiante")) {

                    usuarios[i].rol = nuevoRol;

                    System.out.println();
                    System.out.println("Permisos actualizados");

                } else {

                    System.out.println();
                    System.out.println("Rol invalido");
                }

                return;
            }
        }

        System.out.println();
        System.out.println("Usuario no encontrado");
    }

    static void prestarLibro() {

        System.out.println();
        System.out.print("Ingrese ISBN del libro: ");
        String isbn = scanner.nextLine();

        for (int i = 0; i < cantidadLibros; i++) {

            if (libros[i].isbn.equals(isbn)) {

                if (libros[i].stock > 0) {

                    libros[i].stock--;

                    libros[i].prestados++;

                    System.out.println();
                    System.out.println("Libro prestado");

                } else {

                    System.out.println();
                    System.out.println("No hay stock disponible");
                }

                return;
            }
        }

        System.out.println();
        System.out.println("Libro no encontrado");
    }
}