import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: orc-dev
 * @updated: May 2024
 * 
 * Main ideas:
 *   - BFS (get min step)
 *   - Pruning (stack?, block1)
 *   - Data representation: 64-bit Integer (long)
 * 
 * Performance:
 *   - Runtime (13 ms)
 *   - Memory (44.5 MB)
 */
class Solution {
    
    record ZumaState(long board, long hand){ }
    
    public int findMinStep(String board, String hand) {
        // Init root state
        final ZumaState root = new ZumaState(encode(board), encode(hand));

        // Create the initial set containing the root state
        final List<ZumaState> curr = new ArrayList<>();
        curr.add(root);
        
        final Set<Long> visited = new HashSet<>();
        visited.add(root.board());

        // BFS to find min step
        return bfs(curr, visited, 1);
    }


    private int bfs(List<ZumaState> curr, Set<Long> visited, int step) {
        if (curr.isEmpty()) return -1;

        final List<ZumaState> next = new ArrayList<>();
        final long[] boardList = new long[32];

        for (ZumaState state : curr) {
            final List<long[]> handList = generateHandList(state.hand());
            int size = generateBoardList(boardList, state.board());
            
            for (long[] p : handList) {
                final long ball    = p[0];
                final long newHand = p[1];

                for (int i = 0; i < size; ++i) {
                    final long L = fetch(boardList[i], i + 1);
                    final long R = fetch(boardList[i], i - 1);
                    
                    // Pruning check
                    if (((step > 1) || (L != R)) && ball != L) {
                        continue;
                    }
                    final long rawBoard = boardList[i] | (ball << (i * 3));
                    final long newBoard = updateBoard(rawBoard, i, i);
                    
                    // Terminal check: Find solution
                    if (newBoard == 0) {
                        return step;
                    }
                    // Duplicates handling
                    if (newHand > 0 && !visited.contains(newBoard)) {
                        next.add(new ZumaState(newBoard, newHand));
                        visited.add(newBoard);
                    }
                }
            }
        }
        return bfs(next, new HashSet<>(), step + 1);
    }


    private long updateBoard(long board, int hi, int lo) {
        // Trerminal check: empty or diff-chars
        if (lo < 0 || fetch(board, hi) != fetch(board, lo)) {
            return removeRange(board, hi, lo);
        }
        // Settings for checking current pass
        final long currBall = fetch(board, hi);
        int count = (lo == hi) ? 1 : 2;

        // Check ball at hi + 1
        while (fetch(board, hi + 1) == currBall) {
            hi++;
            count++;
        }
        // Check ball at lo - 1
        while (fetch(board, lo - 1) == currBall) {
            lo--;
            count++;
        }
        // If not enough contigurous balls can be canceled
        if (count < 3) {
            return removeRange(board, hi, lo);
        }
        return updateBoard(board, hi + 1, lo - 1);
    }


    /** Returns the "ball" at index-th "position" */
    private long fetch(long state, int index) {
        return (state >> (index * 3)) & 0b111;
    }

    
    private int generateBoardList(long[] list, long board) {
        int k = 0;
        while (true) {
            list[k] = insertHelper(board, k);
            if (fetch(board, k) == 0)
                break;
            k++;
        }
        return k;
    }


    private List<long[]> generateHandList(long hand) {
        final List<long[]> handList = new ArrayList<>();
        long lastBall = 0L;
        
        for (int i = 0; true; ++i) {
            final long pickBall = fetch(hand, i);
            if (pickBall == 0)
                break;

            if (pickBall != lastBall) {
                final long nextHand = removeHelper(hand, i);
                handList.add(new long[]{pickBall, nextHand});
            }
            lastBall = pickBall;
        }
        return handList;
    }


    private long encode(String state) {
        long result = 0L;
        for (char c : state.toCharArray()) {
            result = (result << 3) | encodeToBin(c);
        }
        return result;
    }


    /** Return a 64-bit with n 1's on the least significant side. */
    private long trailingOnesMask(int n) {
        if (n < 1) {
            return 0L;
        }
        return (-1L) >>> (64 - n);
    }


    /** Remove everything inbetween [hi] and [lo], exclusive */
    private long removeRange(long state, int hi, int lo) {
        if (hi - lo <= 1) return state;
        lo++;
        final int rsh = (hi - lo) * 3;
        final long mask = trailingOnesMask(lo * 3);
        return ((state >> rsh) & ~mask) | (state & mask);
    }


    private long removeHelper(long state, int offset) {
        final long mask = trailingOnesMask(offset * 3);
        return ((state >> 3) & ~mask) | (state & mask);
    }


    private long insertHelper(long state, int offset) {
        final long mask = trailingOnesMask(offset * 3);
        return ((state & ~mask) << 3) | (state & mask);
    }


    private int encodeToBin(char c) {
        return switch (c) {
            case '_' -> 0; 
            case 'B' -> 1;
            case 'G' -> 2;
            case 'R' -> 3;
            case 'W' -> 4;
            case 'Y' -> 5;
            default -> 
                throw new IllegalArgumentException("Invalid color code: " + c);
        };
    }


    /* Debug */
    private String decode(long board) {
        StringBuilder seq = new StringBuilder();
        while (board > 0) {
            char ch = decodeToChar(board & 0b111);
            seq.append(ch);
            board >>= 3;
        }
        return seq.reverse().toString();
    }

    /* Debug */
    private char decodeToChar(long num) {
        return switch((int) num) {
            case 0 -> '_';
            case 1 -> 'B';
            case 2 -> 'G';
            case 3 -> 'R';
            case 4 -> 'W';
            case 5 -> 'Y';
            default -> 
                throw new IllegalArgumentException("Invalid num: " + num);
        };
    }

    /* Debug */
    public static void main(String[] args) {
        System.out.println("Running tests for private methods.");

        Solution unit = new Solution();
        String board = "RGBYWRGBYWRGBYWR";
        String hand = "RGBYW";

        int ans = unit.findMinStep(board, hand);
        System.err.println("ans: " + ans);
    }
}
