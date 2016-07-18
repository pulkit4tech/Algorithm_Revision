
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
        char arr[] = {'p','u','l','k','i','t'};
        countingsort(arr);
        for(int i=0;i<arr.length;i++){
            pout.print(arr[i]+" ");
        }
    }
    
    void countingsort(char arr[]) {
            int n = arr.length;
            char output[] = new char[n];

            int count[] = new int[256];
            for (int i = 0; i < 256; ++i) {
                count[i] = 0;
            }

            // store count of each character
            for (int i = 0; i < n; ++i) {
                ++count[arr[i]];
            }

            for (int i = 1; i <= 255; ++i) {
                count[i] += count[i - 1];
            }

            // Build the output character array
            for (int i = 0; i < n; ++i) {
                output[count[arr[i]] - 1] = arr[i];
                --count[arr[i]];
            }

            for (int i = 0; i < n; ++i) {
                arr[i] = output[i];
            }
        }

    class Sorting {

        int data[];
        int len;

        public Sorting(int data[]) {

            this.data = data;
            len = data.length;
        }

        void quicksort() {
            quicksorthelper(data, 0, len - 1);
        }

        int partition(int arr[], int low, int high) {
            int pivot = arr[high];
            int i = (low - 1); // index of smaller element
            for (int j = low; j <= high - 1; j++) {
                // If current element is smaller than or
                // equal to pivot
                if (arr[j] <= pivot) {
                    i++;

                    // swap arr[i] and arr[j]
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            // swap arr[i+1] and arr[high] (or pivot)
            int temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;

            return i + 1;
        }

        void quicksorthelper(int arr[], int low, int high) {
            if (low < high) {

                int pi = partition(arr, low, high);

                quicksorthelper(arr, low, pi - 1);
                quicksorthelper(arr, pi + 1, high);
            }
        }

        void printArray(int arr[]) {
            int n = arr.length;
            for (int i = 0; i < n; ++i) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

        int[] mergesort() {
            int temp[] = new int[len];
            mergeSortG(data, temp, 0, len - 1);
            return data;
        }

        void mergeSortG(int[] inpArr, int[] tempArr, int begin, int end) {
            int mid = (begin + end) / 2;
            if (begin >= end) {
                return;
            }

            mergeSortG(inpArr, tempArr, begin, mid);
            mergeSortG(inpArr, tempArr, mid + 1, end);
            mergeG(inpArr, tempArr, begin, mid, end);

        }

        void mergeG(int[] inpArr, int[] tempArr, int begin, int mid, int end) {
            for (int i = begin; i <= end; i++) {
                tempArr[i] = inpArr[i];
            }

            int left = begin;
            int right = mid + 1;

            for (int current = begin; current <= end; current++) {
                if (left == mid + 1) {
                    inpArr[current] = tempArr[right++];
                } else if (right > end) {
                    inpArr[current] = tempArr[left++];
                } else if (tempArr[left] < (tempArr[right])) {
                    inpArr[current] = tempArr[left++];
                } else {
                    inpArr[current] = tempArr[right++];
                }
            }

        }

    }

    void phone_dialer(int n[]) {
        int len = n.length;
        char[] result = new char[len];
        printword(n, result, 0, len);
    }

    void printword(int n[], char result[], int curr, int len) {

        if (curr == len) {
            pout.println(result);
            return;
        }

        for (int i = 0; i < 3; i++) {
            result[curr] = getCharKey(n[curr], i);
            printword(n, result, curr + 1, len);
            if (n[curr] == 0 || n[curr] == 1) {
                return;
            }
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

