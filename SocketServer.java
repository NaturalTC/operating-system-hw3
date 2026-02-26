import java.io.*;
import java.net.*;

public class SocketServer {

    public static void main(String[] args) throws Exception {
        int port = 5000; // TODO: match port with SocketClient

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server listening on port " + port);

        Socket socket = serverSocket.accept();
        System.out.println("Client connected.");

        // Get input stream first, then output stream (avoid deadlock)
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        // TODO: read string from client
        String received = in.readLine();
        System.out.println("Received: " + received);

        // TODO: process the string using MessageImpl and send response
        Message msg = new MessageImpl();
        int chars = msg.countCharacters(received);
        int digits = msg.countDigits(received);
        out.println("Characters: " + chars + ", Digits: " + digits);

        socket.close();
        serverSocket.close();
    }
}
