    /**
     * Contains Duplicate III
     * 
     * Given an array of integers, 
     * find out whether there are two distinct indices i and j in the array
     * such that the difference between nums[i] and nums[j] is at most t
     * and the difference between i and j is at most k. 
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            if (k < 1 || t < 0) {
            return false;
        }
        // use k-size slide window
        Map<Long, Long> map = new HashMap<>(); // key is bucket, value is the actual remapped number
        for (int i = 0; i < nums.length; i++) {
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum / ((long) t + 1);
            if (map.containsKey(bucket)
                    || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
                    || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t)) {
                return true;
            }
            if (map.size() >= k) {
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE)
                        / ((long) t + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum);
        }
        return false;
    }
    
    t = 5, num / t = bucket num
    [-9, -5] [-4, -1] [0, 4] [5, 9] [10, 14]
        -2      -1       0      1      2
    
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) {
            return false;
        }
        // use k-size slide window
        Map<Long, Integer> map = new HashMap<>(); // key is bucket, value is the actual number
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            long bucket = getBucket(num, t);
            if (map.cotainsKey(bucket) ||
                    isBucketAlmostDuplicate(map, bucket - 1, num, t) ||
                    isBucketAlmostDuplicate(map, bucket + 1, num, t))   {
                return true;        
            }    
            if (map.size() >= k) {
                long lastBuckt = getBucket(nums[i - k], t);
                map.remove(lastBucket);
            }
            map.put(bucket, num);
        }
     }
    
    private boolean isBucketAlmostDuplicate(Map<Long, Integer> map, long bucket, int num, int t) {
        Integer n = map.get(bucket);
        if (n == null) {
            return false;
        }
        
        return Math.abs((long) n - num ) <= t;
    }
    
    private long getBucket(int num, int t) {
        long bucket = num / ((long) t + 1);
        // shift bucket one left if negative
        return num < 0 ? bucket - 1 : bucket;
    }
     
     
  public class FileSystem {
        private static class File {
            boolean isFile = false;
            Map<String, File> children = new HashMap<>();
            String content = "";
        }

       private static final String SEPARATOR = "/";
       private final File root;
   
    /**
     * Given a path in string format. If it is a file path, return a list that only contains this file's name.
     * If it is a directory path, return the list of file and directory names in this directory.
     * Your output (file and directory names together) should in lexicographic order.
     */
    public List<String> ls(String path) {
        File current = this.root;
        String name = "";
        for (String dir : path.split(SEPARATOR)) {
            if (dir.isEmpty()) { // lang.common3: StringUtils.isBlank(dir)
                continue; // "/a/b/c"
            }
            File child = current.children().get(dir);
            if (child == null) {
                return Collections.emptyList(); // new ArrayList<>();
            }
            current = child;
            name = dir;
        }
        if (current.isFile) {
            List<String> result = new ArrayList<>();
            // guava: return ImmutableList.of(name);
            result.add(name);
            return result;
        }
        return current.children.keySet().stream().sorted().collect(Collectors.toList());
    }
    
    /**
     * Given a directory path that does not exist, you should make a new directory according to the path.
     * If the middle directories in the path don't exist either, you should create them as well.
     * This function has void return type. 
     */
    public void mkdir(String path) {
        upsertDir(path);
    }

    /**
     * Given a file path and file content in string format.
     * If the file doesn't exist, you need to create that file containing given content.
     * If the file already exists, you need to append given content to original content.
     * This function has void return type.
     */
    public void addContentToFile(String filePath, String content) {
        File current = upsertDir(filePath);
        current.isFile = true;
        current.content += content;
    }

    /**
     * Given a file path, return its content in string format.
     */
    public String readContentFromFile(String filePath) {
        return upsertDir(filePath).content;
    }
    
    private File upsertDir(String path) {
        File current = this.root;
        for (String dir : path.split(SEPARATOR)) {
            if (dir.isEmpty()) {
                continue;
            }
            
            current = current.children.computeIfAbsent(dir, k -> new File());
        }
        return current;
    }
   }
   
    class Building implements Comparable<Building> {
        int left;
        int right;
        int height;
        public Building(int left, int right, int height) {
            this.left = left;
            this.right = right;
            this.height = height;
        }
        @Override
        public int compareTo(Building o) {
            if (this.height != o.height) {
                return Integer.compare(this.height, o.height); // ascend on height
            }
            if (this.left != o.left) {
                return Integer.compare(this.left, o.left);
            }
            return Integer.compare(this.right, o.right);
        }
        @Override
        public String toString() {
            return "Building [left=" + left + ", right=" + right + ", height=" + height + "]";
        }
    }
    
   3. Given two lists of intervals, find their overlapping intervals, e.g.
    l1: [1,5], [7,10], [12,18], [22,24]
    l2: [3,8], [13,15], [16,17], [18,21], [22,23]
    returns [3,5],[7,8],[13,15],[16,17],[18,18],[22,23] 
    begs 1, 7, 12, 22, 3, 13, 16, 18, 22
    ends 5, 10, 18, 24, 8, 15, ...
   
    public List<int[]> getSkyline(int[][] buildings) {
        if (buildings.length == 0) {
            return Collections.emptyList();
        }
           
        Building[] lefts = new Building[buildings.length];
        Building[] rights = new Building[buildings.length];
        for (int i = 0; i < buildings.lenght; i++) {
            int[] b = buildings[i];
            Building building = new Building(b[0], b[1], b[2]);
            rights[i] = lefts[i] = building;
        }
        
        Arrays.sort(lefts, (a, b) -> {
            if (a.left != b.left) {
                return Integer.compare(a.left, b.left);
            }
            return Integer.compare(b.height, a.height);
        });
        
        Arrays.sort(rights, (a, b) -> {
            if (a.right != b.right) {
                return Integer.compare(a.left, b.left);
            }
            return Integer.compare(a.height, b.height);
        });
        
        System.out.println(Arrays.toString(lefts));
        System.out.println(Arrays.toString(rights));
        List<int[]> result = new ArrayList<>();
        TreeSet<Building> set = new TreeSet<>();
        int leftsIndex = 1;
        int rightsIndex = 0;
        int h = lefts[0].height;
        int top = h;
        set.add(lefts[0]);
        result.add(new int[] { lefts[0].left, top });
        int index = 0;
        while (rights < buildings.length) {
            if (leftIndex == buildings.length || rights[rightsIndex].right < lefts[leftsIndex].left) {
                index = rights[rightsIndex].right;
                System.out.println("Remove " + rights[rightsIndex]);
                set.remove(rights[rightsIndex++]);
            } else {
                index = lefts[leftsIndex].left;
                System.out.println("Add " + lefts[leftsIndex]);
                set.add(lefts[leftsIndex++]);
            }
            
            System.out.println(set);
            h = set.isEmpty() ? 0 : set.last().height();
            if (h != top) {
                top = h;
                result.add(new int[]{index, top});
            }
        }
        return result;
   }