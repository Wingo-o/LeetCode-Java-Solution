class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert wordList to a HashSet for O(1) lookups
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;  // If endWord is not in the list, no transformation is possible
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        
        int steps = 1;  // Start with the first word (beginWord)
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process all words at the current level
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                
                // Try all possible single-letter transformations
                for (int j = 0; j < currentWord.length(); j++) {
                    char[] wordChars = currentWord.toCharArray();
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordChars[j] = c;
                        String newWord = new String(wordChars);
                        
                        if (newWord.equals(endWord)) {
                            return steps + 1;  // Return the next level count
                        }
                        
                        if (wordSet.contains(newWord)) {
                            queue.offer(newWord);
                            wordSet.remove(newWord);  // Avoid revisiting
                        }
                    }
                }
            }
            steps++;  // Increment steps after processing the current level
        }
        
        return 0;  // No valid transformation sequence found
    }
}
