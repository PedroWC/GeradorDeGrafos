/*
Pedro Henrique Weber Carvalhaes
Ralson Rwan dos Santos Lima
 */

import java.util.*;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

public class GeradorDeGrafos {
    public static void main(String[] args) {
        // Parâmetros iniciais (podem ser lidos de um arquivo ou entrada do usuário)
        int ini = 10; // Número inicial de vértices
        int fim = 200; // Número final de vértices
        int stp = 10; // Incremento
        double p = 0.1; // Probabilidade de adicionar uma aresta

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("V", "E", "gmin", "gmax", "gmed", "diam").setTextAlignment(TextAlignment.CENTER);
        at.addRule();

        // Gerar grafos e calcular propriedades
        for (int n = ini; n <= fim; n += stp) {
            Grafo g = new Grafo(n);
            g.gerarArestasAleatorias(p);
            g.calcularPropriedades();
            g.imprimirPropriedades(at);
        }
        at.addRule();

        System.out.println(at.render());
    }
}