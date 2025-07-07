/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import EDD.Aminoacido;
import EDD.ArbolAVL;
import EDD.HashTable;
import EDD.Lista;
import EDD.Nodo;
import EDD.PatronADN;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author juanp
 */
public class CargarArchivo {
    private String txt;
    private HashTable tabla;
    private ArbolAVL arbolFrecuencias;
    private Helpers help;

    public CargarArchivo() {
        this.txt = "";
        this.tabla = null;
        this.arbolFrecuencias = null;
        this.help = new Helpers();
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public HashTable getTabla() {
        return tabla;
    }

    public void setTabla(HashTable tabla) {
        this.tabla = tabla;
    }

    public ArbolAVL getArbolFrecuencias() {
        return arbolFrecuencias;
    }

    public void setArbolFrecuencias(ArbolAVL arbolFrecuencias) {
        this.arbolFrecuencias = arbolFrecuencias;
    }

    public int cargar() {
        if (txt != null && !txt.isEmpty()) {

            // 1. Eliminar saltos de línea y espacios en blanco
            String secuenciaLimpia = limpiarSecuenciaADN(txt);

            // 2. Verificar que la longitud sea múltiplo de 3
            if (secuenciaLimpia.length() % 3 != 0) {
                secuenciaLimpia = secuenciaLimpia.substring(0, secuenciaLimpia.length() - (secuenciaLimpia.length() % 3));
                return -1;
            }

            // 3. Verificar que solo contiene caracteres válidos (A, T, C, G)
            if (!this.help.validarSecuenciaADN(secuenciaLimpia)) {
                return -2;
            }

            // 4. Procesar la secuencia en la HashTable
            tabla.procesarSecuenciaADN(secuenciaLimpia);
            return 1;
        }

        return 0;
    }

    /**
     * Método para cargar los patrones de la HashTable en el árbol AVL
     *
     * @return true si se cargó correctamente, false si no hay datos
     */
    public boolean cargarArbolFrecuencias() {
        if (tabla == null || tabla.getTamanio() == 0) {
            return false;
        }
        // Recorrer toda la tabla hash
        for (int i = 0; i < tabla.getCapacidad(); i++) {
            Lista lista = tabla.getTabla()[i];
            if (lista != null && !lista.isEmpty()) {
                Nodo actual = lista.getpFirst();
                while (actual != null) {
                    PatronADN patron = (PatronADN) actual.getDato();
                    arbolFrecuencias.insertar(patron);
                    actual = actual.getPnext();
                }
            }
        }
        return true;
    }

    private String limpiarSecuenciaADN(String secuencia) {
        // Eliminar todos los caracteres que no sean A, T, C o G (incluyendo saltos de línea)
        return secuencia.replaceAll("[^ATCG]", "").toUpperCase();
    }

    /**
     * Carga la lista de aminoácidos desde el archivo CSV interno
     *
     * @return Lista con todos los aminoácidos y codones STOP
     */
    public Lista cargarAminoacidos() throws FileNotFoundException, IOException {
        Lista listaAminoacidos = new Lista();

        String path = "test\\ListaAminoacidos.csv";
        File file = new File(path);

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        // Saltar la cabecera
        br.readLine();
        String linea;
        while ((linea = br.readLine()) != null) {
            linea.replaceAll(",", "");
            String[] datos = linea.split(",");
            if (datos.length >= 6) {
                String base1 = datos[0].trim();
                String base2 = datos[1].trim();
                String base3 = datos[2].trim();
                String nombre = datos[3].trim();
                String abrev3 = datos[4].trim();
                String abrev1 = datos[5].trim();

                String tripleta = base1 + base2 + base3;
                boolean esStop = abrev3.equals("-");

                Aminoacido aminoacido = new Aminoacido(
                        nombre,
                        tripleta,
                        abrev3,
                        abrev1,
                        esStop
                );

                listaAminoacidos.insertFinal(aminoacido);
            }
        }

        return listaAminoacidos;
    }
}
