/*
    Searching for a bus stop by full name or by the first few characters in the name, using a
    ternary search tree (TST), returning the full stop information for each stop matching the
    search criteria (which can be zero, one or more stops)

    @ author: Wen Geng Lin
 */

public class searchForBusStop {
    searchForBusStop(String input, TernarySearchTree tst){
        String output = tst.search(input);

        // If there are no matching outputs
        if(output == null){
            System.out.println("There are no bus stops matching the input");
        }
        // If there are multiple possible outputs
        else if(output.equals("-1")){
            int numberOfPossibleStops = tst.al.size();
            System.out.println("stop_id,stop_code,stop_name,stop_desc,stop_lat,stop_lon,zone_id,stop_url,location_type,parent_station");
            for(int i=0; i<numberOfPossibleStops; i++){
                System.out.println(tst.al.get(i));
            }
        }
        // If there is one matching output
        else{
            System.out.println("stop_id,stop_code,stop_name,stop_desc,stop_lat,stop_lon,zone_id,stop_url,location_type,parent_station");
            System.out.println(output);
        }
    }
}
