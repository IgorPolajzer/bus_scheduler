package gtfs;

public class Stop {
    private String stopId;
    private String stopCode;
    private String stopName;
    private String stopDesc;
    private String stopLat;
    private String stopLon;
    private String zoneId;
    private String stopUrl;
    private String locationType;
    private String parentStation;
    private String stopTimezone;
    private String wheelchairBoarding;

    public Stop(String stopId, String stopCode, String stopName, String stopDesc,
                String stopLat, String stopLon, String zoneId, String stopUrl,
                String locationType, String parentStation, String stopTimezone,
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

    public String getStopCode() {
        return stopCode;
    }

    public String getStopName() {
        return stopName;
    }

    public String getStopDesc() {
        return stopDesc;
    }

    public String getStopLat() {
        return stopLat;
    }

    public String getStopLon() {
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