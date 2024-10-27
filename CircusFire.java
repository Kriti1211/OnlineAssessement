import java.util.Scanner;

// partial correct 
public class CircusFire {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read dimensions and number of combinations
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int t = scanner.nextInt();

        // Process each combination
        for (int i = 0; i < t; i++) {
            int fx = scanner.nextInt();
            int fy = scanner.nextInt();
            int ix = scanner.nextInt();
            int iy = scanner.nextInt();
            int jx = scanner.nextInt();
            int jy = scanner.nextInt();

            // Calculate time taken for Jimmy to reach the destination
            int jimmyTime = Math.abs(ix - jx) + Math.abs(iy - jy);
            // Calculate the time taken for fire to reach the destination
            int fireTime = Math.max(Math.abs(fx - jx), Math.abs(fy - jy));

            // Determine if Jimmy can reach before the fire
            if (jimmyTime < fireTime) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        scanner.close();
    }
}
