import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TArbolTrie implements IArbolTrie {

    private TNodoTrie raiz;

    @Override
    public void insertar(String palabra, int pagina) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        raiz.insertar(palabra, pagina);
    }

    @Override
    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }

    @Override
    public int buscar(String palabra) {
        if (raiz != null) {
            List<Integer> results = raiz.buscar(palabra);
            if (!results.isEmpty()) {
                return results.get(0);
            }
        }
        return -1;  // return a default value if no results
    }

    @Override
    public LinkedList<String> predecir(String prefijo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}