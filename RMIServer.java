import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.io.*;
import java.util.*;

public class RMIServer extends UnicastRemoteObject implements RemoteFileObject {

    public RMIServer() throws RemoteException {
        super();
    }

    @Override
    public List<String> readFile(String fileName) throws RemoteException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RemoteException("Error reading file: " + e.getMessage());
        }
        return lines;
    }

    public static void main(String[] args) throws Exception {
        // Create rmiregistry on port 6100 from within the code
        LocateRegistry.createRegistry(6100);

        RMIServer server = new RMIServer();
        Naming.rebind("rmi://127.0.0.1:6100/FileServer", server);

        System.out.println("RMI Server ready.");
    }
}
