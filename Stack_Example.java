import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;

/**
 *
 * @author pulkit4tech
 */
public class Stack_Example implements Runnable {

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
		new Thread(new Stack_Example()).start();
	}

	void solve() throws Exception {
		//balance_paranthesis();
		//next_greatest_element();
		stock_span();
	}
	
	void stock_span(){
		int price[] = {10, 4, 5, 90, 120, 80};
		calSpan(price);
	}
	
	void calSpan(int price[]){
		int len = price.length;
		Stack<Integer> st = new Stack<>();
		int S[] = new int[len];
		S[0] = 1;
		st.push(0);
		for(int i=1;i<len;i++){
			while(st.size()!=0 && price[st.peek()]<=price[i]){
				st.pop();
			}
			
			if(st.size()==0){
				S[i] = i+1;
			}
			else{
				S[i] = i - st.peek();
			}
			
			st.push(i);
		}
		
		for(int i=0;i<len;i++)
			pout.print(S[i] + " ");
		pout.println();
	}
	
	void next_greatest_element(){
		int arr[] = {11,13,1,2,3,21,14,2};
		printNGE(arr);
	}
	
	void printNGE(int arr[]){
		int len = arr.length;
		
		Stack<Integer> st = new Stack<>();
		st.push(arr[0]);
		for(int i=1;i<len;i++){
			int next = arr[i];
			if(st.size()>0){
				int el = st.pop();
				while(el<next){
					pout.println(el + " --> " + next);
					if(st.size()==0)
						break;
					el = st.pop();
				}
				
				if(el>next)
					st.push(el);
			}
			
			st.push(next);
		}
		
		while(st.size()>0){
			pout.println(st.pop() + " --> " + -1);
		}
	}
	
	void balance_paranthesis(){
		String exp = "{}({[]})[]";
		pout.println(areParenthesisBalnced(exp));
	}
	
	boolean isMatchingPair(char char1,char char2){
		if(char1 == '(' && char2 == ')')
			return true;
		
		if(char1 == '{' && char2 == '}')
			return true;
		
		if(char1 == '[' && char2 == ']')
			return true;
		
		return false;
	}
	
	boolean areParenthesisBalnced(String exp){
	
		int i=0;
		Stack<Character> st = new Stack<>();
		
		while(i<exp.length()){
			
			if(exp.charAt(i) == '{' || 
					exp.charAt(i) == '[' ||
					exp.charAt(i) == '(')
				st.push(exp.charAt(i));
			
			if(exp.charAt(i) == '}' || 
					exp.charAt(i) == ']' ||
					exp.charAt(i) == ')'){
				
				if(st.size()==0)
					return false;
				
				if(!isMatchingPair(st.pop(), exp.charAt(i)))
					return false;
			}
			i++;
		}
		
		if(st.size()==0)
			return true;
		else
			return false;
	}
}
