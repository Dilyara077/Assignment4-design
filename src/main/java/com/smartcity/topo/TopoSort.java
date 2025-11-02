package com.smartcity.topo;

import java.util.*;

public class TopoSort {

    public static List<String> sort(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfs(node, visited, stack, graph);
            }
        }

        List<String> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    private static void dfs(String node, Set<String> visited, Stack<String> stack, Map<String, List<String>> graph) {
        visited.add(node);
        for (String neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited, stack, graph);
            }
        }
        stack.push(node);
    }
}
