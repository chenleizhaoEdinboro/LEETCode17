        /**
         * Clone Graph 
 Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

OJ's undirected graph serialization:

Nodes are labeled uniquely.
We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.

As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

    First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
    Second node is labeled as 1. Connect node 1 to node 2.
    Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.

Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
         */
        public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
                if (node == null) {
                        return null;
                }

                // Key is the node in original graph and value is the corresponding
                // cloned node
                Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
                // Queue to iterate through all nodes in original graph
                Queue<UndirectedGraphNode> q = new LinkedList<>();
                q.add(node);

                UndirectedGraphNode graphCopy = new UndirectedGraphNode(node.label);
                map.put(node, graphCopy);

                while (!q.isEmpty()) {
                        UndirectedGraphNode n = q.poll();
                        UndirectedGraphNode clone = map.get(n);
                        for (UndirectedGraphNode neighbor : n.neighbors) {
                                UndirectedGraphNode copy = map.computeIfAbsent(neighbor, k -> {
                                        q.add(k);
                                        return new UndirectedGraphNode(k.label);
                                });
                                clone.neighbors.add(copy);
                        }
                }

                return graphCopy;
        }

        // DFS
        public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
                return clone(node, new HashMap<>());
        }

        private UndirectedGraphNode clone(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> clones) {
                if (node == null) {
                        return null;
                }

                UndirectedGraphNode clone = clones.get(node);
                if (clone != null) {
                        return clone;
                }
                clone = new UndirectedGraphNode(node.label);
                clones.put(node, clone);
                for (UndirectedGraphNode neighbor : node.neighbors) {
                        clone.neighbors.add(clone(neighbor, clones));
                }
                return clone;
        }