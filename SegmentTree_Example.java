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
    	//segment_tree_sum_query();
    	segment_tree_max_query();
    }
    
    void segment_tree_max_query(){
    	int arr[] = {1, 3, 5};
        int n = arr.length;
        SegmentTreeMax  tree = new SegmentTreeMax(arr, n);
 
        // Build segment tree from given array
 
        // Print sum of values in array from index 1 to 3
        pout.println("Max of values in given range = " +
                           tree.queryTree(0, 0, n-1, 0, 2));
 
        // Update: set arr[1] = 10 and update corresponding segment
        // tree nodes
        tree.updateTree(0, 0, n-1, 2, 2, -3);
 
        // Find sum after the value is updated
        pout.println("Max of values in given range after updation = " +
                tree.queryTree(0, 0, n-1, 0, 2));
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
    
    class SegmentTreeMax{
    	int st[];
    	int lazy[];
    	
    	public SegmentTreeMax(int arr[],int n) {
    		int x = (int) Math.ceil(Math.log(n)/Math.log(2));
    		int max_size = (int) (2*Math.pow(2, x) - 1);
    		st = new int[max_size];
    		lazy = new int[max_size];
    		
    		constructST(arr,0,n-1,0);
    	}
    	
    	void constructST(int arr[],int ss,int se,int si){
    		if(ss==se){
    			st[si] = arr[ss];
    			return;
    		}
    		
    		int mid = (ss+se)/2;
    		constructST(arr, ss, mid, 2*si+1);
    		constructST(arr, mid+1, se, 2*si+2);
    		
    		st[si] = Math.max(st[2*si+1], st[2*si+2]);
    	}
    	
    	void updateTree(int node,int ss,int se,int qs,int qe,int diff){
    		
    		if(lazy[node]!=0){
    			st[node] += lazy[node];
    			if(ss!=se){
    				lazy[node*2+1] += lazy[node];
    				lazy[node*2+2] += lazy[node];
    			}
    			lazy[node] = 0;
    		}
    		
    		if(ss>se || qs>se || qe<ss)
    			return;
    		
    		if(qs<=ss && qe >= se){
    			st[node] += diff;
    			if(ss!=se){
    				lazy[node*2+1] += diff;
    				lazy[node*2+2] += diff;
    			}
    			
    			return;
    		}
    		
    		int mid = (ss+se)/2;
    		updateTree(2*node + 1, ss, mid, qs, qe, diff);
    		updateTree(2*node+2, mid+1, se, qs, qe, diff);
    		st[node] = Math.max(st[2*node+1], st[2*node+2]);
    	}
    	
    	int queryTree(int node,int ss,int se,int qs,int qe){
    		if(ss>se || qs>se || qe<ss)
    			return Integer.MIN_VALUE;
    		
    		if(lazy[node]!=0){
    			st[node] += lazy[node];
    			if(ss!=se){
    				lazy[node*2+1] += lazy[node];
    				lazy[node*2+2] += lazy[node];
    			}
    			lazy[node] = 0;
    		}
    		
    		if(qs<=ss && qe >= se)
    			return st[node];
    		
    		int mid = (ss+se)/2;
    		int leftq = queryTree(2*node+1, ss, mid, qs, qe);
    		int rightq = queryTree(2*node+2, mid+1, se, qs, qe);
    		return Math.max(leftq, rightq);
    	}
    }
    
    class SegmentTreeSum{
    	int st[];
    	int lazy[];
    	
    	public SegmentTreeSum(int arr[],int n) {
    		int x = (int) Math.ceil(Math.log(n)/Math.log(2));
    		int max_size = (int) (2*Math.pow(2, x) - 1);
    		st = new int[max_size];
    		lazy = new int[max_size];
    		
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
    		
    		if(qs>e || qe<s)
    			return 0;
    		if(lazy[i]!=0){
    			st[i] += lazy[i];
    			if(s!=e){
    				lazy[2*i+1] += lazy[i];
    				lazy[2*i+2] += lazy[i];
    			}
    			lazy[i] = 0;
    		}
    		
    		if(qs<=s && qe >= e)
    			return st[i];
    		
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
   
    		// lazy propogation technique
    		if(lazy[si] != 0){
    			st[si] += lazy[si];
    			if(ss!=se){
    				//mark its child
    				lazy[2*si+1] += lazy[si];
    				lazy[2*si+2] += lazy[si];
    			}
    			lazy[si] = 0;
    		}
    		
    		if(i<=se&&i>=si){
    			st[si] += diff;
    			if(se!=ss){
    				lazy[si*2+1] += diff;
    				lazy[si*2+2] += diff;
    			}
    			
    			return;
    		}
    		
    		int mid = (ss+se)/2;
    		updateValueUtil(ss, mid, i, diff, 2*si+1);
    		updateValueUtil(mid+1, se, i, diff, 2*si+2);
    		
    		st[si] = st[si*2+1] + st[si*2+2];
    	}
    }
}

