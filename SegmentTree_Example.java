import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author pulkit4tech
 */
public class SegmentTree_Example implements Runnable {

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
        new Thread(new SegmentTree_Example()).start();
    }

    void solve() throws Exception {
    	segment_tree_sum_query();
    }
    
    void segment_tree_sum_query(){
    	int arr[] = {1, 3, 5, 7, 9, 11};
        int n = arr.length;
        SegmentTreeSum  tree = new SegmentTreeSum(arr, n);
 
        // Build segment tree from given array
 
        // Print sum of values in array from index 1 to 3
        pout.println("Sum of values in given range = " +
                           tree.getSum(n, 1, 3));
 
        // Update: set arr[1] = 10 and update corresponding segment
        // tree nodes
        tree.updateValue(arr, n, 1, 10);
 
        // Find sum after the value is updated
        pout.println("Updated sum of values in given range = " +
                tree.getSum(n, 1, 3));
    }
    
    class SegmentTreeSum{
    	int st[];
    	
    	public SegmentTreeSum(int arr[],int n) {
    		int x = (int) Math.ceil(Math.log(n)/Math.log(2));
    		int max_size = (int) (2*Math.pow(2, x) - 1);
    		st = new int[max_size];
    		
    		constructST(arr,0,n-1,0);
    	}
    	
    	int constructST(int arr[],int ss,int se,int si){
    		if(ss==se){
    			st[si] = arr[ss];
    			return arr[ss];
    		}
    		
    		int mid = (ss+se)/2;
    		
    		st[si] = constructST(arr, ss, mid, 2*si+1) + constructST(arr, mid+1, se, 2*si + 2);
    		return st[si];
    	}
    	
    	int getSum(int n,int qs,int qe){
    		if(qs<0||qe>n-1||qs>qe)
    			return -1;
    		
    		return getSumUtil(0,n-1, qs, qe,0);
    	}
    	
    	int getSumUtil(int s,int e,int qs,int qe,int i){
    		if(qs<=s && qe >= e)
    			return st[i];
    		if(qs>e || qe<s)
    			return 0;
    		int mid = (s + e)/2;
    		
    		return getSumUtil(s, mid, qs, qe, 2*i+1) + getSumUtil(mid+1, e, qs, qe, 2*i+2);
    	}
    	
    	void updateValue(int arr[],int n,int i,int new_val){
    		if(i<0||i>n-1)
    			return;
    		
    		int diff = new_val - arr[i];
    		
    		updateValueUtil(0,n-1,i,diff,0);
    	}
    	
    	void updateValueUtil(int ss,int se,int i,int diff,int si){
    		if(i<ss||i>se)
    			return;
    		if(ss==se){
    			st[si] += diff;
    			return;
    		}
    		int mid = (ss+se)/2;
    		updateValueUtil(ss, mid, i, diff, 2*si+1);
    		updateValueUtil(mid+1, se, i, diff, 2*si+2);
    		
    		st[si] = st[si*2+1] + st[si*2+2];
    	}
    }
}

