import java.util.ArrayList;
import java.util.List;

/**
 * @author Bunny
 * @create 2022-01-23 14:54
 */
public class Way {

    private long id;

    //名称
    private String name;

    //道路类型
    private String highway;

    //经过的节点
    private List<Long> nodes;

    private String maxSpeed;

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Way(){

    }
    public Way(long id){
        this.id = id;
        this.nodes = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHighway() {
        return highway;
    }

    public void setHighway(String highway) {
        this.highway = highway;
    }

    public List<Long> getNodes() {
        return nodes;
    }

    public void setNodes(List<Long> nodes) {
        this.nodes = nodes;
    }
}
