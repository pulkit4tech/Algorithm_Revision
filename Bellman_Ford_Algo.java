
/**
*
* @author pulkit4tech
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

// Bellman Ford Algorithm for src to all vertices
// shortest distance even with negative wt. (not possible with dijkstra)
class Bellman_Ford_Algo implements Runnable {

	BufferedReader c;
	PrintWriter pout;
	// static long mod = 1000000007;

	public void run() {
		try {
			c = new BufferedReader(new InputStreamReader(System.in));
			pout = new PrintWriter(System.out, true);
			solve();
			pout.close();
		} catch (Exception e) {
			pout.close();
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String[] args) throws Exception {
		new Thread(new Bellman_Ford_Algo()).start();
	}

	void solve() throws Exception {
		bellmanford();
	}

	private void bellmanford() {
		Graph g = new Graph(5, 8);
		g.addEdge(0, 1, -1);
		g.addEdge(0, 2, 4);
		g.addEdge(1, 2, 3);
		g.addEdge(1, 2, 3);
		g.addEdge(1, 4, 2);
		g.addEdge(3, 2, 5);
		g.addEdge(3, 1, 1);
		g.addEdge(4, 3, -3);
		g.BellmanFord_Algo(g, 0);
	}

	class Graph {
		class Edge {
			int src, dest, wt;

			Edge() {
				src = dest = wt = 0;
			}
		}

		int V, E, countEdge;

		Edge edge[];

		Graph(int v, int e) {
			V = v;
			E = e;
			countEdge = 0;
			edge = new Edge[E];
			for (e--; e >= 0; e--) {
				edge[e] = new Edge();
			}
		}

		void BellmanFord_Algo(Graph g, int src) {
			int V = g.V, E = g.E;
			int dist[] = new int[V];

			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[src] = 0;

			for (int i = 1; i < V; i++) {
				for (int j = 0; j < E; j++) {
					int u = g.edge[j].src;
					int v = g.edge[j].dest;
					int wt = g.edge[j].wt;

					if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
						dist[v] = dist[u] + wt;
					}
				}
			}

			// checking for negative wt cycle

			for (int i = 0; i < E; i++) {
				int u = g.edge[i].src;
				int v = g.edge[i].dest;
				int wt = g.edge[i].wt;

				if (dist[u] != Integer.MAX_VALUE && dist[u] + wt < dist[v]) {
					pout.println("Graph contain negative weight cycle");
				}
			}

			printArr(dist, src);
		}

		void printArr(int dist[], int src) {
			pout.println("Vertex    Distance");
			for (int i = 0; i < V; i++) {
				pout.println(i + "\t\t" + dist[i]);
			}
		}

		void addEdge(int src, int dest, int wt) {
			if (countEdge < E) {
				edge[countEdge].src = src;
				edge[countEdge].dest = dest;
				edge[countEdge].wt = wt;
				countEdge++;
			}
		}
	}
}
