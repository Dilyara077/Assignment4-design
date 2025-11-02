package com.smartcity;

import com.smartcity.dagsp.DagShortestPath;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DagShortestPathTest {

    @Test
    public void testSimpleGraph() {
        // Создаем граф
        Map<String, List<DagShortestPath.Edge>> graph = new LinkedHashMap<>();
        graph.put("A", Arrays.asList(new DagShortestPath.Edge("B", 2),
                new DagShortestPath.Edge("C", 1)));
        graph.put("B", Arrays.asList(new DagShortestPath.Edge("D", 2)));
        graph.put("C", Arrays.asList(new DagShortestPath.Edge("D", 3)));
        graph.put("D", Collections.emptyList());

        // Создаем объект и запускаем поиск путей
        DagShortestPath dsp = new DagShortestPath(graph);
        Map<String, Integer> distances = dsp.shortestPath("A");

        System.out.println("Shortest distances: " + distances);

        // Проверяем корректность
        assertEquals(0, distances.get("A"));  // до себя
        assertEquals(2, distances.get("B"));  // A->B
        assertEquals(1, distances.get("C"));  // A->C
        assertEquals(4, distances.get("D"));  // A->C->D
    }
}
