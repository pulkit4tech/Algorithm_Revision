
/**
*
* @author pulkit4tech
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

// Huffman Coding for lossless data compression
// Greedy Algorithm
class Huffman_Coding implements Runnable {

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
		new Thread(new Huffman_Coding()).start();
	}

	void solve() throws Exception {
		Huffman();
	}

	void Huffman() {
		char arr[] = { 'p', 'u', 'l', 'k', 'i', 't' };
		long freq[] = { 1, 1, 12, 13, 16, 45 };
		int size = arr.length;
		HuffmanCodes(arr, freq, size);
	}

	class MinHeapNode {
		char data;
		long freq;
		MinHeapNode left, right;

		public MinHeapNode(char data, long freq) {
			this.data = data;
			this.freq = freq;
		}
	}

	void HuffmanCodes(char data[], long freq[], int size) {
		MinHeapNode left, right, top;

		PriorityQueue<MinHeapNode> pq = new PriorityQueue<>(new Comparator<MinHeapNode>() {

			@Override
			public int compare(MinHeapNode o1, MinHeapNode o2) {
				// TODO Auto-generated method stub
				return Long.compare(o1.freq, o2.freq);
			}
		});

		for (int i = 0; i < size; i++) {
			pq.add(new MinHeapNode(data[i], freq[i]));
		}

		while (pq.size() != 1) {
			left = pq.poll();
			right = pq.poll();

			top = new MinHeapNode('$', left.freq + right.freq);
			top.left = left;
			top.right = right;

			pq.add(top);
		}

		printCodes(pq.peek(), "");
	}

	void printCodes(MinHeapNode root, String str) {
		if (root == null)
			return;

		if (root.data != '$')
			pout.println(root.data + ":" + str);

		printCodes(root.left, str + "0");
		printCodes(root.right, str + "1");
	}

}
