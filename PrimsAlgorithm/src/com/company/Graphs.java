package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graphs {
    public static Pair<Integer, List<Pair<Integer, Integer>>> findSpanningTree(Graph graph){
        int numberOfVertices = graph.getNumberOfVertices();
        int spanningTreeWeight = 0;

        List<Boolean> visitedVertices = new ArrayList<>(Collections.nCopies(numberOfVertices, false));
        List<Pair<Integer, Integer>> spanningTreeEdges = new ArrayList<>();

        for (int i = 0; i < numberOfVertices; i++) {
            graph.get(i).sort((vertex1, vertex2) -> vertex1.second - vertex2.second);
        }

        visitedVertices.set(0, true);
        for (int i = 0; i < numberOfVertices - 1; i++) {
            Pair<Integer, Integer> addedEdge = new Pair<>(0, 0);
            int minWeight = Integer.MAX_VALUE;
            for (int j = 0; j < numberOfVertices; j++) {
                if (visitedVertices.get(j)) {
                    for (int k = 0; k < graph.get(j).size(); k++) {
                        if (!visitedVertices.get(graph.get(j).get(k).first)) {
                            if (minWeight > graph.get(j).get(k).second) {
                                addedEdge.first = graph.get(j).get(k).first;
                                addedEdge.second = j;
                                minWeight = graph.get(j).get(k).second;
                            }
                            break;
                        }
                    }
                }
            }
            visitedVertices.set(addedEdge.first, true);
            spanningTreeWeight += minWeight;
            if (addedEdge.first < addedEdge.second) {
                spanningTreeEdges.add(addedEdge);
            } else {
                spanningTreeEdges.add(new Pair<>(addedEdge.second, addedEdge.first));
            }

        }

        spanningTreeEdges.sort((vertex1, vertex2) ->
                (vertex1.first - vertex2.first != 0) ?
                        vertex1.first - vertex2.first : vertex1.second - vertex2.second
        );

        return new Pair<>(spanningTreeWeight, spanningTreeEdges);
    }
}
