package com.smartcity.dagsp;

import com.smartcity.topo.TopoSort;
import java.util.*;

public class DagShortestPath {

    public static Map<String, Integer> findShortestPaths(Map<String, List<Edge>> graph, String startNode) {

        Map<String, List<String>> simpleGraph = new LinkedHashMap<>();
        for (String node : graph.keySet()) {
            List<String> neighbors = new ArrayList<>();
            for (Edge e : graph.get(node)) {
                neighbors.add(e.to);
            }
            simpleGraph.put(node, neighbors);
        }
        List<String> topoOrder = TopoSort.sort(simpleGraph);


        Map<String, Integer> dist = new HashMap<>();
        for (String node : graph.keySet()) {
            dist.put(node, Integer.MAX_VALUE);
        }
        dist.put(startNode, 0);

        for (String node : topoOrder) {
            if (dist.get(node) != Integer.MAX_VALUE) {
                for (Edge edge : graph.getOrDefault(node, Collections.emptyList())) {
                    int newDist = dist.get(node) + edge.weight;
                    if (newDist < dist.get(edge.to)) {
                        dist.put(edge.to, newDist);
                    }
                }
            }
        }
        return dist;
    }


    public static class Edge {
        public String to;
        public int weight;

        public Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
