package gtfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StopTime {
    private String tripId;
    private Date arrivalTime;
    private Date departureTime;
    private String stopId;
    private Integer stopSequence;
    private String stopHeadsign;
    private String pickupType;
    private String dropOffType;
    private Double shapeDistTraveled;
    private String timepoint;

    public StopTime(String tripId, Date arrivalTime, Date departureTime,
                    String stopId, Integer stopSequence, String stopHeadsign,
                    String pickupType, String dropOffType, Double shapeDistTraveled,
                    String timepoint) {
        this.tripId = tripId;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.stopId = stopId;
        this.stopSequence = stopSequence;
        this.stopHeadsign = stopHeadsign;
        this.pickupType = pickupType;
        this.dropOffType = dropOffType;
        this.shapeDistTraveled = shapeDistTraveled;
        this.timepoint = timepoint;
    }

    public String getTripId() {
        return tripId;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public String getStopId() {
        return stopId;
    }

    public Integer getStopSequence() {
        return stopSequence;
    }

    public String getStopHeadsign() {
        return stopHeadsign;
    }

    public String getPickupType() {
        return pickupType;
    }

    public String getDropOffType() {
        return dropOffType;
    }

    public Double getShapeDistTraveled() {
        return shapeDistTraveled;
    }

    public String getTimepoint() {
        return timepoint;
    }

    public static Queue<StopTime> getStopTimesByStopId(String stopId, String filePath) {
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader br = new BufferedReader(file);
            br.readLine(); // Skip header line
            String line;
            Queue<StopTime> stopTimes = new LinkedList<>();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", -1);
                if (stopId.equals(fields[3])) {
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.MONTH, Calendar.JUNE);
                    Date arrivalTime = dateFormat.parse(c.get(Calendar.DATE) + "-"
                            + ((c.get(Calendar.MONTH) + 1) % 12) + "-"
                            + c.get(Calendar.YEAR) + " "
                            + fields[1]);
                    Date departureTime = dateFormat.parse(c.get(Calendar.DATE) + "-"
                            + ((c.get(Calendar.MONTH) + 1) % 12) + "-"
                            + c.get(Calendar.YEAR) + " "
                            + fields[2]);

                    stopTimes.add(
                            new StopTime(
                                    fields[0],
                                    arrivalTime,
                                    departureTime,
                                    fields[3],
                                    Objects.equals(fields[4], "") ? null : Integer.parseInt(fields[4]),
                                    fields[5],
                                    fields[6],
                                    fields[7],
                                    Objects.equals(fields[8], "") ? null : Double.parseDouble(fields[8]),
                                    fields[9]
                            )
                    );
                }
            }

            return stopTimes;

        } catch (IOException e) {
            System.out.print(e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public String toString() {
        return "tripId=" + tripId + '\n' +
                ", arrivalTime=" + arrivalTime + '\n' +
                ", departureTime=" + departureTime + '\n' +
                ", stopId=" + stopId + '\n' +
                ", stopSequence=" + stopSequence + '\n' +
                ", stopHeadsign=" + stopHeadsign + '\n' +
                ", pickupType=" + pickupType + '\n' +
                ", dropOffType=" + dropOffType + '\n' +
                ", shapeDistTraveled=" + shapeDistTraveled + '\n' +
                ", timepoint=" + timepoint + '\n';
    }
}
