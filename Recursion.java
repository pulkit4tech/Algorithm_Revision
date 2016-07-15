/**
 *
 * @author pulkit4tech
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Recursion implements Runnable {

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
        new Thread(new Recursion()).start();
    }

    void solve() throws Exception {
       // permutate("bac");
       combination("abc",3);
    }
    
    void combination(String input,int k){
        combination_helper(input.toCharArray(),k,0,new char[k],0,input.length());
    }

    void permutate(String input) {
        int len = input.length();
        char in[] = input.toCharArray();

        permutate_helper(in, 0, len - 1);
    }

    void permutate_helper(char in[], int l, int r) {
        if (l == r) {
            pout.println(in);
        } else {
            for (int i = l; i <= r; i++) {
                char temp = in[i];
                in[i] = in[l];
                in[l] = temp;

                permutate_helper(in, l + 1, r);

                temp = in[i];
                in[i] = in[l];
                in[l] = temp;
            }
        }
    }

    private void combination_helper(char[] input, int k, int index, char[] data, int i,int len) {
        if(index==k){
            pout.println(data);
        }
        else{
            if(i>=len)
                return;
            data[index] = input[i];
            combination_helper(input, k, index+1, data, i+1,len);
            combination_helper(input, k, index, data, i+1,len);
        }
    }

}

