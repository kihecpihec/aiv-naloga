package si.um.feri.service;

import jakarta.ejb.Remote;
import si.um.feri.vao.User;

@Remote
public interface PolnilnaPostajaServiceRemote {
    boolean preveriMoznostPolnjenja(String imePostaje, User user) throws ChargingNotPossibleException;
}