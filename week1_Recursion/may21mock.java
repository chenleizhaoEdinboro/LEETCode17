Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

public boolean exist(char[][] board, String word) {
    //corner case
    if (board == null || word == null || word.length() == 0){
        return false;
    }
    
    int m = board.length;
    int n = board[0].length;
    for (int i =0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (helper(board, word, i, j, 0)){
                return true;
            }
        }
    }
    
    return false;
}

i 1
j 0
k 1
letter B

private boolean helper(char[][] board, String word, int i, int j, int k) {
    int m = board.length;
    int n = board[0].length;
    
    //terminate case
    if (i >= m || j >= n){
        return false;
    }
    
    if (board[i][j] == word.charAt(k)) {
        char letter = board[i][j];
        //leave a mark of this letter
        board[i][j] = '*';
        if (k == word.length() - 1) {
            return true;
        }
        
        if (
            helper(board, word, i - 1, j, k + 1) ||
            helper(board, word, i, j - 1, k + 1) ||
            helper(board, word, i+1, j, k + 1) ||
            helper(board, word, i, j+1, k+1)) {
                return true;
         }
       board[i][j] = letter;
    }
    
    return false;
}

// n * n

public class Solution {
    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || word.length() == 0) {
            return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

// n^2 * 3 ^(n/2)

// Set<Visit>
//  class Visit {
            int x, y, i;
            hashCode()
            equals() x == x, y == y, i == i;
// }
//
    // based on [x, y], search neighbors of char at index of word
    private boolean exist(char[][] board, String word, int i, int x, int y, Set<Point> visited) {
        if (i == word.length()) {
            return true;
        }
        if (x < 0 || y < 0 || x == board.length || y == board[0].length) {
            return false;
        }
        char old = board[x][y];
        if (old != word.charAt(i)) {
            return false;
        }
        board[x][y] = '#';
        for (int[] dir : dirs) {
            if (exist(board, word, i + 1, x + dir[0], y + dir[1])) {
                board[x][y] = old;
                return true;
            }
        }
        board[x][y] = old;
        return false;
    }
} 


    /**
     * Surrounded Regions 
 Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,

X X X X
X O O X
X X O X
X O X X

After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int m = board.length, n = board[0].length;
        Queue<Point> q = new LinkedList<>();
        // Scan the four edges of the board, if you meet an 'O', call a
        // recursive mark function to mark that region to '+';
        for (int i = 0; i < m; i++) {
            q.add(new Point(i, 0));
            q.add(new Point(i, n - 1));
        }
        for (int j = 1; j < n - 1; j++) {
            q.add(new Point(0, j));
            q.add(new Point(m - 1, j));
        }
        while (!q.isEmpty()) {
            Point p = q.poll();
            if (board[p.x][p.y] != 'O') {
                continue;
            }
            for (Point neighbor : getNeighbors(p, board)) {
                q.add(neighbor);
            }
            board[p.x][p.y] = '+';
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    // flip 'O' to 'X';
                    board[i][j] = 'X';
                } else if (board[i][j] == '+') {
                    // flip '+' to 'O';
                    board[i][j] = 'O';
                }
            }
        }
    }

    private List<Point> getNeighbors(Point p, char[][] board) {
        int m = board.length, n = board[0].length;
        List<Point> neighbors = new ArrayList<>();
        for (Point point : new Point[] { new Point(p.x - 1, p.y), new Point(p.x + 1, p.y), new Point(p.x, p.y - 1),
                new Point(p.x, p.y + 1) }) {
            if (point.x >= 0 && point.x < m && point.y >= 0 && point.y < n && board[point.x][point.y] == 'O') {
                neighbors.add(point);
            }
        }
        return neighbors;
    }

http://www.1point3acres.com/bbs/thread-279691-1-1.html
请大家总结一下，今天面试的同学，有什么地方表现的好，什么地方需要提高。回复在这个帖子下。










}