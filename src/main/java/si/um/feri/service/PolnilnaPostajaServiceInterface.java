package si.um.feri.service;

import jakarta.ejb.Local;
import si.um.feri.vao.PolnilnaPostaja;
import si.um.feri.vao.Ponudnik;
import si.um.feri.vao.User;

import java.util.List;
import java.util.Optional;

@Local
public interface PolnilnaPostajaServiceInterface {
    void addPostaja(String ime, String lokacija, Ponudnik ponudnik, boolean isActive, double hitrostPolnjenja);

    void startCharging(String ime, User user);

    void stopCharging(String ime);

    void updateStation(PolnilnaPostaja updatedPostaja);

    void updatePolnilnaPostaja(String oldIme, String newIme);

    void updatePolinlnaPostajaIsActive(String ime, boolean isActive);

    void deletePolnilnaPostajaByIme(String ime);

    Optional<PolnilnaPostaja> getPolnilnePostajeByIme(String ime);

    List<PolnilnaPostaja> getAllPolnilnePostaje();

    void notifyDisplay();
}
