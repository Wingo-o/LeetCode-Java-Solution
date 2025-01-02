class Solution {
    private int[] p;

    public boolean validTree(int n, int[][] edges) {
        p = new int[n];
        for (int i = 0; i < n; ++i) {
            p[i] = i;
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            if (find(a) == find(b)) {
                return false;
            }
            p[find(a)] = find(b);
            --n;
        }
        return n == 1;
    }

    private int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }
}
/*
This solution uses **Union-Find (Disjoint Set Union, DSU)** to determine if the graph forms a valid tree. Let's break down why this works and how it addresses the key requirements for a valid tree.

---

### Key Properties of a Tree:
1. **A tree with `n` nodes must have exactly `n-1` edges.**  
2. **A tree is connected (all nodes are reachable from any other node).**  
3. **A tree has no cycles.**

---

### How the Union-Find Solution Works:

#### 1. **Union-Find Initialization:**
```java
p = new int[n];
for (int i = 0; i < n; ++i) {
    p[i] = i;
}
```
- Each node is initially its own parent (`p[i] = i`), meaning each node is in its own set.  
- This is the foundation of Union-Find – each node starts as its own component.

---

#### 2. **Processing the Edges:**
```java
for (int[] e : edges) {
    int a = e[0], b = e[1];
    if (find(a) == find(b)) {
        return false;  // Cycle detected
    }
    p[find(a)] = find(b);
    --n;
}
```
- For each edge `[a, b]`, the algorithm tries to **union** the sets of `a` and `b`.  
- If `find(a) == find(b)`, `a` and `b` are already in the same set, meaning adding this edge would form a **cycle**. Hence, the graph is **not a tree** (returns `false`).  
- If no cycle is found, the sets of `a` and `b` are merged (`p[find(a)] = find(b)`).  
- Each successful union reduces the count of connected components (`--n`).

---

#### 3. **Final Check for Connectivity:**
```java
return n == 1;
```
- After processing all edges, the graph is valid if and only if exactly **one connected component** remains (`n == 1`).

---

### Union-Find Helper (Path Compression):
```java
private int find(int x) {
    if (p[x] != x) {
        p[x] = find(p[x]);  // Path compression
    }
    return p[x];
}
```
- **Path compression** optimizes the `find` operation by flattening the tree structure, making subsequent finds faster (`O(1)` amortized).  
- This ensures efficient union and find operations, resulting in near constant-time performance.

---

### Why This Works:
- **Cycle Detection:**  
   - If two nodes are already connected (`find(a) == find(b)`), adding another edge creates a cycle.  
- **Connected Component Check:**  
   - If exactly one connected component remains at the end (`n == 1`), the graph is connected.  
- **Edge Count Condition:**  
   - The `n-1` condition is implicitly enforced by the `--n` operation. If the graph has too many edges (creating multiple cycles), the algorithm will return `false` early.

---

### Complexity Analysis:
- **Time Complexity:**  
   - `O(E * α(n))`, where `E` is the number of edges, and `α(n)` is the inverse Ackermann function (extremely small, practically `O(1)`).  
   - This is highly efficient, even for the upper constraint (`n = 2000, edges = 5000`).

- **Space Complexity:**  
   - `O(n)` for the parent array `p`.

---

### Example Walkthrough:
**Example 1:**  
```
n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
```
- Initially: `p = [0, 1, 2, 3, 4]`  
- Process `[0,1]` → Union (0,1), `p = [1, 1, 2, 3, 4]`, `n = 4`  
- Process `[0,2]` → Union (0,2), `p = [1, 2, 2, 3, 4]`, `n = 3`  
- Process `[0,3]` → Union (0,3), `p = [1, 2, 3, 3, 4]`, `n = 2`  
- Process `[1,4]` → Union (1,4), `p = [1, 2, 3, 4, 4]`, `n = 1`  
- Since `n == 1`, the graph forms a valid tree.

**Example 2 (Cycle):**  
```
n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
```
- Initially: `p = [0, 1, 2, 3, 4]`  
- Process `[0,1]` → Union (0,1), `p = [1, 1, 2, 3, 4]`, `n = 4`  
- Process `[1,2]` → Union (1,2), `p = [1, 2, 2, 3, 4]`, `n = 3`  
- Process `[2,3]` → Union (2,3), `p = [1, 2, 3, 3, 4]`, `n = 2`  
- Process `[1,3]` → Cycle detected (`find(1) == find(3)`), returns `false`.  

---

### Why This Approach is Good:
- **Efficient and Simple:** Union-Find is a standard, optimized method for handling connectivity and cycle detection.  
- **Scalable:** Handles large inputs efficiently due to amortized `O(1)` operations.  
- **Direct Cycle Check:** Returns early if a cycle is detected, saving unnecessary computation.
*/