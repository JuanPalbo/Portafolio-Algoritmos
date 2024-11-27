import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TNodoTrie implements INodoTrie {

    private static final int CANT_CHR_ABECEDARIO = 26;
    private TNodoTrie[] hijos;
    private boolean esPalabra;
    private List<Integer> paginas;

    public TNodoTrie() {
        hijos = new TNodoTrie[CANT_CHR_ABECEDARIO];
        esPalabra = false;
        paginas = new ArrayList<>();
    }

    @Override
    public void insertar(String unaPalabra, int pagina) {
        TNodoTrie nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            int indice = unaPalabra.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrie();
            }
            nodo = nodo.hijos[indice];
        }
        nodo.esPalabra = true;
        nodo.paginas.add(pagina);
    }

    public void indizarLibro(String nombreArchivo) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int numLinea = 0;
            while ((linea = reader.readLine()) != null) {
                for (String palabra : linea.split("\\W+")) {
                    insertar(palabra.toLowerCase(), numLinea / 50 + 1);
                }
                numLinea++;
            }
        }
    }

    private void imprimirIndice(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.print(s + ": ");
                for (int pagina : nodo.paginas) {
                    System.out.print(pagina + " ");
                }
                System.out.println();
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    imprimirIndice(s + (char) (c + 'a'), nodo.hijos[c]);
                }
            }
        }
    }

    public void imprimir() {
        imprimirIndice("", this);
    }

    private TNodoTrie buscarNodoTrie(String s) {
        TNodoTrie nodo = this;

        // implementar

        return nodo;
    }

    private void predecir(String s, String prefijo, LinkedList<String> palabras, TNodoTrie nodo) {
        // implementar

    }

    @Override
    public void predecir(String prefijo, LinkedList<String> palabras) {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
                                                                       // Tools | Templates.
    }

    @Override
    public List<Integer> buscar(String s) {
        TNodoTrie nodoActual = this;
        TNodoTrie unHijo = null;

        for (char car : s.toCharArray()) {

            unHijo = nodoActual.hijos[this.obtenerHijos(car)];

            if (unHijo == null) {
                // salir del for
                return null;

            } else {
                nodoActual = unHijo;
            }
        }

        if (nodoActual.esPalabra) {
            return new ArrayList<>(nodoActual.paginas);
        }

        return null;
    }

    private int obtenerHijos(char car) {
        int indice = car - 'a';
        return indice;
    }
}