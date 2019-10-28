package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfVertices = scanner.nextInt();
        int numberOfEdges = scanner.nextInt();

        Graph graph = new Graph(numberOfVertices);

        for (int i = 0; i < numberOfEdges; i++) {
            int vertex1 = scanner.nextInt();
            int vertex2 = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(vertex1, vertex2, weight);
        }

        Pair<Integer, List<Pair<Integer, Integer>>> result;

        result = Graphs.findSpanningTree(graph);

        System.out.println(result.first);
        for (Pair<Integer, Integer> edge : result.second) {
            System.out.println(edge.first + " " + edge.second);
        }
    }
}
