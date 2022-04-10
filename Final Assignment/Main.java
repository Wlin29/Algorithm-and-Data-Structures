import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String stopTimes = "stop_times.txt";
        String stops = "stops.txt";
        String transfers = "transfers.txt";

        Graph adjacencyMatrix = new Graph(stopTimes, stops, transfers);
        HashMap<String, Integer> adjacencyMatrixHashMap = adjacencyMatrix.vertexNumber;
        HashMap<Integer, String> reverseAdjacencyMatrixHashMap = adjacencyMatrix.reverseVertexNumber;
        createHashMap hashMap = new createHashMap();
        HashMap<Integer, ArrayList<String>> listSortedByTime = hashMap.createHashMap(stopTimes);
        TernarySearchTree tst = new TernarySearchTree();
        tst.populateTernarySearchTree(stops, tst);

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        boolean quitApp = false;

        while(quitApp == false){
            System.out.println("\nPlease select one of the following functionalities");
            System.out.println("1: Find the shortest paths between two stops");
            System.out.println("2: Search for bus stop by name or by first few letters in the name");
            System.out.println("3: Search for all trips with a given arrival time");
            System.out.println("4: Quit the program");

            String input = scanner.next();
            switch(input){
                case "1":
                    String startVertex;
                    String endVertex;

                    System.out.print("Please enter the origin stop ID: ");
                    while(!scanner.hasNextInt()) {
                        scanner.next();
                        System.out.println("try again with an integer you nonce");
                    }
                    startVertex = scanner.next();

                    System.out.print("Please enter the destination stop ID: ");
                    while(!scanner.hasNextInt()) {
                        scanner.next();
                        System.out.println("try again with an integer you nonce");
                    }
                    endVertex = scanner.next();

                    shortestPaths shortestPath = new shortestPaths(adjacencyMatrix, startVertex, endVertex, adjacencyMatrixHashMap, reverseAdjacencyMatrixHashMap);
                    System.out.println("");
                    break;
                case "2":
                    String stopName;
                    System.out.print("Please enter the stop name: ");
                    stopName = scanner.next();

                    searchForBusStop searchBusStop = new searchForBusStop(stopName, tst);
                    System.out.println("");
                    break;
                case "3":
                    String arrivalTime = new String();
                    System.out.print("Please enter your preferred arrival time in the format [HH:MM:SS]: ");

                    boolean wrongFormat = true;
                    while(wrongFormat) {
                        arrivalTime = scanner.next();
                        arrivalTime = arrivalTime.replace(":", "");
                        arrivalTime = arrivalTime.replace(" ", "");

                        try {
                            int time = Integer.parseInt(arrivalTime);
                            if(time < 240000){
                                wrongFormat = false;
                            }
                            else{
                                System.out.println("The input is greater than 23:59:59");
                            }
                        } catch(NumberFormatException e){
                            System.out.println("The input is required to be integers separated by colons");
                        }

                    }

                    searchForAllTrips searchArrivalTime = new searchForAllTrips(arrivalTime, listSortedByTime);
                    System.out.println("");
                    break;
                case "4":
                    quitApp = true;
                    break;
                default:
                    System.out.println("Sorry that's an invalid input, please try again");
            }
        }

    }
}
