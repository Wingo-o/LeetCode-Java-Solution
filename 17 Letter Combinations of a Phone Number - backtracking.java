class Solution {
    static Map<Character, List<String>> lettersByDigit = Map.of(
        '2', List.of("a", "b", "c"),
        '3', List.of("d", "e", "f"),
        '4', List.of("g", "h", "i"),
        '5', List.of("j", "k", "l"),
        '6', List.of("m", "n", "o"),
        '7', List.of("p", "q", "r", "s"),
        '8', List.of("t", "u", "v"),
        '9', List.of("w", "x", "y", "z")
    );

    public List<String> letterCombinations(String digits) {
        // Handle edge cases
        if (digits == null || digits.isEmpty()) {
            return List.of();
        }

        List<String> results = new ArrayList<>();
        backtracking(digits, 0, new StringBuilder(), results);
        return results;
    }

    private void backtracking(String digits, int index, StringBuilder current, List<String> results) {
        // Base case: if the current combination length equals the input length, add to results
        if (index == digits.length()) {
            results.add(current.toString());
            return;
        }

        // Get the possible letters for the current digit
        List<String> letters = lettersByDigit.get(digits.charAt(index));
        if (letters == null) return; // Safety check for invalid input (should not happen)

        for (String letter : letters) {
            // Add the letter to the current combination
            current.append(letter);

            // Recurse to the next digit
            backtracking(digits, index + 1, current, results);

            // Backtrack by removing the last character
            current.deleteCharAt(current.length() - 1);
        }
    }
}
