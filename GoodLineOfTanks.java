import java.util.*;

//King dreams - I
// Correct code
public class GoodLineOfTanks {
    public static List<Integer> createGoodLine(int n, int m) {
        List<Integer> result = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            result.add((i % m) + 1); // Fill with tank types from 1 to m in a cyclic manner
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // size of the line
        int m = scanner.nextInt(); // number of tank types

        List<Integer> goodLine = createGoodLine(n, m);

        // Output the result as space-separated integers
        for (int tank : goodLine) {
            System.out.print(tank + " ");
        }
    }
}
