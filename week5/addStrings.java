//Carry + cur1 + cur2 = correct summation
public class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int carry = 0;
        for(int i = num1.length()-1, j = num2.length()-1;i>=0 || j>=0 || carry ==1; i--,j--){
            int cur1 = i < 0 ? 0: num1.charAt(i) - '0';
            int cur2 = j < 0 ? 0: num2.charAt(j) - '0';
            res.append((cur1 + cur2 + carry) % 10);
            carry = (cur1 + cur2 + carry)/10;
        }
        return res.reverse().toString();
    }
}