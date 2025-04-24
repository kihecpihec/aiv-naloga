package si.um.feri.client;

import si.um.feri.ejb.PolnilnaPostajaDaoRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class RemoteClient {
    public static void main(String[] args) {
        try {
            // Konfiguracija JNDI
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

            Context context = new InitialContext(props);

            PolnilnaPostajaDaoRemote dao = (PolnilnaPostajaDaoRemote) context.lookup("ejb:/PolnilnaPostajaEJB/PolnilnaPostajaDaoBean!si.um.feri.ejb.PolnilnaPostajaDaoRemote");

            boolean moznoPolnjenje = dao.preveriPolnjenje("Postaja1", "User1");
            if (moznoPolnjenje) {
                System.out.println("Polnjenje je možno.");
            } else {
                System.out.println("Polnjenje ni možno.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
