import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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
		tree_traversal();
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
	}
}

