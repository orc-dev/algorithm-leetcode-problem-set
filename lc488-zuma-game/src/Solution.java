import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author: orc-dev
 * @updated: Jun. 2024
 * 
 * Thoughts log
 *   - BFS + pruning
 *   - bit-based state implementation
 *   - OOP: use Java record to organize board and hand state
 * 
 * Performance
 *   - Runtime (15 ms)
 *   - Memory (45.54 MB)
 */
class Solution {
    
    public int findMinStep(String board, String hand) {
        final Zuma zuma = Zuma.create(board, hand);
        final HashSet<Long> visited = new HashSet<>();
        final ArrayList<Zuma> init = new ArrayList<>();
        
        visited.add(zuma.board());
        init.add(zuma);
        return bfs(init, 0, visited);
    }

    private int bfs(ArrayList<Zuma> curr, int k, HashSet<Long> visited) {
        if (curr.isEmpty()) {
            return -1;
        }
        final ArrayList<Zuma> next = new ArrayList<>();

        for (Zuma zuma : curr) {
            ArrayList<Zuma> neib = zuma.getNextLevel(k, visited);
            if (neib == null) {
                return k + 1;
            }
            next.addAll(neib);
        }
        return bfs(next, k + 1, visited);
    }
}


/** 
 * Organize an instance of Zuma board state and hand state using long type.
 */
record Zuma(long board, long hand) {
    // Create a new Zuma record from given String representations
    public static Zuma create(String boardStr, String handStr) {
        return new Zuma(
            Zuma.encode(boardStr, false),
            Zuma.encode(handStr, true)
        );
    }

    // Returns a list of neighbors of this state, or null if board has cleared.
    public ArrayList<Zuma> getNextLevel(int depth, HashSet<Long> visited) {
        final ArrayList<Zuma> next = new ArrayList<>();
        final ArrayList<long[]> handList = this.buildHandList();
        final long[] boardList = new long[32];
        final int size = this.buildBoardList(boardList);
        
        for (long[] pair : handList) {
            for (int i = 0; i < size; ++i) {
                final long rawBoard = pruningCheck(boardList[i], pair[0], i * 3, depth);
                if (rawBoard == -1)
                    continue;

                final long nextBoard = updateBoard(rawBoard);
                if (nextBoard == 0)
                    return null;

                if (pair[1] == 0 || visited.contains(nextBoard))
                    continue;
                
                visited.add(nextBoard);
                next.add(new Zuma(nextBoard, pair[1]));
            }
        }
        return next;
    }

    // Make pruning decisions on local state of L, ball and R.
    private long pruningCheck(long insBoard, long ball, int pos, int depth) {
        final long L = (insBoard >> (pos + 3)) & 0x7;
        final long R = (insBoard >> (pos - 3)) & 0x7;

        if (depth == 0 && (ball != R) && (L != R) ||
            depth  > 0 && (ball != R)) {
            return -1;
        }
        return insBoard | (ball << pos);
    }

    // Returns updated board by removing all consecutive balls recursively
    private long updateBoard(long board) {
        long stack = 0;

        for (int i = 0; i < 64; i += 3) {
            final long curr = (board >> i) & 0x7;
            final long top  = (stack) & 0x7;

            // pop (if possible)
            if ((top > 0) && (curr != top) && 
                (stack & 0x3F) == ((stack >> 3) & 0x3F))
            {
                stack >>= 9;
                if ((stack & 0x7) == top)
                    stack >>= 3;
            }
            
            if (curr == 0)
                break;                        // done
            else
                stack = (stack << 3) | curr;  // push and continue
        }
        return stack;
    }

    // Returns a list of pairs of 'the picked ball' and 'the remaining hand'
    private ArrayList<long[]> buildHandList() {
        final ArrayList<long[]> handList = new ArrayList<>();
        long prevBall = 0;
        long ballMask = 0;
        
        for (int i = 0; i < 16; i += 3) {
            final long currBall = (this.hand >> i) & 0x7;
            if (currBall == 0)
                break;

            if (currBall != prevBall) {
                prevBall = currBall;
                handList.add(new long[]{ 
                    currBall, 
                    ((this.hand >> 3) & ~ballMask) | (this.hand & ballMask)
                });
            }
            // update masks
            ballMask = (ballMask << 3) | 0x7;
        }
        return handList;
    }

    // Returns a list of 'insertable board', eg: RB -> {_RB, R_B, RB_}
    private int buildBoardList(long[] buffer) {
        int ptr = 0;
        long ballMask = 0x7;
        long insBoard = this.board << 3;
        buffer[ptr++] = insBoard;

        while (true) {
            final long currBall = this.board & ballMask;
            if (currBall == 0)
                break;
            
            ballMask <<= 3;
            insBoard = (insBoard | currBall) & ~ballMask;
            buffer[ptr++] = insBoard;
        }
        return ptr;
    }

    // Convert a string to an integer representing the board or hand states
    private static long encode(String stateStr, boolean sortFlag) {
        final char[] stateChars = stateStr.toCharArray();
        if (sortFlag)
            Arrays.sort(stateChars);

        long stateBits = 0;
        for (char ch : stateChars) {
            stateBits = (stateBits << 3) | Zuma.encode(ch);
        }
        return stateBits;
    }

    // Map a character to an integer
    private static long encode(char ch) {
        return switch(ch) {
            case 'R' -> 0x1;
            case 'G' -> 0x2;
            case 'B' -> 0x3;
            case 'W' -> 0x4;
            case 'Y' -> 0x5;
            case ' ' -> 0x0;
            default  -> 
                throw new IllegalArgumentException("Invalid char: " + ch);
        };
    }
}
