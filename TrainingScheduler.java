import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TrainingScheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read number of courses
        int M = sc.nextInt();

        // Read time taken for courses in Training 1
        int[] T1 = new int[M];
        for (int i = 0; i < M; i++) {
            T1[i] = sc.nextInt();
        }

        // Read time taken for courses in Training 2
        int[] T2 = new int[M];
        for (int i = 0; i < M; i++) {
            T2[i] = sc.nextInt();
        }

        // Read number of prior commitments
        int P = sc.nextInt();

        // Read commitments
        List<int[]> commitments = new ArrayList<>();
        for (int i = 0; i < P; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            commitments.add(new int[] { start, end });
        }

        // Find available time slots
        List<int[]> availableSlots = findAvailableSlots(commitments);

        // Try to schedule courses
        int result = scheduleCourses(M, T1, T2, availableSlots);
        System.out.println(result);
    }

    private static List<int[]> findAvailableSlots(List<int[]> commitments) {
        List<int[]> availableSlots = new ArrayList<>();
        int lastEnd = 0; // Start of the day

        // Sort commitments by starting time
        commitments.sort((a, b) -> Integer.compare(a[0], b[0]));

        // Add available slots between commitments
        for (int[] commitment : commitments) {
            if (commitment[0] > lastEnd) {
                availableSlots.add(new int[] { lastEnd, commitment[0] });
            }
            lastEnd = Math.max(lastEnd, commitment[1]);
        }

        // Check if there's time after the last commitment
        if (lastEnd < 24) {
            availableSlots.add(new int[] { lastEnd, 24 });
        }

        return availableSlots;
    }

    private static int scheduleCourses(int M, int[] T1, int[] T2, List<int[]> availableSlots) {
        int totalDays = 0;
        int courseIndex = 0;

        // Combine courses of T1 and T2
        int[] totalCourses = new int[M * 2];
        System.arraycopy(T1, 0, totalCourses, 0, M);
        System.arraycopy(T2, 0, totalCourses, M, M);

        // Track the time left in the current day
        int timeLeftInDay = 0;

        // Schedule the courses
        for (int i = 0; i < totalCourses.length; i++) {
            int courseDuration = totalCourses[i];
            boolean scheduled = false;

            // Find a suitable time slot
            for (int j = 0; j < availableSlots.size(); j++) {
                int[] slot = availableSlots.get(j);
                int availableTime = slot[1] - slot[0];

                // If time left in the day, try to use it first
                if (timeLeftInDay > 0) {
                    if (timeLeftInDay >= courseDuration) {
                        timeLeftInDay -= courseDuration;
                        scheduled = true;
                        break;
                    } else {
                        // Use the remaining time in the current day
                        courseDuration -= timeLeftInDay;
                        timeLeftInDay = 0;
                    }
                }

                // If we still need more time, use the available slot
                if (courseDuration > 0) {
                    if (availableTime >= courseDuration) {
                        timeLeftInDay = availableTime - courseDuration; // Set remaining time in the day
                        scheduled = true;
                        // Increment total days only if we used a time slot
                        if (timeLeftInDay == 0) {
                            totalDays++;
                        }
                        break;
                    } else {
                        // Use the entire available slot
                        courseDuration -= availableTime;
                    }
                }
            }

            if (!scheduled) {
                return -1; // If we cannot schedule a course, return -1
            }
        }

        // If there's leftover time in the last day, count it
        if (timeLeftInDay > 0) {
            totalDays++;
        }

        return totalDays;
    }
}
