
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
        //detect_and_remove_loop();
    	
    	push(15);
    	push(10);
    	push(5);
    	push(20);
    	push(2);
    	push(3);
    	
    	printList(head);
    	head = mergeSortList(head);
    	printList(head);
    }
    
    ListNode head;
    
    class ListNode {
    	int val;
    	ListNode next;
     
    	ListNode(int x) {
    		val = x;
    		next = null;
    	}
    }
    
    ListNode mergeSortList(ListNode head) {
    	 
		if (head == null || head.next == null)
			return head;
 
		// count total number of elements
		int count = 0;
		ListNode p = head;
		while (p != null) {
			count++;
			p = p.next;
		}
 
		// break up to two list
		int middle = count / 2;
 
		ListNode l = head, r = null;
		ListNode p2 = head;
		int countHalf = 0;
		while (p2 != null) {
			countHalf++;
			ListNode next = p2.next;
 
			if (countHalf == middle) {
				p2.next = null;
				r = next;
			}
			p2 = next;
		}
 
		// now we have two parts l and r, recursively sort them
		ListNode h1 = mergeSortList(l);
		ListNode h2 = mergeSortList(r);
 
		// merge together
		ListNode merged = merge(h1, h2);
 
		return merged;
	}
    
    ListNode merge(ListNode l, ListNode r) {
		ListNode p1 = l;
		ListNode p2 = r;
 
		ListNode fakeHead = new ListNode(100);
		ListNode pNew = fakeHead;
 
		while (p1 != null || p2 != null) {
 
			if (p1 == null) {
				pNew.next = new ListNode(p2.val);
				p2 = p2.next;
				pNew = pNew.next;
			} else if (p2 == null) {
				pNew.next = new ListNode(p1.val);
				p1 = p1.next;
				pNew = pNew.next;
			} else {
				if (p1.val < p2.val) {
					// if(fakeHead)
					pNew.next = new ListNode(p1.val);
					p1 = p1.next;
					pNew = pNew.next;
				} else if (p1.val == p2.val) {
					pNew.next = new ListNode(p1.val);
					pNew.next.next = new ListNode(p1.val);
					pNew = pNew.next.next;
					p1 = p1.next;
					p2 = p2.next;
 
				} else {
					pNew.next = new ListNode(p2.val);
					p2 = p2.next;
					pNew = pNew.next;
				}
			}
		}
 
		// printList(fakeHead.next);
		return fakeHead.next;
	}
    
    void push(int data){
    	ListNode new_node = new ListNode(data);
    	new_node.next = head;
    	head = new_node;
    }
    
    void printList(ListNode x) {
		if(x != null){
			pout.print(x.val + " ");
			while (x.next != null) {
				pout.print(x.next.val + " ");
				x = x.next;
			}
			pout.println();
		}
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

