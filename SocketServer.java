import java.io.*;
import java.net.*;

public class SocketServer {

    public static void main(String[] args) throws Exception {
        int port = 6100;

        try {
            ServerSocket sock = new ServerSocket(port);
            while (true) {
                Socket client = sock.accept();

                // Lock-step: get input stream first while client gets output stream first
                BufferedReader pin = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String received = pin.readLine();
                System.out.println("Received: " + received);

                // Construct MessageImpl, count characters and digits
                MessageImpl msg = new MessageImpl(received);
                msg.setCounts();

                // Then get ObjectOutputStream and write the MessageImpl object to the socket
                ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
                oos.writeObject(msg);
                oos.flush();

                client.close();
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
