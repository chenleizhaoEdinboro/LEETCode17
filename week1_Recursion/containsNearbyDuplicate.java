public boolean containsNearbyDuplicate(int[] nums, int k) {
        
        /*
         Explanation: It iterates over the array using a sliding window. The front of the window is at i, the rear of the window is k steps back. The elements within that window are maintained using a set. While adding new element to the set, if add() returns false, it means the element already exists in the set. At that point, we return true. If the control reaches out of for loop, it means that inner return true never executed, meaning no such duplicate element was found.
        */
        if(nums==null || nums.length == 0)
            return false;
            
      Set<Integer> set = new HashSet<Integer>();
        for(int head = 0; head< nums.length; head++){
            if(head > k){
                int tail = head - k;
                set.remove(nums[tail-1]);
            }
            if(!set.add(nums[head]))
                return true;
        }
        return false;
    }