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
		balance_paranthesis();
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
