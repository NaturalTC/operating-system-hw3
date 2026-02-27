import java.rmi.*;

public class RMIClient {

    public static void main(String[] args) {
        try {

            // Check to see if a file has been provided when running the java program
            if (args.length < 1) {
                System.out.println("Usage: java RMIClient <filename>");
                return;
            }

            // Save the filename as a String
            String fileName = args[0];

            // Cast to RemoteFileObject, not RMIServer
            RemoteFileObject fileServer = (RemoteFileObject) Naming.lookup("rmi://127.0.0.1:6100/FileServer");

            // Tell the server to open the file — this runs on the server machine,
            // not locally. The server opens a BufferedReader on the file path we pass in.
            fileServer.open(fileName);

            String line;
            // readLine() is called on the remote server each iteration.
            // It reads one line from the file and sends it back over the network.
            // When the file has no more lines, readLine() returns null — same behavior
            // as a regular BufferedReader — so that's our signal to stop looping.
            while ((line = fileServer.readLine()) != null) {
                System.out.println(line);
            }

            // Tell the server to close its BufferedReader now that we're done reading.
            fileServer.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
