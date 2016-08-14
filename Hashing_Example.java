import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;

/**
 *
 * @author pulkit4tech
 */
public class Hashing_Example implements Runnable {

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
		new Thread(new Hashing_Example()).start();
	}

	void solve() throws Exception {
		check_duplicate_within_K();
	}
	
	void check_duplicate_within_K(){
		int arr[] = {10, 5, 3, 4, 3, 5, 6};
        if (checkDuplicatesWithinK(arr, 3))
           pout.println("Yes");
        else
           pout.println("No");
	}
	
	boolean checkDuplicatesWithinK(int arr[], int k)
    {
        HashSet<Integer> set = new HashSet<>();
 
        for (int i=0; i<arr.length; i++)
        {
            
            if (set.contains(arr[i]))
               return true;
 
            set.add(arr[i]);
 
            if (i >= k)
              set.remove(arr[i-k]);
        }
        return false;
    }
}
