//
//Step 1: Construct adj sparse list by list of list
//Step 2: Using BFS to implement Topological Order.
public int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] incLinkCounts = new int[numCourses];
    List<List<Integer>> adjs = new ArrayList<>(numCourses);
    initialiseGraph(incLinkCounts, adjs, prerequisites);
    return solveByBFS(incLinkCounts, adjs);
   // return solveByDFS(adjs);
}

private void initialiseGraph(int[] incLinkCounts, List<List<Integer>> adjs, int[][] prerequisites){
    int n = incLinkCounts.length;
    while (n-- > 0) adjs.add(new ArrayList<>());
    for (int[] edge : prerequisites) {
        incLinkCounts[edge[0]]++;
        //adjs.get(pre).add(Goal classes);
        adjs.get(edge[1]).add(edge[0]);
    }
}
//Topological sort, find the start point which indegree == 0, put the start in the queue

private int[] solveByBFS(int[] incLinkCounts, List<List<Integer>> adjs){
    int[] order = new int[incLinkCounts.length];
    Queue<Integer> toVisit = new ArrayDeque<>();
    for (int i = 0; i < incLinkCounts.length; i++) {
        if (incLinkCounts[i] == 0) toVisit.offer(i);
    }
    int visited = 0;
    while (!toVisit.isEmpty()) {
        int from = toVisit.poll();
        order[visited++] = from;
        for (int to : adjs.get(from)) {
            incLinkCounts[to]--;
            if (incLinkCounts[to] == 0) toVisit.offer(to);
        }
    }
    return visited == incLinkCounts.length ? order : new int[0]; 
}
