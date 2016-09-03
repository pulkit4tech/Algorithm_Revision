/**
 *
 * @author pulkit4tech
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


class Mixed_Example implements Runnable {

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
		new Thread(new Mixed_Example()).start();
	}

	void solve() throws Exception {
		kadane_algo();
	}
	
	void kadane_algo(){
		// for finding largest sum contiguous subarray
		int arr[] = {-1,-2,4,-2,-4,-1,-6,1,-3};
		
		int max_ending_here = 0, max_so_far = 0;
		for(int i = 0; i < arr.length ; i++){
			
			max_ending_here += arr[i];
			if(max_ending_here<0)
				max_ending_here = 0;
			
			if(max_ending_here > max_so_far)
				max_so_far = max_ending_here;
		}
		
		pout.println(max_so_far);
	}
}
