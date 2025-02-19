/*
Time Complexity
	•	The function iterates through each character in the input string s exactly once.
	•	Each character is either:
	•	Pushed onto the stack (for opening brackets) → O(1)
	•	Popped from the stack (for closing brackets) → O(1)
	•	Since each character is processed once, the overall time complexity is O(n), where n is the length of the input string.

Space Complexity
	•	The worst case occurs when the string contains only opening brackets, requiring all n characters to be pushed onto the stack.
	•	In the best case (perfectly valid and balanced brackets), half of the elements will be pushed and then popped, leading to an average space complexity of O(n/2) ≈ O(n).
	•	Thus, the worst-case space complexity is O(n).

Final Complexity Analysis
	•	Time Complexity: O(n)
	•	Space Complexity: O(n) (worst case)
*/

class Solution {
    public boolean isValid(String s) {
        // Use a stack to keep track of opening brackets.
        Stack<Character> stack = new Stack<>();
        
        // Loop over each character in the string.
        for (char c : s.toCharArray()) {
            // If it's an opening bracket, push it onto the stack.
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // If the stack is empty, there's no matching opening bracket.
                if (stack.isEmpty()) {
                    return false;
                }
                // Pop the last opening bracket from the stack.
                char top = stack.pop();
                
                // Check if the popped bracket matches the closing one.
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false;
                }
            }
        }
        
        // If the stack is empty, all brackets were matched; otherwise, it's invalid.
        return stack.isEmpty();
    }
}
