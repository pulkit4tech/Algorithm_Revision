import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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
		// longest_increasing_subsequence();
		// longest_common_subsequence();
		// edit_distance();
		// min_cost_path();
		// coin_change();
		MCM();
	}
	
	void MCM(){
		int arr[] = new int[]{1, 2, 3, 4};
	 
		pout.println("Minimum number of multiplications is (Recursive)"+
                MCM_rec(arr, 1, arr.length-1));
		
	    pout.println("Minimum number of multiplications is (DP)"+
	                          matrixChainMultiplication(arr));
	}
	
	int MCM_rec(int arr[],int i,int j){
		if(i==j)
			return 0;
		
		int min = Integer.MAX_VALUE;
		
		for(int k=i;k<j;k++){
			int count = MCM_rec(arr, i, k) + MCM_rec(arr, k+1, j) + arr[i-1]*arr[k]*arr[j];
			if(count<min)
				min = count;
		}
		
		return min;
	}
	
	int matrixChainMultiplication(int arr[]){
		int n = arr.length;
		int dp[][] = new int[n][n];
		
		for(int L=2;L<n;L++){
			for(int i=1;i<n-L+1;i++){
				
				int j = i + L -1;
				if(j==n)
					continue;
				
				dp[i][j] = Integer.MAX_VALUE;
				for(int k=i;k<j;k++){
					int q = dp[i][k] + dp[k+1][j] + arr[i-1]*arr[k]*arr[j];
					if(q<dp[i][j])
						dp[i][j] = q;
				}
			}
		}
		
		return dp[1][n-1];
	}

	void coin_change() {
		// Given a value N, if we want to make change for
		// N cents, and we have infinite supply of each of
		// S = { S1, S2, .. , Sm} valued coins, how many ways
		// can we make the change? The order of coins doesn’t
		// matter.
		//
		// For example, for N = 4 and S = {1,2,3},
		// there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}.
		// So output should be 4.
		// For N = 10 and S = {2, 5, 3, 6}, there are five solutions:
		// {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}.
		// So the output should be 5

		// refer geekforgeek for more info
		// http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
		int arr[] = { 1, 2, 3 };
		pout.println(coin_change_rec(arr, arr.length, 4));
		pout.println(coin_change_dp(arr, arr.length, 4));
	}

	int coin_change_rec(int arr[], int len, int sum) {
		if (sum == 0)
			return 1;

		if (sum < 0)
			return 0;

		if (len <= 0 && sum >= 1)
			return 0;

		return coin_change_rec(arr, len - 1, sum) + coin_change_rec(arr, len, sum - arr[len - 1]);
	}
	
	int coin_change_dp(int arr[],int len,int sum){
		
		int dp[][] = new int[sum+1][len];
		for(int i=0;i<len;i++)
			dp[0][i] = 1;
		
		for(int i=1;i<=sum;i++){
			for(int j=0;j<len;j++){
				int x,y;
				if(i-arr[j]>=0)
					x = dp[i-arr[j]][j];
				else
					x = 0;
				
				if(j>=1)
					y = dp[i][j-1];
				else
					y = 0;
				
				dp[i][j] = x + y;
			}
		}
		
		return dp[sum][len-1];
	}

	void min_cost_path() {
		// Read more :
		// http://www.geeksforgeeks.org/dynamic-programming-set-6-min-cost-path/
		long cost[][] = { { 1, 2, 3 }, { 4, 8, 2 }, { 1, 5, 3 } };
		pout.println(min_cost_dp(cost, 2, 2));

	}

	long min_cost_dp(long cost[][], int m, int n) {
		long dp[][] = new long[cost.length][cost[0].length];
		dp[0][0] = cost[0][0];

		for (int i = 1; i <= m; i++) {
			dp[i][0] = dp[i - 1][0] + cost[i][0];
		}

		for (int j = 1; j <= n; j++) {
			dp[0][j] = dp[0][j - 1] + cost[0][j];
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = cost[i][j] + Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]);
			}
		}

		return dp[m][n];
	}

	void edit_distance() {
		String str1 = "unday";
		String str2 = "monday";

		pout.println(edit_distance_dp(str1, str2));
	}

	long edit_distance_dp(String s1, String s2) {
		int len1 = s1.length();
		int len2 = s2.length();

		long dp[][] = new long[len1 + 1][len2 + 1];

		for (int i = 0; i <= len1; i++) {
			for (int j = 0; j <= len2; j++) {
				if (i == 0) {
					dp[i][j] = j;
					continue;
				}

				if (j == 0) {
					dp[i][j] = i;
					continue;
				}

				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
				}
			}
		}

		return dp[len1][len2];
	}

	private void longest_common_subsequence() {
		String x = "AGGTAB";
		String y = "AGTB";

		pout.println("LCS: " + lcs(x, y));
	}

	int lcs(String input1, String input2) {
		int len1 = input1.length();
		int len2 = input2.length();
		int dp[][] = new int[len1 + 1][len2 + 1];
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (input1.charAt(i - 1) == input2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}

		return dp[len1][len2];
	}

	private void longest_increasing_subsequence() {
		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
		int n = arr.length;
		pout.println("Length of lis is " + lis(arr, n) + "\n");
	}

	int lis(int arr[], int n) {
		int lis[] = new int[n];
		int i, j, max = 0;

		for (i = 0; i < n; i++)
			lis[i] = 1;

		for (i = 1; i < n; i++) {
			for (j = 0; j < i; j++) {
				if (arr[i] > arr[j] && lis[i] < lis[j] + 1)
					lis[i] = lis[j] + 1;
			}
		}

		for (i = 0; i < n; i++)
			if (max < lis[i])
				max = lis[i];

		return max;
	}

}
