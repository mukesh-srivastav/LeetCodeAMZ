import java.util.*;
public class FindMedianFromDataStream {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;
    double median;
    
    /** initialize your data structure here. */
    public FindMedianFromDataStream() {
        ///we can use a max heap on left side to represent elements
        // that are less than effective median, and a min heap on right side to represent elements that are greater than effective median.
        left = new PriorityQueue<>(Collections.reverseOrder()); // MaxHeap
        right = new PriorityQueue<>(); // Minheap
        median = 0.0;
    }
    
    public void addNum(int num) {
        if (left.size() == right.size()) {
            
            if (num > median) {
                right.add(num);
                median = right.peek();
            } else {
                left.add(num);
                median = left.peek();
            }
            
        } else {
            
            if (left.size() > right.size()) {
                if (num > median) {
                    right.add(num);
                } else {
                    right.add(left.poll());
                    left.add(num);
                }

            } else {
                if (num > median) {
                    left.add(right.poll());
                    right.add(num);
                } else {
                    left.add(num);
                }
            }
            
            median = (double)(left.peek() + right.peek())/2;
        }
    }

    public double findMedian() {
        return median;
    }
}
