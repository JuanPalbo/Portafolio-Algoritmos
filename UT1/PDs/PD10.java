package uy.edu.ucu.aed;

import java.util.ArrayList;

public class PD10 {

    // EJERCICIO 1:
    public static String[] palabrasComunes(String[] palabras1, String[] palabras2) {
        ArrayList<String> comunes = new ArrayList<>();

        for (String palabra1 : palabras1) {
            for (String palabra2 : palabras2) {
                if (palabra1.equals(palabra2) && !comunes.contains(palabra1)) {
                    comunes.add(palabra1);
                }
            }
        }


        return comunes.toArray(new String[0]);
    }

    public static void main(String[] args) {
        // Ejemplo palabrasComunes
        String[] ar1 = {"Hola", "mundo", "de", "los", "algoritmos"};
        String[] ar2 = {"Hola", "mundo", "de", "la", "informática"};
        String[] resultado = palabrasComunes(ar1, ar2);

        System.out.println("Palabras comunes:");
        for (String palabra : resultado) {
            System.out.println(palabra);
        }
    }
}

