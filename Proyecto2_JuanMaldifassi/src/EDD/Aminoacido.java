/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author juanp
 */
public class Aminoacido {
    private String nombre;
    private String tripleta;
    private String abreviatura3;
    private String abreviatura1;
    private boolean esStop;

    /**
     * Constructor para inicializar un aminoácido con todos sus datos.
     *
     * @param nombre Nombre común del aminoácido (ej. "Alanina").
     * @param tripleta Secuencia de tres bases que lo codifican (ej. "GCU").
     * @param abreviatura3 Abreviatura de tres letras (ej. "Ala").
     * @param abreviatura1 Abreviatura de una letra (ej. "A").
     */
    public Aminoacido(String nombre, String tripleta, String abreviatura3, String abreviatura1, boolean esStop) {
        this.nombre = nombre;
        this.tripleta = tripleta;
        this.abreviatura3 = abreviatura3;
        this.abreviatura1 = abreviatura1;
        this.esStop = esStop;
    }

    /**
     * Obtiene el nombre común del aminoácido.
     *
     * @return Nombre (ej. "Alanina").
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la tripleta (codón) que codifica el aminoácido.
     *
     * @return Secuencia de tres bases (ej. "GCU").
     */
    public String getTripleta() {
        return tripleta;
    }

    /**
     * Obtiene la abreviatura de tres letras.
     *
     * @return Abreviatura (ej. "Ala").
     */
    public String getAbreviatura3() {
        return abreviatura3;
    }

    /**
     * Obtiene la abreviatura de una letra.
     *
     * @return Abreviatura (ej. "A").
     */
    public String getAbreviatura1() {
        return abreviatura1;
    }

    /**
     * Establece el nombre del aminoácido.
     *
     * @param nombre Nuevo nombre (ej. "Glicina").
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece la tripleta (codón) del aminoácido.
     *
     * @param tripleta Nueva secuencia de tres bases (ej. "GGU").
     */
    public void setTripleta(String tripleta) {
        this.tripleta = tripleta;
    }

    /**
     * Establece la abreviatura de tres letras.
     *
     * @param abreviatura3 Nueva abreviatura (ej. "Gly").
     */
    public void setAbreviatura3(String abreviatura3) {
        this.abreviatura3 = abreviatura3;
    }

    /**
     * Establece la abreviatura de una letra.
     *
     * @param abreviatura1 Nueva abreviatura (ej. "G").
     */
    public void setAbreviatura1(String abreviatura1) {
        this.abreviatura1 = abreviatura1;
    }

    public boolean esStop() {
        return esStop;
    }

    /**
     * Devuelve una representación legible del aminoácido.
     *
     * @return String con formato: "Alanina (GCU) - Abreviaturas: Ala, A".
     */
    @Override
    public String toString() {
        if (esStop) {
            return "Nombre: " + nombre + "\nTripleta:" + tripleta;
        } else {
            return "Nombre: " + nombre + "\nTripleta:" + tripleta + "\nAbreviatura 3: " + abreviatura3 + "\nAbreviatura 1: " + abreviatura1;
        }
    }
}
