import java.rmi.*;
import java.util.List;

public interface RemoteFileObject extends Remote {
    // TODO: add method signatures based on assignment description
    List<String> readFile(String fileName) throws RemoteException;
}
