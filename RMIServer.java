import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;

public class RMIServer extends UnicastRemoteObject implements RemoteFileObject {

    private BufferedReader reader;

    public RMIServer() throws RemoteException { }

    @Override
    public void open(String fileName) throws RemoteException {
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (java.io.FileNotFoundException fnfe) {
            throw new RemoteException("IO Exception", fnfe);
        }
    }

    @Override
    public String readLine() throws RemoteException {
        try {
            return reader.readLine();
        } catch (java.io.IOException ioe) {
            throw new RemoteException("IO Exception", ioe);
        }
    }

    @Override
    public void close() throws RemoteException {
        try {
            reader.close();
        } catch (java.io.IOException ioe) {
            throw new RemoteException("IO Exception", ioe);
        }
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(6100);
            RemoteFileObject fileServer = new RMIServer();
            registry.rebind("FileServer", fileServer);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
