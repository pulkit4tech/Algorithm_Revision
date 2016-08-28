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
    	 tree.insert(10);
         tree.insert(20);
         tree.insert(30);
         tree.insert(40);
         tree.insert(50);
         tree.insert(25);
  
         /* The constructed AVL Tree would be
              30
             /  \
           20   40
          /  \     \
         10  25    50
         */
         pout.println("The preorder traversal of constructed tree is : ");
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

