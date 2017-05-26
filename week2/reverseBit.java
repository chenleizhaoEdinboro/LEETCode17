public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
    	int result = 0;
    for (int i = 0; i < 32; i++) {
    	//n&1 就是在取 1， n&0就是在取0
        result += n & 1;
        n >>>= 1;   // CATCH: must do unsigned shift
        if (i < 31) // CATCH: for last digit, don't shift!
            result <<= 1;
    }
    return result;
  }
}