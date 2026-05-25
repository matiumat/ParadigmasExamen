package co.edu.poli.Examen2.servicios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import co.edu.poli.Examen2.modelo.ExamenMedico;
import co.edu.poli.Examen2.modelo.ExamenOrina;
import co.edu.poli.Examen2.modelo.ExamenSangre;
import co.edu.poli.Examen2.modelo.ExamenRayosX;

/**
 * Implementación concreta de las interfaces {@link OperacionCRUD} y {@link OperacionArchivo}.
 * <p>
 * Gestiona un arreglo dinámico de objetos de tipo {@link Protocolo} (supersuperclase).
 * El arreglo inicia con tamaño 2 y se duplica automáticamente cuando se llena.
 * </p>
 * <p>
 * Todos los métodos usan {@code throws} para propagar los errores al método
 * invocador en lugar de retornarlos como Strings. Los errores de validación
 * lanzan {@link Exception} y los errores de archivo lanzan {@link IOException}.
 * </p>
 *
 * @author Equipo Contexto 4
 * @version 2.0
 * @see OperacionCRUD
 * @see OperacionArchivo
 */
public class ImplementacionOperacionCRUD implements OperacionCRUD, OperacionArchivo {

    /** Nombre del archivo donde se persisten los protocolos. */
    private static final String NOMBRE_ARCHIVO = "examenes.txt";

    /** Separador de campos dentro de cada línea del archivo. */
    private static final String SEP = ";";

    /**
     * Arreglo de tipo supersuperclase {@link Protocolo}.
     * Inicia con tamaño 2. Se duplica automáticamente al llenarse.
     */
    private ExamenMedico[] arreglo_examenes;

    /**
     * Constructor por defecto. Inicializa el arreglo con tamaño 2.
     */
    public ImplementacionOperacionCRUD() {
        arreglo_examenes = new ExamenMedico[2];
    }

    // =======================================================================
    // OPERACIONES CRUD
    // =======================================================================

    /**
     * {@inheritDoc}
     *
     * @throws Exception Si el protocolo es {@code null}, el numero_id está vacío,
     *                   o ya existe un protocolo con ese numero_id.
     */
    @Override
    public String crear(ExamenMedico examen_medico) throws Exception {
        if (examen_medico == null) {
            throw new Exception("No se puede insertar un protocolo null.");
        }
        if (examen_medico.getCodigo_identificacion() == null ) {
            throw new Exception("El examen debe tener un numero_id valido.");
        }
        for (ExamenMedico p : arreglo_examenes) {
            if (p != null && p.getCodigo_identificacion().equals(examen_medico.getCodigo_identificacion())) {
                throw new Exception("Ya existe un examen con numero_id '"
                        + examen_medico.getCodigo_identificacion() + "'.");
            }
        }

        // Buscar primer null de izquierda a derecha
        for (int i = 0; i < arreglo_examenes.length; i++) {
            if (arreglo_examenes[i] == null) {
                arreglo_examenes[i] = examen_medico;
                return "OK [CREAR]: Examen con numero_id '" + examen_medico.getCodigo_identificacion()
                        + "' insertado en posicion [" + i + "].";
            }
        }

        // Arreglo lleno → duplicar tamaño
        ExamenMedico[] nuevo = new ExamenMedico[arreglo_examenes.length * 2];
        for (int i = 0; i < arreglo_examenes.length; i++) {
            nuevo[i] = arreglo_examenes[i];
        }
        arreglo_examenes = nuevo;

        for (int i = 0; i < arreglo_examenes.length; i++) {
            if (arreglo_examenes[i] == null) {
                arreglo_examenes[i] = examen_medico;
                return "OK [CREAR]: Arreglo duplicado a tamano " + arreglo_examenes.length
                        + ". Examen con numero_id '" + examen_medico.getCodigo_identificacion()
                        + "' insertado en posicion [" + i + "].";
            }
        }

        throw new Exception("No se pudo insertar el examen. Error inesperado.");
    }

    /**
     * {@inheritDoc}
     *
     * @throws Exception Si el índice está fuera de rango o la posición está vacía.
     */
    @Override
    public ExamenMedico leer(int indice) throws Exception {
        if (indice < 0 || indice >= arreglo_examenes.length) {
            throw new Exception("Indice " + indice
                    + " fuera de rango. Tamano actual: " + arreglo_examenes.length);
        }
        if (arreglo_examenes[indice] == null) {
            throw new Exception("No hay examenes en esta posicion [" + indice + "].");
        }
        return arreglo_examenes[indice];
    }

    /**
     * {@inheritDoc}
     *
     * @throws Exception Si el arreglo está completamente vacío.
     */
    @Override
    public ExamenMedico[] leerTodos() throws Exception {
        for (ExamenMedico p : arreglo_examenes) {
            if (p != null) return arreglo_examenes;
        }
        throw new Exception("El arreglo esta vacio. No hay examenes registrados.");
    }

    /**
     * {@inheritDoc}
     *
     * @throws Exception Si el índice es inválido, la posición está vacía,
     *                   el protocolo de reemplazo es null, o el numero_id está vacío.
     */
    @Override
    public String modificar(int indice, ExamenMedico examen_medico) throws Exception {
        if (indice < 0 || indice >= arreglo_examenes.length) {
            throw new Exception("Indice " + indice
                    + " fuera de rango. Tamano actual: " + arreglo_examenes.length);
        }
        if (arreglo_examenes[indice] == null) {
            throw new Exception("No existe examen en la posicion ["
                    + indice + "]. Use CREAR para insertar.");
        }
        if (examen_medico == null) {
            throw new Exception("El examen de reemplazo no puede ser null.");
        }
        if (examen_medico.getCodigo_identificacion() == null || examen_medico.getCodigo_identificacion().trim().isEmpty()) {
            throw new Exception("El examen de reemplazo debe tener un numero_id valido.");
        }
        String idAnterior = arreglo_examenes[indice].getCodigo_identificacion();
        arreglo_examenes[indice] = examen_medico;
        return "OK [MODIFICAR]: Posicion [" + indice + "] actualizada. numero_id '"
                + idAnterior + "' -> '" + examen_medico.getCodigo_identificacion() + "'.";
    }

    /**
     * {@inheritDoc}
     *
     * @throws Exception Si el índice está fuera de rango o la posición ya está vacía.
     */
    @Override
    public String eliminar(int indice) throws Exception {
        if (indice < 0 || indice >= arreglo_examenes.length) {
            throw new Exception("Indice " + indice
                    + " fuera de rango. Tamano actual: " + arreglo_examenes.length);
        }
        if (arreglo_examenes[indice] == null) {
            throw new Exception("No existe examen en la posicion ["
                    + indice + "]. Ya esta vacia.");
        }
        String idEliminado = arreglo_examenes[indice].getCodigo_identificacion();
        arreglo_examenes[indice] = null;
        return "OK [ELIMINAR]: examen con numero_id '" + idEliminado
                + "' eliminado de la posicion [" + indice + "].";
    }

    // =======================================================================
    // OPERACIONES DE ARCHIVO
    // =======================================================================

    /**
     * {@inheritDoc}
     *
     * @throws Exception   Si el arreglo está completamente vacío.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    @Override
    public String serializar() throws IOException, Exception {
        boolean hayDatos = false;
        for (ExamenMedico p : arreglo_examenes) {
            if (p != null) { hayDatos = true; break; }
        }
        if (!hayDatos) {
            throw new Exception("El arreglo esta vacio. No hay datos para serializar.");
        }

        // IOException se propaga directamente al invocador (sin try-catch interno)
        BufferedWriter bw = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO));
        int escritos = 0;
        for (ExamenMedico p : arreglo_examenes) {
            if (p != null) {
                String codigo_identificacion      = p.getCodigo_identificacion()        != null ? p.getCodigo_identificacion()        : "";
                String nombre_paciente      = p.getNombre_paciente()      != null ? p.getNombre_paciente()      : "";
                String fecha_realizacion = p.getFecha_realizacion() != null ? p.getFecha_realizacion() : "";
                String costo_procedimiento       = p.getCosto_procedimiento() != null ? p.getCosto_procedimiento()       : "";

                bw.write(codigo_identificacion + SEP + nombre_paciente + SEP + fecha_realizacion + SEP + costo_procedimiento);
                bw.newLine();
                escritos++;
            }
        }
        bw.close();
        return "OK [SERIALIZAR]: " + escritos + " examen(es) guardados en '"
                + NOMBRE_ARCHIVO + "'.";
    }

    /**
     * {@inheritDoc}
     *
     * @throws IOException Si el archivo no existe o no puede leerse.
     */
    @Override
    public ExamenMedico[] deserializar() throws IOException {
        // Primera pasada: contar líneas (IOException se propaga al invocador)
        int totalLineas = 0;
        BufferedReader br1 = new BufferedReader(new FileReader(NOMBRE_ARCHIVO));
        while (br1.readLine() != null) totalLineas++;
        br1.close();

        ExamenMedico[] resultado = new ExamenMedico[totalLineas];
        int idx = 0;

        // Segunda pasada: reconstruir objetos (IOException se propaga al invocador)
        BufferedReader br2 = new BufferedReader(new FileReader(NOMBRE_ARCHIVO));
        String linea;
        while ((linea = br2.readLine()) != null) {
            String[] partes = linea.split(SEP, -1);

            if (partes.length < 5) {
                System.out.println("AVISO [DESERIALIZAR]: Linea con formato incorrecto, se omite: ["
                        + linea + "]");
                continue;
            }

            String tipo          = partes[0];
            String codigo_identificacion   = partes[1];
            String nombre_paciente      = partes[2];
            String fecha_realizacion = partes[3];
            String costo_procedimiento       = partes[4];
            String rh  = partes[5];
            String tipo_sangre = partes [6];
            

            // Validar que numero_id sea entero; si no, omitir la línea sin try-catch
            boolean esEntero = !codigo_identificacion.isEmpty();
            for (int i = 0; i < codigo_identificacion.length() && esEntero; i++) {
                if (i == 0 && codigo_identificacion.charAt(i) == '-') continue;
                if (!Character.isDigit(codigo_identificacion.charAt(i))) esEntero = false;
            }
            if (!esEntero) {
                System.out.println("AVISO [DESERIALIZAR]: numero_id '" + codigo_identificacion
                        + "' no es entero, se omite la linea.");
                continue;
            }

            int numero_id = Integer.parseInt(codigo_identificacion);

            if (tipo.equals("+")) {
                resultado[idx] = new ExamenSangre();
            } else {
                System.out.println("AVISO [DESERIALIZAR]: Tipo '" + tipo + "' desconocido, se omite.");
                continue;
            }
            idx++;
        }
        br2.close();

        System.out.println("OK [DESERIALIZAR]: " + idx + " examenes(s) cargados desde '"
                + NOMBRE_ARCHIVO + "'.");
        return resultado;
    }

    // =======================================================================
    // MÉTODOS UTILITARIOS
    // =======================================================================

    /**
     * Busca el índice de un protocolo según su numero_id (como String).
     *
     * @param codigo numero_id del protocolo a buscar.
     * @return Índice en el arreglo, o {@code -1} si no se encuentra.
     */
    public int buscarIndicePorCodigo(String codigo_identificacion) {
        if (codigo_identificacion == null || codigo_identificacion.trim().isEmpty()) return -1;
        for (int i = 0; i < arreglo_examenes.length; i++) {
            if (arreglo_examenes[i] != null
                    && arreglo_examenes[i].getCodigo_identificacion().equals(codigo_identificacion)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna el arreglo completo de protocolos.
     *
     * @return Arreglo actual de {@link Protocolo}.
     */
    public ExamenMedico[] getArreglo_examenes() { return arreglo_examenes; }

    /**
     * Retorna el tamaño actual del arreglo.
     *
     * @return Tamaño del arreglo incluyendo posiciones vacías.
     */
    public int getTamano() { return arreglo_examenes.length; }

	
}