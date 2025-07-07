/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author juanp
 */
public class HashTable {
    
    private Lista[] tabla;       // Array de listas para manejar colisiones
    private int capacidad;       // Tamaño de la tabla
    private int tamanio;         // Número de elementos almacenados
    private int colisiones;      // Contador de colisiones

    /**
     * Constructor que inicializa la tabla hash
     *
     * @param capacidad Capacidad inicial de la tabla (debe ser un número primo)
     */
    public HashTable(int capacidad) {
        this.capacidad = capacidad;
        this.tabla = new Lista[capacidad];
        this.tamanio = 0;
        this.colisiones = 0;

        // Inicializar cada bucket con una lista vacía
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new Lista();
        }
    }

    public Lista[] getTabla() {
        return tabla;
    }

    public void setTabla(Lista[] tabla) {
        this.tabla = tabla;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getColisiones() {
        return colisiones;
    }

    public void setColisiones(int colisiones) {
        this.colisiones = colisiones;
    }

    /**
     * Función hash para patrones de ADN (3 caracteres)
     *
     * @param patron El patrón de ADN (3 nucleótidos)
     * @return Índice en la tabla hash
     */
    private int hash(String patron) {
        // Convertir cada carácter a su valor numérico (A=0, T=1, C=2, G=3)
        int valor = 0;
        for (int i = 0; i < patron.length(); i++) {
            char c = patron.charAt(i);
            switch (c) {
                case 'A':
                    valor = valor * 4 + 0;
                    break;
                case 'T':
                    valor = valor * 4 + 1;
                    break;
                case 'C':
                    valor = valor * 4 + 2;
                    break;
                case 'G':
                    valor = valor * 4 + 3;
                    break;
                default:
                    throw new IllegalArgumentException("Carácter inválido en patrón de ADN: " + c);
            }
        }
        return Math.abs(valor) % capacidad;
    }

    /**
     * Procesa una secuencia de ADN, extrayendo todos los patrones de 3
     * caracteres
     *
     * @param secuenciaADN La secuencia completa de ADN
     */
    public void procesarSecuenciaADN(String secuenciaADN) {
        if (secuenciaADN == null || secuenciaADN.length() < 3) {
            return;
        }

        for (int i = 0; i <= secuenciaADN.length() - 3; i++) {
            String patron = secuenciaADN.substring(i, i + 3);
            insertarPatron(patron, i);
        }
    }

    /**
     * Inserta un patrón de ADN en la tabla hash con su posición
     *
     * @param patron El patrón de 3 nucleótidos
     * @param posicion La posición en la secuencia original
     */
    public void insertarPatron(String patron, int posicion) {
        int indice = hash(patron);
        PatronADN patronExistente = buscarPatron(patron);

        // Verificar colisión ANTES de cualquier operación
        boolean hayColision = !tabla[indice].isEmpty();

        if (patronExistente != null) {
            // El patrón ya existe, actualizar frecuencia y posiciones
            patronExistente.incrementarFrecuencia();
            patronExistente.agregarPosicion(posicion);

            // Contar colisión si hay otros patrones en el mismo bucket
            if (hayColision && tabla[indice].getSize() > 1) {
                colisiones++;
            }
        } else {
            // Nuevo patrón
            PatronADN nuevoPatron = new PatronADN(patron, posicion);

            // Contar colisión si el bucket no está vacío
            if (hayColision) {
                colisiones++;
            }

            tabla[indice].insertStart(nuevoPatron);
            tamanio++;
        }
    }

    /**
     * Busca un patrón en la tabla hash
     *
     * @param patron El patrón a buscar
     * @return El PatronADN encontrado o null si no existe
     */
    private PatronADN buscarPatron(String patron) {
        int indice = hash(patron);
        Nodo actual = tabla[indice].getpFirst();

        while (actual != null) {
            PatronADN patronADN = (PatronADN) actual.getDato();
            if (patronADN.getPatron().equals(patron)) {
                return patronADN;
            }
            actual = actual.getPnext();
        }
        return null;
    }
    
    public Lista buscarPorInicial(String inicial){
        Lista listaCoincidencia = new Lista();
        for (int i = 0; i < capacidad; i++) {
            if (tabla[i] != null && !tabla[i].isEmpty()) {
                Nodo actual = tabla[i].getpFirst();
                while (actual != null) {
                    PatronADN patron = (PatronADN) actual.getDato();
                    if(Character.toString(patron.getPatron().charAt(0)).equalsIgnoreCase(inicial)){
                        listaCoincidencia.insertFinal(patron);
                    }
                    actual = actual.getPnext();
                }
            }
        }
        
        return listaCoincidencia;
    }

    /**
     * Obtiene la frecuencia de un patrón de ADN
     *
     * @param patron El patrón a buscar
     * @return La frecuencia del patrón (0 si no existe)
     */
    public int obtenerFrecuencia(String patron) {
        PatronADN patronADN = buscarPatron(patron);
        return (patronADN != null) ? patronADN.getFrecuencia() : 0;
    }

    /**
     * Obtiene las posiciones donde aparece un patrón de ADN
     *
     * @param patron El patrón a buscar
     * @return Lista de posiciones o null no existe
     */
    public Lista obtenerPosiciones(String patron) {
        PatronADN patronADN = buscarPatron(patron);
        if (patronADN != null) {
            return patronADN.getPosiciones();
        } else {
            return null;
        }
    }

    /**
     * Genera un reporte de colisiones en la tabla hash
     *
     * @return String con información sobre las colisiones
     */
    public String reporteColisiones() {
        int bucketsConColisiones = 0;
        StringBuilder detalles = new StringBuilder();

        for (int i = 0; i < capacidad; i++) {
            int elementosEnBucket = tabla[i].getSize();
            if (elementosEnBucket > 1) {
                System.out.println(i);
                bucketsConColisiones++;
                detalles.append("Bucket ").append(i).append(": ")
                        .append(elementosEnBucket).append(" elementos\n");

                // Detalle de patrones en este bucket
                Nodo actual = tabla[i].getpFirst();
                while (actual != null) {
                    PatronADN p = (PatronADN) actual.getDato();
                    detalles.append("   - ").append(p.getPatron()).append("\n");
                    actual = actual.getPnext();
                }
            }
        }

        return "Total de colisiones: " + colisiones
                + "\nBuckets con colisiones: " + bucketsConColisiones
                + "\n\nDetalle de colisiones:\n" + detalles.toString();
    }

    /**
     * Muestra el contenido básico de la tabla hash
     *
     * @return String con la información de la tabla
     */
    public String mostrarTabla() {
        StringBuilder sb = new StringBuilder();

        sb.append("=== TABLA HASH ===\n");
        sb.append("Capacidad: ").append(capacidad).append("\n");
        sb.append("Elementos: ").append(tamanio).append("\n");
        sb.append("Colisiones: ").append(colisiones).append("\n\n");

        sb.append("=== CONTENIDO ===\n");

        for (int i = 0; i < capacidad; i++) {
            if (tabla[i] != null && !tabla[i].isEmpty()) {
                sb.append("Bucket ").append(i).append(":\n");

                Nodo actual = tabla[i].getpFirst();
                while (actual != null) {
                    PatronADN patron = (PatronADN) actual.getDato();
                    sb.append("  - ").append(patron.getPatron())
                            .append(" (Frec: ").append(patron.getFrecuencia())
                            .append(", Pos: ").append(patron.getPosiciones().convertString())
                            .append(")\n");
                    actual = actual.getPnext();
                }
            }
        }

        return sb.toString();
    }

    /**
     * Obtiene una lista de todos los patrones ordenados por frecuencia (de
     * mayor a menor)
     *
     * @return Lista ordenada de patrones
     */
    public Lista obtenerPatronesOrdenadosPorFrecuencia() {
        Lista todosPatrones = new Lista();

        // 1. Recolectar todos los patrones (O(n))
        for (int i = 0; i < capacidad; i++) {
            if (tabla[i] != null) {
                Nodo actual = tabla[i].getpFirst();
                while (actual != null) {
                    todosPatrones.insertFinal(actual.getDato());
                    actual = actual.getPnext();
                }
            }
        }

        // 2. Ordenar usando mergesort (O(n log n) en el mejor caso)
        return mergeSortPorFrecuencia(todosPatrones);
    }

    /**
     * Implementación de mergesort para ordenar por frecuencia
     */
    private Lista mergeSortPorFrecuencia(Lista lista) {
        if (lista.getSize() <= 1) {
            return lista;
        }

        // Dividir la lista en dos
        Lista izquierda = new Lista();
        Lista derecha = new Lista();
        Nodo actual = lista.getpFirst();

        for (int i = 0; i < lista.getSize() / 2; i++) {
            izquierda.insertFinal(actual.getDato());
            actual = actual.getPnext();
        }

        while (actual != null) {
            derecha.insertFinal(actual.getDato());
            actual = actual.getPnext();
        }

        // Ordenar recursivamente
        izquierda = mergeSortPorFrecuencia(izquierda);
        derecha = mergeSortPorFrecuencia(derecha);

        // Combinar
        return merge(izquierda, derecha);
    }

    /**
     * Combina dos listas ordenadas
     */
    private Lista merge(Lista izquierda, Lista derecha) {
        Lista resultado = new Lista();
        Nodo nodoIzq = izquierda.getpFirst();
        Nodo nodoDer = derecha.getpFirst();

        while (nodoIzq != null && nodoDer != null) {
            PatronADN patronIzq = (PatronADN) nodoIzq.getDato();
            PatronADN patronDer = (PatronADN) nodoDer.getDato();

            if (patronIzq.getFrecuencia() >= patronDer.getFrecuencia()) {
                resultado.insertFinal(patronIzq);
                nodoIzq = nodoIzq.getPnext();
            } else {
                resultado.insertFinal(patronDer);
                nodoDer = nodoDer.getPnext();
            }
        }

        while (nodoIzq != null) {
            resultado.insertFinal(nodoIzq.getDato());
            nodoIzq = nodoIzq.getPnext();
        }

        while (nodoDer != null) {
            resultado.insertFinal(nodoDer.getDato());
            nodoDer = nodoDer.getPnext();
        }

        return resultado;
    }

    /**
     * Muestra la lista de patrones ordenados por frecuencia
     *
     * @return String con el reporte formateado
     */
    public String mostrarPatronesOrdenados() {
        Lista ordenados = obtenerPatronesOrdenadosPorFrecuencia();
        StringBuilder sb = new StringBuilder();

        sb.append("=== PATRONES ORDENADOS POR FRECUENCIA ===\n");

        Nodo actual = ordenados.getpFirst();
        while (actual != null) {
            PatronADN patron = (PatronADN) actual.getDato();
            sb.append(patron.getPatron())
                    .append(" \n- Frecuencia: ").append(patron.getFrecuencia())
                    .append(" \n- Posiciones: ");

            // Mostrar posiciones
            Nodo posNodo = patron.getPosiciones().getpFirst();
            while (posNodo != null) {
                sb.append(posNodo.getDato());
                if (posNodo.getPnext() != null) {
                    sb.append(", ");
                }
                posNodo = posNodo.getPnext();
            }

            sb.append("\n\n");
            actual = actual.getPnext();
        }

        return sb.toString();
    }
}
