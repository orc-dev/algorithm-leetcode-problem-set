import java.util.List;
import java.util.StringJoiner;

class ProblemSolver {
    
    public static void main(String[] args) {
        Solution solver = new Solution();
        List<TreeNode> list = solver.generateTrees(8);

        list = solver.generateTrees(3);
        for (TreeNode t : list) {
            System.out.println(toString(t));
        }
    }

    public static String toString(TreeNode node) {
        StringJoiner sj = new StringJoiner(" ", "[", "]");
        stringify(node, sj);
        return sj.toString();
    }

    private static void stringify(TreeNode node, StringJoiner sj) {
        if (node == null) {
            sj.add("_");
            return;
        }
        sj.add(String.valueOf(node.val));
        stringify(node.left, sj);
        stringify(node.right, sj);
    }
}
