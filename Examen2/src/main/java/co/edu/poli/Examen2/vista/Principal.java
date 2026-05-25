package co.edu.poli.Examen2.vista;

import co.edu.poli.Examen2.modelo.*;
import co.edu.poli.Examen2.servicios.ImplementacionOperacionCRUD;

import java.io.IOException;
import java.util.Scanner;

/**
 * Clase principal del sistema de monitoreo astronautas.
 * Presenta un menú interactivo por consola para gestionar protocolos
 * mediante operaciones CRUD y persistencia en archivo plano.
 * <p>
 * Cada opción del menú captura {@link Exception} e {@link IOException}
 * que son lanzadas (throws) desde {@link ImplementacionOperacionCRUD},
 * mostrando el mensaje de error al usuario sin cortar el programa.
 * </p>
 *
 * @author Equipo Contexto 4
 * @version 2.0
 */
public class Principal {


   
    // ---------------------------------------------------------------
    // Utilitarios de impresión
    // ---------------------------------------------------------------

    /** Imprime una línea separadora simple. */
    private static void separador() {
        System.out.println("-------------------------------------------------------------");
    }

    /** Imprime una línea separadora gruesa. */
    private static void separadorGrueso() {
        System.out.println("=============================================================");
    }

    /**
     * Imprime el estado completo del arreglo del CRUD.
     *
     * @param crud Instancia de ImplementacionOperacionCRUD a imprimir.
     */
    private static void imprimirArreglo(ImplementacionOperacionCRUD crud) {
        ExamenMedico[] arr = crud.getArreglo_examenes();
        System.out.println("  Estado del arreglo [tamano=" + arr.length + "]:");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                System.out.println("    [" + i + "] "
                        + arr[i].getClass().getSimpleName()
                        + " | codigo_id='" + arr[i].getCodigo_identificacion() + "'"
                        + " | nombre_paciente='" + arr[i].getNombre_paciente() + "'"
                        + " | fecha_realizacion='" + arr[i].getFecha_realizacion() + "'");
            } else {
                System.out.println("    [" + i + "] -- vacio (null) --");
            }
        }
    }

    // ---------------------------------------------------------------
    // Submenús
    // ---------------------------------------------------------------

    /** Muestra el menú principal. */
    private static void mostrarMenu() {
        separadorGrueso();
        System.out.println("   SISTEMA DE EXAMENES - MENU PRINCIPAL");
        separadorGrueso();
        System.out.println("  1. CREAR   examen");
        System.out.println("  2. LEER    examen por indice");
        System.out.println("  3. LEER    todos los examenes");
        System.out.println("  4. BUSCAR  examenes por codigo_identificacion");
        System.out.println("  5. MODIFICAR examen por codigo_identificacion");
        System.out.println("  6. ELIMINAR  examen por codigo_identificacion");
        separador();
        System.out.println("  7. SERIALIZAR   (guardar en archivo .txt)");
        System.out.println("  8. DESERIALIZAR (cargar desde archivo .txt)");
        separador();
        System.out.println("  9. VER estado actual del arreglo");
        System.out.println("  0. SALIR");
        separadorGrueso();
        System.out.print("  Seleccione una opcion: ");
    }

    /**
     * Solicita datos al usuario y crea un protocolo en el CRUD.
     * Captura {@link NumberFormatException} si el numero_id no es entero,
     * y {@link Exception} si hay error de validación en el servicio.
     *
     * @param crud    Instancia del CRUD.
     * @param scanner Scanner para leer entrada.
     */
    private static void menuCrear(ImplementacionOperacionCRUD crud, Scanner scanner) {
        separadorGrueso();
        System.out.println("  CREAR EXAMEN");
        separador();

        System.out.println("  Tipo de examen:");
        System.out.println("    1. Examen de Sangre");
        System.out.print("  Seleccione tipo: ");
        String opTipo = scanner.nextLine().trim();

        if (!opTipo.equals("1") && !opTipo.equals("2")) {
            System.out.println("  ERROR: Tipo invalido. Debe ser 1 o 2.");
            return;
        }

        System.out.print("  numero_id (entero, ej: 101): ");
        String entradaId = scanner.nextLine().trim();

        // try-catch para NumberFormatException si el usuario ingresa un String no numérico
        int numero_id;
        try {
            numero_id = Integer.parseInt(entradaId);
        } catch (NumberFormatException e) {
            System.out.println("  ERROR: numero_id invalido. '" + entradaId
                    + "' no es un numero entero. Operacion cancelada.");
            System.out.println("  Detalle: " + e.getMessage());
            return;
        }

        System.out.print("  Registro: ");
        String registro = scanner.nextLine().trim();
        System.out.print("  Instrucciones: ");
        String instrucciones = scanner.nextLine().trim();
        System.out.print("  Limites (ej: 1.0 Sv): ");
        String limites = scanner.nextLine().trim();

    }

    /**
     * Lee un protocolo por su índice. Captura {@link Exception}
     * lanzada por {@code leer()} con throws si el índice es inválido.
     *
     * @param crud    Instancia del CRUD.
     * @param scanner Scanner para leer entrada.
     */
    private static void menuLeerPorIndice(ImplementacionOperacionCRUD crud, Scanner scanner) {
        separadorGrueso();
        System.out.println("  LEER EXAMEN POR INDICE");
        separador();

        System.out.print("  Ingrese el indice: ");
        String entrada = scanner.nextLine().trim();
        int indice;
        try {
            indice = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("  ERROR: Debe ingresar un numero entero valido.");
            return;
        }

        // try-catch para Exception lanzada por leer() con throws
        try {
            ExamenMedico p = crud.leer(indice);
            separador();
            System.out.println("  Tipo        : " + p.getClass().getSimpleName());
        } catch (Exception e) {
            System.out.println("  ERROR [LEER]: " + e.getMessage());
        }
    }

    /**
     * Muestra todos los protocolos. Captura {@link Exception}
     * lanzada por {@code leerTodos()} con throws si el arreglo está vacío.
     *
     * @param crud Instancia del CRUD.
     */
    private static void menuLeerTodos(ImplementacionOperacionCRUD crud) {
        separadorGrueso();
        System.out.println("  TODOS LOS EXAMENES");
        separador();

        // try-catch para Exception lanzada por leerTodos() con throws
        try {
            ExamenMedico[] todos = crud.leerTodos();
            for (int i = 0; i < todos.length; i++) {
                if (todos[i] != null) {
                    System.out.println("  [" + i + "] ");
                } else {
                    System.out.println("  [" + i + "] -- vacio (null) --");
                }
            }
        } catch (Exception e) {
            System.out.println("  ERROR [LEER TODOS]: " + e.getMessage());
        }
    }

    /**
     * Busca un protocolo por su numero_id.
     *
     * @param crud    Instancia del CRUD.
     * @param scanner Scanner para leer entrada.
     */
    private static void menuBuscarPorCodigo(ImplementacionOperacionCRUD crud, Scanner scanner) {
        separadorGrueso();
        System.out.println("  BUSCAR EXAMEN POR numero_id");
        separador();

        System.out.print("  Ingrese el numero_id a buscar: ");
        String entrada = scanner.nextLine().trim();

        int idx = crud.buscarIndicePorCodigo(entrada);
        separador();
        if (idx >= 0) {
            // try-catch para Exception lanzada por leer() con throws
            try {
                ExamenMedico p = crud.leer(idx);
                System.out.println("  Encontrado en posicion [" + idx + "]");
                System.out.println("  Tipo        : " + p.getClass().getSimpleName());
       
            } catch (Exception e) {
                System.out.println("  ERROR [BUSCAR]: " + e.getMessage());
            }
        } else {
            System.out.println("  ERROR: No se encontro examen con numero_id '"
                    + entrada + "'.");
        }
    }

    /**
     * Modifica un protocolo buscado por su numero_id.
     * Captura {@link Exception} lanzada por {@code modificar()} con throws.
     *
     * @param crud    Instancia del CRUD.
     * @param scanner Scanner para leer entrada.
     */
    private static void menuModificar(ImplementacionOperacionCRUD crud, Scanner scanner) {
        separadorGrueso();
        System.out.println("  MODIFICAR EXAMEN POR numero_id");
        separador();

        System.out.print("  Ingrese el numero_id del examen a modificar: ");
        String idBuscar = scanner.nextLine().trim();

        int idx = crud.buscarIndicePorCodigo(idBuscar);
        if (idx < 0) {
            System.out.println("  ERROR: No se encontro examen con numero_id '"
                    + idBuscar + "'.");
            return;
        }

        try {
            System.out.println("  Examen encontrado en posicion [" + idx + "]: "
                    );
        } catch (Exception e) {
            System.out.println("  ERROR: " + e.getMessage());
            return;
        }

        separador();
        System.out.println("  Ingrese los nuevos datos:");

        System.out.println("  Tipo:");
        System.out.println("    1. Examen Sangre");
        System.out.print("  Seleccione tipo: ");
        String opTipo = scanner.nextLine().trim();
        if (!opTipo.equals("1") && !opTipo.equals("2")) {
            System.out.println("  ERROR: Tipo invalido.");
            return;
        }

        System.out.print("  Nuevo numero_id (entero): ");
        String entradaId = scanner.nextLine().trim();
        int nuevoId;
        try {
            nuevoId = Integer.parseInt(entradaId);
        } catch (NumberFormatException e) {
            System.out.println("  ERROR: numero_id invalido. '" + entradaId + "' no es entero.");
            return;
        }

        System.out.print("  Nuevo registro: ");
        String nuevoRegistro = scanner.nextLine().trim();
        System.out.print("  Nuevas instrucciones: ");
        String nuevasInstrucciones = scanner.nextLine().trim();
        System.out.print("  Nuevos limites (ej: 1.0 Sv): ");
        String nuevosLimites = scanner.nextLine().trim();

 
        }



    /**
     * Elimina un protocolo buscado por su numero_id.
     * Captura {@link Exception} lanzada por {@code eliminar()} con throws.
     *
     * @param crud    Instancia del CRUD.
     * @param scanner Scanner para leer entrada.
     */
    private static void menuEliminar(ImplementacionOperacionCRUD crud, Scanner scanner) {
        separadorGrueso();
        System.out.println("  ELIMINAR EXAMEN POR numero_id");
        separador();

        System.out.print("  Ingrese el numero_id del examen a eliminar: ");
        String idBuscar = scanner.nextLine().trim();

        int idx = crud.buscarIndicePorCodigo(idBuscar);
        if (idx < 0) {
            System.out.println("  ERROR: No se encontro examen con numero_id '"
                    + idBuscar + "'.");
            return;
        }

        try {
            System.out.println("  Examen encontrado: "
                   );
        } catch (Exception e) {
            System.out.println("  ERROR: " + e.getMessage());
            return;
        }

        System.out.print("  Confirmar eliminacion (s/n): ");
        String confirmacion = scanner.nextLine().trim();
        separador();

        if (confirmacion.equalsIgnoreCase("s")) {
            // try-catch para Exception lanzada por eliminar() con throws
            try {
                System.out.println("  " + crud.eliminar(idx));
            } catch (Exception e) {
                System.out.println("  ERROR [ELIMINAR]: " + e.getMessage());
            }
        } else {
            System.out.println("  Operacion cancelada por el usuario.");
        }
    }

    /**
     * Serializa el arreglo al archivo protocolos.txt.
     * Captura {@link Exception} e {@link IOException} lanzadas
     * por {@code serializar()} con throws.
     *
     * @param crud Instancia del CRUD.
     */
    private static void menuSerializar(ImplementacionOperacionCRUD crud) {
        separadorGrueso();
        System.out.println("  SERIALIZAR — Guardar examenes en archivo .txt");
        separador();

        // try-catch para Exception e IOException lanzadas por serializar() con throws
        try {
            System.out.println("  " + crud.serializar());
        } catch (IOException e) {
            System.out.println("  ERROR [SERIALIZAR - ARCHIVO]: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  ERROR [SERIALIZAR]: " + e.getMessage());
        }
    }

    /**
     * Deserializa los protocolos desde protocolos.txt.
     * Captura {@link IOException} lanzada por {@code deserializar()} con throws.
     *
     * @param crud Instancia del CRUD.
     */
    private static void menuDeserializar(ImplementacionOperacionCRUD crud) {
        separadorGrueso();
        System.out.println("  DESERIALIZAR — Cargar examenes desde archivo .txt");
        separador();

        // try-catch para IOException lanzada por deserializar() con throws
        try {
            ExamenMedico[] cargados = crud.deserializar();
            if (cargados.length == 0) {
                System.out.println("  No se cargaron protocolos.");
                return;
            }
            System.out.println("  Examenes cargados desde archivo:");
            for (int i = 0; i < cargados.length; i++) {
                if (cargados[i] != null) {
                    System.out.println("    [" + i + "] "
                            + cargados[i].getClass().getSimpleName()
                            + " | " );
                }
            }
        } catch (IOException e) {
            System.out.println("  ERROR [DESERIALIZAR]: No se pudo leer el archivo. "
                    + e.getMessage());
        }
    }

    // ---------------------------------------------------------------
    // MAIN
    // ---------------------------------------------------------------

    /**
     * Método principal. Ejecuta el menú interactivo por consola.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        ImplementacionOperacionCRUD crud = new ImplementacionOperacionCRUD();
        Scanner scanner = new Scanner(System.in);
        String opcion;

        separadorGrueso();
        System.out.println("   BIENVENIDO AL SISTEMA DE EXAMENES");
   
        separadorGrueso();

        do {
            mostrarMenu();
            opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1": menuCrear(crud, scanner);           break;
                case "2": menuLeerPorIndice(crud, scanner);   break;
                case "3": menuLeerTodos(crud);                break;
                case "4": menuBuscarPorCodigo(crud, scanner); break;
                case "5": menuModificar(crud, scanner);       break;
                case "6": menuEliminar(crud, scanner);        break;
                case "7": menuSerializar(crud);               break;
                case "8": menuDeserializar(crud);             break;
                case "9":
                    separadorGrueso();
                    System.out.println("  VER ESTADO DEL ARREGLO");
                    separador();
                    imprimirArreglo(crud);
                    break;
                case "0":
                    separadorGrueso();
                    System.out.println("  Saliendo del sistema. Hasta luego.");
                    separadorGrueso();
                    break;
                default:
                    System.out.println("  ERROR: Opcion invalida. Ingrese un numero del 0 al 9.");
                    break;
            }

            if (!opcion.equals("0")) {
                System.out.println("\n  Presione ENTER para continuar...");
                scanner.nextLine();
            }

        } while (!opcion.equals("0"));

        scanner.close();
    }
}