package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private List<List<Pair<Integer, Integer>>> graph;

    Graph(int numberOfPoints) {
        this.graph = new ArrayList<>(numberOfPoints);
        for (int i = 0; i < numberOfPoints; i++) {
            graph.add(new LinkedList<>());
        }
    }

    public void addEdge(int vertex1, int vertex2, int weight) {
        graph.get(vertex1).add(new Pair<>(vertex2, weight));
        graph.get(vertex2).add(new Pair<>(vertex1, weight));
    }

    public List<Pair<Integer, Integer>> get(int vertex) {
        return graph.get(vertex);
    }

    public int getNumberOfVertices() {
        return graph.size();
    }
}
