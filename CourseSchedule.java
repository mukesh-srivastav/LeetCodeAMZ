/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 

Constraints:

1 <= numCourses <= 105
0 <= prerequisites.length <= 5000
prerequisites[i].length == 2
0 <= ai, bi < numCourses
All the pairs prerequisites[i] are unique.
 */
import java.util.*;
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        boolean visited[] = new boolean[numCourses];
        boolean inTheCall[] = new boolean[numCourses];
        
        List<List<Integer>> g = new LinkedList<>();
        
        for (int i=0; i<numCourses; i++) {
            g.add(new LinkedList<Integer>());
        }
        
        for (int [] edge : prerequisites) {
            g.get(edge[0]).add(edge[1]);  
        }
        
        for (int i=0; i<numCourses; i++) {
            if (visited[i] == false) {
                if (isCyclic(i, g, visited, inTheCall))
                    return false;
            }
        }
        
        return true;
    }
    
    private boolean isCyclic(int v, List<List<Integer>> g, boolean[] visited, boolean[] inTheCall)
    {
        visited[v] = true;
        inTheCall[v] = true;
        
       List<Integer> adjV = g.get(v);
        
        for (int i : adjV) {
            if (visited[i] == false) {
                if (isCyclic(i, g, visited, inTheCall))
                    return true;
            } else if (inTheCall[i]) {
                    return true;
            }
        }
        
        inTheCall[v] = false;
        
        return false;
    }
}
