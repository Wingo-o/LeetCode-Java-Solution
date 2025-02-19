/*
Let's denote \( V \) as the number of nodes and \( E \) as the number of edges in the graph.

- **Time Complexity:**  
  Each node is processed once (thanks to the memoization in the HashMap), and for every node, we iterate over its neighbors. Thus, the overall time complexity is \(O(V + E)\).

- **Space Complexity:**  
  The HashMap stores every node (which is \(O(V)\)) and the recursion stack in the worst case can go as deep as \(O(V)\). Hence, the space complexity is \(O(V)\).

So, the overall complexity of the program is:  
- **Time:** \(O(V + E)\)  
- **Space:** \(O(V)\)
*/

class Solution {
   private HashMap<Node, Node> map = new HashMap<>();

   public Node cloneGraph(Node node) {
       if (node == null) {
           return null;
       }
      
       return dfs(node);
   }

   Node dfs(Node node) {
       // If node is already cloned, return the clone
       if (map.containsKey(node)) {
           return map.get(node);
       }
      
       // Create a clone for the current node
       Node clone = new Node(node.val);
       map.put(node, clone);
      
       // Clone neighbors recursively
       for (Node neighbor : node.neighbors) {
           clone.neighbors.add(dfs(neighbor));
       }
      
       return clone;
   }
}
