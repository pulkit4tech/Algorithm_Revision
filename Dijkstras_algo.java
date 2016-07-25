/**
 *
 * @author pulkit4tech
 */


//Adjancy matrix solution 


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstras_algo implements Runnable {

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
        new Thread(new Dijkstras_algo()).start();
    }

    void solve() throws Exception {
       dijkstras();
    }
    
    private void dijkstras() {
    	int V = 9;
        Graph g = new Graph(V);
        addEdge(0, 1, 4);
        addEdge(0, 7, 8);
        addEdge(1, 2, 8);
        addEdge(1, 7, 11);
        addEdge(2, 3, 7);
        addEdge(2, 8, 2);
        addEdge(2, 5, 4);
        addEdge(3, 4, 9);
        addEdge(3, 5, 14);
        addEdge(4, 5, 10);
        addEdge(5, 6, 2);
        addEdge(6, 7, 1);
        addEdge(6, 8, 6);
        addEdge(7, 8, 7);
     
        dijkstra_algo(g, 0);
	}
	boolean adjmatrix[][];
    long distance[][];
    
    class Heap{
    	int vertex;
    	long dist;
    }
    PriorityQueue<Heap> getPriorityQueue(int v){
    	PriorityQueue<Heap> pq = new PriorityQueue<>(new Comparator<Heap>() {

			@Override
			public int compare(Heap o1, Heap o2) {
				// TODO Auto-generated method stub
				if(o1.dist>o2.dist)
					return 1;
				else if(o1.dist==o2.dist)
					return 0;
				else
					return -1;
			}
		});
    	
    	return pq;
    }
    
    void dijkstra_algo(Graph g,int src){
    	int V = g.V;
    	long dist [] = new long [V];
    	boolean check[] = new boolean[V]; 
    	
    	Arrays.fill(dist, Long.MAX_VALUE);
    	dist[src] = 0;
    	
    	PriorityQueue<Heap> heap = getPriorityQueue(V);
    	for(int v=0;v<V;v++){
    		Heap hp = new Heap();
    		hp.vertex = v;
    		hp.dist = dist[v];
    		heap.add(hp);
    	}
    	
    	while(!heap.isEmpty()){
    		
    		//extract vertex with min distance
    		Heap min = heap.poll();
    		check[min.vertex] = true;
    		//get adjacent vertex
    		PriorityQueue<Heap> tempheap = getPriorityQueue(heap.size());
    		while(!heap.isEmpty()){
    			Heap temp = heap.poll();
    			if(!check[temp.vertex]&&adjmatrix[min.vertex][temp.vertex]&&
    					distance[min.vertex][temp.vertex]+dist[min.vertex]<dist[temp.vertex]){
    				dist[temp.vertex] = dist[min.vertex] + distance[min.vertex][temp.vertex];
    				temp.dist = dist[temp.vertex];
    			}
    			tempheap.add(temp);
    		}
    		heap = tempheap;
    	}
    	
    	printArr(dist,V);
    }
    
    void printArr(long dist[], int n)
    {
        pout.println("Vertex   Distance from Source");
        for (int i = 0; i < n; ++i)
            pout.print(i+" \t\t "+dist[i]+"\n");
    }
    

    
    class Graph{
    	int V;    	
    	public Graph(int V) {
    		adjmatrix = new boolean[V][V];
    		distance = new long[V][V];
    		this.V = V;
		}
    }
    
    void addEdge(int src,int dest,int wt){
    	
    	adjmatrix[src][dest] = true;
    	adjmatrix[dest][src] = true;
    	distance[src][dest] = wt;
    	distance[dest][src] = wt;
    	
    }
}

