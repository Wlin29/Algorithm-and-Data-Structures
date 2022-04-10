/*
    Searching for all trips with a given arrival time, returning full details of all trips matching the
    criteria (zero, one or more), sorted by trip id

    @author: Wen Geng Lin
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class searchForAllTrips {

    searchForAllTrips(String input, HashMap<Integer, ArrayList<String>> listSortedByTime){
        input = input.replace(":", "");
        int arrivalTime = Integer.parseInt(input);

        System.out.print(listSortedByTime.get(arrivalTime));
    }
}
