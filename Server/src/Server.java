import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static Socket socket;
    private static ServerSocket serverSocket;
    private static ArrayList<ServerThread> threadArrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(4000);

        while (true) {
            System.out.println("Server is waiting for the clients requests..!");
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected");

            ServerThread serverThread = new ServerThread(socket, threadArrayList);
            threadArrayList.add(serverThread);
            serverThread.start();
        }


    }

}
