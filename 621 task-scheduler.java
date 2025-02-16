/*
How It Works
	1.	Frequency Count:
We first count the occurrences of each task.
	2.	Max-Heap:
We use a max-heap (priority queue) so that at each step we can pick the task with the highest remaining frequency.
	3.	Cycle Simulation:
We process tasks in cycles of length  n+1  (the current task plus  n  cooldown slots). For each cycle:
	•	We try to execute up to  n+1  tasks by polling the heap.
	•	For each executed task, we decrease its count.
	•	If a task is not yet finished (i.e., its count is still above 0), we store it temporarily.
	•	After the cycle, we add the remaining tasks back into the heap.
	•	If we haven’t used all  n+1  slots in the cycle (because the heap ran out of tasks) and there are still tasks remaining overall, we add the idle time corresponding to the unused slots.
	4.	Time Complexity:
Each task is processed and reinserted at most once. Since the heap contains at most 26 elements, operations are efficient. Overall, the time complexity is  O(N \log 26) , which is effectively  O(N) .
	5.	Space Complexity:
The extra space used is  O(26)  for the frequency array and heap, which is  O(1)  in practical terms.

This heap approach simulates the CPU scheduling process and returns the minimum number of intervals (including idle times) required to complete all tasks.
*/


class Solution {

    public int leastInterval(char[] tasks, int n) {
        // Count the frequency of each task.
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }
        
        // Create a max-heap (priority queue) where the task with the highest frequency is at the top.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int count : freq) {
            if (count > 0) {
                maxHeap.offer(count);
            }
        }
        
        int time = 0;
        // Process tasks in cycles of length (n + 1)
        while (!maxHeap.isEmpty()) {
            int cycle = n + 1;
            List<Integer> tempList = new ArrayList<>();
            
            // Try to execute up to (n+1) tasks in this cycle.
            while (cycle > 0 && !maxHeap.isEmpty()) {
                int currCount = maxHeap.poll();
                // Execute the task: decrease its count.
                currCount--;
                // Increment the time for executing this task.
                time++;
                // If the task still has remaining counts, add it to the temporary list.
                if (currCount > 0) {
                    tempList.add(currCount);
                }
                cycle--;
            }
            
            // Reinsert tasks that are still pending into the heap.
            for (int remaining : tempList) {
                maxHeap.offer(remaining);
            }
            
            // If there are still tasks left, but we haven't filled the whole cycle,
            // count the idle slots.
            if (!maxHeap.isEmpty()) {
                time += cycle;
            }
        }
        
        return time;
    }












    // public int leastInterval(char[] tasks, int n) {
    //     // Count the frequency of each task.
    //     int[] freq = new int[26];
    //     for (char task : tasks) {
    //         freq[task - 'A']++;
    //     }
        
    //     // Find the maximum frequency and how many tasks have that frequency.
    //     int maxFreq = 0;
    //     int countMax = 0;
    //     for (int f : freq) {
    //         if (f > maxFreq) {
    //             maxFreq = f;
    //             countMax = 1;
    //         } else if (f == maxFreq) {
    //             countMax++;
    //         }
    //     }
        
    //     // The idea is to arrange the most frequent tasks first. They will form:
    //     // (maxFreq - 1) chunks with each chunk of length (n + 1), and then we add the tasks
    //     // with maximum frequency in the last chunk.
    //     int intervals = (maxFreq - 1) * (n + 1) + countMax;
        
    //     // The result is the maximum between the computed intervals and the total number of tasks.
    //     return Math.max(intervals, tasks.length);
    // }






    // public int leastInterval(char[] tasks, int n) {
    //     // frequencies of the tasks
    //     int[] frequencies = new int[26];
    //     for (int t : tasks) {
    //         frequencies[t - 'A']++;
    //     }

    //     Arrays.sort(frequencies);

    //     // max frequency
    //     int f_max = frequencies[25];
    //     int idle_time = (f_max - 1) * n;
    //     int freqLength = frequencies.length;
    //     for (int i = frequencies.length - 2; i >= 0 && idle_time > 0; --i) {
    //         idle_time -= Math.min(f_max - 1, frequencies[i]); 
    //     }
    //     idle_time = Math.max(0, idle_time);

    //     return idle_time + tasks.length;
    // }
}
