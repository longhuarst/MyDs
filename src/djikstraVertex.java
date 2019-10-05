import java.util.List;
import java.util.Map;

public class djikstraVertex {

    String data;

    Map<djikstraVertex,String> next;//到下一个节点和长度


    int shortest;//最短路径值

    String shortestPath;//最短路径


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Map<djikstraVertex, String> getNext() {
        return next;
    }

    public void setNext(Map<djikstraVertex, String> next) {
        this.next = next;
    }

    public int getShortest() {
        return shortest;
    }

    public void setShortest(int shortest) {
        this.shortest = shortest;
    }

    public String getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(String shortestPath) {
        this.shortestPath = shortestPath;
    }
}
