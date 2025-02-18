/**
 * %java Graph.java tinyG.txt
 Note: Graph.java uses unchecked or unsafe operations.
 Note: Recompile with -Xlint:unchecked for details.
 13 vertices, 13 edges
 0: 5 1 2 6
 1: 0
 2: 0
 3: 4 5
 4: 3 6 5
 5: 0 4 3
 6: 4 0
 7: 8
 8: 7
 9: 12 10 11
 10: 9
 11: 12 9
 12: 9 11
 */


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;


public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Queue<Integer>[] adj;



    // create an empty Graph with V vertices
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V;
        this.E = 0;
        adj = (Queue<Integer>[]) new Queue[V];
        for (int v = 0; v < V; v++)
            adj[v] = new ArrayDeque<Integer>();
    }

    /**try – определяет блок кода, в котором может произойти исключение;
     catch – определяет блок кода, в котором происходит обработка исключения; */

    // create a Graph from input stream
    public Graph(In in) {
        if (in == null) throw new IllegalArgumentException("argument is null");
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            adj = (Queue<Integer>[]) new Queue[V];
            for (int v = 0; v < V; v++)
                adj[v] = new ArrayDeque<Integer>();

            int E = in.readInt();
            if (E < 0) throw new IllegalArgumentException("Number of Edges must be non negative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                addEdge(v, w);
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    // add en edge v-w
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    // return number of Vertices
    public int V() {
        return V;
    }

    // return number of Edges
    public int E() {
        return E;
    }

    // iterator for adjacent vertices
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    // degree of given vertex
    public int degree(int v) {
        return adj[v].size();
    }

    // string representations
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v])
                s.append(w + " ");
            s.append(NEWLINE);
        }
        return s.toString();
    }


    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        StdOut.println(G);
    }
}
