class ProblemRunner {

    final static Solution solver = new Solution();
    public static void main(String[] args) {
        // run some tests
        runTest("RRGGBBYYWWRRGGBB", "RGBYW", -1);
        runTest("YYRRYYRYRYYRRYY", "RRRYR", 3);
        runTest("RRYGGYYRRYYGGYRR", "GGBBB", 5);
    }

    /** Run an instance of test */
    private static void runTest(String board, String hand, int answer) {
        final int studAns  = solver.findMinStep(board, hand);
        final String reset = "\u001B[0m";
        final String color = color(studAns == answer);
        
        System.out.println("Board : " + board);
        System.out.println("Hand  : " + hand);
        System.out.println("Answer: " + color + studAns + reset);
        System.out.println();
    }

    /** Set colors for correct and wrong answers */
    private static String color(boolean flag) {
        if (flag)
            return "\033[38;5;43m";
        else
            return "\033[38;5;160m";
    }
}
