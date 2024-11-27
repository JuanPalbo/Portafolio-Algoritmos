
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ernesto
 */
public interface INodoTrie {

    List<Integer> buscar(String s); // devuelve la cantidad de comparaciones!
    void imprimir();

    void insertar(String unaPalabra, int pagina);
    public void predecir(String prefijo, LinkedList<String> palabras);
    
    
}
