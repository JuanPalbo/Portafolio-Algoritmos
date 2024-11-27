package ut6.ut6_pd1;

import java.util.HashMap;
import java.util.LinkedList;

public class TNodoTrie implements INodoTrie {

    private HashMap<Character, TNodoTrie> hijos;
    private boolean esPalabra;

    public TNodoTrie() {
        hijos = new HashMap<>();
        esPalabra = false;
    }

    @Override
    public void insertar(String unaPalabra) {
        TNodoTrie nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            char caracter = unaPalabra.charAt(c);
            nodo.hijos.putIfAbsent(caracter, new TNodoTrie());
            nodo = nodo.hijos.get(caracter);
        }
        nodo.esPalabra = true;
    }

    private void imprimir(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);
            }
            for (Character caracter : nodo.hijos.keySet()) {
                imprimir(s + caracter, nodo.hijos.get(caracter));
            }
        }
    }

    @Override
    public void imprimir() {
        imprimir("", this);
    }

    private TNodoTrie buscarNodoTrie(String s) {
        TNodoTrie nodo = this;
        for (int c = 0; c < s.length(); c++) {
            char caracter = s.charAt(c);
            if (!nodo.hijos.containsKey(caracter)) {
                return null;
            }
            nodo = nodo.hijos.get(caracter);
        }
        return nodo;
    }

    private void predecir(String s, LinkedList<String> palabras, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(s);
            }
            for (Character caracter : nodo.hijos.keySet()) {
                predecir(s + caracter, palabras, nodo.hijos.get(caracter));
            }
        }
    }

    @Override
    public void predecir(String prefijo, LinkedList<String> palabras) {
        TNodoTrie nodo = buscarNodoTrie(prefijo);
        predecir(prefijo, palabras, nodo);
    }

    @Override
    public int buscar(String s) {
        TNodoTrie nodo = buscarNodoTrie(s);
        return nodo != null && nodo.esPalabra ? 1 : 0;
    }
}
