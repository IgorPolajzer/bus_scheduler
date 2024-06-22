package gtfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Route {
    private Integer routeId;
    private String agencyId;
    private String routeShortName;
    private String routeLongName;
    private String routeDesc;
    private Integer routeType;
    private String routeUrl;
    private String routeColor;
    private String routeTextColor;

    public Route(Integer routeId,
                 String agencyId,
                 String routeShortName,
                 String routeLongName,
                 String routeDesc,
                 Integer routeType,
                 String routeUrl,
                 String routeColor,
                 String routeTextColor) {
        this.routeId = routeId;
        this.agencyId = agencyId;
        this.routeShortName = routeShortName;
        this.routeLongName = routeLongName;
        this.routeDesc = routeDesc;
        this.routeType = routeType;
        this.routeUrl = routeUrl;
        this.routeColor = routeColor;
        this.routeTextColor = routeTextColor;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public String getRouteShortName() {
        return routeShortName;
    }

    public String getRouteLongName() {
        return routeLongName;
    }

    public String getRouteDesc() {
        return routeDesc;
    }

    public Integer getRouteType() {
        return routeType;
    }

    public String getRouteUrl() {
        return routeUrl;
    }

    public String getRouteColor() {
        return routeColor;
    }

    public String getRouteTextColor() {
        return routeTextColor;
    }

    public static Route getRouteFromId(String id, String filePath) {
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader br = new BufferedReader(file);
            br.readLine(); // Skip header line
            String line;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",", -1);
                if (id.equals(fields[0])) {
                    return new Route(
                            Integer.parseInt(fields[0]),
                            fields[1],
                            fields[2],
                            fields[3],
                            fields[4],
                            Integer.parseInt(fields[5]),
                            fields[6],
                            fields[7],
                            fields[8]
                    );
                }
            }

            System.out.print("Route with given ID not found");
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "routeId=" + routeId + '\n' +
                ", agencyId=" + agencyId + '\n' +
                ", routeShortName=" + routeShortName + '\n' +
                ", routeLongName=" + routeLongName + '\n' +
                ", routeDesc=" + routeDesc + '\n' +
                ", routeType=" + routeType + '\n' +
                ", routeUrl=" + routeUrl + '\n' +
                ", routeColor=" + routeColor + '\n' +
                ", routeTextColor=" + routeTextColor + '\n';
    }
}
