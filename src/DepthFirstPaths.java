// через зеленую кнопку
/**
 * % G 0
 * 0 to 0 :   0
 * 0 to 1 :   0-5-3-2-1
 * 0 to 2 :   0-5-3-2
 * 0 to 3 :   0-5-3
 * 0 to 4 :   0-5-3-2-4
 * 0 to 5 :   0-5*/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;


public class DepthFirstPaths {
    private boolean[] marked; // marked[v] = is there an s-v path?
    private int[] edgeTo;  // edgeTo[v] = last edge on s-v path
    private final int s; // source vertex

    // constructor
    public DepthFirstPaths(Graph G, int s) {
    this.s = s;
    marked = new boolean[G.V()];
    edgeTo = new int[G.V()];
    //recursive search
    dfs(G, s);
    }

    // recursive search of connected vertices to s
    public void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
    }

    // is there path from s to v?
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    // path from s to v
    // null if no path
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = edgeTo[w])
            path.push(w);
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstPaths dfp = new DepthFirstPaths(G, s);

        for (int v = 0; v < G.V(); v++)
            if (dfp.hasPathTo(v)) {
                StdOut.printf("%d to %d :   ", s, v);
                for (int w : dfp.pathTo(v))
                    if (w == s) StdOut.print(w);
                    else        StdOut.print("-" +w);

            StdOut.println();
            }
            else StdOut.printf("%d to %d:  not connected\n", s, v);
    }
}
