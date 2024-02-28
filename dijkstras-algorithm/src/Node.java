import lombok.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Node implements Comparable<Node> {

    // name
    private String name;

    // simulate infinity
    private Integer distance = Integer.MAX_VALUE;

    // list to store the shortest path
    private List<Node> shortestPath = new LinkedList<>();

    // map to storage the adjacent nodes of that node and the edge weight separating them
    private Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(String name) {
        this.name = name;
    }

    public void addAdjacentNode(Node node, int weight) {
        adjacentNodes.put(node, weight);
    }

    // priority queue to extract the unresolved node with the shortest path
    @Override
    public int compareTo(Node node) {
        return Integer.compare(this.distance, node.getDistance());
    }
}
