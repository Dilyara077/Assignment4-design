package com.smartcity.scc;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class SCCFinderTest {

    @Test
    public void testSimpleSCC() {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B"));
        graph.put("B", Arrays.asList("C"));
        graph.put("C", Arrays.asList("A"));
        graph.put("D", Arrays.asList("E"));
        graph.put("E", Collections.emptyList());

        SCCFinder sccFinder = new SCCFinder();
        List<List<String>> sccs = sccFinder.findSCC(graph);

        System.out.println("SCCs: " + sccs);

        // Проверим, что у нас есть два компонента: [A,B,C] и [D], [E]
        assertEquals(3, sccs.size(), "Should detect 3 components");
    }
}
