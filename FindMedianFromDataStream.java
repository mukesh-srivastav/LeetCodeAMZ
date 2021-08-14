/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 

Example 1:

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
 */
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
