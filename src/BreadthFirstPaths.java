// запуск через зеленую кнопку

/**  % tinyCG.txt 0
 *
 * 6 vertices, 8 edges
 0 to 0 : 0  dist: 0
 0 to 1 : 0-1  dist: 1
 0 to 2 : 0-2  dist: 1
 0 to 3 : 0-5-3  dist: 2
 0 to 4 : 0-2-4  dist: 2
 0 to 5 : 0-5  dist: 1*/


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


import java.util.ArrayDeque;
import java.util.Queue;

public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked; // marked[v] = is there an s-v path?
    private int[] edgeTo; // edgeTo[v] = last edge on s-v path
    private int[] distTo; // distance to v from source s

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        bfs(G, s);
    }

    public void bfs(Graph G, int s) {
        Queue<Integer> q = new ArrayDeque<>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        marked[s] = true;
        distTo[s] = 0;
        q.add(s);
        while (!q.isEmpty()) {
            int v = q.remove();
            for (int w : G.adj(v))
                if (!marked[w]) {
                    marked[w] = true;
                    distTo[w] = distTo[v] + 1;
                    edgeTo[w] = v;
                    q.add(w);
                }
        }

    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);

        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
        StdOut.println(G.V() + " vertices, " + G.E() + " edges");

        for (int v = 0; v < G.V(); v++)
            if (bfs.hasPathTo(v)) {
                StdOut.printf("%d to %d : ", s, v);
                for (int w : bfs.pathTo(v))
                    if (w == s) StdOut.print(w);
                    else        StdOut.print("-" + w);
                StdOut.print("  dist: " +bfs.distTo(v));
                StdOut.println();
            }
            else StdOut.printf("%d to %d:  not connected\n", s, v);
    }
}
