import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class createHashMap {

    static HashMap<Integer, ArrayList<String>> createHashMap(String stopTimesFile){
        try{
            // This hashmap will map each time to the stopInfo arraylist which contains info on all stops for that given time
            HashMap<Integer, ArrayList<String>> listSortedByTime = new HashMap<Integer, ArrayList<String>>();

            File stopTimes = new File(stopTimesFile);
            Scanner scanner = new Scanner(stopTimes);
            // Ignore the first line as that only contains the header
            String line = scanner.nextLine();
            // This arraylist will contain all the stops for a given time
            ArrayList<String> stopInfo = new ArrayList<>();
            String[] stopTimesInfo = new String[10];
            String arrivalTime = new String();
            // Convert the arrival time to an int for checking validity
            int timeAsInt = 0;

            while(scanner.hasNextLine()){
                line = scanner.nextLine();
                stopTimesInfo = line.trim().split(",");
                arrivalTime = stopTimesInfo[2];
                // get rid of ":" so we can convert the time to an int for checking validity
                arrivalTime = arrivalTime.replace(":", "");
                arrivalTime = arrivalTime.replace(" ", "");
                timeAsInt = Integer.parseInt(arrivalTime);

                // Make sure that the time is valid
                if(timeAsInt<235959){
                    // If there is no key for that certain time, create one and initialise the value with a new arraylist
                    if(listSortedByTime.get(timeAsInt)==null){
                        stopInfo = new ArrayList<>();
                        listSortedByTime.put(timeAsInt, stopInfo);
                    }
                    // else add the information of the current line to the arraylist
                    else{
                        stopInfo = listSortedByTime.get(timeAsInt);
                        stopInfo.add(line);
                        listSortedByTime.put(timeAsInt, stopInfo);
                    }
                }
            }
            scanner.close();
            return listSortedByTime;

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        return null;
    }
}
