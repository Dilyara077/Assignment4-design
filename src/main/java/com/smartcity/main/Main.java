package com.smartcity.main;

import com.smartcity.scc.SCCFinder;
import com.smartcity.topo.TopoSort;
import com.smartcity.dagsp.DagShortestPath;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        // ===== Strongly Connected Components =====
        System.out.println("=== Strongly Connected Components ===");
        Map<String, List<String>> graphSCC = new LinkedHashMap<>();
        graphSCC.put("A", Arrays.asList("B"));
        graphSCC.put("B", Arrays.asList("C"));
        graphSCC.put("C", Arrays.asList("A", "D"));
        graphSCC.put("D", Arrays.asList("E"));
        graphSCC.put("E", Collections.emptyList());

        SCCFinder sccFinder = new SCCFinder();
        List<List<String>> components = sccFinder.findSCC(graphSCC);
        for (List<String> comp : components) {
            System.out.println(comp);
        }

        // ===== Topological Sort =====
        System.out.println("\n=== Topological Sort ===");
        Map<String, List<String>> graphTopo = new LinkedHashMap<>();
        graphTopo.put("A", Arrays.asList("B", "C"));
        graphTopo.put("B", Arrays.asList("D"));
        graphTopo.put("C", Arrays.asList("D"));
        graphTopo.put("D", Collections.emptyList());

        TopoSort topo = new TopoSort(graphTopo);
        List<String> topoOrder = topo.sort(graphTopo);
        System.out.println("Topological order: " + topoOrder);

        // ===== DAG Shortest Path =====
        System.out.println("\n=== DAG Shortest Path ===");
        Map<String, List<DagShortestPath.Edge>> dag = new LinkedHashMap<>();
        dag.put("A", Arrays.asList(new DagShortestPath.Edge("B", 2), new DagShortestPath.Edge("C", 1)));
        dag.put("B", Arrays.asList(new DagShortestPath.Edge("D", 2)));
        dag.put("C", Arrays.asList(new DagShortestPath.Edge("D", 3)));
        dag.put("D", Collections.emptyList());

        DagShortestPath dsp = new DagShortestPath(dag);
        Map<String, Integer> shortestPaths = dsp.shortestPath("A");

        for (String node : shortestPaths.keySet()) {
            System.out.println("Shortest distance from A to " + node + " = " + shortestPaths.get(node));
        }
    }
}

