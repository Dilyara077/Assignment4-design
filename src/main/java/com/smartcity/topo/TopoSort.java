package com.smartcity.topo;

import com.smartcity.utils.SimpleMetrics;
import java.util.*;

public class TopoSort {

    private final SimpleMetrics metrics;

    public TopoSort(SimpleMetrics metrics) {
        this.metrics = metrics;
    }

    public List<String> sort(Map<String, List<String>> graph) {
        metrics.start();
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfs(node, graph, visited, stack);
            }
        }

        List<String> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        metrics.stop();
        return result;
    }

    private void dfs(String node, Map<String, List<String>> graph, Set<String> visited, Stack<String> stack) {
        metrics.incrementOps();
        visited.add(node);
        for (String neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited, stack);
            }
        }
        stack.push(node);
    }
}

