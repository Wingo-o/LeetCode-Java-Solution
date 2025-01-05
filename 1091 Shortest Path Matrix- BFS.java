class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        
        // If start or end is blocked, return -1 immediately
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }
        
        // 8 possible movement directions (x, y)
        int[][] directions = {
            {1, 0}, {0, 1}, {-1, 0}, {0, -1},  // Horizontal and Vertical
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1} // Diagonal
        };
        
        // Queue for BFS (stores x, y, path length)
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1}); // Starting point (0,0) with path length 1
        
        // Mark the starting point as visited
        grid[0][0] = 1;
        
        // BFS Traversal
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int pathLength = current[2];
            
            // If we reach the bottom-right corner
            if (x == n - 1 && y == n - 1) {
                return pathLength;
            }
            
            // Explore all 8 directions
            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                
                // Check if within bounds and is passable
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && grid[nx][ny] == 0) {
                    queue.add(new int[]{nx, ny, pathLength + 1});
                    grid[nx][ny] = 1; // Mark as visited
                }
            }
        }
        
        // No path found
        return -1;
    }
}
/*

How the Code Works:
Edge Case Check:

If the start or end point is blocked (grid[0][0] or grid[n-1][n-1] is 1), return -1 immediately.
Queue Initialization:

Use a queue to perform BFS, storing the current cell’s coordinates (x, y) and the path length.
The starting cell (0,0) is enqueued with an initial path length of 1.
BFS Traversal:

Dequeue the front element, check if it's the target (n-1, n-1).
Explore all 8 possible directions from the current cell.
If the neighbor is within bounds and unvisited (grid[nx][ny] == 0), enqueue it with an incremented path length.
Mark visited cells by setting them to 1 to avoid revisiting.
Return Result:

If (n-1, n-1) is reached, return the path length.
If the queue is empty and the target is not reached, return -1.

 */