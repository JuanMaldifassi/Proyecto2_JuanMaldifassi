/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import EDD.HashTable;

/**
 *
 * @author juanp
 */
public class CargarArchivo {
    private String txt;
    private HashTable tabla;

    public CargarArchivo(String txt, HashTable tabla) {
        this.txt = txt;
        this.tabla = tabla;
    }

    public CargarArchivo() {
        this.txt = "";
        this.tabla = null;
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
            if (!validarSecuenciaADN(secuenciaLimpia)) {
                return -2;
            }

            // 4. Procesar la secuencia en la HashTable
            tabla.procesarSecuenciaADN(secuenciaLimpia);
            return 1;
        }
        
        return 0;
    }

    private String limpiarSecuenciaADN(String secuencia) {
        // Eliminar todos los caracteres que no sean A, T, C o G (incluyendo saltos de línea)
        return secuencia.replaceAll("[^ATCG]", "").toUpperCase();
    }

    private boolean validarSecuenciaADN(String secuencia) {
        // Verificar que solo contiene A, T, C, G
        return secuencia.matches("^[ATCG]+$");
    }
}
