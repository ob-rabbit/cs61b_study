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

    //节点额外信息
    private Map<String, String> extraInfo;
    private Set<Long> ways;

    public Node() {

    }

    public Node(long id, double lon, double lat) {
        this.id = id;
        this.latitude = lat;
        this.longitude = lon;

        this.adjacentNodes = new HashSet<>();
        this.extraInfo = new HashMap<>();
        this.ways = new HashSet<>();
    }

    public Map<String, String> getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Map<String, String> extraInfo) {
        this.extraInfo = extraInfo;
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
}
