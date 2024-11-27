import java.util.LinkedList;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        TArbolTrieTelefonos trieAbonados = new TArbolTrieTelefonos();
        String[] file = ManejadorArchivosGenerico.leerArchivo("src\\abonados.txt");
        String[] linea;

        for (String string : file) {
            linea = string.split(",");
            TAbonado nuevoAbonado = new TAbonado(linea[1], linea[0]);
            trieAbonados.insertar(nuevoAbonado);
        }

        String codigoPais = "598"; // utilizar el indicado en el archivo "codigos.txt"
        String codigoArea = "93";// utilizar el indicado en el archivo "codigos.txt"
        LinkedList<TAbonado> ab = trieAbonados.buscarTelefonos(codigoPais, codigoArea);

        for (TAbonado tAbonado : ab) {
            System.out.println(tAbonado);
            String[] lineaEscrita = { tAbonado.getNombre() + "," + tAbonado.getTelefono() };
            ManejadorArchivosGenerico.escribirArchivo("src\\salida.txt", lineaEscrita);
        }
    }
}