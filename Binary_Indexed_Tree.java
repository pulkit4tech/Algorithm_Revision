import java.io.PrintWriter;

class Binary_Indexed_Tree{
	
	
	
	private static void solve() throws Exception {

		PrintWriter pout = new PrintWriter(System.out,true);
		int arr[] = {9,1,4,9,0,4,8,9,0,1};
		
		int BIT[] = construct_BIT(arr);
		pout.println(get_val(BIT, 1, 4));
		update_val(BIT, -4, arr.length, 3);
		pout.println(get_val(BIT, 3, arr.length-1));
		
		pout.close();
	}
	
	public static int[] construct_BIT(int arr[]){
		int BIT[] = new int[arr.length + 1];
		
		for(int i = 0; i<arr.length; i++){
			update_val(BIT,arr[i],arr.length,i+1);
		}
		
		return BIT;
	}
	
	
	public static void update_val(int[] BIT, int new_val, int length, int index) {
		while(index<=length){
			BIT[index] = Math.min(BIT[index], new_val);
			index += index & (-index);
		}
	}
	
	public static int get_val(int BIT[], int start, int end){
		end++;
		int min_value = Integer.MAX_VALUE;
		while(end>0){
			min_value = Math.min(min_value, BIT[end]);
			end -= end & (-end);
		}
		
		return min_value;
	}
}