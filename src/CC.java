// через ЗК

/**
 * % java CC tinyG.txt
 *  3 components
 *  0 1 2 3 4 5 6
 *  7 8
 *  9 10 11 12*/


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.Queue;

public class CC {
    private int[] id;
    private boolean[] marked; // marked[v] = is there an s-v path?
    private int count; // number of connected components

    //constructor
    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        count = 0;
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
    }

    // number of CC
    public int count() {
        return count;
    }

    // component identifier for v
    public int id(int v) {
        return id[v];
    }

    //are v and w connected?
    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    // depth-first search
    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v))
            if (!marked[w])
                dfs(G, w);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        CC cc = new CC(G);

        // number of connected components
        StdOut.println(cc.count() + " components");

        // compute list of vertices in each cc
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[cc.count()];
        for (int i = 0; i < cc.count(); i++)
            components[i] = new LinkedList<>();
        for (int v = 0; v < G.V(); v++)
            components[cc.id(v)].add(v);

        // print results
        for (int i = 0; i < cc.count(); i++) {
            for (int v : components[i])
                StdOut.print(v + " ");
            StdOut.println();
        }
    }
}
