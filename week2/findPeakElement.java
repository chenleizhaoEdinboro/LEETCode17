public class Solution {
    public int findPeakElement(int[] A) {
         int start = 0, end = A.length-1;
        while(start+1 < end){
            int mid = start + (end - start)/2;
            //上升区间， 下降区间讨论
            if(A[mid] < A[mid+1]){
                start = mid;
            }else if(A[mid-1] > A[mid]){
                end = mid;
            }else{
                start = mid;//分不出波峰波谷的时候，随便划界限，也可以是end = mid;
            }
        }
        if(A[start] > A[end])
            return start;
        else
            return end;
    }
}