import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author pulkit4tech
 */
public class Graph_Traversal implements Runnable {

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
        new Thread(new Graph_Traversal()).start();
    }

    void solve() throws Exception {
	// For Referenced (GeekforGeek Solution) 	
    	breadthFirst();
    }
    
    void breadthFirst(){
    	Graph g = new Graph(4);
    	g.addEdge(0, 1);
    	g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
  
        pout.println("Following is Breadth First Traversal "+
                            "(starting from vertex 2)");
  
         g.BFS(2);
    }
    
    class Graph{
    	int V;
    	LinkedList<Integer> adj[];
    	
    	Graph(int v){
    		V = v;
    		adj = new LinkedList[v];
    		for(int i=0;i<v;i++)
    			adj[i] = new LinkedList<>();
    	}
    	
    	void addEdge(int v,int w){
    		adj[v].add(w);
    	}
    	
    	void BFS(int s){
    		boolean visited[] = new boolean[V];
    		Queue<Integer> queue = new LinkedList<>();
    		visited[s] = true;
    		queue.add(s);
    		
    		while(queue.size()!=0){
    			s = queue.poll();
    			pout.print(s+" ");
    			
    			// Iterate over adjacent vertex
    			Iterator<Integer> it = adj[s].iterator();
    			while(it.hasNext()){
    				int n = it.next();
    				if(!visited[n]){
    					visited[n] = true;
    					queue.add(n); 
    				}
    			}
    		}
    	}
    }
}

