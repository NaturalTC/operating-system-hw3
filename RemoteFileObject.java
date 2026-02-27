import java.rmi.*;

public interface RemoteFileObject extends Remote
{
    public abstract void open(String fileName)
        throws RemoteException;

    public abstract String readLine()
        throws RemoteException;

    public abstract void close()
        throws RemoteException;
}
