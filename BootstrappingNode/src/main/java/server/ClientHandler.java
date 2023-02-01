package server;


import communication.Message;
import communication.MessageBroadcaster;
import data.Credentials;
import nodes.NodesContainer;
import data.User;
import data.UserGenerator;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler implements Runnable{
    private final ObjectOutputStream toClient;
    private final List<User> users = new ArrayList<>();
    private final Socket client;


    public ClientHandler(Socket socket) throws IOException {/*
    initiate
    */
        toClient = new ObjectOutputStream(socket.getOutputStream());
        client = socket;
    }
    private void updateAllNodes(User user) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username" , user.getUsername());
        jsonObject.put("password" , user.getPassword());
        Message message = new Message();
        message.setFunction("WriteObject");
        String[] params = new String[3];
        params[0] = "admin";
        params[1] = "users";
        params[2] = jsonObject.toJSONString();
        message.setParams(params);
        MessageBroadcaster messageBroadcaster = new MessageBroadcaster();
        messageBroadcaster.broadcastToAll(message);
    }
    @Override
    public void run() {

        try {
                     NodesContainer nodesContainer = NodesContainer.getInstance();
                     int leastRegisteredNodePort = nodesContainer.getLeastRegisteredNodePort();
                     UserGenerator userGenerator = new UserGenerator();
                     User user = userGenerator.generateUser();
                     while (users.contains(user)){
                         user = userGenerator.generateUser();
                     }
                     users.add(user);

                      Credentials clientCredentials = new Credentials(user , leastRegisteredNodePort);
                      toClient.writeObject(clientCredentials);
                      toClient.flush();
                      client.close();

                      updateAllNodes(user);

    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

