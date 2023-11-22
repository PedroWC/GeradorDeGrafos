/*
Pedro Henrique Weber Carvalhaes
Ralson Rwan dos Santos Lima
 */

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import java.util.*;

class Grafo {
    private final List<Set<Integer>> adjacencias; // Lista de adjacências para representar o grafo
    private final int V; // Número de vértices
    private int E; // Número de arestas
    private int gmin; // Grau mínimo
    private int gmax; // Grau máximo
    private double gmed; // Média dos graus
    private int diam; // Diâmetro do grafo

    public Grafo(int V) {
        this.V = V;
        this.adjacencias = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adjacencias.add(new HashSet<>());
        }
    }

    public void gerarArestasAleatorias(double p) {
        Random rand = new Random();
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (rand.nextDouble() < p) {
                    adjacencias.get(i).add(j);
                    adjacencias.get(j).add(i);
                    E++; // Incrementar o contador de arestas
                }
            }
        }
    }

    public void calcularPropriedades() {
        // Calcular grau min, grau max, média dos graus, e diâmetro do grafo
        this.gmin = Integer.MAX_VALUE;
        this.gmax = Integer.MIN_VALUE;
        int somaDosGraus = 0;

        for (Set<Integer> vizinhos : adjacencias) {
            int grau = vizinhos.size();
            this.gmin = Math.min(gmin, grau);
            this.gmax = Math.max(gmax, grau);
            somaDosGraus += grau;
        }

        this.gmed = somaDosGraus / (double) V;

        calcularDiametro();
    }

    public void calcularDiametro() {
        int diametroTemp = 0;
        for (int i = 0; i < V; i++) {
            int distanciaMaxima = bfs(i);
            diametroTemp = Math.max(diametroTemp, distanciaMaxima);
        }
        this.diam = diametroTemp;
    }

    private int bfs(int origem) {
        Queue<Integer> fila = new LinkedList<>();
        boolean[] visitado = new boolean[V];
        int[] distancia = new int[V];

        fila.add(origem);
        visitado[origem] = true;
        distancia[origem] = 0;

        while (!fila.isEmpty()) {
            int u = fila.remove();
            for (int v : adjacencias.get(u)) {
                if (!visitado[v]) {
                    fila.add(v);
                    visitado[v] = true;
                    distancia[v] = distancia[u] + 1;
                }
            }
        }

        // Retorna a maior distância a partir do vértice de origem
        int distanciaMaxima = 0;
        for (int dist : distancia) distanciaMaxima = Math.max(distanciaMaxima, dist);

        return distanciaMaxima;
    }

    public void imprimirPropriedades(AsciiTable at) {
        at.addRow(V, E, gmin, gmax, String.format("%.2f", gmed), diam).setTextAlignment(TextAlignment.CENTER);
    }
}