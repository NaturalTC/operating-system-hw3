import java.io.*;
import java.net.*;

public class SocketClient {

    public static void main(String[] args) throws Exception {
        String input;
        if (args.length >= 1) {
            input = args[0];
        } else {
            System.out.print("Enter message to send: ");
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            input = console.readLine();
        }

        String host = "127.0.0.1";
        int port = 6100;

        try {
            Socket sock = new Socket(host, port);

            // Lock-step: get output stream first while server gets input stream first
            PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);
            pout.println(input);

            // Then get ObjectInputStream and read the MessageImpl object from the socket
            ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
            Message msg = (Message) ois.readObject();

            System.out.println("Characters: " + msg.getCharacterCount());
            System.out.println("Digits: " + msg.getDigitCount());

            sock.close();
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
