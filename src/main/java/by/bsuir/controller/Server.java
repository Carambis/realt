package by.bsuir.controller;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Server {
    private Socket clientSocket = null;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;

    public Server(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> void sendRequest(T obj) {
        try {
            objectOutputStream.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> T recvRequest() {
        T obj;
        try {
            obj = (T) objectInputStream.readObject();
        } catch (IOException e) {
            obj = null;
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            obj = null;
            e.printStackTrace();
        }
        return obj;
    }

    public void close() throws IOException{
        objectInputStream.close();
        objectOutputStream.close();
        clientSocket.close();
    }
}
