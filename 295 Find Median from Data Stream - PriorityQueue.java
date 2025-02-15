class MedianFinder {
    // Max-heap: stores the smaller half of the numbers
    private PriorityQueue<Integer> maxHeap;
    // Min-heap: stores the larger half of the numbers
    private PriorityQueue<Integer> minHeap;

    /** Initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // largest at the top
        minHeap = new PriorityQueue<>(); // smallest at the top
    }
    
    public void addNum(int num) {
        // Step 1: Add the number to the max-heap.
        maxHeap.offer(num);
        
        // Step 2: Balance: move the largest number in maxHeap to minHeap.
        minHeap.offer(maxHeap.poll());
        
        // Step 3: If minHeap has more elements, move the smallest number back to maxHeap.
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    public double findMedian() {
        // If maxHeap has more elements, the median is its top element.
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }
        // Otherwise, average the top elements of both heaps.
        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
