/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author juanp
 */
public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    // Método para obtener la altura de un nodo
    private int altura(NodoAVL nodo) {
        return (nodo == null) ? 0 : nodo.getAltura();
    }

    // Método para obtener el factor de balance de un nodo
    private int getBalance(NodoAVL nodo) {
        return (nodo == null) ? 0 : altura(nodo.getIzquierdo()) - altura(nodo.getDerecho());
    }

    // Rotación simple a la derecha
    private NodoAVL rotacionDerecha(NodoAVL y) {
        NodoAVL x = y.getIzquierdo();
        NodoAVL T2 = x.getDerecho();

        x.setDerecho(y);
        y.setIzquierdo(T2);

        y.setAltura(Math.max(altura(y.getIzquierdo()), altura(y.getDerecho())) + 1);
        x.setAltura(Math.max(altura(x.getIzquierdo()), altura(x.getDerecho())) + 1);

        return x;
    }

    // Rotación simple a la izquierda
    private NodoAVL rotacionIzquierda(NodoAVL x) {
        NodoAVL y = x.getDerecho();
        NodoAVL T2 = y.getIzquierdo();

        y.setIzquierdo(x);
        x.setDerecho(T2);

        x.setAltura(Math.max(altura(x.getIzquierdo()), altura(x.getDerecho())) + 1);
        y.setAltura(Math.max(altura(y.getIzquierdo()), altura(y.getDerecho())) + 1);

        return y;
    }

    // Insertar un nuevo patrón en el árbol
    public void insertar(PatronADN patron) {
        raiz = insertar(raiz, patron);
    }

    private NodoAVL insertar(NodoAVL nodo, PatronADN patron) {
        if (nodo == null) {
            return new NodoAVL(patron);
        }

        // Comparar por frecuencia
        if (patron.getFrecuencia() < nodo.getPatron().getFrecuencia()) {
            nodo.setIzquierdo(insertar(nodo.getIzquierdo(), patron));
        } else if (patron.getFrecuencia() > nodo.getPatron().getFrecuencia()) {
            nodo.setDerecho(insertar(nodo.getDerecho(), patron));
        } else {
            // Misma frecuencia, incrementamos la cantidad
            nodo.setCantidad(nodo.getCantidad() + 1);
            return nodo;
        }

        // Actualizar altura
        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())));

        // Balancear el árbol
        int balance = getBalance(nodo);

        // Caso izquierda izquierda
        if (balance > 1 && patron.getFrecuencia() < nodo.getIzquierdo().getPatron().getFrecuencia()) {
            return rotacionDerecha(nodo);
        }

        // Caso derecha derecha
        if (balance < -1 && patron.getFrecuencia() > nodo.getDerecho().getPatron().getFrecuencia()) {
            return rotacionIzquierda(nodo);
        }

        // Caso izquierda derecha
        if (balance > 1 && patron.getFrecuencia() > nodo.getIzquierdo().getPatron().getFrecuencia()) {
            nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
            return rotacionDerecha(nodo);
        }

        // Caso derecha izquierda
        if (balance < -1 && patron.getFrecuencia() < nodo.getDerecho().getPatron().getFrecuencia()) {
            nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    // Eliminar un patrón del árbol (por frecuencia)
    public void eliminar(PatronADN patron) {
        raiz = eliminar(raiz, patron);
    }

    private NodoAVL eliminar(NodoAVL nodo, PatronADN patron) {
        if (nodo == null) {
            return nodo;
        }

        if (patron.getFrecuencia() < nodo.getPatron().getFrecuencia()) {
            nodo.setIzquierdo(eliminar(nodo.getIzquierdo(), patron));
        } else if (patron.getFrecuencia() > nodo.getPatron().getFrecuencia()) {
            nodo.setDerecho(eliminar(nodo.getDerecho(), patron));
        } else {
            // Misma frecuencia, decrementamos la cantidad o eliminamos
            if (nodo.getCantidad() > 1) {
                nodo.setCantidad(nodo.getCantidad() - 1);
                return nodo;
            } else {
                // Nodo con un hijo o sin hijos
                if (nodo.getIzquierdo() == null || nodo.getDerecho() == null) {
                    NodoAVL temp = (nodo.getIzquierdo() != null) ? nodo.getIzquierdo() : nodo.getDerecho();

                    // Sin hijos
                    if (temp == null) {
                        temp = nodo;
                        nodo = null;
                    } else { // Un hijo
                        nodo = temp;
                    }
                } else {
                    // Nodo con dos hijos: obtener el sucesor inorden (mínimo en subárbol derecho)
                    NodoAVL temp = minValorNodo(nodo.getDerecho());

                    // Copiar los datos del sucesor
                    nodo.setPatron(temp.getPatron());
                    nodo.setCantidad(temp.getCantidad());

                    // Eliminar el sucesor
                    nodo.setDerecho(eliminar(nodo.getDerecho(), temp.getPatron()));
                }
            }
        }

        // Si el árbol tenía solo un nodo
        if (nodo == null) {
            return nodo;
        }

        // Actualizar altura
        nodo.setAltura(1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())));

        // Balancear el árbol
        int balance = getBalance(nodo);

        // Caso izquierda izquierda
        if (balance > 1 && getBalance(nodo.getIzquierdo()) >= 0) {
            return rotacionDerecha(nodo);
        }

        // Caso izquierda derecha
        if (balance > 1 && getBalance(nodo.getIzquierdo()) < 0) {
            nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
            return rotacionDerecha(nodo);
        }

        // Caso derecha derecha
        if (balance < -1 && getBalance(nodo.getDerecho()) <= 0) {
            return rotacionIzquierda(nodo);
        }

        // Caso derecha izquierda
        if (balance < -1 && getBalance(nodo.getDerecho()) > 0) {
            nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }

    // Encontrar el nodo con mínimo valor (para eliminación)
    private NodoAVL minValorNodo(NodoAVL nodo) {
        NodoAVL actual = nodo;
        while (actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }
        return actual;
    }

    // Obtener el patrón con mayor frecuencia (más a la derecha)
    public PatronADN getMaxFrecuencia() {
        if (raiz == null) {
            return null;
        }
        NodoAVL actual = raiz;
        while (actual.getDerecho() != null) {
            actual = actual.getDerecho();
        }
        return actual.getPatron();
    }

    // Obtener el patrón con menor frecuencia (más a la izquierda)
    public PatronADN getMinFrecuencia() {
        if (raiz == null) {
            return null;
        }
        NodoAVL actual = raiz;
        while (actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }
        return actual.getPatron();
    }

    // Método para buscar un patrón por frecuencia (no se usa directamente en este contexto)
    public NodoAVL buscar(int frecuencia) {
        return buscar(raiz, frecuencia);
    }

    private NodoAVL buscar(NodoAVL nodo, int frecuencia) {
        if (nodo == null || nodo.getPatron().getFrecuencia() == frecuencia) {
            return nodo;
        }

        if (frecuencia < nodo.getPatron().getFrecuencia()) {
            return buscar(nodo.getIzquierdo(), frecuencia);
        }

        return buscar(nodo.getDerecho(), frecuencia);
    }
}
