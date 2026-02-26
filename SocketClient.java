import java.io.*;
import java.net.*;

public class SocketClient {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java SocketClient <string>");
            return;
        }

        String input = args[0];
        String host = "127.0.0.1";
        int port = 5000; // TODO: match port with SocketServer

        Socket socket = new Socket(host, port);

        // Get output stream first, then input stream (avoid deadlock)
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // TODO: send input string to server
        out.println(input);

        // TODO: read and print response from server
        String response = in.readLine();
        System.out.println("Server response: " + response);

        socket.close();
    }
}
