package com.smartcity.dagsp;

import java.util.*;

public class DagShortestPath {

    private final Map<String, List<Edge>> graph;

    public DagShortestPath(Map<String, List<Edge>> graph) {
        this.graph = graph;
    }

    public Map<String, Integer> shortestPath(String start) {
        Map<String, Integer> distance = new HashMap<>();

        for (String node : graph.keySet()) {
            distance.put(node, Integer.MAX_VALUE);
        }
        distance.put(start, 0);

        List<String> topoOrder = topologicalSort();

        for (String node : topoOrder) {
            if (distance.get(node) != Integer.MAX_VALUE) {
                for (Edge edge : graph.getOrDefault(node, Collections.emptyList())) {
                    int newDist = distance.get(node) + edge.weight;
                    if (newDist < distance.get(edge.target)) {
                        distance.put(edge.target, newDist);
                    }
                }
            }
        }

        return distance;
    }

    private List<String> topologicalSort() {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfs(node, visited, stack);
            }
        }

        List<String> order = new ArrayList<>();
        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }
        return order;
    }

    private void dfs(String node, Set<String> visited, Stack<String> stack) {
        visited.add(node);
        for (Edge edge : graph.getOrDefault(node, Collections.emptyList())) {
            if (!visited.contains(edge.target)) {
                dfs(edge.target, visited, stack);
            }
        }
        stack.push(node);
    }

    public static class Edge {
        public String target;
        public int weight;

        public Edge(String target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }
}
