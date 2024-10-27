import java.util.*;

//Manageable
public class SmartGardener {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of sprinklers
        int N = scanner.nextInt();

        // Read ranges and costs
        int[] ranges = new int[N];
        int[] costs = new int[N];

        for (int i = 0; i < N; i++) {
            ranges[i] = scanner.nextInt();
        }

        for (int i = 0; i < N; i++) {
            costs[i] = scanner.nextInt();
        }

        // Calculate the minimum cost to water the garden
        int result = minCostToWaterGarden(N, ranges, costs);

        // Output the result
        System.out.println(result);

        // Close the scanner
        scanner.close();
    }

    private static int minCostToWaterGarden(int N, int[] ranges, int[] costs) {
        List<int[]> intervals = new ArrayList<>();

        // Create intervals based on sprinkler ranges and costs
        for (int i = 0; i < N; i++) {
            int start = Math.max(0, i - ranges[i]);
            int end = Math.min(N, i + ranges[i]);
            intervals.add(new int[] { start, end, costs[i] });
        }

        // Sort intervals by starting point and then by ending point
        intervals.sort(Comparator.comparingInt(a -> a[0]));

        int currentEnd = 0;
        int totalCost = 0;
        int index = 0;

        while (currentEnd < N) {
            int bestCost = Integer.MAX_VALUE;
            int bestEnd = currentEnd;

            // Find the best sprinkler to extend coverage
            while (index < intervals.size() && intervals.get(index)[0] <= currentEnd) {
                if (intervals.get(index)[1] > bestEnd) {
                    bestEnd = intervals.get(index)[1];
                    bestCost = intervals.get(index)[2];
                } else if (intervals.get(index)[1] == bestEnd) {
                    bestCost = Math.min(bestCost, intervals.get(index)[2]);
                }
                index++;
            }

            if (bestEnd <= currentEnd) {
                return -1; // Cannot cover the garden
            }

            totalCost += bestCost;
            currentEnd = bestEnd;
        }

        return totalCost;
    }
}