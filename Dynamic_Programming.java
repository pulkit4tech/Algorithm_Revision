import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 *
 * @author pulkit4tech
 */
public class Dynamic_Programming implements Runnable {

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
        new Thread(new Dynamic_Programming()).start();
    }

    void solve() throws Exception {
       longest_increasing_subsequence();
    }
    
    private void longest_increasing_subsequence() {
         int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
        int n = arr.length;
        System.out.println("Length of lis is "
                           + lis(arr, n) + "\n");
    }

    int lis(int arr[],int n){
        int lis[] = new int[n];
        int i,j,max = 0;
        
        for (i=0;i<n;i++)
            lis[i] = 1;
        
        for(i=1;i<n;i++){
            for(j=0;j<i;j++){
                if(arr[i]>arr[j]&&lis[i]<lis[j]+1)
                    lis[i] = lis[j]+1;
            }
        }
        
        for(i=0;i<n;i++)
            if(max<lis[i])
                max=lis[i];
        
        return max;
    }

    
}
