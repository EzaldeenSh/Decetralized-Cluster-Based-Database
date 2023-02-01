package server;

import nodes.NodesStarter;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        new NodesStarter().startAllNodes();
        System.out.println("Server Started");
        try{
            ServerSocket serverSocket = new ServerSocket(8000);
            serverSocket.setReuseAddress(true);
            while (true){
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
