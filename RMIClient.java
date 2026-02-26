import java.rmi.*;
import java.util.List;

public class RMIClient {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java RMIClient <filename>");
            return;
        }

        String fileName = args[0];

        // Look up the remote object — cast to RemoteFileObject, not RMIServer
        RemoteFileObject remoteFile = (RemoteFileObject) Naming.lookup("rmi://127.0.0.1:6100/FileServer");

        // TODO: call remote method and print each line
        List<String> lines = remoteFile.readFile(fileName);
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
