package com.smartcity.dag;

import com.smartcity.utils.SimpleMetrics;
import java.util.*;

public class DagShortestPath {

    private final SimpleMetrics metrics;

    public DagShortestPath(SimpleMetrics metrics) {
        this.metrics = metrics;
    }

    public Map<String, Integer> shortestPath(Map<String, List<String>> graph, String start) {
        metrics.start();
        Map<String, Integer> distance = new HashMap<>();
        for (String node : graph.keySet()) {
            distance.put(node, Integer.MAX_VALUE);
        }
        distance.put(start, 0);

        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                topoDFS(node, graph, visited, stack);
            }
        }

        while (!stack.isEmpty()) {
            String node = stack.pop();
            if (distance.get(node) != Integer.MAX_VALUE) {
                for (String neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                    metrics.incrementOps();
                    int newDist = distance.get(node) + 1; // вес ребра = 1
                    if (newDist < distance.get(neighbor)) {
                        distance.put(neighbor, newDist);
                    }
                }
            }
        }

        metrics.stop();
        return distance;
    }

    private void topoDFS(String node, Map<String, List<String>> graph, Set<String> visited, Stack<String> stack) {
        metrics.incrementOps();
        visited.add(node);
        for (String neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                topoDFS(neighbor, graph, visited, stack);
            }
        }
        stack.push(node);
    }
}
