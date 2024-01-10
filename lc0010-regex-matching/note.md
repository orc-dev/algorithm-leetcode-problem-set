### Leetcode 10. Regular Expression Matching

#### @ DP State Specification
 - On the i-th iteration, `dp[j]` represents the matching state between `pattern[0:i]` and `string[0:j - 1]`.

#### @ DP Tabular Representation
    ```python
           j: 0     1     2     3     4     5     6    7      8     9 
            | "" | "c" | "a" | "t" | "a" | "n" | "d" | "d" | "o" | "g" | (string: "catanddog")
     i | "" |TRUE|  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  | (init dp table)   
     0 |"c" |
     1 |"a*"|
     2 |"g*"|
     3 |".*"|
     4 |"d*"|
     5 |"d" |
     6 |"o" |
     7 |"g" |  - |  -  |  -  |  -  |  -  |  -  |  -  |  -  |  -  | ans |
    
       (patten: "ca*g*.*d*dog")
    ```

#### @ DP Recursion
 - 'char with asterisk'
    - If the top cell is true, current cell is true. Since current pattern token can match to empty string.
    - If the left cell is true and current cell has point-match, then current cell is true.
    ```java
        | TRUE |          |      |      |
        |_curr_|    or    | TRUE | _pm_ |
    ```

 - 'single char'
    - If top-left cell is true and current cell has point-match, then current cell is true.
    ```java
        | TRUE |      | 
        |      | _pm_ |
    ```

#### @ DP Implementation
 - For 'char with asterisk' case, we do forward traversal.
 - For 'single char' case, we do backward tranversal.

