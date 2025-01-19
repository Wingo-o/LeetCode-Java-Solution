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

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return List.of();

        List<List<Integer>> result = new ArrayList();

        levelHepler(result, root, 0);
        return result;
    }

    public void levelHepler(List<List<Integer>> result, TreeNode tempNode, int height) {
        if (tempNode == null) {
            return;
        }

        if (height == result.size()) {
            result.add(new ArrayList<Integer>());
        }
        result.get(height).add(tempNode.val);

        levelHepler(result, tempNode.left, height + 1);
        levelHepler(result, tempNode.right, height + 1);
    }
}
