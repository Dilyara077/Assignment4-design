package com.smartcity.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GraphGenerator {

    private static final Random random = new Random();


    public static Map<String, List<String>> generateDAG(int numNodes, double edgeDensity) {
        Map<String, List<String>> graph = new LinkedHashMap<>();

        List<String> nodes = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            nodes.add("N" + i);
            graph.put("N" + i, new ArrayList<>());
        }

        for (int i = 0; i < numNodes; i++) {
            for (int j = i + 1; j < numNodes; j++) {
                if (random.nextDouble() < edgeDensity) {
                    graph.get(nodes.get(i)).add(nodes.get(j));
                }
            }
        }
        return graph;
    }

    public static void saveGraphToFile(Map<String, List<String>> graph, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("{\n");
            int count = 0;
            for (var entry : graph.entrySet()) {
                writer.write("  \"" + entry.getKey() + "\": " + entry.getValue().toString());
                if (++count < graph.size()) writer.write(",");
                writer.write("\n");
            }
            writer.write("}\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String basePath = "src/data/";

        int[] sizes = {8, 15, 40}; // small, medium, large
        double[] densities = {0.2, 0.5, 0.8}; // sparse, medium, dense

        for (int n : sizes) {
            for (double d : densities) {
                Map<String, List<String>> dag = generateDAG(n, d);
                String filename = basePath + "graph_" + n + "_nodes_" + (int)(d*100) + "_density.json";
                saveGraphToFile(dag, filename);
                System.out.println("Generated: " + filename);
            }
        }
    }
}
