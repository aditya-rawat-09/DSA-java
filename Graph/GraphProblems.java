package graph;

import java.util.*;

public class GraphProblems {

    int vertices;
    LinkedList<Integer>[] adjList;

    Graph(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) adjList[i] = new LinkedList<>();
    }
}
