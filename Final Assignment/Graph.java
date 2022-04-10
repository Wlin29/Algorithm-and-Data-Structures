/*
    Construct an adjacency matrix with the vertices found in stops.txt and
    edges found in stop_times.txt and transfers.txt.\

    Since the bus stop IDs do not start from 1, a hashmap is used to store
    and find the adjacency table entry of each bus stop

    @ author: Wen Geng Lin
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Graph {
    String stopTimesFile;
    String stopsFile;
    String transfersFile;

    final double INF = Double.MAX_VALUE;

    // [stopID, Adjacency matrix entry]
    HashMap<String, Integer> vertexNumber = new HashMap<String, Integer>();
    // [Adjacency matrix entry, stopID]
    HashMap<Integer, String> reverseVertexNumber = new HashMap<Integer, String>();

    // 8757 is the number of bus stops listed in stops.txt
    double[][] adjacencyMatrix = new double[8757][8757];

    Graph(String stopTimes, String stops, String transfers) {
        this.stopTimesFile = stopTimes;
        this.stopsFile = stops;
        this.transfersFile = transfers;

        // Initialise the adjacency matrix
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                if(i==j){
                    adjacencyMatrix[i][j] = 0;
                }
                else{
                    adjacencyMatrix[i][j] = INF;
                }
            }
        }

        // Populate the hashmap with the stopIDs found in stops.txt
        try {
            File stopFile = new File(stops);
            Scanner scanner = new Scanner(stopFile);
            // Do nothing with the first line as it is just contains a header
            String[] line = scanner.nextLine().trim().split(",");


            // Populate the vertexNumber hashmap with the stopID, along with it's associated Adjacency matrix entry
            int vertexCount = 0;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine().trim().split(",");
                String stopID = line[0];
                vertexNumber.put(stopID, vertexCount);
                reverseVertexNumber.put(vertexCount, stopID);
                vertexCount++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Populate the adjacency matrix with the connections found in stop_times.txt
        try {
            File stopTimesFile = new File(stopTimes);
            Scanner scanner = new Scanner(stopTimesFile);
            // Do nothing with the first line as it is just contains a header
            String[] line = scanner.nextLine().trim().split(",");

            String currentTripID = null;
            String previousTripID = null;
            String currentBusStopID = null;
            String previousBusStopID = null;
            // All connections found in this file has a weight of 1
            double weight = 1;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine().trim().split(",");
                currentTripID = line[0];
                currentBusStopID = line[3];

                // If it is still the same trip ID, the weight of the trip from one bus stop to the next one is 1
                if (currentTripID.equals(previousTripID)) {
                    // Get the vertex number of both bus stops
                    int vertexOrigin = vertexNumber.get(previousBusStopID);
                    int vertexDestination = vertexNumber.get(currentBusStopID);
                    adjacencyMatrix[vertexOrigin][vertexDestination] = weight;
                }

                previousTripID = currentTripID;
                previousBusStopID = currentBusStopID;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Populate the adjacency matrix with the connections found in transfers.txt
        try {
            File transfersFile = new File(transfers);
            Scanner scanner = new Scanner(transfersFile);
            // Do nothing with the first line as it is just contains a header
            String[] line = scanner.nextLine().trim().split(",");

            String fromBusStopID;
            String toBusStopID;
            int transferType;
            double minTransferTime;
            double weight;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine().trim().split(",");
                fromBusStopID = line[0];
                toBusStopID = line[1];
                transferType = Integer.parseInt(line[2]);

                if (transferType == 0) {
                    // If transfer type 0, weight = 2
                    weight = 2;

                    // Get the vertex number of both bus stops
                    int vertexOrigin = vertexNumber.get(fromBusStopID);
                    int vertexDestination = vertexNumber.get(toBusStopID);
                    adjacencyMatrix[vertexOrigin][vertexDestination] = weight;
                } else if (transferType == 2) {
                    // If transfer type 2, weight = min transfer time / 100
                    minTransferTime = Double.parseDouble(line[3]);
                    weight = minTransferTime / 100;

                    // Get the vertex number of both bus stops
                    int vertexOrigin = vertexNumber.get(fromBusStopID);
                    int vertexDestination = vertexNumber.get(toBusStopID);
                    adjacencyMatrix[vertexOrigin][vertexDestination] = weight;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
