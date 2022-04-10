// Dijkstra's algorithm adapted from the example found at geeksforgeeks.org
// ( https://www.geeksforgeeks.org/printing-paths-dijkstras-shortest-path-algorithm/ )
// A Java program for Dijkstra's
// single source shortest path
// algorithm. The program is for
// adjacency matrix representation
// of the graph.

import java.util.HashMap;

class dijkstrasAlgorithm {

    public static final int NO_PARENT = -1;

    // Function that implements Dijkstra's
    // single source shortest path
    // algorithm for a graph represented
    // using adjacency matrix
    // representation
    public static void dijkstra(double[][] adjacencyMatrix, int origin, int destination, HashMap<String, Integer> hashmap, HashMap<Integer, String> reverseHashmap) {
        int nVertices = adjacencyMatrix[0].length;
        int startVertex = 0;
        int endVertex = 0;
        try{
            startVertex = hashmap.get(Integer.toString(origin));
            endVertex = hashmap.get(Integer.toString(destination));
        }catch(NullPointerException e){
            System.out.println("Bus stop ID does not exist");
            return;
        }


        // shortestDistances[i] will hold the
        // shortest distance from src to i
        double[] shortestDistances = new double[nVertices];

        // added[i] will true if vertex i is
        // included / in shortest path tree
        // or shortest distance from src to
        // i is finalized
        boolean[] added = new boolean[nVertices];

        // Initialize all distances as
        // INFINITE and added[] as false
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Double.MAX_VALUE;
            added[vertexIndex] = false;
        }

        // Distance of source vertex from
        // itself is always 0
        shortestDistances[startVertex] = 0;

        // Parent array to store shortest
        // path tree
        int[] parents = new int[nVertices];

        // The starting vertex does not
        // have a parent
        parents[startVertex] = NO_PARENT;

        // Find shortest path for all
        // vertices
        for (int i = 1; i < nVertices; i++) {

            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // first iteration.
            int nearestVertex = -1;
            double shortestDistance = Double.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            // Mark the picked vertex as
            // processed
            if(nearestVertex != -1){
                added[nearestVertex] = true;

                // Update dist value of the
                // adjacent vertices of the
                // picked vertex.
                for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
                    double edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                    if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
                        parents[vertexIndex] = nearestVertex;
                        shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                    }
                }
            }
        }

        printSolution(startVertex, endVertex, shortestDistances, parents, reverseHashmap);
    }

    // A utility function to print
    // the constructed distances
    // array and shortest paths
    public static void printSolution(int startVertex, int endVertex, double[] distances, int[] parents, HashMap<Integer, String> reverseHashmap) {
        int nVertices = distances.length;
        String origin = reverseHashmap.get(startVertex);
        String destination = reverseHashmap.get(endVertex);

        System.out.print("Vertex\t\t\t\t cost\t\tPath");
        System.out.print("\n" + origin + " -> ");
        System.out.print(destination + " \t\t ");
        System.out.print(distances[endVertex] + "\t\t");
        printPath(endVertex, parents, reverseHashmap);
    }

    // Function to print shortest path
    // from source to currentVertex
    // using parents array
    public static void printPath(int currentVertex, int[] parents, HashMap<Integer, String> reverseHashmap) {

        // Base case : Source node has
        // been processed
        if (currentVertex == NO_PARENT) {
            return;
        }
        printPath(parents[currentVertex], parents, reverseHashmap);
        System.out.print(reverseHashmap.get(currentVertex) + " ");
    }
}