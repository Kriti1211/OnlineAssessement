import java.util.*;

//Credential Verification I
//Correct code
public class MaximizeReward {

    static class IndexValue {
        int index;
        int value;

        IndexValue(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static int solution(int[] a, int[] b, int[] c) {
        int n = a.length;
        IndexValue[] indexValues = new IndexValue[n];

        // Create an array of IndexValue objects
        for (int i = 0; i < n; ++i) {
            indexValues[i] = new IndexValue(i, b[i]);
        }

        // Sort the indexValues array based on the values in array b
        Arrays.sort(indexValues, Comparator.comparingInt(iv -> iv.value));

        int s = 1 << n; // 2^n
        int[] t = new int[s];
        int[] dp = new int[s];
        int[] trailing = new int[s];

        // Initialize the trailing array for bit manipulation
        for (int i = 0; i < n; ++i) {
            trailing[1 << i] = i; // Store the index corresponding to the single bit set
        }

        // Fill the DP and trailing arrays
        for (int i = 1; i < s; ++i) {
            int prev = i & (i - 1); // Get the previous state
            int lastIndex = trailing[i & -i]; // Get the index of the last set bit
            int x = indexValues[lastIndex].index; // Get the original index from the sorted array

            t[i] = t[prev] + c[x]; // Calculate the total value for the current subset
            dp[i] = dp[prev] + a[x] - b[x] * t[i]; // Calculate the DP value for the current subset
        }

        // Return the maximum value from the dp array
        return Arrays.stream(dp).max().getAsInt();
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[] { 10, 10, 10 }, new int[] { 1, 2, 3 }, new int[] { 2, 2, 2 }));
    }
}
