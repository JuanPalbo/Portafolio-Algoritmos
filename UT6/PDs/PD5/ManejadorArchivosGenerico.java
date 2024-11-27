package ut6.ut6_pd5;

import java.io.*;
import java.util.ArrayList;

public class ManejadorArchivosGenerico {

    public static void escribirArchivo(String nombreCompletoArchivo, String[] listaLineasArchivo) {
        try (FileWriter fw = new FileWriter(nombreCompletoArchivo);
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (String linea : listaLineasArchivo) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo " + nombreCompletoArchivo);
            e.printStackTrace();
        }
    }

    public static String[] leerArchivo(String nombreCompletoArchivo) {
        ArrayList<String> listaLineasArchivo = new ArrayList<>();
        try (FileReader fr = new FileReader(nombreCompletoArchivo);
             BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                listaLineasArchivo.add(linea);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo " + nombreCompletoArchivo);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + nombreCompletoArchivo);
            e.printStackTrace();
        }
        return listaLineasArchivo.toArray(new String[0]);
    }
}
