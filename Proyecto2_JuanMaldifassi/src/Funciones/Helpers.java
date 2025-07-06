/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

/**
 *
 * @author juanp
 */
public class Helpers {
    /**
     * Valida si una cadena contiene solo letras (incluyendo ñ y mayúsculas/minúsculas).
     * @param letra Cadena a validar
     * @return true si la cadena contiene solo letras, false en caso contrario
     */
    private boolean validarLetras(String letra){
        return letra.matches("[a-zA-Zñ]*");
    }
    
    /**
     * Valida que una palabra contenga solo caracteres alfabéticos válidos.
     * @param palabra Palabra a validar
     * @return La palabra si es válida, null si contiene caracteres no permitidos
     */
    public String validarPalabra(String palabra){
        if(validarLetras(palabra)){
            return palabra;
        }else{
            return null;
        }
    }
    
    public boolean validarSecuenciaADN(String secuencia) {
        // Verificar que solo contiene A, T, C, G
        return secuencia.matches("^[ATCG]+$");
    }
}
