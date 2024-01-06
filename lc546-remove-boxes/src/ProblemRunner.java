import java.util.Arrays;

class ProblemRunner {
    public static void main(String[] args) {
        // run some tests
        runTest(new int[]{1,3,2,2,2,3,4,3,1}, 23);
        runTest(new int[]{1, 2}, 2);
        runTest(new int[]{1,2,1,2,1}, 11);
    }

    /** Run an instance of test */
    private static void runTest(int[] boxes, int answer) {
        Solution solver = new Solution();
        int studAns = solver.removeBoxes(boxes);
        final String reset = "\u001B[0m";
        final String color = color(studAns == answer);
        
        System.out.println("boxes : " + Arrays.toString(boxes));
        System.out.println("output: " + color + studAns + reset);
        System.out.println();
        return;
    }

    /** Set colors for correct and wrong answers */
    private static String color(boolean flag) {
        if (flag)
            return "\033[38;5;43m";
        else
            return "\033[38;5;160m";
    }
}
