import gtfs.Stop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class BusScheduler {

    private static Stop getStopFromId(String id){
        try{
            FileReader file = new FileReader("gtfs/stops.txt");
            BufferedReader br = new BufferedReader(file);
            br.readLine(); // Skip header line
            String line = br.readLine();
            String[] fields;
            boolean found = false;

            while (!found) {
                fields = line.split(",", -1);
                if ((line = br.readLine()) == null) {
                    break;
                }
                if(id.equals(fields[0])){
                    found = true;
                }
            }

            if(found) {
                fields = line.split(",", -1);
                return new Stop(fields[0],
                        fields[1],
                        fields[2],
                        fields[3],
                        fields[4],
                        fields[5],
                        fields[6],
                        fields[7],
                        fields[8],
                        fields[9],
                        fields[10],
                        fields[11]
                );
            }
            else{
                System.out.print("Stop with given ID not found");
            }

        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        return null;
    }
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length != 3) {
            System.out.print("Not enough arguments! Use case: station_id number_of_coming_busses type_od_time(relative/absolute)");
            System.exit(0);
        }

        String stationId = args[0];
        String arivingBussesCtr = args[1];
        String timeType = args[2];

        if(!Objects.equals(timeType, "absolute") && !Objects.equals(timeType, "relative")){
            System.out.print("type of time must be 'relative' or 'absolute'");
            System.exit(0);
        }

        Stop stop = getStopFromId(stationId);

        if (stop == null){
            System.out.print("Something went wrong while creating stop");
            System.exit(1);
        }

        System.out.print(stop.getStopName());
    }
}
