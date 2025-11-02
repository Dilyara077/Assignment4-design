package com.smartcity.scc;

import java.util.*;

public class SCCFinder {

    public List<List<String>> findSCC(Map<String, List<String>> graph) {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();

        // 1. Заполняем стек по завершению DFS
        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                fillOrder(node, visited, stack, graph);
            }
        }

        // 2. Строим транспонированный граф
        Map<String, List<String>> transposed = transpose(graph);

        // 3. Извлекаем вершины из стека и находим компоненты
        visited.clear();
        List<List<String>> sccList = new ArrayList<>();

        while (!stack.isEmpty()) {
            String node = stack.pop();
            if (!visited.contains(node)) {
                List<String> component = new ArrayList<>();
                dfsTransposed(node, visited, transposed, component);
                sccList.add(component);
            }
        }

        return sccList;
    }

    private void fillOrder(String node, Set<String> visited, Stack<String> stack, Map<String, List<String>> graph) {
        visited.add(node);
        for (String neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                fillOrder(neighbor, visited, stack, graph);
            }
        }
        stack.push(node);
    }

    private Map<String, List<String>> transpose(Map<String, List<String>> graph) {
        Map<String, List<String>> transposed = new HashMap<>();
        for (String node : graph.keySet()) {
            for (String neighbor : graph.get(node)) {
                transposed.computeIfAbsent(neighbor, k -> new ArrayList<>()).add(node);
            }
        }
        for (String node : graph.keySet()) {
            transposed.putIfAbsent(node, new ArrayList<>());
        }
        return transposed;
    }

    private void dfsTransposed(String node, Set<String> visited, Map<String, List<String>> graph, List<String> component) {
        visited.add(node);
        component.add(node);
        for (String neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                dfsTransposed(neighbor, visited, graph, component);
            }
        }
    }
}
