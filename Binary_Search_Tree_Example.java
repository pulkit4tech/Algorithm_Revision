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
		//small_operation_on_BST();
		//predecessor_and_successor();
		//isBST();
		//lowestCommonAncestor();
		inorderSuccessor();
	}
	
	private void inorderSuccessor(){
		BinarySearchTree tree = new BinarySearchTree();
		 
        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 
              \
               45*/
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        tree.insert(45);
        
        tree.inorderSuc(tree.root, 80);
	}
	
	private void lowestCommonAncestor(){
		BinarySearchTree tree = new BinarySearchTree();
		 
        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 
              \
               45*/
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        tree.insert(45);
        
        tree.lca(tree.root, 30, 45 );
	}
	
	private void isBST(){

//		METHOD 1 (Simple but Wrong)
//		Following is a simple program. 
//		For each node, check if left node of it is smaller than the node and 
//		right node of it is greater than the node.

//		METHOD 2 (Correct but not efficient)
//		For each node, check if max value in left subtree is smaller than the node and 
//		min value in right subtree greater than the node.
		
//		METHOD 3 (Correct and Efficient)
//		Method 2 above runs slowly since it traverses over some parts of the tree many times.
//		A better solution looks at each node only once. 
//		The trick is to write a utility helper function isBSTUtil(struct node* node, int min, int max) 
//		that traverses down the tree keeping track of the narrowing min and max allowed values as it goes, 
//		looking at each node only once. The initial values for min and max should be INT_MIN and INT_MAX — 
//		they narrow from there.
		
		
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
        
        tree.isBSTHelper(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private void predecessor_and_successor(){
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
    
        tree.findPreSuc(tree.root,tree.pre,tree.suc,60);
        if(tree.pre!=null)
			pout.println("Predecessor is: " + tree.pre.data);
		else
			pout.println("No predecessor");
		
		if(tree.suc!=null)
			pout.println("Successor is: " + tree.suc.data);
		else
			pout.println("No Successor");
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
	        tree.delete(40);
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
		Node pre,suc;
		
		public BinarySearchTree(){
			root=null;
			pre=null;
			suc=null;
		}
		
		void inorderSuc(Node root,int key){
			Node temp = inorderSucHelper(root,key);
			if(temp==null)
				pout.println("No inorder Successor");
			else
				pout.println("Inorder Successor of "+ key+ " is: "+temp.data);
		}
		
		Node inorderSucHelper(Node root, int key){
			
			if(root==null)
				return null;
			
			// search in tree to get Successor
			Node succ = null;
			while(root!=null){
				if(key<root.data){
					succ = root;
					root = root.left;
				}
				else if(key>root.data)
					root = root.right;
				else{
					Node temp = minVal(root.right);
					if(temp!=null)
						succ = temp;
					break;
				}
			}
			
			return succ;
		}
		
		void lca(Node root,int key1,int key2){
			Node temp = lcaHelper(root,key1,key2);
			if(temp == null)
				pout.println("No Lowest Common ancestor");
			else{
				if(searchHelper(temp, key1)!=null){
					if(searchHelper(temp, key2)!=null)
						pout.println("Lowest Common Ancestor : "+ temp.data);
					else
						pout.println(key2 + " Not found in BST");
				}
				else{
					pout.println(key1 + " Not found in BST");
				}
			}
		}
		
		Node lcaHelper(Node root,int key1,int key2){
			if(root==null)
				return null;
			
			if(root.data > key1 && root.data > key2)
				return lcaHelper(root.left, key1, key2);
			
			if(root.data < key1 && root.data < key2)
				return lcaHelper(root.right, key1, key2);
			
			return root;
		}
		
		boolean isBSTHelper(Node root,int min,int max){
			if(root==null)
				return true;
			
			if(root.data<min || root.data>max)
				return false;
			
			return (isBSTHelper(root.left, min, root.data-1)&&isBSTHelper(root.right, root.data+1, max));
			
		}
			
		void findPreSuc(Node root,Node pre,Node suc,int key){
			if(root==null)
				return ;
			
			if(root.data==key){
				//predecessor
				if(root.left!=null){
					Node temp = root.left;
					while(temp.right!=null)
						temp = temp.right;
					this.pre = temp;
				}
				
				if(root.right!=null){
					Node temp = root.right;
					while(temp.left!=null)
						temp = temp.left;
					this.suc = temp;
				}
				
				return;
			}
			
			if(key<root.data){
				//search on left side
				this.suc = root;
				findPreSuc(root.left, this.pre, this.suc, key);
			}
			else{
				this.pre = root;
				findPreSuc(root.right, this.pre, this.suc, key);
			}
		}
		
		void delete(int key){
			
//			1) Node to be deleted is leaf: Simply remove from the tree.
//        50                            50
//     /     \         delete(20)      /   \
//    30      70       --------->    30     70 
//   /  \    /  \                     \    /  \ 
// 20   40  60   80                   40  60   80
// 2) Node to be deleted has only one child: Copy the child to the node and delete the child
//        50                            50
//     /     \         delete(30)      /   \
//    30      70       --------->    40     70 
//      \    /  \                          /  \ 
//      40  60   80                       60   80
// 3) Node to be deleted has two children: Find inorder successor of the node. Copy contents of the inorder successor to the node and delete the inorder successor. Note that inorder predecessor can also be used.
//        50                            60
//     /     \         delete(50)      /   \
//    40      70       --------->    40    70 
//           /  \                            \ 
//          60   80                           80
// The important thing to note is, inorder successor is needed only when right child is not empty. In this particular case, inorder successor can be obtained by finding the minimum value in right child of the node.
			
			root = deleteHelper(root,key);
		}
		
		Node deleteHelper(Node node,int key){
			if(node == null)
				return root;
			
			if(key<node.data)
				node.left = deleteHelper(node.left, key);
			else if(key>node.data)
				node.right = deleteHelper(node.right, key);
			else{
				if(node.left == null)
					return node.right;
				else if(node.right == null)
					return node.left;
				
				node.data = minVal(node.right).data;
				// now delete that successor
				node.right = deleteHelper(node.right, node.data);
			}
			
			return node;
		}
		
		Node minVal(Node root){
			if(root == null)
				return null;
			while(root.left!=null)
				root = root.left;
			return root;
		}
		
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
