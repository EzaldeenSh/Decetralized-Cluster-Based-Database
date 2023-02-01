package nodes;

public class Node implements Comparable{
    private final String nodeId;
    private final int portNumber;
    private int numberOfRegisteredUsers;

    public Node(String nodeId, int portNumber, int numberOfRegisteredUsers) {
        this.nodeId = nodeId;
        this.portNumber = portNumber;
        this.numberOfRegisteredUsers = numberOfRegisteredUsers;
    }

    @Override
    public String toString() {
        return "nodes.Node{" +
                "nodeId='" + nodeId + '\'' +
                ", portNumber=" + portNumber +
                ", numberOfConnectedUsers=" + numberOfRegisteredUsers +
                '}';
    }
    public String getNodeId() {
        return nodeId;
    }
    public int getPortNumber() {
        return portNumber;
    }

    public int getNumberOfRegisteredUsers() {
        return numberOfRegisteredUsers;
    }

    public void setNumberOfRegisteredUsers(int numberOfRegisteredUsers) {
        this.numberOfRegisteredUsers = numberOfRegisteredUsers;
    }
    @Override
    public int compareTo(Object o) {
        Node otherNode = (Node) o;
        return Integer.compare(this.numberOfRegisteredUsers, otherNode.numberOfRegisteredUsers);
    }
}
