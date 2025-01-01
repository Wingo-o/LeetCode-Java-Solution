class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        
        // Map to track cloned nodes
        HashMap<Node, Node> map = new HashMap<>();
        
        // Initialize BFS queue
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        
        // Clone the first node and add it to the map
        map.put(node, new Node(node.val));
        
        // Start BFS traversal
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            
            // Iterate through the neighbors
            for (Node neighbor : current.neighbors) {
                // If neighbor is not cloned yet, clone and add to the queue
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val));
                    queue.add(neighbor);
                }
                // Link the clone's neighbors
                map.get(current).neighbors.add(map.get(neighbor));
            }
        }
        
        // Return the clone of the starting node
        return map.get(node);
    }
}
