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
        // combination("abc", 2);
        int no[] = {1,2,3};
        phone_dialer(no);
    }

    
    void phone_dialer(int n[]){
        int len = n.length;
        char[] result = new char[len];
        printword(n,result,0,len);
    }
    
    void printword(int n[],char result[],int curr,int len){
        
        if(curr==len){
            pout.println(result);
            return;
        }
        
        for(int i=0;i<3;i++){
            result[curr] = getCharKey(n[curr], i);
            printword(n, result, curr+1, len);
            if(n[curr]==0||n[curr]==1) return;
        }
    }
    
    void combination(String input, int k) {
        combination_helper(input.toCharArray(), k, 0, new char[k], 0, input.length());
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

    private void combination_helper(char[] input, int k, int index, char[] data, int i, int len) {
        if (index == k) {
            pout.println(data);
        } else {
            if (i >= len) {
                return;
            }
            data[index] = input[i];
            combination_helper(input, k, index + 1, data, i + 1, len);
            combination_helper(input, k, index, data, i + 1, len);
        }
    }

    static final char[][] PHONE_CHAR_MAP = {{'A', 'B', 'C'}, {'D', 'E', 'F'}, {'G', 'H', 'I'}, {'J', 'K', 'L'},
    {'M', 'N', 'O'}, {'P', 'R', 'S'}, {'T', 'U', 'V'}, {'W', 'X', 'Y'}};

    public static char getCharKey(int teleKey, int place) {
        char teleChar;
        if (teleKey == 0) {
            teleChar = '0';
        } else if (teleKey == 1) {
            teleChar = '1';
        } else {
            teleChar = PHONE_CHAR_MAP[teleKey - 2][place];
        }

        return teleChar;
    }
}

