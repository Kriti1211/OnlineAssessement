import java.util.Scanner;

public class LostInWoods {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        String path = scanner.next();

        StringBuilder signal = new StringBuilder();
        char prev = ' ';
        boolean isJumping = false;

        for (int i = 1; i < N; i++) { // Start from the second character, skipping 'S'
            char current = path.charAt(i);

            if (current == '*') {
                if (!isJumping) {
                    signal.append('J');
                    isJumping = true;
                }
            } else {
                if (isJumping) {
                    isJumping = false;
                }
                signal.append('W');
            }
        }

        System.out.println(signal.toString());
    }
}