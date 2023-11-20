import java.util.Scanner;

public class Grafo {
    private int[][] aristas;
    private int[][] copiris;
    Scanner sc = new Scanner(System.in);

    public Grafo(int n) {
        this.aristas = new int[n][n];
        this.copiris = new int[n][n];
    }

    public int[][] getCopiris() {
        return copiris;
    }

    public void setCopiris(int[][] copiris) {
        this.copiris = copiris;
    }
    public void ponderadoNODir() {
        //System.out.println("digite el valor de las lineas digite \"s\" en caso haya relacion");
        for (int i = 0; i < aristas.length; i++) {
            for (int j = i; j < aristas[0].length; j++) {
                System.out.print("hay relacion entre "+(i+1)+" y "+(j+1)+" ? ");
                String relacion = sc.next();
                try {
                    aristas[i][j]= aristas[j][i] =  Integer.parseInt(relacion);
                    copiris[i][j]= copiris[j][i] =  Integer.parseInt(relacion);
                } catch (NumberFormatException e) {
                    // No hace nada o màs bien, se salta el error
                }
            }
        }
        System.out.println();
    }
    public String toString() {
        String s = "";
        for (int i = 0; i < aristas.length; i++) {
            for (int j = 0; j < aristas[0].length; j++) {
                s+=aristas[i][j]+"\t";
            }
            s+="\n";
        }
        return s ;
    }
    public void volverLlenar() {
        for (int i = 0; i < aristas.length; i++) {
            for (int j = i; j < aristas[0].length; j++) {
                copiris[i][j]= copiris[j][i] = aristas[i][j];
            }
        }
    }
    public Dijkstra dijkstra(int vertice, int fin, int trae,String suple, Dijkstra a) {
        if (copiris[vertice][fin]!=0 && a.getCantidad()>copiris[vertice][fin]+trae) {
            a.setCantidad(copiris[vertice][fin]+trae);
            copiris[vertice][fin]=copiris[fin][vertice]=0;
            a.setRecorrido(suple);
        }

        for (int i = 0; i < copiris.length; i++) {	//for en 1
            if (copiris[vertice][i]!=0 && a.getCantidad()>(copiris[vertice][i]+trae) ) {
                int tempo =	copiris[vertice][i]+trae;
                copiris[vertice][i] = copiris[i][vertice] = 0;//eliminando
                String suple2= suple+" "+i+" ";			//reemplaza valores
                a=dijkstra(i, fin, tempo,suple2, a);
            }
        }
        volverLlenar();
        return a;
    }
}
