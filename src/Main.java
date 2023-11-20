import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Grafo ponderadoNodir;
        System.out.print("digite el número de vertices: ");
        int numero = sc.nextInt();

        ponderadoNodir = new Grafo(numero);
        ponderadoNodir.ponderadoNODir();
        System.out.println(ponderadoNodir);

        System.out.println();

        System.out.print("digite el vertice a empezar: ");
        int vertice = sc.nextInt();
        System.out.print("digite el vertice fin: ");
        int fin = sc.nextInt();


        Dijkstra dij = new Dijkstra(999, "",vertice,fin);
        System.out.println(ponderadoNodir.dijkstra(vertice, fin, 0,"", dij));

    }
}