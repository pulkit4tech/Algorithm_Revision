import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
//    	breadthFirst();
//    	depthFirst();
//    	is_there_Cycle();
    	topological_sort();
    }
    
    void topological_sort(){
    	 Graph g = new Graph(6);
         g.addEdge(5, 2);
         g.addEdge(5, 0);
         g.addEdge(4, 0);
         g.addEdge(4, 1);
         g.addEdge(2, 3);
         g.addEdge(3, 1);
 
         g.topologicalsort();
    }
    
    void is_there_Cycle(){
    	Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
     
        if(g.isCyclic())
            pout.println("\nDirected Graph contains cycle");
        else
            pout.println("\nDirected Graph doesn't contain cycle");
        
        if(g.isCyclic_SetOperation())
            pout.println("Undirected Graph contains cycle (using set)");
        else
            pout.println("Unidirected Graph doesn't contain cycle (using set)");
        
        
//        The time complexity of the union-find algorithm is O(ELogV). Like directed graphs, we can use DFS to detect cycle in an undirected graph in O(V+E) time. We do a DFS traversal of the given graph. For every visited vertex ‘v’, if there is an adjacent ‘u’ such that u is already visited and u is not parent of v, then there is a cycle in graph. 
//        If we don’t find such an adjacent for any vertex, we say that there is no cycle. The assumption of this approach is that there are no parallel edges between any two vertices.
        
        if(g.isCyclic_Other())
            pout.println("Graph contains cycle (using other)");
        else
            pout.println("Graph doesn't contain cycle (using other)");
        
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
    	ArrayList<Edge> edge;
    	LinkedList<Integer> adj[];
    	
    	Graph(int v){
    		V = v;
    		edge = new ArrayList<>();
    		adj = new LinkedList[v];
    		for(int i=0;i<v;i++)
    			adj[i] = new LinkedList<>();
    	}
    	
    	class Edge{
    		int src,dest;
    	}
    	
    	void addEdge(int v,int w){
    		adj[v].add(w);
    		Edge temp = new Edge();
    		temp.src = v;
    		temp.dest = w;
    		edge.add(temp);
    	}
    	
    	void topologicalsort(){
    		
    		boolean visited[] = new boolean[V];
    		Stack<Integer> st = new Stack<>();
    		for(int i=0;i<V;i++)
    			if(!visited[i])
    				topologicalsort_Helper(visited, i, st);
    		pout.println("Topological Sort : ");
    		while(!st.isEmpty()){
    			pout.print(st.pop()+" ");
    		}
    		pout.println();
    	}
    	
    	void topologicalsort_Helper(boolean visited[],int v,Stack<Integer> st){
    		if(!visited[v]){
    			visited[v] = true;
    			
    			Iterator<Integer> it = adj[v].iterator();
    			while(it.hasNext()){
    				int n = it.next();
    				if(!visited[n])
    					topologicalsort_Helper(visited, n, st);
    			}
    			
    			st.push(v);
    		}
    	}
    	
    	boolean isCyclicUtil_Other(int v, boolean visited[], int parent)
        {
            // Mark the current node as visited
            visited[v] = true;
            Integer i;
     
            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> it = adj[v].iterator();
            while (it.hasNext())
            {
                i = it.next();
     
                // If an adjacent is not visited, then recur for that
                // adjacent
                if (!visited[i])
                {
                    if (isCyclicUtil_Other(i, visited, v))
                        return true;
                }
     
                // If an adjacent is visited and not parent of current
                // vertex, then there is a cycle.
                else if (i != parent)
                    return true;
            }
            return false;
        }
     
        // Returns true if the graph contains a cycle, else false.
        boolean isCyclic_Other()
        {
            // Mark all the vertices as not visited and not part of
            // recursion stack
            boolean visited[] = new boolean[V];
     
            // Call the recursive helper function to detect cycle in
            // different DFS trees
            for (int u = 0; u < V; u++)
                if (!visited[u]) // Don't recur for u if already visited
                    if (isCyclicUtil_Other(u, visited, -1))
                        return true;
     
            return false;
        }
    	
    	
    	// valid only for undirected graph
    	boolean isCyclic_SetOperation(){
    		int parent[] = new int[V];
    		Arrays.fill(parent, -1);
    		
    		for(int i=0;i<edge.size();i++){
    			int x = find(parent,edge.get(i).src);
    			int y = find(parent,edge.get(i).dest);
    			
    			if(x==y)
    				return true;
    			
    			union(parent, x, y);
    		}
    		
    		return false;
    	}
    	
    	// Set operation
    	int find(int parent[],int i){
    		if(parent[i] == -1)
    			return i;
    		
    		return find(parent,parent[i]);
    	}
    	
    	void union(int parent[],int x,int y){
    		int xparent = find(parent,x);
    		int yparent = find(parent,y);
    		parent[xparent] = yparent;
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

