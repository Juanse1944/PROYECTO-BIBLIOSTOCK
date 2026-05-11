package main;

import java.util.Scanner;

public class Bibliostock {

    static Scanner scanner = new Scanner(System.in);

    static String[] titulos = new String[100];
    static String[] autores = new String[100];
    static String[] isbn = new String[100];

    static int[] stock = new int[100];
    static int[] prestados = new int[100];

    static double[] precios = new double[100];

    static int cantidadLibros = 0;

    static String[] usuarios = new String[50];
    static int[] claves = new int[50];
    static String[] roles = new String[50];

    static int cantidadUsuarios = 1;

    static String usuarioActual = "";
    static String rolActual = "";

    public static void main(String[] args) {

        usuarios[0] = "admin";
        claves[0] = 12345;
        roles[0] = "admin";

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
            String usuario = scanner.nextLine();

            System.out.print("Clave numerica: ");
            int clave = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < cantidadUsuarios; i++) {

                if (usuarios[i].equals(usuario)
                        && claves[i] == clave) {

                    usuarioActual = usuarios[i];
                    rolActual = roles[i];

                    acceso = true;

                    System.out.println();
                    System.out.println("Bienvenido " + usuarioActual);

                    if (rolActual.equals("admin")) {

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

        System.out.print("Titulo: ");
        titulos[cantidadLibros] = scanner.nextLine();

        System.out.print("Autor: ");
        autores[cantidadLibros] = scanner.nextLine();

        System.out.print("ISBN: ");
        isbn[cantidadLibros] = scanner.nextLine();

        for (int i = 0; i < cantidadLibros; i++) {

            if (isbn[i].equals(isbn[cantidadLibros])) {

                System.out.println();
                System.out.println("El ISBN ya existe");
                return;
            }
        }

        System.out.print("Stock: ");
        stock[cantidadLibros] = scanner.nextInt();

        System.out.print("Precio: ");
        precios[cantidadLibros] = scanner.nextDouble();

        scanner.nextLine();

        prestados[cantidadLibros] = 0;

        cantidadLibros++;

        System.out.println();
        System.out.println("Libro agregado");
    }

    static void editarLibro() {

        System.out.println();
        System.out.print("Ingrese ISBN: ");
        String buscar = scanner.nextLine();

        for (int i = 0; i < cantidadLibros; i++) {

            if (isbn[i].equals(buscar)) {

                System.out.print("Nuevo titulo: ");
                titulos[i] = scanner.nextLine();

                System.out.print("Nuevo autor: ");
                autores[i] = scanner.nextLine();

                System.out.print("Nuevo stock: ");
                stock[i] = scanner.nextInt();

                System.out.print("Nuevo precio: ");
                precios[i] = scanner.nextDouble();

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
        String buscar = scanner.nextLine();

        for (int i = 0; i < cantidadLibros; i++) {

            if (isbn[i].equals(buscar)) {

                for (int j = i; j < cantidadLibros - 1; j++) {

                    titulos[j] = titulos[j + 1];
                    autores[j] = autores[j + 1];
                    isbn[j] = isbn[j + 1];

                    stock[j] = stock[j + 1];
                    prestados[j] = prestados[j + 1];

                    precios[j] = precios[j + 1];
                }

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

            System.out.println("Titulo    : " + titulos[i]);
            System.out.println("Autor     : " + autores[i]);
            System.out.println("ISBN      : " + isbn[i]);
            System.out.println("Stock     : " + stock[i]);
            System.out.println("Prestados : " + prestados[i]);
            System.out.println("Precio    : $" + precios[i]);
        }
    }

    static void mostrarResumen() {

        int totalStock = 0;
        double valorTotal = 0;

        int mayor = 0;
        int menor = 0;

        for (int i = 0; i < cantidadLibros; i++) {

            totalStock += stock[i];

            valorTotal += stock[i] * precios[i];

            if (stock[i] > stock[mayor]) {
                mayor = i;
            }

            if (stock[i] < stock[menor]) {
                menor = i;
            }
        }

        System.out.println();
        System.out.println("========== RESUMEN ==========");

        System.out.println("Total libros     : " + cantidadLibros);
        System.out.println("Total unidades   : " + totalStock);
        System.out.println("Valor inventario : $" + valorTotal);

        if (cantidadLibros > 0) {

            System.out.println();
            System.out.println("Libro con mayor stock: " + titulos[mayor]);

            System.out.println("Libro con menor stock: " + titulos[menor]);
        }
    }

    static void crearUsuario() {

        System.out.println();
        System.out.println("===== CREAR USUARIO =====");

        System.out.print("Nombre usuario: ");
        usuarios[cantidadUsuarios] = scanner.nextLine();

        if (!usuarios[cantidadUsuarios].matches("[a-zA-Z]+")) {

            System.out.println();
            System.out.println("El usuario solo debe tener letras");
            return;
        }

        System.out.print("Clave numerica: ");
        claves[cantidadUsuarios] = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Rol (admin/estudiante): ");
        roles[cantidadUsuarios] = scanner.nextLine();

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

            if (usuarios[i].equals(nombre)) {

                System.out.println();
                System.out.println("Usuario encontrado");

                System.out.println("Rol actual: " + roles[i]);

                System.out.print("Nuevo rol (admin/estudiante): ");
                String nuevoRol = scanner.nextLine();

                if (nuevoRol.equals("admin")
                        || nuevoRol.equals("estudiante")) {

                    roles[i] = nuevoRol;

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
        String buscar = scanner.nextLine();

        for (int i = 0; i < cantidadLibros; i++) {

            if (isbn[i].equals(buscar)) {

                if (stock[i] > 0) {

                    stock[i]--;

                    prestados[i]++;

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
