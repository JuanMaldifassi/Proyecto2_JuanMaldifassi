/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import javax.swing.JOptionPane;

/**
 *
 * @author juanp
 */
public class Lista {
    
    private Nodo pFirst; //nodo apuntador al primero
    private int size; //tamaño de la lista
    
    /**
     * Constructor que inicializa una lista vacía.
     */
    public Lista() {
        this.pFirst = null;
        this.size = 0; 
    }
    
    /**
     * Obtiene el primer nodo de la lista.
     * @return Referencia al primer nodo.
     */
    public Nodo getpFirst() {
        return pFirst;
    }

    /**
     * Establece el primer nodo de la lista.
     * @param pFirst Nuevo nodo a establecer como primero.
     */
    public void setpFirst(Nodo pFirst) {
        this.pFirst = pFirst;
    }

    /**
     * Obtiene el tamaño actual de la lista.
     * @return Número de elementos en la lista.
     */
    public int getSize() {
        return size;
    }

    /**
     * Establece el tamaño de la lista.
     * @param size Nuevo tamaño a establecer.
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    /**
     * Verifica si la lista está vacía.
     * @return true si la lista no contiene elementos, false en caso contrario.
     */
    public boolean isEmpty(){
        return this.pFirst == null;
    }

    
    /**
     * Inserta un nuevo elemento al inicio de la lista.
     * @param dato Objeto a insertar.
     * @return Nodo recién creado.
     */
    public Nodo insertStart(Object dato){
        Nodo pNew = new Nodo();
        pNew.setDato(dato);
       
        if (isEmpty()) {   
            pFirst = pNew;
        } else{
            pNew.setPnext(pFirst);
            pFirst = pNew;
        }
        size++;
        return pNew;
    }

    /**
     * Inserta un nuevo elemento al final de la lista.
     * @param dato Objeto a insertar.
     */
    public void insertFinal(Object dato){
        Nodo pNew = new Nodo(dato);
        if(isEmpty()){
            pFirst = pNew;
        }else{
            Nodo aux = pFirst;
            while (aux.getPnext() != null){
                aux = aux.getPnext();
            }
            aux.setPnext(pNew);
        }
        size++;
    }
    
    /**
     * Convierte el contenido de la lista a una cadena de texto.
     * @return String con todos los elementos de la lista.
     */
    public String convertString(){
        if(!isEmpty()){
            Nodo aux = pFirst;
            String expresion = "";
            
            for(int i = 0; i <size;i++){
            expresion += aux.getDato().toString() + "\n";
            aux = aux.getPnext();
            }
            return expresion;
        }
        return "Lista vacia";
    }
    
    /**
     * Muestra el contenido de la lista en un cuadro de diálogo.
     */
    public void print(){
        if (!isEmpty()){
            Nodo aux = pFirst;
            String expresion = "";
            while(aux != null){
               expresion = expresion + aux.getDato().toString() + "\n";
               aux = aux.getPnext();
            }
            JOptionPane.showMessageDialog(null,expresion);
            
        }else{
            JOptionPane.showMessageDialog(null, "La lista esta vacia");
        }
    }
    
    /**
     * Elimina el primer elemento de la lista.
     * @return true si se eliminó correctamente, false si la lista estaba vacía.
     */
    public boolean deleteStart(){
        if(!isEmpty()){
            pFirst = pFirst.getPnext();
            size--;
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Elimina el último elemento de la lista.
     */
    public void deleteFinal(){
        if(!isEmpty()){
            if (getSize()==1) {
                destructor();
            }else{
                Nodo pointer = getpFirst();
                while(pointer.getPnext() != null && pointer.getPnext().getPnext()!= null){
                    pointer = pointer.getPnext();
                }
                pointer.setPnext(null);
            }
            size --;
        }  
    }
    
    /**
     * Elimina un elemento específico de la lista.
     * @param referencia Objeto a eliminar.
     */
    public void deleteForReference(Object referencia){
        if (search(referencia)) {
            if (pFirst.getDato() == referencia) {
                pFirst = pFirst.getPnext();
            } else{
                Nodo aux = pFirst;
                while(aux.getPnext().getDato() != referencia){
                    aux = aux.getPnext();
                }
                Nodo siguiente = aux.getPnext().getPnext();
                aux.setPnext(siguiente);  
            }
            size--;
        }
    }
    
    /**
     * Elimina un elemento en una posición específica de la lista.
     * @param posicion Índice del elemento a eliminar.
     */
    public void deleteForPosition(int posicion){
        if(posicion>=0 && posicion<size){
            if(posicion == 0){
                pFirst = pFirst.getPnext();
            }
            else{
                Nodo aux = pFirst;
                for (int i = 0; i < posicion-1; i++) {
                    aux = aux.getPnext();
                }
                Nodo siguiente = aux.getPnext();
                aux.setPnext(siguiente.getPnext());
            }
            size--;
        }
    }
    
    /**
     * Obtiene el valor de un nodo en una posición específica.
     * @param posicion Índice del nodo a consultar.
     * @return Objeto almacenado en la posición solicitada, o null si no existe.
     */
    public Object getValue(int posicion){
        if(posicion>=0 && posicion<size){
            if (posicion == 0) {
                return pFirst.getDato();
            }else{
                Nodo aux = pFirst;
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getPnext();
                }
                return aux.getDato();
            }
        }
        return null;
    }
    
    /**
     * Busca un objeto en la lista.
     * @param referencia Objeto a buscar.
     * @return true si el objeto existe en la lista, false en caso contrario.
     */
    public boolean search(Object referencia){
        Nodo aux = pFirst;
        boolean encontrado = false;
        while(aux != null && encontrado != true){
            if (referencia == aux.getDato()){ 
                encontrado = true;
            }
            else{
                aux = aux.getPnext();
            }
        }
        return encontrado;
    }
    
    /**
     * Destructor de la lista (similar a empty() pero con nombre diferente).
     */
    public void destructor(){
        pFirst = null;
        size = 0;
    }
}
