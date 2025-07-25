/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author juanp
 */
public class PatronADN {
    
    private String patron;
    private int frecuencia;
    private Lista posiciones;

    /**
     * Constructor para crear un nuevo patrón de ADN
     *
     * @param patron El patrón de 3 nucleótidos
     * @param primeraPosicion La primera posición donde aparece
     */
    public PatronADN(String patron, int primeraPosicion) {
        this.patron = patron;
        this.frecuencia = 1;
        this.posiciones = new Lista();
        this.posiciones.insertFinal(primeraPosicion);
    }

    public String getPatron() {
        return patron;
    }

    public void setPatron(String patron) {
        this.patron = patron;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Lista getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(Lista posiciones) {
        this.posiciones = posiciones;
    }

    public String mostrarPosiciones() {
        String posicionesStr = "";
        for (int i = 0; i < this.posiciones.getSize() - 1; i++) {
            int pos = (int) this.posiciones.getValue(i);
            String posStr = String.valueOf(pos);
            posicionesStr += posStr + ",";
        }
        int pos = (int) this.posiciones.getValue(this.posiciones.getSize() - 1);
        String posStr = String.valueOf(pos);
        posicionesStr += posStr;

        return posicionesStr;
    }

    @Override
    public String toString() {
        return patron + "\nFrecuencia: " + frecuencia + "\nPosiciones: " + this.mostrarPosiciones();
    }

    /**
     * Incrementa la frecuencia del patrón
     */
    public void incrementarFrecuencia() {
        frecuencia++;
    }

    /**
     * Agrega una nueva posición donde aparece el patrón
     *
     * @param posicion La posición a agregar
     */
    public void agregarPosicion(int posicion) {
        posiciones.insertFinal(posicion);
    }
}
