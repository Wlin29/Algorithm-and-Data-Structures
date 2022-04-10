/*
    Finding the shortest paths between 2 bus stops (as input by the user), returning the list of stops
    en route as well as the associated “cost”.

    @ author: Wen Geng Lin
 */

import java.util.HashMap;

public class shortestPaths {

    shortestPaths(Graph adjacencyMatrix, String startVertex, String endVertex, HashMap<String, Integer> hashmap, HashMap<Integer, String> reverseHashmap){
        int start = Integer.parseInt(startVertex);
        int end = Integer.parseInt(endVertex);

        dijkstrasAlgorithm dijkstra = new dijkstrasAlgorithm();
        dijkstra.dijkstra(adjacencyMatrix.adjacencyMatrix, start, end, hashmap, reverseHashmap);
    }

}
