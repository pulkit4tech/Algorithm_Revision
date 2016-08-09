import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
//      Question?
//		If you are given two traversal sequences,
//		can you construct the binary tree?			
//		
//		It depends on what traversals are given. 
//		If one of the traversal methods is Inorder then the tree can be constructed, otherwise not.
		
//		tree_traversal();
//		tree_diameter();
//		getMaxWidth();
		print_node_at_k_distance();
	}
	
	void print_node_at_k_distance(){
		BinaryTree<Integer> tree = new BinaryTree<>();
        tree.root = new Node<Integer>(1);
        tree.root.left = new Node<Integer>(2);
        tree.root.right = new Node<Integer>(3);
        tree.root.left.left = new Node<Integer>(4);
        tree.root.left.right = new Node<Integer>(5);
        
        tree.printKDistant(tree.root, 2);
	}
	
	void getMaxWidth(){
		
		BinaryTree<Integer> tree = new BinaryTree<>();
        tree.root = new Node<Integer>(1);
        tree.root.left = new Node<Integer>(2);
        tree.root.right = new Node<Integer>(3);
        tree.root.left.left = new Node<Integer>(4);
        tree.root.left.right = new Node<Integer>(5);
        
		int h = tree.height(tree.root);
		int count[] = new int[h];
		int level = 0;
		
		tree.getMaxWidth(tree.root, count, level);
		pout.println("level    width");
		for(int i=0;i<h;i++)
			pout.println(i+"         "+count[i]);
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
		
		Height h = new Height();
		pout.println("Diameter is (optimized) :");
		pout.println(tree.diameterOpt(tree.root, h));
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
        
        pout.println("\nInorder using stack (not recursive) is ");
        tree.printInorderNotRec(tree.root);
        
        pout.println("\nInorder using morris traversal (not recursive or stack) is ");
        tree.morrisTraversal(tree.root);
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
		
		void printKDistant(Node<T> node, int k) 
	    {
	        if (node == null)
	            return;
	        if (k == 0) 
	        {
	            pout.print(node.key + " ");
	            return;
	        } 
	        else
	        {
	            printKDistant(node.left, k - 1);
	            printKDistant(node.right, k - 1);
	        }
	    }
		
		void getMaxWidth(Node<T> node,int count[],int level){
			if(node==null)
				return;
			
			count[level]++;
			getMaxWidth(node.left, count, level+1);
			getMaxWidth(node.right, count, level+1);
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
		
		// O(N) => Optimized
		int diameterOpt(Node<T> root, Height height)
	    {
	        /* lh --> Height of left subtree
	           rh --> Height of right subtree */
	        Height lh = new Height(), rh = new Height();
	 
	        if (root == null)
	        {
	            height.h = 0;
	            return 0; // diameter is also 0 
	        }
	         
	        
	        /* Get the heights of left and right subtrees in lh and rh
	         And store the returned values in ldiameter and ldiameter */
	        lh.h++;     rh.h++; 
	        int ldiameter = diameterOpt(root.left, lh);
	        int rdiameter = diameterOpt(root.right, rh);
	 
	        /* Height of current node is max of heights of left and
	         right subtrees plus 1*/
	        height.h = Math.max(lh.h, rh.h) + 1;
	 
	        return Math.max(lh.h + rh.h + 1, Math.max(ldiameter, rdiameter));
	    } 
		
		//height of tree
		int height(Node<T> node){
			if(node == null)
				return 0;
			
			return 1 + Math.max(height(node.left), height(node.right));
		}
		
		// TODO : Awesome algo (revise again)
		// Inorder without using recursion or stack
		void morrisTraversal(Node<T> root){
			if(root == null)
				return;
			
			Node<T> current = root;
			while(current!= null){
				if(current.left == null){
					pout.print(current.key + " ");
					current = current.right;
				}
				else{
					Node<T> predecessor = current.left;
					// Moving to rightmost child of left subtree
					while(predecessor.right!=null&&predecessor.right!=current)
						predecessor = predecessor.right;
					
					// Threading BT: make current as right child of its inorder predecessor
					if(predecessor.right==null){
						predecessor.right = current;
						current = current.left;
					}
					// else revert changes made to original tree
					else{
						predecessor.right = null;
						pout.print(current.key + " ");
						current = current.right;
					}
				}
			}
		}
		
		void printInorderNotRec(Node<T> root){
			if(root == null)
				return;
			
			Stack<Node<T>> st = new Stack<>();
			Node<T> node = root;
			
			while(node!=null){
				st.push(node);
				node = node.left;
			}
			
			while(st.size()>0){
				node = st.pop();
				pout.print(node.key + " ");
				if(node.right!=null){
					node = node.right;
					while(node!=null){
						st.push(node);
						node = node.left;
					}
				}
			}
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
	
	class Height
	{
	    int h;
	}
}

