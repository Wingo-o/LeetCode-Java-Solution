// Let  N  be the total number of nodes across all  k  lists.
// Time Complexity: O(N log k)
// Space Complexity (excluding output): O(k)
// Space Complexity (including output): O(N)

public ListNode mergeKLists(ListNode[] lists) {
        
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> a.val - b.val);
        ListNode result = new ListNode();
        ListNode tail = result;

        // add the listNode to the pq
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        while (!pq.isEmpty()){
            ListNode node = pq.poll();

            tail.next = node;
            tail = tail.next;

            if (node.next != null) {
                pq.offer(node.next);
            }
        }
        return result.next;
    }
