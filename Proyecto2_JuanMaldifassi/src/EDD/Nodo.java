/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author juanp
 */
public class Nodo {
    //Atributos
     private Object dato; //Variable donde se guardará el valor
     private Nodo pnext; //Variable para enlazar los nodos
    
    /**
     * Constructor vacío que inicializa un nodo con valores nulos.
     */
    public Nodo(){
       this.dato = null;
       this.pnext = null; 
    }
    
    /**
     * Constructor que inicializa el nodo con un dato específico.
     * @param dato El objeto que se almacenará en el nodo.
     */
    public Nodo(Object dato) {
        this.dato = dato;
        this.pnext = null;
    }
    
    /**
     * Obtiene el dato almacenado en el nodo.
     * @return El objeto almacenado en el nodo.
     */
    public Object getDato() {
        return dato;
    }

    /**
     * Establece o modifica el dato del nodo.
     * @param dato El nuevo objeto a almacenar en el nodo.
     */
    public void setDato(Object dato) {
        this.dato = dato;
    }

    /**
     * Obtiene la referencia al siguiente nodo enlazado.
     * @return El nodo siguiente en la estructura.
     */
    public Nodo getPnext() {
        return pnext;
    }

    /**
     * Establece la referencia al siguiente nodo.
     * @param pnext El nodo que será enlazado como siguiente.
     */
    public void setPnext(Nodo pnext) {
        this.pnext = pnext;
    }
}
