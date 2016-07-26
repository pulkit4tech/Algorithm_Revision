/**
*
* @author pulkit4tech
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


class Dijkstras_adjancy_list implements Runnable {

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
       new Thread(new Dijkstras_adjancy_list()).start();
   }

   void solve() throws Exception {
       dijkstra();
   }

   private void dijkstra() {
	   Graph graph = new Graph(11);
       graph.addEdge(1, 3, 1);
       graph.addEdge(1, 2, 3);
       graph.addEdge(2, 5, 5);
       graph.addEdge(2, 4, 3);
       graph.addEdge(3, 5, 8);
       graph.addEdge(3, 6, 5);
       graph.addEdge(4, 5, 1);
       graph.addEdge(4, 7, 10);
       graph.addEdge(5, 6, 2);
       graph.addEdge(5, 8, 8);
       graph.addEdge(5, 7, 2);
       graph.addEdge(6, 8, 15);
       graph.addEdge(7, 8, 5);
       graph.addEdge(9, 11, 2);
       graph.computeSortestPathsFrom(1);
	
}

public class Graph {

	    private Node[] vertices; 
	    private int size;
	    private MinPriorityQueue queue;

	    public Graph(int size) {
	        this.size = size;
	        vertices = new Node[size];
	        addNodes();
	        queue = new MinPriorityQueue(size);
	    }

	    public class Node {
	        int name;
	        int cost;
	        Neighbour neighbourList;
	        State state;

	        Node(int name) {
	            this.name = name;
	            // State is enum and is there in default folder as State.java
	            state = State.NEW;
	            cost = Integer.MAX_VALUE;
	        }
	    }

	    public class Neighbour {
	        int index;
	        int weight;
	        Neighbour next;

	        public Neighbour(int index, Neighbour next, int weight) {
	            this.index = index;
	            this.next = next;
	            this.weight = weight;
	        }
	    }

	    private void addNodes() {
	        for (int i = 1; i <= size; i++) {
	            addNode(i);
	        }
	    }

	    public void addNode(int name) {
	        vertices[name - 1] = new Node(name);
	    }

	    public void addEdge(int sourceName, int destiName, int weight) {
	        int srcIndex = sourceName - 1;
	        int destiIndex = destiName - 1;
	        Node srcNode = vertices[srcIndex];
	        Node destiNode = vertices[destiIndex];
	        srcNode.neighbourList = new Neighbour(destiIndex, srcNode.neighbourList, weight);
	       
	        destiNode.neighbourList = new Neighbour(srcIndex, destiNode.neighbourList, weight);
	    }

	    public void computeSortestPathsFrom(int sourceNodeName) {
	        for (int i = 0; i < size; i++) {
	            if (vertices[i].name == sourceNodeName) {
	                applyDijkstraAlgorithm(vertices[i]);
	                break;
	            }
	        }
	    }

	    
	    private void applyDijkstraAlgorithm(Node sourceNode) {
	        queue.add(sourceNode);
	        sourceNode.state = State.IN_Q;
	        sourceNode.cost = 0; 
	        while (!queue.isEmpty()) {
	            Node visitedNode = queue.remove();
	            visitedNode.state = State.VISITED;
	            Neighbour connectedEdge = visitedNode.neighbourList;
	            while (connectedEdge != null) {
	                Node neighbour = vertices[connectedEdge.index];
	                
	                if (neighbour.state == State.NEW) {
	                    queue.add(neighbour);
	                    neighbour.state = State.IN_Q;
	                }
	                
	                if (neighbour.state != State.VISITED && ((connectedEdge.weight + visitedNode.cost) < neighbour.cost)) {
	                    neighbour.cost = connectedEdge.weight + visitedNode.cost;
	                }
	                connectedEdge = connectedEdge.next;
	            }
	        }
	        
	        //now printing the shortest distances
	        for(int i = 0; i < size; i++){
	            if(vertices[i].cost != Integer.MAX_VALUE){
	                System.out.println("distance from "+sourceNode.name +" to "+vertices[i].name+" is " +vertices[i].cost);
	            }else{
	                System.out.println(vertices[i].name +" is not reachable from "+sourceNode.name);
	            }
	        }
	    }

	    public class MinPriorityQueue {
	        Node[] queue;
	        int maxSize;
	        int rear = -1, front = -1;

	        MinPriorityQueue(int maxSize) {
	            this.maxSize = maxSize;
	            queue = new Node[maxSize];
	        }

	        public void add(Node node) {
	            queue[++rear] = node;
	        }

	        public Node remove() {
	            Node minValuedNode = null;
	            int minValue = Integer.MAX_VALUE;
	            int minValueIndex = -1;
	            front++;
	            for (int i = front; i <= rear; i++) {
	                if (queue[i].state == State.IN_Q && queue[i].cost < minValue) {
	                    minValue = queue[i].cost;
	                    minValuedNode = queue[i];
	                    minValueIndex = i;
	                }
	            }

	            swap(front, minValueIndex); 
	            queue[front] = null;
	            return minValuedNode;
	        }

	        public void swap(int index1, int index2) {
	            Node temp = queue[index1];
	            queue[index1] = queue[index2];
	            queue[index2] = temp;
	        }

	        public boolean isEmpty() {
	            return front == rear;
	        }
	    }
   }
}

