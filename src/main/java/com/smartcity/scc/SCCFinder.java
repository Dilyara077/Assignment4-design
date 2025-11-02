package com.smartcity.scc;

import com.smartcity.utils.SimpleMetrics;
import java.util.*;

public class SCCFinder {

    private final SimpleMetrics metrics;

    public SCCFinder(SimpleMetrics metrics) {
        this.metrics = metrics;
    }

    public List<List<String>> findSCC(Map<String, List<String>> graph) {
        metrics.start();
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfs(graph, node, visited, stack);
            }
        }

        Map<String, List<String>> reversed = reverseGraph(graph);
        visited.clear();
        List<List<String>> sccList = new ArrayList<>();

        while (!stack.isEmpty()) {
            String node = stack.pop();
            if (!visited.contains(node)) {
                List<String> component = new ArrayList<>();
                dfsCollect(reversed, node, visited, component);
                sccList.add(component);
            }
        }

        metrics.stop();
        return sccList;
    }

    private void dfs(Map<String, List<String>> graph, String node, Set<String> visited, Stack<String> stack) {
        metrics.incrementOps();
        visited.add(node);
        for (String neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, visited, stack);
            }
        }
        stack.push(node);
    }

    private void dfsCollect(Map<String, List<String>> graph, String node, Set<String> visited, List<String> component) {
        metrics.incrementOps();
        visited.add(node);
        component.add(node);
        for (String neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfsCollect(graph, neighbor, visited, component);
            }
        }
    }

    private Map<String, List<String>> reverseGraph(Map<String, List<String>> graph) {
        Map<String, List<String>> reversed = new HashMap<>();
        for (String node : graph.keySet()) {
            for (String neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                reversed.computeIfAbsent(neighbor, k -> new ArrayList<>()).add(node);
                metrics.incrementOps();
            }
        }
        return reversed;
    }
}
