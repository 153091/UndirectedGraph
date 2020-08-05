import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedStack;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;
import java.util.Stack;

public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Stack<Integer>[] adj;



    // create an empty Graph with V vertices
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V;
        this.E = 0;
        adj = (Stack<Integer>[]) new Stack[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Stack<>();
    }

    /**try – определяет блок кода, в котором может произойти исключение;
     catch – определяет блок кода, в котором происходит обработка исключения; */

    // create a Graph from input stream
    public Graph(In in) {
        if (in == null) throw new IllegalArgumentException("argument is null");
        try {
            this.V = in.readInt();
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            adj = (Stack<Integer>[]) new Stack[V];
            for (int v = 0; v < V; v++)
                adj[v] = new Stack<Integer>();

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
        adj[v].push(w);
        adj[w].push(v);
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
