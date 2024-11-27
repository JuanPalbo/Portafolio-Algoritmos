package ut6.ut6_pd5;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        TArbolTrieTelefonos trieAbonados = new TArbolTrieTelefonos();

        // Cargar los abonados desde el archivo "abonados.txt"
        String[] lineas = ManejadorArchivosGenerico.leerArchivo("abonados.txt");
        for (String linea : lineas) {
            String[] datos = linea.split(",");
            if (datos.length == 2) {
                String nombre = datos[0].trim();
                String telefono = datos[1].trim();
                TAbonado abonado = new TAbonado(nombre, telefono);
                trieAbonados.insertar(abonado);
            }
        }

        String codigoPais = "598"; // Utilizar el indicado en el archivo "codigos.txt"
        String codigoArea = "93";  // Utilizar el indicado en el archivo "codigos.txt"
        LinkedList<TAbonado> abonados = trieAbonados.buscarTelefonos(codigoPais, codigoArea);

        // Crear el archivo "salida.txt" con los abonados correspondientes al país y área
        String[] salida = new String[abonados.size()];
        int index = 0;
        for (TAbonado abonado : abonados) {
            salida[index++] = abonado.getNombre() + ": " + abonado.getTelefono();
        }
        ManejadorArchivosGenerico.escribirArchivo("salida.txt", salida);
    }
}
