public class Solution {
    //hashmap  <element, freqency>
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i =0; i < nums1.length; i++){
            if(map.containsKey(nums1[i])){
                map.put(nums1[i], map.get(nums1[i])+1);
            }
            else
            map.put(nums1[i],1);
        }
        ArrayList<Integer> resList = new ArrayList<>();
        for(int i=0; i < nums2.length; i++){
            if(map.containsKey(nums2[i]) && map.get(nums2[i]) > 0){
                resList.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i])-1);
            }
        }
        int[] result = new int[resList.size()];
        for(int i=0; i< resList.size(); i++){
            result[i] = resList.get(i);
        }
        return result;
    }
}