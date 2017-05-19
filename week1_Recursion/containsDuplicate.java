//Time complexity is O(NlogN),since array sort, extra memory space O(1)
public class Solution {
    
    public boolean containsDuplicate(int[] nums) {
        if(nums == null || nums.length == 0)
            return false;
        Arrays.sort(nums);
        
        int current = 0;
        for(int next=1; next<nums.length; next++){
            if(nums[current] == nums[next]){
                return true;
            }
            current++;
        }
        return false;
    }
}