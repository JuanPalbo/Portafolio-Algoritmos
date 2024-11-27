package ut6.ut6_pd1;

public class CompararImplementaciones {

    public static void main(String[] args) {
        TNodoTrie trieOriginal = new TNodoTrie();
        TNodoTrie trieConHashMap = new TNodoTrie();
        
        String[] palabras = {"ejemplo", "trie", "autocompletar", "texto", "predecir"};
        
        // Insertar palabras en ambas implementaciones
        for (String palabra : palabras) {
            trieOriginal.insertar(palabra);
            trieConHashMap.insertar(palabra);
        }
        
        // Medir tiempo de búsqueda en trie original
        long inicioOriginal = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            for (String palabra : palabras) {
                trieOriginal.buscar(palabra);
            }
        }
        long finOriginal = System.nanoTime();
        long tiempoOriginal = finOriginal - inicioOriginal;
        
        // Medir tiempo de búsqueda en trie con HashMap
        long inicioHashMap = System.nanoTime();
        for (int i = 0; i < 1000000; i++) {
            for (String palabra : palabras) {
                trieConHashMap.buscar(palabra);
            }
        }
        long finHashMap = System.nanoTime();
        long tiempoHashMap = finHashMap - inicioHashMap;
        
        System.out.println("Tiempo de ejecución (trie original): " + tiempoOriginal + " nanosegundos.");
        System.out.println("Tiempo de ejecución (trie con HashMap): " + tiempoHashMap + " nanosegundos.");
    }
}

