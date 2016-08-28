/**
 *
 * @author pulkit4tech
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class AVL_Tree_Example implements Runnable {

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
        new Thread(new AVL_Tree_Example()).start();
    }

    void solve() throws Exception {
       AVL_example();
    }
    
    private void AVL_example(){
    	AVLTree tree = new AVLTree();
    	 
        /* Constructing tree given in the above figure */
        tree.root = tree.insertHelp(tree.root, 9);
        tree.root = tree.insertHelp(tree.root, 5);
        tree.root = tree.insertHelp(tree.root, 10);
        tree.root = tree.insertHelp(tree.root, 0);
        tree.root = tree.insertHelp(tree.root, 6);
        tree.root = tree.insertHelp(tree.root, 11);
        tree.root = tree.insertHelp(tree.root, -1);
        tree.root = tree.insertHelp(tree.root, 1);
        tree.root = tree.insertHelp(tree.root, 2);
 
        /* The constructed AVL Tree would be
           9
          /  \
         1    10
        /  \    \ 
       0    5    11 
      /    /  \
    -1   2    6
         */
        pout.println("The preorder traversal of constructed tree is : ");
        tree.preOrder();
 
        tree.root = tree.deleteNode(tree.root, 10);
 
        /* The AVL Tree after deletion of 10
           1
          /  \
         0    9
        /     / \  
       -1    5   11  
      /  \
     2    6
         */
        pout.println("");
        pout.println("Preorder traversal after deletion of 10 :");
        tree.preOrder();
    }
    
    class Node{
    	int data,height;
    	Node left,right;
    	
    	Node(int d){
    		data = d;
    		height = 1;
    	}
    }
    
    class AVLTree{
    	
//    	a) Left Left Case
//
//    T1, T2, T3 and T4 are subtrees.
//             z                                      y 
//            / \                                   /   \
//           y   T4      Right Rotate (z)          x      z
//          / \          - - - - - - - - ->      /  \    /  \ 
//         x   T3                               T1  T2  T3  T4
//        / \
//      T1   T2
//    b) Left Right Case
//
//         z                               z                           x
//        / \                            /   \                        /  \ 
//       y   T4  Left Rotate (y)        x    T4  Right Rotate(z)    y      z
//      / \      - - - - - - - - ->    /  \      - - - - - - - ->  / \    / \
//    T1   x                          y    T3                    T1  T2 T3  T4
//        / \                        / \
//      T2   T3                    T1   T2
//    c) Right Right Case
//
//      z                                y
//     /  \                            /   \ 
//    T1   y     Left Rotate(z)       z      x
//        /  \   - - - - - - - ->    / \    / \
//       T2   x                     T1  T2 T3  T4
//           / \
//         T3  T4
//    d) Right Left Case
//
//       z                            z                            x
//      / \                          / \                          /  \ 
//    T1   y   Right Rotate (y)    T1   x      Left Rotate(z)   z      y
//        / \  - - - - - - - - ->     /  \   - - - - - - - ->  / \    / \
//       x   T4                      T2   y                  T1  T2  T3  T4
//      / \                              /  \
//    T2   T3                           T3   T4
    
    	
    	Node root;
    	
    	int height(Node x){
    		if(x==null)
    			return 0;
    		
    		return x.height;
    	}
    	
    	int max(int a,int b){
    		return Math.max(a, b);
    	}
    	
    	Node rightRotate(Node z){
    		Node temp1 = z.left;
    		Node temp2 = temp1.right;
    		
    		//rotate
    		temp1.left =z;
    		z.right = temp2;
    		
    		z.height = max(height(z.left),height(z.right)) + 1;
    		temp1.height = max(height(temp1.left),height(temp1.right)) + 1;
    		
    		return temp1;
    	}
    	
    	Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;
     
            // Perform rotation
            y.left = x;
            x.right = T2;
     
            //  Update heights
            x.height = max(height(x.left), height(x.right)) + 1;
            y.height = max(height(y.left), height(y.right)) + 1;
     
            // Return new root
            return y;
        }
    	
    	int getBalance(Node x){
    		if(x==null)
    			return 0;
    		
    		return height(x.left) - height(x.right);
    	}
    	
    	void insert(int key){
    		root = insertHelp(root, key);
    	}
    	
    	Node insertHelp(Node node,int key){
    		if(node==null)
    			return new Node(key);
    		
    		if(key<node.data)
    			node.left = insertHelp(node.left, key);
    		else
    			node.right = insertHelp(node.right, key);
    		
    		node.height = max(height(node.left),height(node.right))+1;
    		
    		int balance = getBalance(node);
    		// Left Left Case
            if (balance > 1 && key < node.left.data) {
                return rightRotate(node);
            }
     
            // Right Right Case
            if (balance < -1 && key > node.right.data) {
                return leftRotate(node);
            }
     
            // Left Right Case
            if (balance > 1 && key > node.left.data) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
     
            // Right Left Case
            if (balance < -1 && key < node.right.data) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
    		return node;
    	}
    	
    	
    	Node minValueNode(Node node) {
            Node current = node;
     
            /* loop down to find the leftmost leaf */
            while (current.left != null) {
                current = current.left;
            }
     
            return current;
        }
     
        Node deleteNode(Node root, int key) {
     
            // STEP 1: PERFORM STANDARD BST DELETE
            if (root == null) {
                return root;
            }
     
            // If the key to be deleted is smaller than the root's key,
            // then it lies in left subtree      
            if (key < root.data) {
                root.left = deleteNode(root.left, key);
            } 
     
            // If the key to be deleted is greater than the root's key,
            // then it lies in right subtree
            else if (key > root.data) {
                root.right = deleteNode(root.right, key);
            } 
             
            // if key is same as root's key, then this is the node
            // to be deleted
            else {
     
                // node with only one child or no child
                if ((root.left == null) || (root.right == null)) {
                    Node temp = null;
                    if (temp == root.left) {
                        temp = root.right;
                    } else {
                        temp = root.left;
                    }
     
                    // No child case
                    if (temp == null) {
                        temp = root;
                        root = null;
                    } else // One child case
                    {
                        root = temp; // Copy the contents of the non-empty child
                    }
                } else {
     
                    // node with two children: Get the inorder successor (smallest
                    // in the right subtree)
                    Node temp = minValueNode(root.right);
     
                    // Copy the inorder successor's data to this node
                    root.data = temp.data;
     
                    // Delete the inorder successor
                    root.right = deleteNode(root.right, temp.data);
                }
            }
     
            // If the tree had only one node then return
            if (root == null) {
                return root;
            }
     
            // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
            root.height = max(height(root.left), height(root.right)) + 1;
     
            // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
            //  this node became unbalanced)
            int balance = getBalance(root);
     
            // If this node becomes unbalanced, then there are 4 cases
            // Left Left Case
            if (balance > 1 && getBalance(root.left) >= 0) {
                return rightRotate(root);
            }
     
            // Left Right Case
            if (balance > 1 && getBalance(root.left) < 0) {
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
     
            // Right Right Case
            if (balance < -1 && getBalance(root.right) <= 0) {
                return leftRotate(root);
            }
     
            // Right Left Case
            if (balance < -1 && getBalance(root.right) > 0) {
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
     
            return root;
        }
    	
    	void preOrder(){
    		preOrderHelp(root);
    	}
    	 void preOrderHelp(Node node) {
    	        if (node != null) {
    	            pout.print(node.data + " ");
    	            preOrderHelp(node.left);
    	            preOrderHelp(node.right);
    	        }
    	    }
    }
}

