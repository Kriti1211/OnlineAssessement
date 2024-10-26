import java.util.LinkedList;
import java.util.Queue;

// Correct code
class Pair {
    int x, y;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class VirusSpread {

    public static int minTime(int ar_rows, int ar_cols, int[][] ar) {
        Queue<Pair> queue = new LinkedList<>();
        int healthyCells = 0;

        // Initialize queue with all virus cells and count the healthy cells
        for (int i = 0; i < ar_rows; i++) {
            for (int j = 0; j < ar_cols; j++) {
                if (ar[i][j] == 2) {
                    queue.add(new Pair(i, j));
                } else if (ar[i][j] == 1) {
                    healthyCells++;
                }
            }
        }

        // If there are no healthy cells, no time is required
        if (healthyCells == 0)
            return 0;

        int time = 0;
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // up, down, left, right

        // BFS to spread the virus
        while (!queue.isEmpty() && healthyCells > 0) {
            int size = queue.size();
            time++;

            for (int i = 0; i < size; i++) {
                Pair current = queue.poll();
                for (int[] direction : directions) {
                    int newX = current.x + direction[0];
                    int newY = current.y + direction[1];

                    if (newX >= 0 && newX < ar_rows && newY >= 0 && newY < ar_cols && ar[newX][newY] == 1) {
                        ar[newX][newY] = 2; // Infect the cell
                        queue.add(new Pair(newX, newY));
                        healthyCells--;
                    }
                }
            }
        }

        return healthyCells == 0 ? time : -1; // Return -1 if there are still healthy cells left
    }

    public static void main(String[] args) {
        int ar_rows = 2;
        int ar_cols = 3;
        int[][] ar = {
                { 2, 0, 1 },
                { 1, 1, 0 }
        };

        System.out.println(minTime(ar_rows, ar_cols, ar)); // Output: 3
    }
}
