import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
    private Socket socket;
    private ArrayList<ServerThread> threadArrayList;
    private PrintWriter output;
    private BufferedReader reader;

    public ServerThread(Socket socket, ArrayList<ServerThread> threads) {
        try {
            this.socket = socket;
            this.threadArrayList = threads;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            String msg;
            while ((msg = reader.readLine()) != null) {
                if (msg.equalsIgnoreCase( "exit")) {
                    return;
                }
                for (ServerThread cl : threadArrayList) {
                    cl.output.println(msg);
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred "+ e.getStackTrace());
        }
        finally {
            try {
                reader.close();
                output.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
