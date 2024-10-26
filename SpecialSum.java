import java.util.Scanner;

// Correct code
public class SpecialSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the size of the array
        int n = sc.nextInt();
        int[] arr = new int[n];

        // Read the array elements
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Edge cases
        if (n == 0) {
            System.out.println(0);
            return;
        }
        if (n == 1) {
            System.out.println(arr[0]);
            return;
        }

        // DP array to store the maximum sum up to each index
        int[] dp = new int[n];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        // Fill the DP array
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + arr[i]);
        }

        // The last element in dp array has the maximum sum possible
        System.out.println(dp[n - 1]);
    }
}
