class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Graph representation
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        // Build the adjacency list
        for (int[] pair : prerequisites) {
            graph.get(pair[1]).add(pair[0]);
        }

        // Visited arrays
        boolean[] visiting = new boolean[numCourses];
        boolean[] visited = new boolean[numCourses];

        // Perform DFS for cycle detection
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(i, graph, visiting, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasCycle(int course, List<List<Integer>> graph, boolean[] visiting, boolean[] visited) {
        if (visiting[course]) return true;  // Cycle detected
        if (visited[course]) return false;  // Already processed

        visiting[course] = true;
        for (int neighbor : graph.get(course)) {
            if (hasCycle(neighbor, graph, visiting, visited)) {
                return true;
            }
        }
        visiting[course] = false;
        visited[course] = true;
        return false;
    }
}

How This Fix Works:
Graph Representation: The prerequisites are treated as a directed graph where pair[1] points to pair[0].
DFS: We perform a DFS on each node (course). If we detect a node that is already being processed (visiting), this means there is a cycle.

Visiting vs. Visited:
visiting[course] - Marks the node as currently being processed (part of the current path).
visited[course] - Marks the node as fully processed.

Example Walkthrough:
Input: numCourses = 2, prerequisites = [[1, 0], [0, 1]]
The adjacency list will look like:
rust
Copy code
0 -> 1
1 -> 0
The DFS detects a cycle when traversing from 0 -> 1 -> 0.

Time Complexity:
Time: O(V + E), where V is the number of courses, and E is the number of prerequisites.
Space: O(V + E) for graph representation and recursion stack.
This approach correctly handles all cases and efficiently detects cycles in the graph of course prerequisites.
