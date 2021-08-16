/**
 * You are asked to cut off all the trees in a forest for a golf event. The forest is represented as an m x n matrix. In this matrix:

0 means the cell cannot be walked through.
1 represents an empty cell that can be walked through.
A number greater than 1 represents a tree in a cell that can be walked through, and this number is the tree's height.
In one step, you can walk in any of the four directions: north, east, south, and west. If you are standing in a cell with a tree, you can choose whether to cut it off.

You must cut off the trees in order from shortest to tallest. When you cut off a tree, the value at its cell becomes 1 (an empty cell).

Starting from the point (0, 0), return the minimum steps you need to walk to cut off all the trees. If you cannot cut off all the trees, return -1.

You are guaranteed that no two trees have the same height, and there is at least one tree needs to be cut off.

 

Example 1:


Input: forest = [[1,2,3],[0,0,4],[7,6,5]]
Output: 6
Explanation: Following the path above allows you to cut off the trees from shortest to tallest in 6 steps.
Example 2:


Input: forest = [[1,2,3],[0,0,0],[7,6,5]]
Output: -1
Explanation: The trees in the bottom row cannot be accessed as the middle row is blocked.
Example 3:

Input: forest = [[2,3,4],[0,0,5],[8,7,6]]
Output: 6
Explanation: You can follow the same path as Example 1 to cut off all the trees.
Note that you can cut off the first tree at (0, 0) before making any steps.
 

Constraints:

m == forest.length
n == forest[i].length
1 <= m, n <= 50
0 <= forest[i][j] <= 109
 */
import java.util.*;

public class CutOffTreesForGolfEvent {

    public int cutOffTree(List<List<Integer>> forest) {
        if (forest.get(0).get(0) == 0)
            return -1;
        
        List<Integer> height = new ArrayList<>();
        HashMap<Integer,Cell> map = new HashMap<>();

        for (int i=0;i<forest.size();i++) {
            for (int j=0;j<forest.get(0).size();j++) {
                if (forest.get(i).get(j) > 1) {
                    height.add(forest.get(i).get(j));
                    map.put(forest.get(i).get(j), new Cell(i,j));
                }
            }
        }
        
        Collections.sort(height);
        int index = 0;
        int count = height.size();
        Cell curr = new Cell(0,0);
        int res = 0;
        while (count > 0) {
            Cell destination = map.get(height.get(index));
            int distance = bfs(curr, destination, forest);
            if (distance == -1) return -1;
            res += distance;
            curr = destination;
            index++;
            count--;
        }
        return res;
    }
    
    public int bfs(Cell curr, Cell destination, List<List<Integer>> forest) {
        boolean[][] seen = new boolean[forest.size()][forest.get(0).size()];
        int[][] path = new int[][]{{-1,0}, {1,0}, {0, 1}, {0, -1}};
        Queue<Cell> q = new LinkedList<>();
        q.offer(curr);
        seen[curr.x][curr.y] = true;
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                Cell node = q.poll();
                size--;
                if (node.match(destination)) return level;
                for (int[] p: path) {
                    int nextX = node.x + p[0];
                    int nextY = node.y + p[1];
                    if (valid(nextX, nextY, forest) && !seen[nextX][nextY]) {
                        q.offer(new Cell(nextX, nextY));
                        seen[nextX][nextY] = true;
                    }
                }
            }
            level++;
        }
        return -1;
    }
    
    public boolean valid(int x, int y, List<List<Integer>> forest) {
        int r = forest.size();
        int c = forest.get(0).size();
        if (x < 0 || y < 0 || x >= r || y >= c) return false;
        else if (forest.get(x).get(y) == 0) return false;
        else {
            return true;
        }
    }

    class Cell {
        public int x;
        public int y;
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean match(Cell other) {
            return this.x == other.x && this.y == other.y;
        }
    }    
}
