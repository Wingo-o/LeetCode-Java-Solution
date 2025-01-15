class Solution {

    List<List<Integer>> results = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtracking(candidates, new ArrayList<>(), target, 0);
        return results;
    }

    void backtracking(int[] candidates, List<Integer> tempList, int target, int start) {
        if (target == 0) {
            results.add(new ArrayList<>(tempList));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            tempList.add(candidates[i]);
            backtracking(candidates, tempList, target - candidates[i], i); // Pass `i` to allow reuse
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> combinations = solution.combinationSum(candidates, target);

        // Iterate over results
        System.out.println("Unique Combinations:");
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
    }
}

/*
You’re correct—[2, 3] is not added to the results because it doesn’t sum up to the target. Let’s revisit the execution for clarity, ensuring correctness.

Step-by-Step Debugging of the Backtracking

Input:

candidates = [2, 3, 6, 7];
target = 7;

Recursive Execution Steps

Step 1: Start with the initial call
	•	tempList = [], target = 7, start = 0.

Step 2: Add 2 (first candidate)
	•	tempList = [2], target = 7 - 2 = 5.
	•	Recursive call: backtracking(candidates, [2], 5, 0).

Step 3: Add 2 again
	•	tempList = [2, 2], target = 5 - 2 = 3.
	•	Recursive call: backtracking(candidates, [2, 2], 3, 0).

Step 4: Add 2 again
	•	tempList = [2, 2, 2], target = 3 - 2 = 1.
	•	Recursive call: backtracking(candidates, [2, 2, 2], 1, 0).

Step 5: Add 2 again
	•	tempList = [2, 2, 2, 2], target = 1 - 2 = -1.
	•	Target is negative → backtrack:
	•	Remove last 2: tempList = [2, 2, 2].

Step 6: Explore the next candidate (3)
	•	tempList = [2, 2, 3], target = 1 - 3 = 0.
	•	Target is 0 → Add [2, 2, 3] to results.
	•	Backtrack:
	•	Remove 3: tempList = [2, 2].

Step 7: Backtrack further
	•	Remove last 2: tempList = [2].
	•	Explore the next candidate (3):
	•	tempList = [2, 3], target = 5 - 3 = 2.
	•	Recursive call: backtracking(candidates, [2, 3], 2, 1).

Step 8: Add 3 again
	•	tempList = [2, 3, 3], target = 2 - 3 = -1.
	•	Target is negative → backtrack:
	•	Remove 3: tempList = [2, 3].

Step 9: Backtrack further
	•	Remove 3: tempList = [2].
	•	Backtrack further:
	•	Remove 2: tempList = [].

Step 10: Explore the next candidate (3)
	•	tempList = [3], target = 7 - 3 = 4.
	•	Recursive call: backtracking(candidates, [3], 4, 1).

Step 11: Add 3 again
	•	tempList = [3, 3], target = 4 - 3 = 1.
	•	Recursive call: backtracking(candidates, [3, 3], 1, 1).

Step 12: Add 3 again
	•	tempList = [3, 3, 3], target = 1 - 3 = -1.
	•	Target is negative → backtrack:
	•	Remove 3: tempList = [3, 3].

Step 13: Backtrack further
	•	Remove 3: tempList = [3].
	•	Backtrack further:
	•	Remove 3: tempList = [].

Step 14: Explore the next candidate (6)
	•	tempList = [6], target = 7 - 6 = 1.
	•	Recursive call: backtracking(candidates, [6], 1, 2).

Step 15: Add 6 again
	•	tempList = [6, 6], target = 1 - 6 = -1.
	•	Target is negative → backtrack:
	•	Remove 6: tempList = [6].

Step 16: Backtrack further
	•	Remove 6: tempList = [].

Step 17: Explore the next candidate (7)
	•	tempList = [7], target = 7 - 7 = 0.
	•	Target is 0 → Add [7] to results.

Final results

After all recursive calls complete:

[[2, 2, 3], [7]]

Important Notes:
	•	[2, 3] is not added to results because it doesn’t reach a target of 0. Instead, it leaves target = 2 when explored, which is invalid.
	•	The recursive function ensures only valid combinations are added.

*/


// Not good version
class Solution {

    List<List<Integer>> results = new ArrayList<>();
    Set<List<Integer>> added = new HashSet<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backtracking(candidates, null, target);
        return results;
    }

    void backtracking(int[] candidates, List<Integer> tempList, int target) {
        if (tempList == null) {
            tempList = new ArrayList<>();
        }
        
        int sum = 0;

        for (Integer candidate : tempList) {
            sum += candidate;
        }

        if (sum >= target) {
            if (sum == target && !tempList.isEmpty()) {
                List<Integer> listToAdd = new ArrayList<>(tempList);
                Collections.sort(listToAdd);
                if (added.add(listToAdd)) {
                    results.add(listToAdd);
                }
            }
            return;
        }

        for (int candidate : candidates) {
            tempList.add(candidate);
            backtracking(candidates, tempList, target);
            tempList.remove(tempList.size() - 1);
        }
    }
}
