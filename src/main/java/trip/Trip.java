package trip;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.network.Node;

import java.util.*;

public class Trip {

    private final String householdId;
    private final int personId;
    private final int tripId;
    private final int startTime;
    private final String mainMode;
    private final Purpose startPurpose;
    private final Purpose endPurpose;
    private final Map<Place,String> zones;
    private final Map<Place,Coord> coords;

    private Node origNode;

    private Node destNode;
    private final Map<Place,Boolean> coordsInsideBoundary;
    private final Map<String, Map<String,Object>> routeAttributes = new LinkedHashMap<>();

    public Trip(String householdId, int personId, int tripId, int startTime,
                String mainMode, Purpose startPurpose, Purpose endPurpose, Map<Place, String> zones, Map<Place,Coord> coords, Map<Place,Boolean> coordsInsideBoundary) {
        this.householdId = householdId;
        this.personId = personId;
        this.tripId = tripId;
        this.startTime = startTime;
        this.mainMode = mainMode;
        this.startPurpose = startPurpose;
        this.endPurpose = endPurpose;
        this.zones = zones;
        this.coords = coords;
        this.coordsInsideBoundary = coordsInsideBoundary;
    }

    public boolean routable(Place a, Place b) {
        if(coords.get(a) != null && coords.get(b) != null) {
            return coordsInsideBoundary.get(a) && coordsInsideBoundary.get(b) && !coords.get(a).equals(coords.get(b));
        } else {
            return false;
        }
    }

    public String getZone(Place place) { return zones.get(place); }

    public Coord getCoord(Place place) { return coords.get(place); }

    public String getHouseholdId() {
        return householdId;
    }

    public int getPersonId() {
        return personId;
    }

    public int getTripId() {
        return tripId;
    }

    public Object getAttribute(String route, String attr) {
        if(routeAttributes.get(route) != null) {
            return routeAttributes.get(route).get(attr);
        } else return null;
    }
}
