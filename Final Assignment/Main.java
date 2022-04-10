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
        createHashMap hashMap = new createHashMap();
        HashMap<Integer, ArrayList<String>> listSortedByTime = hashMap.createHashMap(stopTimes);
        TernarySearchTree tst = new TernarySearchTree();
        tst.populateTernarySearchTree(stops);

        Scanner scanner = new Scanner(System.in);
        boolean quitApp = false;

        while(quitApp == false){
            System.out.println("Please select one of the following functionalities");
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
                    startVertex = scanner.next();
                    System.out.print("Please enter the destination stop ID: ");
                    endVertex = scanner.next();

                    shortestPaths shortestPath = new shortestPaths(adjacencyMatrix, startVertex, endVertex);
                    break;
                case "2":
                    String stopName;
                    System.out.print("Please enter the stop name: ");
                    stopName = scanner.next();

                    searchForBusStop searchBusStop = new searchForBusStop(stopName, tst);
                    break;
                case "3":
                    String arrivalTime;
                    System.out.print("Please enter your preferred arrival time: ");
                    arrivalTime = scanner.next();

                    searchForAllTrips searchArrivalTime = new searchForAllTrips(arrivalTime, listSortedByTime);
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
