import java.util.Scanner;

//Correct
public class FuriousDhoni {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input dimensions of the matrix
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] matrix = new int[n][m];

        // Input matrix values
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        // Variable to store the maximum reward
        int maxReward = Integer.MIN_VALUE;

        // Loop through each starting cell (i, j)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Calculate maximum reward starting from cell (i, j)
                maxReward = Math.max(maxReward, getMaxRewardFromCell(matrix, i, j, n, m));
            }
        }

        // Output the maximum reward
        System.out.println(maxReward);
        sc.close();
    }

    // Function to calculate the maximum reward from a given cell (start from
    // matrix[i][j])
    private static int getMaxRewardFromCell(int[][] matrix, int i, int j, int n, int m) {
        int maxReward = Integer.MIN_VALUE;

        // Check cells to the right and below the current cell
        for (int x = i; x < n; x++) {
            for (int y = j; y < m; y++) {
                if (x != i || y != j) { // Ensure at least two players are involved
                    int reward = Math.abs(matrix[x][y] - matrix[i][j]);
                    maxReward = Math.max(maxReward, reward);
                }
            }
        }

        return maxReward;
    }
}
