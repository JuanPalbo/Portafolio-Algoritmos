
import java.util.*;

public class TVertice<T> implements IVertice, IVerticeKevinBacon {

    private Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;
    private int numBacon=0;
    private int num_bajo=0;
    private int num_bp=0;
    private TVertice predecesor;


    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
        datos = null;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

    public T getDatos() {
        return datos;
    }

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        TAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
        }
        return null;
    }

    @Override
    public void bpf(Collection<TVertice> visitados) {
        setVisitado(true);
        visitados.add(this);
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {
                vertAdy.bpf(visitados);
            }
        }
    }

    /**
     * Este método se utiliza para encontrar todos los caminos desde el vértice actual hasta el vértice de destino.
     * Utiliza un enfoque de búsqueda en profundidad para recorrer el grafo.
     *
     * @param etVertDest La etiqueta del vértice de destino.
     * @param caminoPrevio El camino previo que se ha recorrido.
     * @param todosLosCaminos Una colección de todos los caminos encontrados hasta ahora.
     * @return Una colección de todos los caminos desde el vértice actual hasta el vértice de destino.
     */
    @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        // Marcar el vértice actual como visitado
        setVisitado(true);
        // Iterar sobre todos los vértices adyacentes
        for (TAdyacencia adyacente : getAdyacentes()) {
            TVertice destino = adyacente.getDestino();
            // Si el vértice adyacente no ha sido visitado
            if (!destino.getVisitado()) {
                // Si el vértice adyacente es el destino
                if (adyacente.getDestino().getEtiqueta().equals(etVertDest)) {
                    // Copiar el camino previo y agregar la adyacencia actual a él
                    TCamino caminoCopia = caminoPrevio.copiar();
                    caminoCopia.agregarAdyacencia(adyacente);
                    // Agregar el nuevo camino a la colección de todos los caminos
                    todosLosCaminos.getCaminos().add(caminoCopia);
                } else {
                    // Si el vértice adyacente no es el destino, agregar la adyacencia al camino previo
                    caminoPrevio.agregarAdyacencia(adyacente);
                    // Llamar recursivamente al método para el vértice adyacente
                    destino.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
                    // Eliminar la adyacencia actual del camino previo
                    caminoPrevio.eliminarAdyacencia(adyacente);
                }
            }
        }
        // Marcar el vértice actual como no visitado
        setVisitado(false);
        // Devolver la colección de todos los caminos
        return todosLosCaminos;
    }
    /**
     * Este método implementa el algoritmo de búsqueda en anchura (BFS) en un grafo.
     * BFS es un algoritmo de recorrido donde debes comenzar a recorrer desde un nodo seleccionado (nodo fuente o inicial)
     * y recorrer el grafo por capas, explorando así los nodos vecinos (nodos que están directamente conectados al nodo fuente).
     * Luego debes moverte hacia los nodos vecinos del siguiente nivel.
     *
     * @param visitados Esta es una colección de vértices que han sido visitados por el algoritmo BFS.
     */
    @Override
    public void bea(Collection<TVertice> visitados) {
        // Marca el nodo actual como visitado y lo encola
        this.setVisitado(true);
        LinkedList<TVertice> queue = new LinkedList<>();
        queue.addLast(this);
        visitados.add(this);

        while (!queue.isEmpty()) {
            // Desencola un vértice de la cola y lo imprime
            TVertice x = queue.removeFirst();
            LinkedList<TAdyacencia> xAdyacentes = x.getAdyacentes();

            // Obtiene todos los vértices adyacentes del vértice desencolado x
            // Si un adyacente no ha sido visitado, entonces lo marca como visitado y lo encola
            for (TAdyacencia v : xAdyacentes) {
                if (!v.getDestino().getVisitado()) {
                    v.getDestino().setVisitado(true);
                    queue.addLast(v.getDestino());
                    visitados.add(v.getDestino());
                }
            }
        }
    }
    @Override
    public int getBacon() {
        return this.numBacon;
    }

    @Override
    public void setBacon(int newBacon) {
        this.numBacon = newBacon;
    }

    /**
     * Este método calcula el número de Bacon para cada vértice en el grafo.
     * El número de Bacon de un vértice es la longitud del camino más corto desde el vértice actual hasta ese vértice.
     * Utiliza un enfoque de Búsqueda en Anchura (BFS) para recorrer el grafo.
     * Marca el vértice actual como visitado, lo añade a la cola y establece su número de Bacon a 0.
     * Luego, mientras la cola no esté vacía, desencola un vértice, obtiene todos sus vértices adyacentes y, para cada vértice adyacente que no ha sido visitado,
     * lo marca como visitado, lo añade a la cola y establece su número de Bacon al número de Bacon del vértice actual más uno.
     */
    public void numBacon() {
        // Marca el vértice actual como visitado y lo añade a la cola
        setVisitado(true);
        LinkedList<TVertice> cola = new LinkedList<>();
        cola.addLast(this);
        this.setBacon(0);

        while (!cola.isEmpty()) {
            // Desencola un vértice de la cola
            TVertice x = cola.removeFirst();
            LinkedList<TAdyacencia> adyacentesX = x.getAdyacentes();

            int baconActual = x.getBacon();

            // Obtiene todos los vértices adyacentes del vértice desencolado x
            // Si un adyacente no ha sido visitado, entonces lo marca como visitado y lo añade a la cola
            for (TAdyacencia v : adyacentesX) {
                TVertice destino = v.getDestino();
                if (!destino.getVisitado()) {
                    destino.setVisitado(true);
                    cola.addLast(v.getDestino());
                    destino.setBacon(baconActual + 1);
                }
            }
        }
    }
    /**
     * Este método realiza una Búsqueda en Profundidad (DFS) en el grafo para generar una ordenación topológica de los vértices.
     * Una ordenación topológica es una ordenación lineal de sus vértices tal que para cada arista dirigida (u, v) del vértice u al vértice v, u viene antes que v en la ordenación.
     * El método utiliza un enfoque recursivo para recorrer el grafo.
     * Añade el vértice actual al conjunto de vértices visitados y luego itera sobre todos los vértices adyacentes.
     * Si un vértice adyacente no ha sido visitado, llama recursivamente al método sobre él.
     * Después de visitar todos los vértices adyacentes, el vértice actual se empuja a la pila, que finalmente contendrá la ordenación topológica de los vértices.
     *
     * .
     * @param pila Una pila para contener la ordenación topológica de los vértices.
     */
    public void ordenacionTopologicaDFS(Stack<TVertice> pila) {
        setVisitado(true);
        for (TAdyacencia a : this.getAdyacentes()) {
            TVertice w = a.getDestino();
            if (!w.getVisitado()) {
                w.ordenacionTopologicaDFS(pila);
            }
        }
        pila.push(this);
    }
    /**
     * Este método genera todas las posibles ordenaciones topológicas del grafo utilizando Búsqueda en Profundidad (DFS).
     * Una ordenación topológica es una ordenación lineal de sus vértices tal que para cada arista dirigida (u, v) del vértice u al vértice v, u viene antes que v en la ordenación.
     * Se crea un conjunto para llevar un seguimiento de los vértices visitados y una lista para almacenar la ordenación actual.
     * Itera sobre todos los vértices del grafo, y si un vértice no ha sido visitado, llama al método DFS sobre él.
     * Los vértices se añaden a la lista en postorden, lo que significa que un vértice se añade a la lista solo después de que todos sus vecinos hayan sido visitados.
     *
     * @param ordenacionActual Una lista que representa la ordenación actual.
     * @param todasLasOrdenaciones Una lista de listas de vértices, donde cada lista representa una posible ordenación topológica del grafo.
     */
    public void todasLasOrdenacionesTopologicasDFS(List<TVertice> ordenacionActual, List<List<TVertice>> todasLasOrdenaciones) {
        setVisitado(true);
        if (!ordenacionActual.contains(this)) {
            ordenacionActual.add(this);
            if (ordenacionActual.size() == this.getAdyacentes().size()) {
                todasLasOrdenaciones.add(new ArrayList<>(ordenacionActual));
            } else {
                for (TAdyacencia a : this.getAdyacentes()) {
                    TVertice w = a.getDestino();
                    if (!ordenacionActual.contains(w)) {
                        w.todasLasOrdenacionesTopologicasDFS(ordenacionActual, todasLasOrdenaciones);
                    }
                }
            }
            ordenacionActual.remove(this);
        }
    }


    /**
     * Este método implementa el algoritmo de Búsqueda en Profundidad (DFS) para detectar ciclos en un grafo.
     * Utiliza un enfoque recursivo para recorrer el grafo.
     *
     * @param enPila Un mapa que realiza un seguimiento de los vértices que están actualmente en la pila de recursión.
     *               La clave es la etiqueta del vértice y el valor es un booleano que indica si el vértice está en la pila.
     * @param vertices Un mapa de todos los vértices en el grafo. La clave es la etiqueta del vértice y el valor es el objeto vértice.
     * @return true si se detecta un ciclo en el grafo, false en caso contrario.
     */
    public boolean dfsCiclo(Map<Comparable, Boolean> enPila, Map<Comparable, TVertice> vertices) {
        // Marca el vértice actual como visitado y lo añade a la pila de recorrido
        setVisitado(true);
        enPila.put(this.getEtiqueta(), true);

        // Para cada vértice adyacente al vértice actual
        for (TAdyacencia adyacente : this.getAdyacentes()) {
            TVertice verticeAdyacente = vertices.get(adyacente.getEtiqueta());

            // Si el vértice adyacente no ha sido visitado, llama recursivamente a la función dfsCiclo
            if (!verticeAdyacente.getVisitado()) {
                if (verticeAdyacente.dfsCiclo(enPila, vertices)) {
                    return true;
                }
            }
            // Si el vértice adyacente ha sido visitado y está en la pila de recorrido, entonces hay un ciclo en el grafo
            else if (enPila.getOrDefault(adyacente.getEtiqueta(), false)) {
                return true;
            }
        }

        // Quita el vértice actual de la pila de recorrido
        enPila.put(this.getEtiqueta(), false);

        return false;
    }
    /**
     * Este método realiza una Búsqueda en Profundidad (DFS) en el grafo para generar una ordenación topológica de los vértices.
     * Una ordenación topológica es una ordenación lineal de sus vértices tal que para cada arista dirigida (u, v) del vértice u al vértice v, u viene antes que v en la ordenación.
     * El método utiliza un enfoque recursivo para recorrer el grafo.
     * Añade el vértice actual al conjunto de vértices visitados y luego itera sobre todos los vértices adyacentes.
     * Si un vértice adyacente no ha sido visitado, llama recursivamente al método sobre él.
     * Después de visitar todos los vértices adyacentes, el vértice actual se añade al principio de la lista, que finalmente contendrá la ordenación topológica de los vértices.
     *
     *
     * @param lista Una lista para contener la ordenación topológica de los vértices.
     */
    public void ordenacionTopologicaDFS_Lista(LinkedList<TVertice> lista) {
        setVisitado(true);
        for (TAdyacencia a : this.getAdyacentes()) {
            TVertice w = a.getDestino();
            if (!w.getVisitado()) {
                w.ordenacionTopologicaDFS_Lista(lista);
            }
        }
        lista.addFirst(this);
    }

    /**
     * Este método verifica si existe un camino desde el vértice actual hasta el vértice de destino proporcionado.
     * Marca el vértice actual como visitado y luego itera sobre todos los vértices adyacentes.
     * Si un vértice adyacente no ha sido visitado, verifica si es el vértice de destino.
     * Si es el vértice de destino, el método devuelve verdadero, indicando que existe un camino.
     * Si no es el vértice de destino, el método se llama a sí mismo recursivamente en el vértice adyacente.
     * Si la llamada recursiva devuelve verdadero, el método devuelve verdadero.
     * Si no se encuentra ningún camino después de verificar todos los vértices adyacentes, el método devuelve falso.
     *
     * @param destino El vértice de destino.
     * @return verdadero si existe un camino desde el vértice actual hasta el vértice de destino, falso en caso contrario.
     */
    public boolean conectadoCon(TVertice destino) {
        setVisitado(true);
        for (TAdyacencia a : getAdyacentes()){
            if(!a.getDestino().getVisitado()){
                if(a.getDestino().equals(destino)){
                    return true;
                }else{
                    boolean resultDes = a.getDestino().conectadoCon(destino);
                    if(resultDes){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public Double costoAunaAdy(Comparable etiquetaVertice){
        for(TAdyacencia ady : this.adyacentes){
            if(ady.getEtiqueta().equals(etiquetaVertice))
                return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }
    public Double costoAOtroVertice(LinkedList<Comparable> vertices, ArrayList<Double> D, Comparable etiquetaVertice){
        return D.get(vertices.indexOf(etiquetaVertice));
    }
    public void articulacion(LinkedList<TVertice> puntosDeArticulacion, int[] contador) {
        this.setVisitado(true);
        LinkedList<TVertice> hijos = new LinkedList<TVertice>();
        this.num_bp = contador[0];
        this.num_bajo = contador[0];
        for(TAdyacencia ady : this.getAdyacentes()) {
            TVertice verticeAdyacente = ady.getDestino();
            if (!verticeAdyacente.visitado) {
                contador[0]++;
                verticeAdyacente.articulacion(puntosDeArticulacion, contador);
                if (this.num_bajo > verticeAdyacente.num_bajo) {
                    this.num_bajo = verticeAdyacente.num_bajo;
                }
                hijos.add(verticeAdyacente);
            } else {
                if (this.num_bajo > verticeAdyacente.num_bp) {
                    this.num_bajo = verticeAdyacente.num_bp;
                }
            }
        }
        if (this.num_bp == 0 && hijos.size() > 1) {
            puntosDeArticulacion.add(this);
        } else {
            if (contador[0]!=0) {
                for(TVertice hijo : hijos) {
                    if (hijo.num_bajo>=this.num_bp) {
                        puntosDeArticulacion.add(this);
                    }
                }
            }
        }
    }


    public TCamino caminoMasCorto(Comparable etVertDest, TCamino caminoPrevio, TCamino caminoFinal, double[] costo, double[] min) {
        setVisitado(true);
        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {
                caminoPrevio.agregarAdyacencia(adyacente);
                costo[0] += adyacente.getCosto();
                if (vertAdy.etiqueta.compareTo(etVertDest) == 0) {
                    if (costo[0] < min[0]) {
                        min[0] = costo[0];
                        caminoFinal.getOtrosVertices().clear();
                        caminoFinal.getOtrosVertices().addAll(caminoPrevio.getOtrosVertices());
                        caminoFinal.setCostoTotal(caminoPrevio.getCostoTotal());
                    }
                } else {
                    vertAdy.caminoMasCorto(etVertDest, caminoPrevio, caminoFinal, costo, min);
                }
                costo[0] -= adyacente.getCosto();
                caminoPrevio.eliminarAdyacencia(adyacente);
            }
        }
        setVisitado(false);
        return caminoFinal;
    }

    public List<TVertice> menosSaltos(Comparable destino) {
        Queue<TVertice> cola = new LinkedList<>();
        List<TVertice> result = new LinkedList<>();
        cola.add(this);
        this.visitado = true;
        loop:
        while (!cola.isEmpty()) {
            TVertice x = cola.remove();
            for (TAdyacencia ady : (LinkedList<TAdyacencia>) x.getAdyacentes()) {
                TVertice y = ady.getDestino();
                if (!y.getVisitado()) {
                    y.setVisitado(true);
                    cola.add(y);
                    y.predecesor = x;
                    if (y.etiqueta.compareTo(destino) == 0) {
                        result.add(0, y);
                        TVertice p = y.predecesor;
                        while (p.etiqueta.compareTo(this.etiqueta) != 0) {
                            result.add(0, p);
                            p = p.predecesor;
                        }
                        result.add(0, this);
                        break loop;
                    }
                }
            }
        }
        return result;
    }

}
