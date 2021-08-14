/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 

Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
 */
public class NumberOfIslands {
    int n, m;
    public int numIslands(char[][] grid) {
        this.n= grid.length;
        if (n == 0)
            return 0;
        this.m= grid[0].length;
        if (m == 0)
            return 0;
        int count = 0;
        boolean visited[][] = new boolean[n][m];
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == '1' && visited[i][j] == false) {
                    dfs(grid, i, j, visited);
                    count++;
                }
            }
        }
        
        return count;
    }
    
    private void dfs(char [][]grid, int i, int j, boolean visited[][]) {
        int di[] = new int[] {0, 0, 1, -1};
        int dy[] = new int[] {1, -1, 0, 0};
        
        visited[i][j] = true;
        
        for (int it=0; it<4; it++) {
            int nx = i + di[it];
            int ny = j + dy[it];
            
            if (isValid(nx, ny) && grid[nx][ny] == '1' && visited[nx][ny] == false) {
                dfs(grid, nx, ny, visited);
            }
        }
    }
    
    private boolean isValid(int i, int j) {
        return (i>=0 && j>=0 && i<n && j <m);
    }
}
