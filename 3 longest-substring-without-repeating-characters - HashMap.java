/*
Let’s analyze how left changes for the input s = "abbaaabb" using the optimized sliding window approach with a HashMap.

Initial Setup
	•	map stores the latest index of each character.
	•	left tracks the start of the current valid window.
	•	right iterates through the string.

Step-by-Step Execution

Step	right	char	map (latest index)	left update	maxLength
0	0	‘a’	{ ‘a’ → 0 }	0	1
1	1	‘b’	{ ‘a’ → 0, ‘b’ → 1 }	0	2
2	2	‘b’	{ ‘a’ → 0, ‘b’ → 2 }	2 (since b was last at 1)	2
3	3	‘a’	{ ‘a’ → 3, ‘b’ → 2 }	2	2
4	4	‘a’	{ ‘a’ → 4, ‘b’ → 2 }	4 (since a was last at 3)	2
5	5	‘a’	{ ‘a’ → 5, ‘b’ → 2 }	5 (since a was last at 4)	2
6	6	‘b’	{ ‘a’ → 5, ‘b’ → 6 }	5 (since b was last at 2, left remains 5)	2
7	7	‘b’	{ ‘a’ → 5, ‘b’ → 7 }	7 (since b was last at 6)	2

Final left values at each step

right	left
0	0
1	0
2	2
3	2
4	4
5	5
6	5
7	7

Final Answer

The maximum substring length without repeating characters is 2 (e.g., "ab", "ba", "aa", etc.).

Key Observations
	1.	The left pointer only moves forward when a duplicate character is found.
	2.	When a duplicate appears, left moves to max(previous duplicate’s index + 1, current left).
	3.	Worst case: left moves at most n times, so the algorithm runs in O(n) time.

This ensures an efficient O(n) sliding window approach instead of O(n²)! 
*/


import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            // If duplicate is found, move left to max(previous index + 1, current left)
            if (map.containsKey(c)) {
                left = Math.max(map.get(c) + 1, left);
            }
            
            map.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
