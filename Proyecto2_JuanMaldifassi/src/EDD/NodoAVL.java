/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author juanp
 */
public class NodoAVL {
    private PatronADN patron;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    private int altura;
    private int cantidad;

    public NodoAVL(PatronADN patron) {
        this.patron = patron;
        this.altura = 1;
        this.cantidad = 1;
    }

    // Getters y Setters
    public PatronADN getPatron() {
        return patron;
    }

    public void setPatron(PatronADN patron) {
        this.patron = patron;
    }

    public NodoAVL getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoAVL getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
