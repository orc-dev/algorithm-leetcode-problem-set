class ZumaSolver {

    static void Main() {
        const string board = "RBYYBBRRB";
        const string hand  = "YRBGB";

        Solution solver = new();
        int answer = solver.FindMinStep(board, hand);
       Console.WriteLine(answer);
    }
}
