package si.um.feri.ejb;

import jakarta.ejb.Local;

@Local
public interface PolnilnaPostajaDaoLocal {
    boolean preveriPolnjenje(String stationName, String currentUserName);
}
