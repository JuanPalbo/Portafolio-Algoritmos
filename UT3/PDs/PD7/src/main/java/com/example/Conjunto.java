package com.example;

public class Conjunto<T> extends Lista<T> implements IConjunto<T> {

    @Override
    public IConjunto<T> union(IConjunto<T> otroConjunto) {
        Nodo<T> actual = this.getPrimero();
        Nodo<T> nodoAInsertar;
        Conjunto<T> union = new Conjunto<T>();

        while (actual != null) {
            union.insertar(actual.clonar());
            actual = actual.getSiguiente();
        }

        actual = otroConjunto.getPrimero();

        while (actual != null) {

            nodoAInsertar = union.buscar(actual.getEtiqueta());

            if (nodoAInsertar == null) {
                union.insertar(actual.clonar());
            }
            actual = actual.getSiguiente();
        }
        return union;
    }

    @Override
    public IConjunto<T> interseccion(IConjunto<T> otroConjunto) {

        Nodo<T> actual = this.getPrimero();
        Nodo<T> nodoAInsertar;
        Conjunto<T> interseccion = new Conjunto<T>();

        while (actual != null) {

            nodoAInsertar = otroConjunto.buscar(actual.getEtiqueta());

            if (nodoAInsertar != null) {
                interseccion.insertar(nodoAInsertar.clonar());
            }
            actual = actual.getSiguiente();
        }

        return interseccion;
    }

}
