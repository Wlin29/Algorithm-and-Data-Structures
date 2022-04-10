/*
    Searching for all trips with a given arrival time, returning full details of all trips matching the
    criteria (zero, one or more), sorted by trip id

    @author: Wen Geng Lin
 */

import java.util.ArrayList;
import java.util.HashMap;

public class searchForAllTrips {

    searchForAllTrips(String input, HashMap<Integer, ArrayList<String>> listSortedByTime){
        int arrivalTime = Integer.parseInt(input);

        ArrayList<String> result = (listSortedByTime.get(arrivalTime));
        System.out.println("trip_id,arrival_time,departure_time,stop_id,stop_sequence,stop_headsign,pickup_type,drop_off_type,shape_dist_traveled");
        for(int i=0; i< result.size(); i++){
            System.out.println(result.get(i));
        }
    }
}
