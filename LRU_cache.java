/**
*
* @author pulkit4tech
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class LRU_cache implements Runnable {

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
       new Thread(new LRU_cache()).start();
   }

   void solve() throws Exception {
      lru_cache();
   }
   
   private void lru_cache(){
	   LRUCache cache = new LRUCache(2);
	   cache.set(2, 4);
	   cache.set(2, 5);
	   pout.println(cache.get(2));
	   cache.set(3, 1);
	   cache.get(2);
	   cache.set(5, 1);
	   pout.println(cache.get(2));
   }
   
   class Node{
	    int key;
	    int value;
	    Node pre;
	    Node next;
	 
	    public Node(int key, int value){
	        this.key = key;
	        this.value = value;
	    }
	}
   
   class LRUCache {
	    int capacity;
	    HashMap<Integer, Node> map = new HashMap<Integer, Node>();
	    Node head=null;
	    Node end=null;
	 
	    public LRUCache(int capacity) {
	        this.capacity = capacity;
	    }
	 
	    public int get(int key) {
	        if(map.containsKey(key)){
	            Node n = map.get(key);
	            remove(n);
	            setHead(n);
	            return n.value;
	        }
	 
	        return -1;
	    }
	 
	    public void remove(Node n){
	        if(n.pre!=null){
	            n.pre.next = n.next;
	        }else{
	            head = n.next;
	        }
	 
	        if(n.next!=null){
	            n.next.pre = n.pre;
	        }else{
	            end = n.pre;
	        }
	 
	    }
	 
	    public void setHead(Node n){
	        n.next = head;
	        n.pre = null;
	 
	        if(head!=null)
	            head.pre = n;
	 
	        head = n;
	 
	        if(end ==null)
	            end = head;
	    }
	 
	    public void set(int key, int value) {
	        if(map.containsKey(key)){
	            Node old = map.get(key);
	            old.value = value;
	            remove(old);
	            setHead(old);
	        }else{
	            Node created = new Node(key, value);
	            if(map.size()>=capacity){
	                map.remove(end.key);
	                remove(end);
	                setHead(created);
	 
	            }else{
	                setHead(created);
	            }    
	 
	            map.put(key, created);
	        }
	    }
	}
}

