//XOR exclusive or is the most useful operation in bit manipulation
/*A	B	!A	A && B	A || B	A ^ B
  0	0	1	 0	      0	      0
  0	1	1	 0	      1	      1
  1	0	0	 0	      1	      1
  1	1	0	 1	      1	      0
*/
public class Solution {
    public int singleNumber(int[] nums) {
        if(nums == null || nums.length ==0)
            return -1;
       //set initial value to 0
       int result = 0;
        for(int i = 0; i< nums.length; i++){
             result ^=nums[i];
        }
        return result;
    }
}