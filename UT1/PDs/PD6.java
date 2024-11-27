import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Principal {

    public static void imprimirTablero(int largo, int ancho) {
        for (int i = 0; i < largo; i++) {
            for (int j = 0; j < ancho; j++) {
                if ((i + j) % 2 == 0) {
                    System.out.print("# ");
                } else {
                    System.out.print(" #");
                }
            }
            System.out.println();
        }
    }

    public static void leerEntradaArchivo(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            int entero = Integer.parseInt(br.readLine());
            double puntoFlotante = Double.parseDouble(br.readLine());
            String cadena = br.readLine();

            System.out.println("El entero leído es: " + entero);
            System.out.println("El número de punto flotante es: " + puntoFlotante);
            System.out.println("La cadena leída es: \"" + cadena + "\"");
            System.out.println("¡Hola " + cadena + "! La suma de " + entero + " y " + puntoFlotante + " es " + (entero + puntoFlotante) + ".");
            System.out.println("La división entera de " + puntoFlotante + " y " + entero + " es " + (int)(puntoFlotante / entero) + " y su resto es " + (puntoFlotante % entero) + ".");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void transformarTextoT9(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
             FileWriter fw = new FileWriter("src/salida.txt")) {
            String linea;
            while ((linea = br.readLine()) != null) {
                for (char c : linea.toCharArray()) {
                    switch (Character.toLowerCase(c)) {
                        case 'a': case 'b': case 'c':
                            fw.write('2');
                            break;
                        case 'd': case 'e': case 'f':
                            fw.write('3');
                            break;
                        case 'g': case 'h': case 'i':
                            fw.write('4');
                            break;
                        case 'j': case 'k': case 'l':
                            fw.write('5');
                            break;
                        case 'm': case 'n': case 'o':
                            fw.write('6');
                            break;
                        case 'p': case 'q': case 'r': case 's':
                            fw.write('7');
                            break;
                        case 't': case 'u': case 'v':
                            fw.write('8');
                            break;
                        case 'w': case 'x': case 'y': case 'z':
                            fw.write('9');
                            break;
                        case ' ':
                            fw.write('0');
                            break;
                        case '.':
                            fw.write('1');
                            break;
                    }
                }
                fw.write('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Llamar al ejercicio 1
        System.out.println("--- EJERCICIO 1: TABLERO ---");
        imprimirTablero(7, 7);
        
        // Llamar al ejercicio 2
        System.out.println("\n--- EJERCICIO 2: LECTURA DE ARCHIVO ---");
        leerEntradaArchivo("src/entrada.txt");
        
        // Llamar al ejercicio 3
        System.out.println("\n--- EJERCICIO 3: TRANSFORMACIÓN T9 ---");
        transformarTextoT9("src/entrada.txt");
    }
}
