import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author pulkit4tech
 */
public class Binary_Search_Tree_Example implements Runnable {

	BufferedReader c;
	PrintWriter pout;
	static long mod = 1000000007;

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
		new Thread(new Binary_Search_Tree_Example()).start();
	}

	void solve() throws Exception {
		small_operation_on_BST();
	}
	
	private void small_operation_on_BST() {
		 BinarySearchTree tree = new BinarySearchTree();
		 
	        /* Let us create following BST
	              50
	           /     \
	          30      70
	         /  \    /  \
	       20   40  60   80 */
	        tree.insert(50);
	        tree.insert(30);
	        tree.insert(20);
	        tree.insert(40);
	        tree.insert(70);
	        tree.insert(60);
	        tree.insert(80);
	 
	        if(tree.search(40)!=null)
	        	pout.println("Found node 40");
	        else
	        	pout.println("40 node not found");
	        
	        // print inorder traversal of the BST
	        tree.inorder(tree.root);
	}

	class BinarySearchTree{
		class Node{
			int data;
			Node left,right;
			
			public Node(int data){
				this.data = data;
				left=right=null;
			}
		}
		
		Node root;
		
		void insert(int key){
			root = insertHelper(root,key);
		}
		
		Node insertHelper(Node root,int key){
			if(root==null){
				return new Node(key);
			}
			
			if(key < root.data)
				root.left = insertHelper(root.left, key);
			else if(key > root.data)
				root.right = insertHelper(root.right, key);
			
			return root;
		}
		
		Node search(int key){
			return searchHelper(root,key);
		}
		
		Node searchHelper(Node root,int key){
			if(root == null || root.data == key)
				return root;
			
			if(root.data > key)
				return searchHelper(root.left, key);
			
			return searchHelper(root.right, key);
		}
		
		void inorder(Node root) {
	        if (root != null) {
	            inorder(root.left);
	            pout.println(root.data);
	            inorder(root.right);
	        }
	    }
	}
}
