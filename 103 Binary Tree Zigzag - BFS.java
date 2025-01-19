/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return List.of();

        List<List<Integer>> result = new ArrayList();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            List<Integer> tempList = new ArrayList<>();
            boolean leftToRight = level % 2 == 1;

            for (int i = 0; i < queueSize; i++) {
                TreeNode tempNode = queue.poll();
                tempList.add(tempNode.val);

                if (tempNode.left != null) queue.add(tempNode.left);
                if (tempNode.right != null) queue.add(tempNode.right); 
            }

            if (!leftToRight) {
                Collections.reverse(tempList);
            } 
                
            result.add(tempList);
            level++;
        }
        return result;
    }
}

// Enhance logic: use dequeue

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return List.of();

        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Deque<Integer> tempDeque = new ArrayDeque<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();

                // Add to deque based on traversal direction
                if (leftToRight) {
                    tempDeque.addLast(currentNode.val);
                } else {
                    tempDeque.addFirst(currentNode.val);
                }

                // Add child nodes to the queue for the next level
                if (currentNode.left != null) queue.add(currentNode.left);
                if (currentNode.right != null) queue.add(currentNode.right);
            }

            // Convert deque to a list and add to the result
            result.add(new ArrayList<>(tempDeque));
            leftToRight = !leftToRight; // Toggle the direction for the next level
        }

        return result;
    }
}
