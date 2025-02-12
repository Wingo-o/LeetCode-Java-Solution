class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Count frequencies.
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Step 2: Use a min-heap (PriorityQueue) to keep the top k frequent elements.
        // The comparator orders by frequency (ascending).
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = 
            new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        
        // Add all entries to the heap.
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            minHeap.offer(entry);
            // Maintain the heap size to be at most k.
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        // Step 3: Extract the keys of the top k frequent elements from the heap.
        int[] result = new int[k];
        // We fill the result from the end (optional, as order doesn't matter for this problem).
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll().getKey();
        }
        return result;
    }

    // public int[] topKFrequent(int[] nums, int k) {
    //     // Step 1: Count frequencies.
    //     Map<Integer, Integer> frequencyMap = new HashMap<>();
    //     for (int num : nums) {
    //         frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
    //     }

    //     // Step 2: Create buckets. 
    //     // The maximum possible frequency is nums.length, so create an array of size nums.length + 1.
    //     List<Integer>[] buckets = new List[nums.length + 1];
    //     for (int key : frequencyMap.keySet()) {
    //         int freq = frequencyMap.get(key);
    //         if (buckets[freq] == null) {
    //             buckets[freq] = new ArrayList<>();
    //         }
    //         buckets[freq].add(key);
    //     }

    //     // Step 3: Gather the top k frequent elements.
    //     int[] result = new int[k];
    //     int index = 0;
    //     // Iterate from highest frequency to lowest.
    //     for (int i = buckets.length - 1; i >= 0 && index < k; i--) {
    //         if (buckets[i] != null) {
    //             for (int num : buckets[i]) {
    //                 result[index++] = num;
    //                 if (index == k) {
    //                     break; // We've found k elements.
    //                 }
    //             }
    //         }
    //     }
    //     return result;
    // }

    // public int[] topKFrequent(int[] nums, int k) {
    //     Map<Integer, Integer> countByNum = new HashMap<>();
    //     for (int num : nums) {
    //         int count = countByNum.getOrDefault(num, 0);
    //         countByNum.put(num, count + 1);
    //     }
    //     LinkedHashMap<Integer, Integer> sortedMap = countByNum.entrySet()
    //         .stream()
    //         .sorted(Map.Entry.<Integer, Integer>comparingByValue(Comparator.reverseOrder()))
    //         .collect(Collectors.toMap(
    //             Map.Entry::getKey, 
    //             Map.Entry::getValue, 
    //             (oldValue, newValue) -> oldValue, // not needed here since keys are unique
    //             LinkedHashMap::new               // maintain insertion (sorted) order
    //         ));
    //     int[] result = new int[k];
    //     int i = 0;
    //     for (Integer key : sortedMap.keySet()) {
    //         if (i == k) break;
    //         result[i++] = key;
    //     }
    //     return result;
    // }
}
