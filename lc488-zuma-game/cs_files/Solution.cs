// Author: orc-dev
// Update: Jun. 2024
// 
// Performance
//   - Runtime (100 ms)
//   - Memory (50.64 MB)
class Solution {

    public int FindMinStep(string board, string hand) {
        Zuma z = new(board, hand);
        List<Zuma> init = new(){z};
        HashSet<long> visited = new(){z.Board};

        return BFS(init, visited, 0);
    }

    private int BFS(List<Zuma> curr, HashSet<long> visited, int depth) {
        if (curr.Count == 0)
            return -1;

        List<Zuma> next = new();

        foreach (Zuma z in curr) {
            List<Zuma>? neib = z.GetNextLevel(visited, depth);
            if (neib == null) {
                return depth + 1;
            }
            next.AddRange(neib);
        }
        return BFS(next, visited, depth + 1);
    }
}

// Organize board state and hand state
record Zuma {

    public long Board { get; }
    public long Hand  { get; }

    // Constructor with string args
    public Zuma(string boardStr, string handStr) {
        this.Board = Zuma.Encode(boardStr, false);
        this.Hand  = Zuma.Encode(handStr, true);
    }

    // Constructor with ulong args
    public Zuma(long board, long hand) {
        this.Board = board;
        this.Hand  = hand;
    }

    // Get states in next level
    public List<Zuma>? GetNextLevel(HashSet<long> visited, int depth) {
        List<Zuma> next = new();
        List<long> boardList = this.GetBoardList();
        List<(long, long)> handList = this.GetHandList();

        foreach ((long ball, long nextHand) in handList) {
            for (int i = 0; i < boardList.Count; ++i) {
                long nextBoard  = Zuma.ComputerBoard(boardList[i], ball, i * 3, depth);
                if (nextBoard == -1) 
                    continue;

                Zuma z = new(nextBoard, nextHand);
                if (z.Board == 0)
                    return null;

                if (z.Hand == 0 || visited.Contains(nextBoard))
                    continue;

                visited.Add(nextBoard);
                next.Add(z);
            }
        }
        return next;
    }

    // Compute the board state after insert 'ball' at 'pos' of current board
    private static long ComputerBoard(long insBoard, long ball, int pos, int depth) {
        long L = (insBoard >> (pos + 3)) & 0x7;
        long R = (insBoard >> (pos - 3)) & 0x7;

        if (depth == 0 && ball != L && ball != R && L != R ||
            depth  > 0 && ball != L && ball != R)
            return -1;
        
        long board = insBoard | (ball << pos);
        long stack = 0;

        for (int i = 0; i < 64; i += 3) {
            long curr = (board >> i) & 0x7;
            long top  = (stack & 0x7);

            // pop (if possible)
            if ((top > 0) && (curr != top) && 
                ((stack >> 3) & 0x3f) == (stack & 0x3f)
            ) {
                stack >>= 9;
                if ((stack & 0x7) == top)
                    stack >>= 3;
            }

            if (curr == 0)
                break;
            else
                stack = (stack << 3) | curr; 
        }
        return stack;
    }

    // Returns a tuple (picked ball, remaining hand)
    private List<(long, long)> GetHandList() {
        List<(long, long)> handList = new();
        long prevBall = 0;
        long ballMask = 0;
        
        for (int i = 0; i < 16; i += 3) {
            long currBall = (this.Hand >> i) & 0x7;
            if (currBall == 0)
                break;

            if (currBall != prevBall) {
                prevBall = currBall;
                handList.Add((
                    currBall,
                    ((this.Hand >> 3) & ~ballMask) | (this.Hand & ballMask)
                ));
            }
            ballMask = (ballMask << 3) | 0x7;
        }
        return handList;
    }

    // Returns a List of board states with possible inserting holes
    private List<long> GetBoardList() {
        List<long> boardList = new();
        long insBoard = this.Board << 3;
        long ballMask = 0x7;
        boardList.Add(insBoard);

        while (true) {
            long currBall = this.Board & ballMask;
            if (currBall == 0)
                break;

            ballMask <<= 3;
            insBoard = (insBoard | currBall) & ~ballMask;
            boardList.Add(insBoard);
        }
        return boardList;
    }

    // Convert string representation to bit-based representation
    private static long Encode(string stateStr, bool sortFlag) {
        List<char> charList = stateStr.ToList();
        if (sortFlag)
            charList.Sort();

        return charList.Select(Zuma.Encode)
                       .Aggregate(0L, (stateBit, ball) => (stateBit << 3 | ball));
    }

    // Convert a char to a 3-bit group
    private static long Encode(char ch) => ch switch {
        'R' => 0x1,
        'G' => 0x2,
        'B' => 0x3,
        'W' => 0x4,
        'Y' => 0x5,
        _ => throw new ArgumentException($"Invalid char: {ch}")
    };
}