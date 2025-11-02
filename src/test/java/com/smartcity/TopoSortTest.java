package com.smartcity;

import com.smartcity.topo.TopoSort;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TopoSortTest {

    @Test
    public void testSimpleTopoSort() {

        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("C"));
        graph.put("B", Arrays.asList("C"));
        graph.put("C", Arrays.asList("D"));
        graph.put("D", Collections.emptyList());

        TopoSort topo = new TopoSort(graph);
        List<String> order = TopoSort.sort(graph);

        System.out.println("Topological order: " + order);


        boolean validOrder = (order.equals(Arrays.asList("A", "B", "C", "D")) ||
                order.equals(Arrays.asList("B", "A", "C", "D")));

        assertTrue(validOrder, "Topological order should be valid");
    }
}
