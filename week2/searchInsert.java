public class Solution {
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        
        int index = firstFind( A, target);
        System.out.println("index is "+index);
        if( index == 0)
            {
                if(A[0]<target)
                    return 1;
                else
                    return 0;
            }
            
        if(index == A.length){
            
            return A.length;
        }
            
        if( target- A[index-1] <=A[index]-target){
            return index;
        }
            
        return index;
    }
     private int firstFind(int[] A, int target){
          int start = 0, end = A.length-1;
          while(start +1 < end){
              int mid = start+ (end - start)/2;
                
                if(A[mid] < target){
                    start = mid;
                }else if(A[mid] > target){
                    end = mid;
                }else{
                    end = mid;
                }
          }
          
          if(A[start] >= target)
            return start;
          
          if(A[end] >=target)
          return end;
          
          return A.length;
      }
}
//version 2, leetcode solution
public int searchInsert(int[] A, int target) {
        int low = 0, high = A.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(A[mid] == target) 
                return mid;
            else if(A[mid] > target) 
                high = mid-1;
            else low = mid+1;
        }
        return low;
    }