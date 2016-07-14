/**
 *
 * @author pulkit4tech
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class LinkedListQuestion implements Runnable {

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
        new Thread(new LinkedListQuestion()).start();
    }

    void solve() throws Exception {
        reverseLinkedList();
    }
    
    void reverseLinkedList() {
        LinkedList<String> list = new LinkedList<>();
        list.push("This");
        list.push("is");
        list.push("a");
        list.push("test");
        list.printList();
        list.reverse(list.head, 4);
        list.printList();
    }
    
    private class LinkedList<T>{
        
        Node head;
        
        class Node<T>{
            T data;
            Node next;
            
            Node(T d){
                data = d;
                next = null;
            }
        }
        
        // Insert node at front
        void push(T val){
            Node new_val = new Node(val);
            new_val.next = head;
            head = new_val;
        }
        
        // printing list
        void printList(){
            Node temp = head;
            while(temp!=null){
                pout.print(temp.data + " ");
                temp = temp.next;
            }
            pout.println();
        }
        
        // reversing kth sub list
        void reverse(Node head,int k){
            this.head = reverseHelper(head, k);
        }
        // helper function
        Node reverseHelper(Node head, int k){
            Node current = head;
            Node next = null;
            Node prev = null;
            
            int i = 0;
            
            while(i<k && current != null){
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
                i++;
            }
            
            if(next!=null)
                head.next = reverseHelper(next, k);
            
            return prev;
        }
    }

}

