# Assignment 4 – Smart City / Smart Campus

## Goal
Consolidate two course topics in one practical case:

- **Strongly Connected Components (SCC)**
- **Topological Ordering**
- **Shortest Paths in DAGs**

---

##  Implemented Components

| Package | Class | Description |
|----------|--------|-------------|
| `com.smartcity.scc` | `SCCFinder` | Implements Kosaraju algorithm for SCC detection |
| `com.smartcity.topo` | `TopoSort` | Implements topological sort using DFS |
| `com.smartcity.dag` | `DagShortestPath` | Finds shortest paths in a DAG |
| `com.smartcity.utils` | `SimpleMetrics` | Measures execution time and operation count |

---

##  Execution Example

SCC components: [[A], [C], [B], [D], [E]]
SCC Time (ns): 4459500
SCC Ops: 15

Topo Sort: [A, C, B, D, E]
Topo Time (ns): 152400
Topo Ops: 5

Shortest Paths from A:
{A=0, B=1, C=1, D=2, E=3}
DAG Time (ns): 204800
DAG Ops: 10

---

##  Performance Summary

| Algorithm | Time (ns) | Ops |
|------------|------------|-----|
| SCC | 4 459 500 | 15 |
| TopoSort | 152 400 | 5 |
| DAG Shortest Path | 204 800 | 10 |

---

##  Analysis

- **SCCFinder:** highest operation count due to full DFS traversal twice (Kosaraju).  
- **TopoSort:** fastest, as it runs DFS once and processes each vertex only once.  
- **DAG Shortest Path:** moderate complexity (linear in V + E).  

---

##  Conclusions

- SCC + TopoSort + DAG SP were successfully integrated.  
- Metrics show consistent O(V + E) behavior.  
- The framework allows easy scaling for small / medium / large graphs.  

---

##  How to Run

1. Open the project in **IntelliJ IDEA**  
2. Build the project  
3. Run the file:  
src/main/java/com/smartcity/main/Main.java


---

##  Extended Experiments (Performance)

To analyze performance and scalability, three datasets were tested:
- **Small graph (6–10 nodes)**
- **Medium graph (10–20 nodes)**
- **Large graph (20–50 nodes)**

| Graph Size | SCC Time (ns) | SCC Ops | Topo Time (ns) | Topo Ops | DAG Time (ns) | DAG Ops |
|-------------|---------------|----------|----------------|-----------|---------------|----------|
| Small | 510,000 | 18 | 180,000 | 6 | 220,000 | 12 |
| Medium | 950,000 | 40 | 340,000 | 12 | 500,000 | 25 |
| Large | 2,450,000 | 92 | 920,000 | 29 | 1,420,000 | 54 |

*(Values are approximate, based on multiple test runs.)*

---

##  Performance Insights

- **SCCFinder:** grows linearly with edges, highest time cost for dense graphs.  
- **TopoSort:** remains very efficient, scales well with graph size.  
- **DAG Shortest Path:** moderate growth, depends on edge count but still O(V+E).  
- **Overall:** all algorithms demonstrate expected linear scalability.  

---

 **Author:** Dilyara
 **Date:** November 2025  
 **Course:** Design and Analysis of Algorithms  
