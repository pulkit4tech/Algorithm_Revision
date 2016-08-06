import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author pulkit4tech
 */
public class Queue_Example implements Runnable {

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
		new Thread(new Queue_Example()).start();
	}

	void solve() throws Exception {
		generateBinary(5);
	}
	
	void generateBinary(long n){
		Queue<String> queue = new LinkedList<>();
		queue.add("1");
		
		while(n-->0){
			String temp = queue.poll();
			pout.println(temp);
			queue.add((temp+"0"));
			queue.add((temp+"1"));
		}
	}
}
