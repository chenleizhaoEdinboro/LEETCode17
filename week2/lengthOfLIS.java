public class Solution {
     public int lengthOfLIS(int[] nums) {            
        List<Integer> dp = new ArrayList<>();
    
    for (int num : nums) {
        //Using Collections.binarySearch of arraylist for optimization
       
        int index = Collections.binarySearch(dp, num);
        if (index < 0) {
      
            index = -(index + 1);
        }
        
        if (index >= dp.size()) {
            dp.add(num);
        } else {
            dp.set(index, num);
        }      
    }
    return dp.size();
    }
}