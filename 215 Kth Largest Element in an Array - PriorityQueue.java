/* 
Let’s analyze the code step by step:
	1.	Building the Heap:
You iterate over the nums array (of size n) and add each element to the priority queue. Each insertion takes O(\log n) in the worst case. Therefore, this loop takes:

O(n \log n)

	2.	Extracting k Elements:
You then perform k poll operations from the heap. Each poll() operation takes O(\log n) time, so this loop takes:

O(k \log n)

	3.	Overall Time Complexity:
Combining both parts, the total time complexity is:

O(n \log n + k \log n)

In the worst case where k \approx n, this simplifies to:

O(n \log n)

	4.	Space Complexity:
The heap stores all n elements from the input array, resulting in:

O(n)


Final Answer
	•	Time Complexity: O(n \log n)
	•	Space Complexity: O(n)
*/

Both approaches use a heap (PriorityQueue), but they differ in terms of efficiency and memory usage. Let’s compare them.

Approach 1 (Min-Heap of Size k) – My Approach

import java.util.PriorityQueue;

public class KthLargestElement {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : nums) {
            minHeap.offer(num); // Add element
            if (minHeap.size() > k) {
                minHeap.poll(); // Remove smallest element if heap size > k
            }
        }
        
        return minHeap.peek(); // k-th largest element
    }
}

Time Complexity
	•	O(n log k) → Because the heap is always of size k, inserting an element (offer()) and removing (poll()) takes O(log k).
	•	We iterate over n elements, leading to O(n log k) complexity.

Space Complexity
	•	O(k) → The heap stores only k elements, reducing memory usage.

Approach 2 (Max-Heap of Size n) – Your Approach

import java.util.PriorityQueue;
import java.util.Collections;

public class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : nums) {
            queue.add(num);
        }
        int result = 0;
        while (k != 0) {
            k--;
            result = queue.poll();
        }
        return result;
    }
}

Time Complexity
	•	O(n log n) → The heap stores all n elements. Inserting (add()) each element takes O(log n).
	•	Extracting k elements (poll()) takes **O(k log n)`.
	•	Total: O(n log n) (slower than O(n log k) in the first approach).

Space Complexity
	•	O(n) → The heap stores all n elements, leading to higher memory usage.

Comparing Both Approaches

Approach	Time Complexity	Space Complexity	Advantage
Min-Heap (size k) (My Approach)	O(n log k)	O(k)	Faster for large n, lower memory usage
Max-Heap (size n) (Your Approach)	O(n log n)	O(n)	Conceptually simpler but less efficient

Why is Min-Heap Better?
	1.	Better Performance
	•	Since k is usually much smaller than n, O(n log k) is much better than O(n log n).
	•	Especially beneficial when n is large (e.g., millions of elements).
	2.	Less Memory Usage
	•	Min-heap stores only k elements, reducing space complexity to O(k).
	•	Max-heap stores all n elements, leading to O(n) space usage.
	3.	Faster for Large k Values
	•	If k is close to n, both approaches perform similarly.
	•	But when k is small relative to n, min-heap is significantly faster.

When to Use Each Approach?

✅ Use Min-Heap (O(n log k)) when k is small compared to n
✅ Use Max-Heap (O(n log n)) when k is close to n (but sorting might be better)

Final Verdict:
🚀 Min-Heap (O(n log k)) is the better approach for most cases because it is faster and uses less memory.
