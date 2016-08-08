import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author pulkit4tech
 */
public class Binary_Tree_Example implements Runnable {

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
		new Thread(new Binary_Tree_Example()).start();
	}

	void solve() throws Exception {
		//tree_traversal();
		tree_diameter();
	}
	
	
	void tree_diameter(){
		
		BinaryTree<Integer> tree = new BinaryTree<>();
        tree.root = new Node<Integer>(1);
        tree.root.left = new Node<Integer>(2);
        tree.root.right = new Node<Integer>(3);
        tree.root.left.left = new Node<Integer>(4);
        tree.root.left.right = new Node<Integer>(5);
        
		pout.println("Diameter is (not optimized):");
		pout.println(tree.diameter(tree.root));
	}
	
	void tree_traversal(){
		BinaryTree<Integer> tree = new BinaryTree<>();
        tree.root = new Node<Integer>(1);
        tree.root.left = new Node<Integer>(2);
        tree.root.right = new Node<Integer>(3);
        tree.root.left.left = new Node<Integer>(4);
        tree.root.left.right = new Node<Integer>(5);
 
        pout.println("Preorder traversal of binary tree is ");
        tree.printPreorder(tree.root);
 
        pout.println("\nInorder traversal of binary tree is ");
        tree.printInorder(tree.root);
 
        pout.println("\nPostorder traversal of binary tree is ");
        tree.printPostorder(tree.root);
        
        pout.println("\nLevel order traversal of binary tree is ");
        tree.printLevelorder(tree.root);
	}
	
	class Node<T>{
		T key;
		Node<T> left,right;
		
		public Node(T item){
			key = item;
			left=right=null;
		}
	}
	
	class BinaryTree<T>{
		Node<T> root;
		
		public BinaryTree() {
			root = null;
		}
		
//		The diameter of a tree T is the largest of the following quantities:
//			(Refer : GeeksForGeeks)
//			* the diameter of T’s left subtree
//			* the diameter of T’s right subtree
//			* the longest path between leaves that goes through the root of T 
//			(this can be computed from the heights of the subtrees of T)
		
		// O(N^2) => Not optimized
		int diameter(Node<T> root){
			if(root == null)
				return 0;
			
			int leftheight = height(root.left);
			int rightheight = height(root.right);
			
			int leftdiameter = diameter(root.left);
			int rightdiameter = diameter(root.right);
			
			return Math.max(Math.max(leftdiameter,rightdiameter),leftheight+rightheight+1);
		}
		
		
		 
		
		//height of tree
		int height(Node<T> node){
			if(node == null)
				return 0;
			
			return 1 + Math.max(height(node.left), height(node.right));
		}
		
		void printInorder(Node<T> node){
			if(node == null)
				return;
			
			printInorder(node.left);
			pout.print(node.key+" ");
			printInorder(node.right);
			
		}
		
		void printPostorder(Node<T> node){
			if(node == null)
				return;
			
			printPostorder(node.left);
			printPostorder(node.right);
			pout.print(node.key+" ");
			
		}
		
		void printPreorder(Node<T> node){
			if(node == null)
				return;
			
			pout.print(node.key+" ");
			printPreorder(node.left);
			printPreorder(node.right);
			
		}
		
		// using queue complexity => O(N)
		void printLevelorder(Node<T> node){
			Queue<Node<T>> q = new LinkedList<>();
			q.add(node);
			
			while(!q.isEmpty()){
				
				Node<T> temp = q.poll();
				pout.print(temp.key+" ");
				if(temp.left!=null)
					q.add(temp.left);
				if(temp.right!=null)
					q.add(temp.right);
			}
		}
	}
}

