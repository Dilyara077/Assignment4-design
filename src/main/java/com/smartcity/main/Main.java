package com.smartcity.main;

import com.smartcity.scc.SCCFinder;
import com.smartcity.topo.TopoSort;
import com.smartcity.dag.DagShortestPath;
import com.smartcity.utils.SimpleMetrics;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // === Пример графа ===
        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D"));
        graph.put("C", Arrays.asList("D"));
        graph.put("D", Arrays.asList("E"));
        graph.put("E", Collections.emptyList());

        // === Создаём измеритель ===
        SimpleMetrics metrics = new SimpleMetrics();

        // === Алгоритм 1: Strongly Connected Components ===
        SCCFinder sccFinder = new SCCFinder(metrics);
        System.out.println("SCC components: " + sccFinder.findSCC(graph));
        System.out.println("SCC Time (ns): " + metrics.getTimeNano());
        System.out.println("SCC Ops: " + metrics.getOpsCount());
        System.out.println("-----------------------------------");

        // === Алгоритм 2: Topological Sort ===
        metrics = new SimpleMetrics(); // новый таймер
        TopoSort topoSort = new TopoSort(metrics);
        System.out.println("Topo Sort: " + topoSort.sort(graph));
        System.out.println("Topo Time (ns): " + metrics.getTimeNano());
        System.out.println("Topo Ops: " + metrics.getOpsCount());
        System.out.println("-----------------------------------");

        // === Алгоритм 3: DAG Shortest Path ===
        metrics = new SimpleMetrics(); // новый таймер
        DagShortestPath dag = new DagShortestPath(metrics);
        System.out.println("Shortest Paths from A:");
        System.out.println(dag.shortestPath(graph, "A"));
        System.out.println("DAG Time (ns): " + metrics.getTimeNano());
        System.out.println("DAG Ops: " + metrics.getOpsCount());
    }
}

