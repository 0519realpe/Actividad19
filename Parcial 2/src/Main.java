import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    // CREACION DE LISTAS
    private static ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
    private static HashSet<String> catalogoMaterias = new HashSet<>();
    private static HashMap<String, ArrayList<Double>> registroCalificaciones = new HashMap<>();

    public static void agregarEstudiante(Scanner sc) {
        System.out.println("Ingrese nombre: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese ID: ");
        String id = sc.nextLine();

        Estudiante estudiante = new Estudiante(nombre, id);
        listaEstudiantes.add(estudiante);

        System.out.println("Estudiante agregado con exito");
    }

    public static void mostrarEstudiante() {
        if (listaEstudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados");
            return;
        }

        System.out.println("\n LISTA DE ESTUDIANTES");
        for (Estudiante e : listaEstudiantes) {
            System.out.println(e);
        }
    }

    public static void buscarEstudiante(Scanner sc) {
        System.out.println("Buscar por nombre o ID: ");
        String busqueda = sc.nextLine();

        for (Estudiante e : listaEstudiantes) {
            if (e.getNombre().equalsIgnoreCase(busqueda) ||
                    e.getId().equalsIgnoreCase(busqueda)) {
                System.out.println("Encontrado " + e);
                return;
            }
        }
        System.out.println("Estudiante no encontrado");
    }

    public static void eliminarEstudiante(Scanner sc) {
        System.out.println("Ingresar el ID del estudiante: ");
        String id = sc.nextLine();

        for (Estudiante e : listaEstudiantes) {
            if (e.getId().equalsIgnoreCase(id)) {
                listaEstudiantes.remove(e);
                System.out.println("Estudiante eliminado con exito");
                return;
            }
        }
        System.out.println("Estudiante no encontrado");
    }

    public static void registrarEstudiante(Scanner sc) {
        System.out.println("Ingrese nombre de la materia: ");
        String materia = sc.nextLine();
        catalogoMaterias.add(materia);
        System.out.println("Materia registrada con exito");
    }

    public static void verificarMaterias(Scanner sc) {
        System.out.println("Ingrese el nombre de la materia a verificar: ");
        String materia = sc.nextLine();

        if (catalogoMaterias.contains(materia)) {
            System.out.println("La materia está en el catalogo");
        } else {
            System.out.println("La materia no está registrada");
        }
    }

    public static void contarMaterias(Scanner sc) {
        System.out.println("Cantidad de materias registradas: " + catalogoMaterias.size());
    }

    public static void agregarCalificacion(Scanner sc) {
        System.out.println("Buscar al estudiante por nombre o ID para agregar calificaciones: ");
        String busqueda = sc.nextLine().trim();

        for (Estudiante e : listaEstudiantes) {
            if (e.getNombre().equalsIgnoreCase(busqueda) || e.getId().equalsIgnoreCase(busqueda)) {
                System.out.println("Ingrese calificación para " + e.getNombre() + " (" + e.getId() + "): ");
                double calificacion = sc.nextDouble();
                sc.nextLine(); // Para consumir el salto de línea

                // Si no existe, crea una nueva lista de calificaciones
                registroCalificaciones.putIfAbsent(e.getId(), new ArrayList<>());
                registroCalificaciones.get(e.getId()).add(calificacion);
                System.out.println("Calificación agregada con éxito.");
                return;
            }
        }
        System.out.println("Estudiante no encontrado.");
    }

    public static void calcularPromedio(Scanner sc) {
        System.out.println("Buscar al estudiante por nombre o ID para calcular el promedio: ");
        String busqueda = sc.nextLine().trim();
        Estudiante estudiante = null;

        for (Estudiante e : listaEstudiantes) {
            if (e.getNombre().equalsIgnoreCase(busqueda) || e.getId().equalsIgnoreCase(busqueda)) {
                estudiante = e;
                break;
            }
        }

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        // Obtener las calificaciones del estudiante
        ArrayList<Double> calificaciones = registroCalificaciones.get(estudiante.getId());
        if (calificaciones == null || calificaciones.isEmpty()) {
            System.out.println("Este estudiante no tiene calificaciones registradas.");
            return;
        }

        // Calcular el promedio de las calificaciones
        double suma = 0;
        for (Double calificacion : calificaciones) {
            suma += calificacion;
        }
        double promedio = suma / calificaciones.size();
        System.out.println("El promedio de " + estudiante.getNombre() + " es: " + promedio);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=============================");
            System.out.println("1. Agregar estudiante ");
            System.out.println("2. Mostrar estudiantes ");
            System.out.println("3. Buscar estudiante ");
            System.out.println("4. Eliminar estudiante ");
            System.out.println("5. Registrar Materia ");
            System.out.println("6. Verificar Materia ");
            System.out.println("7. Contar Materias ");
            System.out.println("8. Agregar Calificación ");
            System.out.println("9. Calcular Promedio ");
            System.out.println("0. Salir ");
            System.out.println("SELECIONE UNA OPCION");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el salto de línea restante

            switch (opcion) {
                case 1 -> agregarEstudiante(sc);
                case 2 -> mostrarEstudiante();
                case 3 -> buscarEstudiante(sc);
                case 4 -> eliminarEstudiante(sc);
                case 5 -> registrarEstudiante(sc);
                case 6 -> verificarMaterias(sc);
                case 7 -> contarMaterias(sc);
                case 8 -> agregarCalificacion(sc);
                case 9 -> calcularPromedio(sc);
                case 0 -> {
                    System.out.println("Gracias por usar el programa");
                    sc.close();  // Cerramos el scanner al salir
                    return;
                }
                default -> System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (opcion != 0);
    }
}