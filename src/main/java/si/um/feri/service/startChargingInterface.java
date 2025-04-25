package si.um.feri.service;

import jakarta.ejb.Remote;
import si.um.feri.vao.User;

@Remote
public interface startChargingInterface {
    void canCharge(String ime, User user);
}
