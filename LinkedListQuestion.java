
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
        // reverseLinkedList();
        detect_and_remove_loop();
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

    private void detect_and_remove_loop() {
        LinkedList<Integer> list = new LinkedList();
        list.push(50);
        list.push(20);
        list.push(15);
        list.push(4);
        list.push(10);

        // Creating a loop for testing 
        list.head.next.next.next.next.next = list.head.next.next;
        //list.printList(); it will enter into infinite loop .. comment it out to check
        list.detectAndRemoveLoop(list.head);
        System.out.println("Linked List after removing loop : ");
        list.printList();
    }

    private class LinkedList<T> {

        Node head;

        class Node<T> {

            T data;
            Node next;

            Node(T d) {
                data = d;
                next = null;
            }
        }

        // Insert node at front
        void push(T val) {
            Node new_val = new Node(val);
            new_val.next = head;
            head = new_val;
        }

        // printing list
        void printList() {
            Node temp = head;
            while (temp != null) {
                pout.print(temp.data + " ");
                temp = temp.next;
            }
            pout.println();
        }

        // reversing kth sub list
        void reverse(Node head, int k) {
            this.head = reverseHelper(head, k);
        }

        // helper function
        Node reverseHelper(Node head, int k) {
            Node current = head;
            Node next = null;
            Node prev = null;

            int i = 0;

            while (i < k && current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
                i++;
            }

            if (next != null) {
                head.next = reverseHelper(next, k);
            }

            return prev;
        }
// Function that detects loop in the list
    boolean detectAndRemoveLoop(Node node) {
        Node slow = node, fast = node;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // If slow and fast meet at same point then loop is present
            if (slow == fast) {
                removeLoop(slow, node);
                return true;
            }
        }
        return false;
    }

    // Function to remove loop
    void removeLoop(Node loop, Node curr) {
        Node ptr1 = curr, ptr2 = null;

        while (true) {

            /* Now start a pointer from loop_node and check if it ever
             reaches ptr2 */
            ptr2 = loop;
            while (ptr2.next != loop && ptr2.next != ptr1) {
                ptr2 = ptr2.next;
            }

            /* If ptr2 reahced ptr1 then there is a loop. So break the
             loop */
            if (ptr2.next == ptr1) {
                break;
            }

            /* If ptr2 did't reach ptr1 then try the next node after ptr1 */
            ptr1 = ptr1.next;
        }

        /* After the end of loop ptr2 is the last node of the loop. So
         make next of ptr2 as NULL */
        ptr2.next = null;
    }
    }

}

