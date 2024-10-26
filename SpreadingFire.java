import java.util.*;
//Correct code

public class SpreadingFire {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read dimensions of the plane and number of fire points
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K = scanner.nextInt();

        // Read fire points
        List<int[]> firePoints = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            firePoints.add(new int[] { x, y });
        }

        // Read radius increment per time unit
        int R = scanner.nextInt();

        // Read time T
        int T = scanner.nextInt();

        // Calculate the number of untouched points
        long untouchedPoints = calculateUntouchedPoints(N, M, firePoints, R, T);

        // Output the result
        System.out.println(untouchedPoints);
    }

    private static long calculateUntouchedPoints(int N, int M, List<int[]> firePoints, int R, int T) {
        boolean[][] burned = new boolean[N + 1][M + 1]; // Create a grid to track burned points

        // Calculate the radius at time T
        int currentRadius = R * T; // Total radius after time T

        for (int[] point : firePoints) {
            int centerX = point[0];
            int centerY = point[1];

            // Mark all points within the current radius as burned
            for (int x = Math.max(0, centerX - currentRadius); x <= Math.min(N, centerX + currentRadius); x++) {
                for (int y = Math.max(0, centerY - currentRadius); y <= Math.min(M, centerY + currentRadius); y++) {
                    if (Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2) <= Math.pow(currentRadius, 2)) {
                        burned[x][y] = true;
                    }
                }
            }
        }

        // Count untouched points
        long count = 0;
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (!burned[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }
}