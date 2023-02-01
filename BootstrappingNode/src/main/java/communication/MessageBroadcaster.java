package communication;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MessageBroadcaster {
    private final List<Integer> nodesPorts;
    public MessageBroadcaster(){
        nodesPorts = new ArrayList<>();
        nodesPorts.add(8081);
        nodesPorts.add(8082);
        nodesPorts.add(8083);
        nodesPorts.add(8084);
    }
    public void broadcastToAll(Message message) throws IOException {
        for(int portNumber : nodesPorts){
            broadcastMessage(portNumber , message);
        }
    }
    private void broadcastMessage(int portNumber,Message message) throws IOException {
        Socket socket = new Socket("localhost", portNumber);
        ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
        toServer.writeObject("Node");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        toServer.writeObject(message);
        socket.close();
    }
}
