package gtfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Trip {
    private Integer routeId;
    private Integer serviceId;
    private String tripId;
    private String tripHeadsign;
    private String tripShortName;
    private Integer directionId;
    private String blockId;
    private Integer shapeId;
    private String wheelchairAccessible;
    private String bikesAllowed;
    private String duty;
    private Integer dutySequenceNumber;
    private Integer runSequenceNumber;

    public Trip(Integer routeId,
                Integer serviceId,
                String tripId,
                String tripHeadsign,
                String tripShortName,
                Integer directionId,
                String blockId,
                Integer shapeId,
                String wheelchairAccessible,
                String bikesAllowed,
                String duty,
                Integer dutySequenceNumber,
                Integer runSequenceNumber) {
        this.routeId = routeId;
        this.serviceId = serviceId;
        this.tripId = tripId;
        this.tripHeadsign = tripHeadsign;
        this.tripShortName = tripShortName;
        this.directionId = directionId;
        this.blockId = blockId;
        this.shapeId = shapeId;
        this.wheelchairAccessible = wheelchairAccessible;
        this.bikesAllowed = bikesAllowed;
        this.duty = duty;
        this.dutySequenceNumber = dutySequenceNumber;
        this.runSequenceNumber = runSequenceNumber;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public String getTripId() {
        return tripId;
    }

    public String getTripHeadsign() {
        return tripHeadsign;
    }

    public String getTripShortName() {
        return tripShortName;
    }

    public Integer getDirectionId() {
        return directionId;
    }

    public String getBlockId() {
        return blockId;
    }

    public Integer getShapeId() {
        return shapeId;
    }

    public String getWheelchairAccessible() {
        return wheelchairAccessible;
    }

    public String getBikesAllowed() {
        return bikesAllowed;
    }

    public String getDuty() {
        return duty;
    }

    public Integer getDutySequenceNumber() {
        return dutySequenceNumber;
    }

    public Integer getRunSequenceNumber() {
        return runSequenceNumber;
    }

    public static Trip getTripFromId(String id, String filePath) {
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader br = new BufferedReader(file);
            br.readLine(); // Skip header line
            String line;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", -1);
                if (id.equals(fields[2])) {
                    return new Trip(
                            Integer.parseInt(fields[0]),
                            Integer.parseInt(fields[1]),
                            fields[2],
                            fields[3],
                            fields[4],
                            Integer.parseInt(fields[5]),
                            fields[6],
                            Integer.parseInt(fields[7]),
                            fields[8],
                            fields[9],
                            fields[10],
                            Integer.parseInt(fields[11]),
                            Integer.parseInt(fields[12])
                    );
                }
            }

            System.out.print("Trip with given ID not found");
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        return null;
    }
    @Override
    public String toString() {
        return "routeId=" + routeId + '\n' +
                ", serviceId=" + serviceId + '\n' +
                ", tripId=" + tripId + '\n' +
                ", tripHeadsign=" + tripHeadsign + '\n' +
                ", tripShortName=" + tripShortName + '\n' +
                ", directionId=" + directionId + '\n' +
                ", blockId=" + blockId + '\n' +
                ", shapeId=" + shapeId + '\n' +
                ", wheelchairAccessible=" + wheelchairAccessible + '\n' +
                ", bikesAllowed=" + bikesAllowed + '\n' +
                ", duty=" + duty + '\n' +
                ", dutySequenceNumber=" + dutySequenceNumber + '\n' +
                ", runSequenceNumber=" + runSequenceNumber + '\n';
    }
}
