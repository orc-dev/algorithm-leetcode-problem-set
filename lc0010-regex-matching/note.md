### Leetcode 10. Regular Expression Matching

#### @ DP State Specification
 - On the i-th iteration, `dp[j]` represents the matching state between `pattern[0:i]` and `string[0:j - 1]`.

#### @ DP Tabular Representation
    ```java
    
    
    
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

