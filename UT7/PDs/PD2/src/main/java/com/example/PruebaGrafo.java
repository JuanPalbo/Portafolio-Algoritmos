package com.example;

public class PruebaGrafo {

    public static void main(String[] args) {

        /* ej1 */
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("vertices.txt", "aristas.txt", false,
                TGrafoDirigido.class);

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");

        /* ej2 */

        Double[][] floyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(floyd, gd.getVertices(), "Matriz Floyd");

        /* ej3 */
        Object[] etiquetasarray = gd.getEtiquetasOrdenado();
        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println("excentricidad de " + etiquetasarray[i] + " : "
                    + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
        }
        System.out.println();
        System.out.println("Centro del grafo: " + gd.centroDelGrafo());

    }
}
