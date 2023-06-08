import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
    private Socket socket;
    private ArrayList<ServerThread> threadArrayList;
    private PrintWriter output;

    public ServerThread(Socket socket, ArrayList<ServerThread> threads) {
        this.socket = socket;
        this.threadArrayList = threads;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String outputString = input.readLine();

                if (outputString.equals("exit")) {
                    break;
                }
                printAllClient(outputString);
                System.out.println("Server Received" + outputString);
            }
        } catch (Exception e) {
            System.out.println("Error occurred "+ e.getStackTrace());
        }
    }

    private void printAllClient(String outputString) {
        for (ServerThread st : threadArrayList) {
            st.output.println(outputString);
        }
    }
}
