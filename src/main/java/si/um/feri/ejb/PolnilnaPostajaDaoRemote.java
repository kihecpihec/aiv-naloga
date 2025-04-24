package si.um.feri.ejb;

import jakarta.ejb.Remote;

@Remote
public interface PolnilnaPostajaDaoRemote {
    boolean preveriPolnjenje(String stationName, String currentUserName);
}
