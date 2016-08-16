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
public class Graph_Example implements Runnable {

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
        new Thread(new Graph_Example()).start();
    }

    void solve() throws Exception {
	// For Referenced (GeekforGeek Solution) 	
    	breadthFirst();
    	depthFirst();
    	is_there_Cycle();
    }
    
    void is_there_Cycle(){
    	Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
     
        if(g.isCyclic())
            pout.println("\nGraph contains cycle");
        else
            pout.println("\nGraph doesn't contain cycle");
        
    }
    
    void depthFirst(){
    	Graph g = new Graph(4);
    	 
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
 
        pout.println("\nFollowing is Depth First Traversal");
 
        g.DFS();
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
    	
    	boolean isCyclic(){
    		boolean visited[] = new boolean[V];
    		boolean recStack[] = new boolean[V];
    		
    		for(int i=0;i<V;i++)
    			if(isCyclicUtil(i,visited,recStack))
    				return true;
    		
    		return false;
    	}
    	
    	boolean isCyclicUtil(int v,boolean visited[], boolean recStack[]){
    		if(!visited[v]){
    			visited[v] = true;
    			recStack[v] = true;
    			
    			Iterator<Integer> it = adj[v].iterator();
    			while(it.hasNext()){
    				int n = it.next();
    				if(!visited[n]&&isCyclicUtil(n, visited, recStack))
    					return true;
    				else if(recStack[n])
    					return true;
    			}
    		}
    		recStack[v] = false;
    		return false;
    	}
    	
    	void DFS(){
    		boolean visited[] = new boolean[V];
    		
    		for(int i=0;i<V;i++)
    			if(!visited[i])
    				DFSutil(i,visited);
    	}
    	
    	void DFSutil(int v,boolean visited[]){
    		visited[v] = true;
    		pout.print(v+" ");
    		
    		Iterator<Integer> it = adj[v].iterator();
    		while(it.hasNext()){
    			int n = it.next();
    			if(!visited[n])
    				DFSutil(n,visited);
    		}
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

