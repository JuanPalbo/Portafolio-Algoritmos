public class Main {

    public static void main(String[] args){
        TArbolTrie trie = new TArbolTrie();

        // Leer las palabras del archivo "PalabrasIndice.txt" y construir el trie
        String[] palabrasIndice = ManejadorArchivosGenerico.leerArchivo("./src/PalabrasIndice.txt");
        for (String palabra : palabrasIndice) {
            trie.insertar(palabra, 0);  
        }

        // Leer el libro del archivo "libro.txt" y indizarlo usando el trie
        String[] libro = ManejadorArchivosGenerico.leerArchivo("./src/libro.txt");
        for (int i = 0; i < libro.length; i++) {
            String[] palabras = libro[i].split("\\s+");
            for (String palabra : palabras) {
                trie.insertar(palabra, i + 1); 
            }
        }

        // Imprimir el índice
        trie.imprimir();
    }
}