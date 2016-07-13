/**
 *
 * @author pulkit4tech
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

class Priority_Queue implements Runnable {

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
        new Thread(new Priority_Queue()).start();
    }

    void solve() throws Exception {
        priorityQueue();
    }

    void priorityQueue() {
     // Using built in Priority Queue
        PriorityQueue<Integer> maxpq = new PriorityQueue<>(2,new Comparator<Integer>() {
            @Override
            public int compare(Integer first, Integer second) {
                return -first.compareTo(second);
           }
        });
        maxpq.add(100);
        maxpq.add(-100);
        maxpq.add(0);
        maxpq.add(12);
        pout.println(maxpq.peek());
        maxpq.poll();
        pout.println(maxpq);
    }
}

