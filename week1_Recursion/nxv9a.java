    /**
     * Combination Sum
 Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:

    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

For example, given candidate set [2, 3, 6, 7] and target 7,
A solution set is:

[
  [7],
  [2, 2, 3]
]
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        return combinationSum(new sArrayList<>(), new ArrayList<>(), candidates, target, 0);
    }

    private List<List<Integer>> combinationSum(List<List<Integer>> result, List<Integer> cur, int[] candidates,
            int target, int start) {
        if (target == 0) {
            result.add(new ArrayList<>(cur));
            return result;
        }
        if (target < 0) {
            return result;
        }
        for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
            cur.add(candidates[i]);
            combinationSum(result, cur, candidates, target - candidates[i], i);
            cur.remove(cur.size() - 1);
        }
        return result;
    }
    
        /**
     * Combination Sum
 Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:

    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

For example, given candidate set [2, 3, 6, 7] and target 7,
A solution set is:

[
  [7],
  [2, 2, 3]
]
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        return combinationSum(new ArrayList<>(), new ArrayList<>(), candidates, target, 0);
    }

    private List<List<Integer>> combinationSum(List<List<Integer>> result, List<Integer> cur, int[] candidates,
            int target, int start) {
        if (target == 0) {
            result.add(new ArrayList<>(cur));
            return result;
        }
        if (target < 0) {
            return result;
        }
        for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
            cur.add(candidates[i]);
            combinationSum(result, cur, candidates, target - candidates[i], i);
            cur.remove(cur.size() - 1);
        }
        return result;
    }
 /**
     * Combination Sum II
 Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:

    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.

For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
[1,1,2,5,6,7,10]
A solution set is:

[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        return combinationSum2(new ArrayList<>(), new ArrayList<>(), candidates, target, 0);
    }

    private List<List<Integer>> combinationSum2(List<List<Integer>> result, List<Integer> cur, int[] candidates,
            int target, int start) {
        if (target == 0) {
            result.add(new ArrayList<>(cur));
            return result;
        }
        if (target < 0) {
            return result;
        }
        for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
            if (i > start && candidates[i] == candidates[i-1]) {
                continue;
            }
            cur.add(candidates[i]);
            combinationSum2(result, cur, candidates, target - candidates[i], i + 1);
            cur.remove(cur.size() - 1);
        }
        return result;
    }
    
        /**
 Combination Sum IV
 Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:
nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.

Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers? 
Refer to Coin Change:  all positive numbers and no duplicates. You can reuse the same num.
(1, 3) and (3, 1) are different
     */
    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;画
        }
        // note that nums are all positive numbers and no duplicates
        Arrays.sort(nums);
        // res[i] means target == i, number of combination
        int[] res = new int[target + 1];
        res[0] = 1;
        for (int i = nums[0]; i < res.length; i++) {
            for (int num : nums) {
                if (num > i) {
                    break;
                }
                // num = 3, i = 5, res[2] + 3 = 5
                //    |  2  |   3   |
                //    |      5      |
                //    | 1 |    4    |
                //    | 2   |   2 |1|
                //    |      4    |1|
                res[i] += res[i - num];
            }
        }
        return res[target];
    }
    
       /**
     * Coin Change
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
Example 1:
coins = [1, 2, 5], amount = 11
return 3 (11 = 5 + 5 + 1)

Example 2:
coins = [2], amount = 3
return -1.

Note:
You may assume that you have an infinite number of each kind of coin. 
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        // nums[i] means amount of i returns nums[i] minimum number of coins
        int[] nums = new int[amount + 1];
        Map<Integer, List<Integer>> map = new HashMap<>();
        // key is amount, value is the coins combination
        // return map.getOrDefault(amount, null); Colllections.emptyList() <=> new ArrayList<>(); // 10 initial capacity
        // computeIfAbsent
        // coins[0] + 1 may overflow if coins[0] == Integer.MAX_VALUE
        // 3, 5
        // nums[3] = 1
        // nums[4] = 0
        // nums[5] = 1
        // nums[7] = 3 + nums[4]
        int max = 0;
        for (int i = coins[0]; i <= amount; i++) {
            for (int coin : coins) {
                if (coin > i) {
                    break;
                }
                if (coin == i) {
                    nums[i] = 1;
                    break;
                }
                if (nums[i - coin] == 0) {
                    // i - coin is not assigned yet
                    continue;
                }
                // nums[4] + 1  = 5
                //   2                3
                // nums[3] + 2  = 5
                //   1                2
                int candidate = nums[i - coin] + 1;
                // nums[i] <=> map.getOrDefault(i, Collections.emptyList()).size()
                if (nums[i] == 0 || candidate < nums[i]) {
                    max = i;
                    // 0 means it's not assigned yet
                    nums[i] = candidate;
                    List<Integer> l = map.get(i - coin);
                    if (l == null) {
                        l = new ArrayList<>();
                    } else {
                        // clone
                        l = new ArrayList<>(l);
                    }
                    l.add(coin);
                    map.put(i, l);
                }
            }
        }

    map.get(max);
//      for (int i = 1; i < nums.length; i++) {
//          System.out.print(String.forrang'omat("%5d", i));
//      }
//      System.out.println();
//      for (int i = 1; i < nums.length; i++) {
//          if (nums[i] == 0) {
//              System.out.print(String.format("%5s", "X"));
//          } else {
//              System.out.print(String.format("%5d", nums[i]));
//          }
//      }
//      System.out.println();
        if (nums[amount] == 0) {
            return -1;
        }
        return nums[amount];
    }
    
/**
 * Target Sum 
 You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:

Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.

Note:

    The length of the given array is positive and will not exceed 20.
    The sum of elements in the given array will not exceed 1000.
    Your output answer is guaranteed to be fitted in a 32-bit integer.

The original problem statement is equivalent to:
Find a subset of nums that need to be positive, and the rest of them negative, such that the sum is equal to target

Let P be the positive subset and N be the negative subset
For example:
Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]

Then let's see how this can be converted to a subset sum problem:

                  sum(P) - sum(N) = target
sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
                       2 * sum(P) = target + sum(nums)

So the original problem has been converted to a subset sum problem as follows:
Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2

Note that the above formula has proved that target + sum(nums) must be even
     */
    public int findTargetSumWays(int[] nums, int s) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum < s || (s + sum) % 2 > 0) {
            // 2 * sum(P) = target + sum(nums) => (target + sum(nums)) % 2 == 0
            return 0;
        }
        // (s + sum) >>> 1 => (s + sum) / 2 
        // >>> v.s. >> algorithmic shift, logrithmic shift
        return subsetSum(nums, (s + sum) >>> 1);
    }

    // Use knapsack, pick num out of nums to sum to s
    // nums are positive but may contain duplicates
    // Hence we cannot use combination sum IV
    private int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums) {
            for (int i = s; i >= n; i--) {
                // i - n >= 0
                dp[i] += dp[i - n];
            }
        }
        return dp[s];
    }
    
    /**
     * Backpack
Given n items with size Ai, an integer m denotes the size of a backpack. How full you can fill this backpack?
Example

If we have 4 items with size [2, 3, 5, 7], the backpack size is 11, we can select [2, 3, 5], so that the max size we can fill this backpack is 10. If the backpack size is 12. we can select [2, 3, 7] so that we can fulfill the backpack.

You function should return the max size we can fill in the given backpack.
Note

You can not divide any item into small pieces.
     */
    public int backPack(int m, int[] A) {
        int[] result = new int[m + 1];
        // f(j) = max(f(j - wi) + vi), vi = wi in this case
        for (int i = 0; i < A.length; i++) {
            for (int j = m; j >= A[i]; j--) {
                result[j] = Math.max(result[j - A[i]] + A[i], result[j]);
            }
        }

        return result[m];
    }
    
    /**
     * Backpack II 
Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?
Example

Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.
Note

You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

     */
    public int backPackII(int m, int[] A, int V[]) {
        int[] result = new int[m + 1];
        // f(j) = max(f(j - wi) + vi)
        for (int i = 0; i < A.length; i++) {
            for (int j = m; j >= A[i]; j--) {
                result[j] = Math.max(result[j - A[i]] + V[i], result[j]);
            }
        }

        return result[m];
    }
    
    2. Detect Cycle in an undirected graph: BFS, DFS, Union Find

Detect Cycle in a directed graph: DFS, http://www.geeksforgeeks.org/detect-cycle-direct-graph-using-colors/


    enum Color {
        WHITE, GRAY, BLACK;
    }

    /**
     * http://www.geeksforgeeks.org/detect-cycle-direct-graph-using-colors/
     * WHITE : Vertex is not processed yet. Initially all vertices are WHITE.
     * 
     * GRAY : Vertex is being processed (DFS for this vertex has started, but
     * not finished which means that all descendants (ind DFS tree) of this
     * vertex are not processed yet (or this vertex is in function call stack)
     * 
     * BLACK : Vertex and all its descendants are processed.
     * 
     * While doing DFS, if we encounter an edge from current vertex to a GRAY
     * vertex, then this edge is back edge and hence there is a cycle.
     */
     // 1 -> 3
     // 1 -> 2, 2 -> 3
    public boolean hasCycle(GraphNode rootNode) {
        // you can actually use two sets, one for processing and one for
        // processed, this way you save space
        return hasCycle(rootNode, new HashMap<>());
    }

    private boolean hasCycle(GraphNode rootNode, Map<GraphNode, Color> map) {
        if (rootNode == null) {
            return false;
        }
        // base case
        Color state = map.getOrDefault(rootNode, Color.WHITE);
        if (state == Color.GRAY) {
            return true;
        }
        map.put(rootNode, Color.GRAY);

        for (GraphNode node : rootNode.getChildren()) {
            if (hasCycle(node, map)) {
                return true;
            }
        }

        map.put(rootNode, Color.BLACK);
        return false;
    }