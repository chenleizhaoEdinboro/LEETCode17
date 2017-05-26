public class Solution {
    //runtime and space are both O(n)
    
    public int[] twoSum(int[] nums, int target) {
     int[] result = new int[2];
       
     Map<Integer, Integer> map = new HashMap<Integer, Integer>();
     for (int i = 0; i < nums.length; i++) {
        if (map.containsKey(target - nums[i])) {
            result[1] = i ;
            result[0] = map.get(target - nums[i]);
            return result;
        }
        map.put(nums[i], i );
    }
    return result;
    }//end of two sum
       
 }//end of solution
