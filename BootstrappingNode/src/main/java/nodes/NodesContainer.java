package nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NodesContainer {
    private final List<Node> nodeList;
    private static NodesContainer instance;
    private NodesContainer(){
        nodeList = new ArrayList<>();
        initializeList();
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public static NodesContainer getInstance(){
        if(instance == null)
            instance = new NodesContainer();

        return instance;
    }
    private void initializeList(){
        Node node1 = new Node("node1" , 8081 , 0);
        Node node2 = new Node("node2" , 8082 , 0);
        Node node3 = new Node("node3" , 8083 , 0);
        Node node4 = new Node("node4" , 8084 , 0);
        this.nodeList.add(node1);
        this.nodeList.add(node2);
        this.nodeList.add(node3);
        this.nodeList.add(node4);
    }
    public int getLeastRegisteredNodePort(){
        Collections.sort(nodeList);
        Node resultNode = nodeList.remove(0);
        resultNode.setNumberOfRegisteredUsers(resultNode.getNumberOfRegisteredUsers() + 1);
        nodeList.add(resultNode);
        return resultNode.getPortNumber();

    }



}
