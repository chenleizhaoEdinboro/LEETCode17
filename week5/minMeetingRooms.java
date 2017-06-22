/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 *  [0,30], [5,15], [15,20], are sorted based on the start time
 */
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length == 0)
            return 0;
        if(intervals.length == 1)
            return 1;
        Arrays.sort(intervals, new IntervalComparator());
        PriorityQueue<Integer> pq = new PriorityQueue();
        pq.add(intervals[0].end);
        for(int i=1; i <  intervals.length;i++){
            Interval curr = intervals[i];
            if(curr.start >= pq.peek()){
                //no more meeting room needed
                pq.remove();
            }
            pq.add(intervals[i].end);
        }
        return pq.size();
    }
    
    private class IntervalComparator implements Comparator<Interval>{
        @Override
        public int compare( Interval a, Interval b){
            return a.start - b.start;
        }
    }
}