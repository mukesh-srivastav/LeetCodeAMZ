/**
 * There are 8 prison cells in a row and each cell is either occupied or vacant.

Each day, whether the cell is occupied or vacant changes according to the following rules:

If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
Otherwise, it becomes vacant.
Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.

You are given an integer array cells where cells[i] == 1 if the ith cell is occupied and cells[i] == 0 if the ith cell is vacant, and you are given an integer n.

Return the state of the prison after n days (i.e., n such changes described above).

 

Example 1:

Input: cells = [0,1,0,1,1,0,0,1], n = 7
Output: [0,0,1,1,0,0,0,0]
Explanation: The following table summarizes the state of the prison on each day:
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
Example 2:

Input: cells = [1,0,0,1,0,0,1,0], n = 1000000000
Output: [0,0,1,1,1,1,1,0]

 */
import java.util.*;
public class PrisonCellsAfterNDays {
    public int[] prisonAfterNDays(int[] cells, int n) {
        int res[] = new int[8];
        // There are only 6 cells that can change, 
        //so all possibilities are 2^6 = 64. 
        //We can use a hashmap with max capacity of 64 to hash each of the state to detect if that state is being repeated or not and get remainder from that particular counter (in case this divison by 14 is not understood).
        n = (n%14 == 0) ? 14: n%14; 
        while (n-- > 0) {
            for (int i=1; i<7; i++) {
                res[i]  = (cells[i-1] == cells[i+1]) ? 1 : 0;
            }
            res[0] = 0;
            res[7] = 0;
            
            cells = Arrays.copyOf(res, 8);
        }
        
        return res;
    }
}
