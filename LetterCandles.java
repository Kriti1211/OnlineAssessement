import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

// Correct code
public class LetterCandles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read the number of letter candles
        int n = sc.nextInt();
        // Read the number of candles Alice can remove
        int m = sc.nextInt();
        // Read the string representing the letter candles in the box
        String s = sc.next();

        // Count the frequency of each letter
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        // Use a max-heap (priority queue) to store frequencies in descending order
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int freq : frequencyMap.values()) {
            maxHeap.add(freq);
        }

        // Remove candles to minimize cost
        while (m > 0 && !maxHeap.isEmpty()) {
            int highestFreq = maxHeap.poll();
            if (highestFreq > 1) {
                maxHeap.add(highestFreq - 1);
            }
            m--;
        }

        // Calculate the minimum possible cost
        int minCost = 0;
        while (!maxHeap.isEmpty()) {
            int freq = maxHeap.poll();
            minCost += freq * freq;
        }

        // Output the minimum possible cost
        System.out.println(minCost);
    }
}
