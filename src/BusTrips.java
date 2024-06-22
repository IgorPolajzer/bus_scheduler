import gtfs.Route;
import gtfs.Stop;
import gtfs.StopTime;
import gtfs.Trip;

import java.security.KeyPair;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class BusTrips {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Not enough arguments! Use case: station_id number_of_coming_busses type_of_time(relative/absolute)");
            System.exit(0);
        }

        String stationId = args[0];
        int arrivingBussesCtr = Integer.parseInt(args[1]);
        String timeType = args[2];

        if (!Objects.equals(timeType, "absolute") && !Objects.equals(timeType, "relative")) {
            System.out.println("Type of time must be 'relative' or 'absolute'");
            System.exit(0);
        }

        // Retireve Stop object from given stop id
        Stop stop = Stop.getStopFromId(stationId, "gtfs/stops.txt");
        Queue<StopTime> stopTimes = StopTime.getStopTimesByStopId(stop.getStopId(), "gtfs/stop_times.txt");

        if (stop == null) {
            System.out.println("Stop with given ID not found or error reading stops file.");
            System.exit(1);
        }

        Date date = new Date();
        Queue<StopTime> validStopTimes = filterValidStopTimes(stopTimes, date, arrivingBussesCtr);

        displaySortedStopTimes(stop, validStopTimes, date, timeType);
    }

    private static Queue<StopTime> filterValidStopTimes(Queue<StopTime> stopTimes, Date date, int arrivingBussesCtr) {
        Queue<StopTime> validStopTimes = new LinkedList<>();

        // Iterate through all the stop times and add the valid ones to the que
        while (!stopTimes.isEmpty() && validStopTimes.size() < arrivingBussesCtr) {
            StopTime stopTime = stopTimes.remove();
            long timeDiffMillis = date.getTime() - stopTime.getArrivalTime().getTime();
            long timeDiffHours = timeDiffMillis / (60 * 60 * 1000);

            if (timeDiffHours <= 2 && timeDiffMillis > 0) {
                validStopTimes.add(stopTime);
            }
        }

        return validStopTimes;
    }

    private static void displaySortedStopTimes(Stop stop, Queue<StopTime> validStopTimes, Date date, String timeType) {
        Queue<Map.Entry<Integer, Queue<StopTime>>> sortedStopTimes = new LinkedList<>();

        for (StopTime stopTime : validStopTimes) {
            Trip trip = Trip.getTripFromId(stopTime.getTripId(), "gtfs/trips.txt");

            if (trip == null) {
                System.out.println("Trip with ID " + stopTime.getTripId() + " not found or error reading trips file.");
                continue;
            }

            Route route = Route.getRouteFromId(trip.getRouteId().toString(), "gtfs/routes.txt");

            if (route == null) {
                System.out.println("Route with ID " + trip.getRouteId() + " not found or error reading routes file.");
                continue;
            }

            // Check if an entry with the same bus line number already exists
            boolean exists = false;
            for (Map.Entry<Integer, Queue<StopTime>> sortedStopTime : sortedStopTimes) {
                // Add new entry to the queue
                if (Objects.equals(sortedStopTime.getKey(), trip.getRouteId())) {
                    sortedStopTime.getValue().add(stopTime);
                    exists = true;
                    break;
                }
            }

            // Create new queue for the bus line number and add the first entry
            if (!exists) {
                Queue<StopTime> newLine = new LinkedList<>();
                newLine.add(stopTime);
                sortedStopTimes.add(Map.entry(trip.getRouteId(), newLine));
            }
        }


        // Print out the result
        System.out.println(stop.getStopName());

        for (Map.Entry<Integer, Queue<StopTime>> line : sortedStopTimes) {
            System.out.print(line.getKey() + ": ");

            for (StopTime stopTime : line.getValue()) {
                long time = stopTime.getArrivalTime().getTime();

                if (timeType.equals("absolute")) {
                    System.out.print(stopTime.getArrivalTime().getHours() + ":" + stopTime.getArrivalTime().getMinutes());
                } else if (timeType.equals("relative")) {
                    long diff = date.getTime() - time;
                    long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);

                    System.out.print(minutes + "min");
                }
                System.out.print(", ");
            }

            System.out.println();
        }
    }
}
