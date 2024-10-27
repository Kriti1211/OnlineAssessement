import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//hackerearth
class Goblet {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Read the first line containing N and K
        String[] firstLine = br.readLine().split(" ");
        int N = Integer.parseInt(firstLine[0]); // Number of cities
        int K = Integer.parseInt(firstLine[1]); // Distance K

        // Read the second line containing city information
        String[] cityInfo = br.readLine().split(" ");
        int[] cities = new int[N];
        for (int i = 0; i < N; i++) {
            cities[i] = Integer.parseInt(cityInfo[i]);
        }

        int portkeys = 0; // Minimum portkeys required
        int i = 0; // Current city index

        while (i < N) {
            int pos = -1;

            // Check the farthest city within range K where a portkey can be placed
            for (int j = Math.min(N - 1, i + K - 1); j >= Math.max(0, i - K + 1); j--) {
                if (cities[j] == 1) {
                    pos = j;
                    break;
                }
            }

            // If no suitable city found within range, it's impossible to cover all cities
            if (pos == -1) {
                System.out.println(-1);
                return;
            }

            // Place a portkey at `pos` and move `i` to the next uncovered city
            portkeys++;
            i = pos + K;
        }

        System.out.println(portkeys);
    }
}
