import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Bunny
 * @create 2022-01-23 14:54
 */
public class Node {

    private long id;

    private String name;

    //节点经度
    private double longitude;
    //节点纬度
    private double latitude;

    private Set<Long> adjacentNodes;

    private Set<Long> ways;

    private double priority = 0;

    private double distance = 0;

    public Node() {

    }

    public Node(long id, double lon, double lat) {
        this.id = id;
        this.latitude = lat;
        this.longitude = lon;

        this.adjacentNodes = new HashSet<>();

        this.ways = new HashSet<>();
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWays(Set<Long> ways) {
        this.ways = ways;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Set<Long> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Set<Long> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public Set<Long> getWays() {
        return ways;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }
}
