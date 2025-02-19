// Each lookup and insertion in the hash map is O(1) on average, making the overall solution O(n).
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        
        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            // If the complement exists in the map, return the pair of indices
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            // Otherwise, add the current number with its index to the map
            map.put(nums[i], i);
        }
        return new int[]{};
    }
}


// time complexity of O(n^2)
// class Solution {
//     public int[] twoSum(int[] nums, int target) {
        
//         for (int i = 0; i < nums.length - 1; i++) {
//             int left = nums[i];
//             for (int j = i+1; j < nums.length; j++) {
//                 int right = nums[j];
//                 if (left + right == target) {
//                     return new int[] {i, j};
//                 }
//             }
//         }
//         return new int[]{};
//     }
// }
