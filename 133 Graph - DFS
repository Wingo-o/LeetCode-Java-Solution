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
