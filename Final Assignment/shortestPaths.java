/*
    Finding the shortest paths between 2 bus stops (as input by the user), returning the list of stops
    en route as well as the associated “cost”.

    @ author: Wen Geng Lin
 */

public class shortestPaths {

    shortestPaths(String stopTimes, String stops, String transfers, String startVertex, String endVertex){
        Graph adjacencyMatrix = new Graph(stopTimes, stops, transfers);

        int start = Integer.parseInt(startVertex);
        int end = Integer.parseInt(endVertex);

        dijkstrasAlgorithm dijkstra = new dijkstrasAlgorithm();
        dijkstra.dijkstra(adjacencyMatrix.adjacencyMatrix, start, end);
    }

}
