import java.util.Scanner;

// Passes only 1st test case
public class GoldTreasure {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input for the first cave
        int n = sc.nextInt();
        int[] cave1 = new int[n];
        for (int i = 0; i < n; i++) {
            cave1[i] = sc.nextInt();
        }

        // Input for the second cave
        int m = sc.nextInt();
        int[] cave2 = new int[m];
        for (int i = 0; i < m; i++) {
            cave2[i] = sc.nextInt();
        }

        // Variables to store the collected gold from each cave
        int i = 0, j = 0;
        long sum1 = 0, sum2 = 0, maxCoins = 0;

        // Traverse both caves with two pointers
        while (i < n && j < m) {
            if (cave1[i] < cave2[j]) {
                sum1 += cave1[i++];
            } else if (cave1[i] > cave2[j]) {
                sum2 += cave2[j++];
            } else {
                // Collect the maximum possible coins and switch
                maxCoins += Math.max(sum1, sum2) + cave1[i];
                sum1 = 0;
                sum2 = 0;
                i++;
                j++;
            }
        }

        // Collect remaining coins from the longer remaining segment
        while (i < n) {
            sum1 += cave1[i++];
        }
        while (j < m) {
            sum2 += cave2[j++];
        }

        // Add the remaining maximum coins from either cave
        maxCoins += Math.max(sum1, sum2);

        // Output the result
        System.out.println(maxCoins);
    }
}
