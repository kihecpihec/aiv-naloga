package si.um.feri.dao.interfaces;

import si.um.feri.vao.PolnilnaPostaja;
import si.um.feri.vao.Ponudnik;

import java.util.List;
import java.util.Optional;

public interface PolnilnaPostajaDAOInterface {
    void insertPolnilnaPostaja(PolnilnaPostaja polnilnaPostaja);
    List<PolnilnaPostaja> getPolnilnaPostajaByPonudnik(Ponudnik ponudnik);
    Optional<PolnilnaPostaja> getPolnilnaPostajaByIme(String ime);
    Optional<PolnilnaPostaja> getPolnilnaPostajaByRegija(String lokacija);
    List<PolnilnaPostaja> getAllPolnilnePostaje();
    void updatePolnilnaPostaja(PolnilnaPostaja polnilnaPostaja);
    void updatePolnilnaPostajaIme(String ime, String newIme);
    void updatePolnilnaPostajaIsActive(String ime, boolean isActive);
    void deletePolnilnaPostajaByIme(String ime);
    Optional<PolnilnaPostaja> getPolnilnaPostajaByHitrost(double hitrost);
    boolean preveriPolnjenje(String stationName, String currentUserName);
}
