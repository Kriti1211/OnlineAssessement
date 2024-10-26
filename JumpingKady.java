import java.util.*;

//Correct code
public class JumpingKady {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int X = sc.nextInt();
        int sx = sc.nextInt() - 1;
        int sy = sc.nextInt() - 1;
        int ex = sc.nextInt() - 1;
        int ey = sc.nextInt() - 1;

        int[][] grid = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { sx, sy, 0 });
        grid[sx][sy] = 1;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int dist = curr[2];

            if (x == ex && y == ey) {
                System.out.println(dist);
                return;
            }

            for (int i = -X; i <= X; i++) {
                for (int j = -X; j <= X; j++) {
                    if (Math.abs(i) + Math.abs(j) == X) {
                        int newX = x + i;
                        int newY = y + j;
                        if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == 0) {
                            queue.offer(new int[] { newX, newY, dist + 1 });
                            grid[newX][newY] = 1;
                        }
                    }
                }
            }
        }

        System.out.println(-1);
    }
}