import EstructurasDeDAtos.*;

public class DFS {
    private GrafoMA grafo;
    private int tiempo;
    private int[] tiempoExploracion;
    private int[] tiempoFinalizacion;
    private char[] marcas;
    private int[] vérticeAnterior;

    public DFS(GrafoMA grafo) {
        this.grafo = grafo;
        tiempo = 0;
        tiempoExploracion = new int[grafo.cantNodos];
        tiempoFinalizacion = new int[grafo.cantNodos];
        marcas = new char[grafo.cantNodos];
        vérticeAnterior = new int[grafo.cantNodos];

        for (int i = 0; i < grafo.cantNodos; i++) {
            marcas[i] = 'A';
        }
    }

    private void DFS_FOREST(GrafoMA g) {
       IConjunto vertices = g.vertices();
       IConjunto verticesAux = new Conjunto();
        verticesAux.inicializar();

        while (!vertices.conjuntoVacio()) {
            int v = vertices.elegir();
            marcas[v] = 'A';
            vérticeAnterior[v] = 0;
            vertices.sacar(v);
            verticesAux.agregar(v);
        }

        tiempo = 0;
        while (!verticesAux.conjuntoVacio()) {
            int v2 = verticesAux.elegir();
            verticesAux.sacar(v2);
            if (marcas[v2] == 'A') {
                DFS(g, v2);
            }
        }
    }

    private IConjunto adyacentes(int vertice) {
        IConjunto adyacentes = new Conjunto();
        adyacentes.inicializar();

       IConjunto todosVertices = grafo.vertices();
        while (!todosVertices.conjuntoVacio()) {
            int i = todosVertices.elegir();
            todosVertices.sacar(i);

            if (grafo.existeArista(vertice, i)) {
                adyacentes.agregar(i);
            }
        }
        return adyacentes;
    }


    private void DFS(GrafoMA g, int origen) {
        tiempo = tiempo + 1;
        tiempoExploracion[origen] = tiempo;
        marcas[origen] = 'i';
        IConjunto ady = adyacentes(origen);
        System.out.println("Visitando: " + origen);
        while (!ady.conjuntoVacio()) {
            int v = ady.elegir();
            ady.sacar(v);
            if (marcas[v] == 'A') {
                vérticeAnterior[v] = origen;
                DFS(g, v);
            }
        }
        marcas[origen] = 'h';
        tiempo = tiempo + 1;
        tiempoFinalizacion[origen] = tiempo;
    }

    public static void main(String[] args) {
        GrafoMA grafo = new GrafoMA();
        grafo.inicializarGrafo();
        grafo.agregarVertice(0);
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarVertice(5);
        grafo.agregarVertice(6);
        grafo.agregarVertice(7);


        grafo.agregarArista(1,2, 8);
        grafo.agregarArista(2,3, 7);
        grafo.agregarArista(3, 4, 2);
        grafo.agregarArista(4,6, 20);
        grafo.agregarArista(0,5, 11);
        grafo.agregarArista(5,0, 7);
        grafo.agregarArista(7,6, 7);
        grafo.agregarArista(5,7, 17);
        grafo.agregarArista(0,7, 4);


        DFS recoorido = new DFS(grafo);
        recoorido.DFS_FOREST(grafo);
    }
}