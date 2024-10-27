
class WeddingToast {
    public double champagneTower(int k, int r, int c) {
        // If no champagne is poured, the glass is empty.
        if (k == 0)
            return 0.0;

        // Create a 2D array to represent the glasses pyramid.
        double[][] dp = new double[r + 1][c + 2];
        dp[0][0] = k; // Put all the poured champagne in the top glass.

        // Iterate through each row.
        for (int i = 0; i < r; i++) {
            for (int j = 0; j <= c; j++) {
                if (dp[i][j] > 1.0) {
                    // Calculate excess champagne and distribute it equally to the glasses below.
                    double spare = dp[i][j] - 1;
                    spare /= 2;
                    dp[i][j] = 1; // Set the current glass to hold exactly one cup.
                    dp[i + 1][j] += spare; // Distribute excess to the left glass below.
                    dp[i + 1][j + 1] += spare; // Distribute excess to the right glass below.
                }
            }
        }

        // Check the value in the desired glass (r, c).
        double result = dp[r][c];

        // Ensure that the value doesn't exceed 1, as each glass can hold a maximum of
        // one cup.
        return Math.min(result, 1.0);
    }
}

// ChatGPT answer
/*
 * import java.util.Scanner;
 * import java.text.DecimalFormat;
 * 
 * public class WeddingToast {
 * public static void main(String[] args) {
 * Scanner sc = new Scanner(System.in);
 * 
 * // Input K, I, and J
 * int K = sc.nextInt();
 * int I = sc.nextInt();
 * int J = sc.nextInt();
 * 
 * // Initialize a 2D array to store champagne amount in each glass
 * double[][] glasses = new double[I + 1][I + 1];
 * 
 * // Pour K units of champagne into the topmost glass
 * glasses[0][0] = K;
 * 
 * // Calculate overflow in each glass up to row I
 * for (int row = 0; row < I; row++) {
 * for (int col = 0; col <= row; col++) {
 * if (glasses[row][col] > 1.0) { // If the glass is overflowing
 * double overflow = glasses[row][col] - 1.0;
 * glasses[row][col] = 1.0; // Fill the glass to its capacity
 * glasses[row + 1][col] += overflow / 2.0; // Overflow to the glass below left
 * glasses[row + 1][col + 1] += overflow / 2.0; // Overflow to the glass below
 * right
 * }
 * }
 * }
 * 
 * // Format the output to 6 decimal places
 * DecimalFormat df = new DecimalFormat("0.000000");
 * 
 * // Output the amount of champagne in the jth glass of the Ith row
 * System.out.println(df.format(glasses[I - 1][J - 1]));
 * }
 * }
 * 
 */