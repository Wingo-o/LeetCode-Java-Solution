class Solution {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, List<Integer> tempList, List<List<Integer>> result) {
        // Base case: If the tempList size equals the nums array length, we have a valid permutation
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        // Try each number not already in tempList
        for (int num : nums) {
            if (tempList.contains(num)) continue; // Skip duplicates
            tempList.add(num); // Choose
            backtrack(nums, tempList, result); // Explore
            tempList.remove(tempList.size() - 1); // Unchoose
        }
    }
}
