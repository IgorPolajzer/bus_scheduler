package gtfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Stop {
    private String stopId;
    private Integer stopCode;
    private String stopName;
    private String stopDesc;
    private Double stopLat;
    private Double stopLon;
    private String zoneId;
    private String stopUrl;
    private String locationType;
    private String parentStation;
    private String stopTimezone;
    private String wheelchairBoarding;

    public Stop(String stopId,
                Integer stopCode,
                String stopName,
                String stopDesc,
                Double stopLat,
                Double stopLon,
                String zoneId,
                String stopUrl,
                String locationType,
                String parentStation,
                String stopTimezone,
                String wheelchairBoarding) {
        this.stopId = stopId;
        this.stopCode = stopCode;
        this.stopName = stopName;
        this.stopDesc = stopDesc;
        this.stopLat = stopLat;
        this.stopLon = stopLon;
        this.zoneId = zoneId;
        this.stopUrl = stopUrl;
        this.locationType = locationType;
        this.parentStation = parentStation;
        this.stopTimezone = stopTimezone;
        this.wheelchairBoarding = wheelchairBoarding;
    }

    public String getStopId() {
        return stopId;
    }

    public Integer getStopCode() {
        return stopCode;
    }

    public String getStopName() {
        return stopName;
    }

    public String getStopDesc() {
        return stopDesc;
    }

    public Double getStopLat() {
        return stopLat;
    }

    public Double getStopLon() {
        return stopLon;
    }

    public String getZoneId() {
        return zoneId;
    }

    public String getStopUrl() {
        return stopUrl;
    }

    public String getLocationType() {
        return locationType;
    }

    public String getParentStation() {
        return parentStation;
    }

    public String getStopTimezone() {
        return stopTimezone;
    }

    public String getWheelchairBoarding() {
        return wheelchairBoarding;
    }

    public static Stop getStopFromId(String id, String filePath) {
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader br = new BufferedReader(file);
            br.readLine(); // Skip header line
            String line;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", -1);
                if (id.equals(fields[0])) {
                    return new Stop(fields[0],
                            Objects.equals(fields[1], "") ? null : Integer.parseInt(fields[1]),
                            fields[2],
                            fields[3],
                            Objects.equals(fields[4], "") ? null : Double.parseDouble(fields[4]),
                            Objects.equals(fields[5], "") ? null : Double.parseDouble(fields[5]),
                            fields[6],
                            fields[7],
                            fields[8],
                            fields[9],
                            fields[10],
                            fields[11]
                    );
                }
            }

            System.out.print("Stop with given ID not found");
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "stopId=" + stopId + '\n' +
                ", stopCode=" + stopCode + '\n' +
                ", stopName=" + stopName + '\n' +
                ", stopDesc=" + stopDesc + '\n' +
                ", stopLat=" + stopLat + '\n' +
                ", stopLon=" + stopLon + '\n' +
                ", zoneId=" + zoneId + '\n' +
                ", stopUrl=" + stopUrl + '\n' +
                ", locationType=" + locationType + '\n' +
                ", parentStation=" + parentStation + '\n' +
                ", stopTimezone=" + stopTimezone + '\n' +
                ", wheelchairBoarding=" + wheelchairBoarding + '\n';
    }
}