package si.um.feri.service;

import jakarta.ejb.Local;
import si.um.feri.vao.PolnilnaPostaja;
import si.um.feri.vao.Ponudnik;
import si.um.feri.vao.User;
import java.util.List;
import java.util.Optional;

@Local
public interface PolnilnaPostajaServiceLocal {
    void addPostaja(String ime, String lokacija, Ponudnik ponudnik, boolean isActive, double hitrostPolnjenja);
    void startCharging(String ime, User user);
    void stopCharging(String ime);
    boolean canCharge(String ime, User user) throws ChargingNotPossibleException; // Prilagojeno za vračanje boolean in metanje izjeme
    void updateStation(PolnilnaPostaja updatedPostaja);
    void updatePolnilnaPostaja(String oldIme, String newIme); // Prej updatePolnilnaPostajaIme
    void updatePolinlnaPostajaIsActive(String ime, boolean isActive); // Prej updatePolnilnaPostajaIsActive
    void deletePolnilnaPostajaByIme(String ime);
    Optional<PolnilnaPostaja> getPolnilnePostajeByIme(String ime); // Prej getPolnilnaPostajaByIme
    List<PolnilnaPostaja> getAllPolnilnePostaje();
}