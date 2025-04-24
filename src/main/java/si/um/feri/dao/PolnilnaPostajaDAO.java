package si.um.feri.dao;

import si.um.feri.dao.interfaces.PolnilnaPostajaDAOInterface;
import si.um.feri.vao.PolnilnaPostaja;
import si.um.feri.vao.Ponudnik;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PolnilnaPostajaDAO implements PolnilnaPostajaDAOInterface {
    private static PolnilnaPostajaDAO instance;
    private List<PolnilnaPostaja> polnilnePostaje = new ArrayList<>();

    private PolnilnaPostajaDAO() {}

    public static PolnilnaPostajaDAO getInstance() {
        if (instance == null) {
            instance = new PolnilnaPostajaDAO();
        }
        return instance;
    }

    @Override
    public void insertPolnilnaPostaja(PolnilnaPostaja polnilnaPostaja) {
        polnilnePostaje.add(polnilnaPostaja);
    }

    @Override
    public List<PolnilnaPostaja> getPolnilnaPostajaByPonudnik(Ponudnik ponudnik) {
        return polnilnePostaje.stream().filter(polnilnaPostaja -> polnilnaPostaja.getPonudnik().equals(ponudnik)).toList();
    }

    @Override
    public Optional<PolnilnaPostaja> getPolnilnaPostajaByIme(String ime) {
        return polnilnePostaje.stream().filter(polnilnaPostaja -> polnilnaPostaja.getIme().equals(ime)).findFirst();
    }

    @Override
    public Optional<PolnilnaPostaja> getPolnilnaPostajaByRegija(String lokacija) {
        return polnilnePostaje.stream().filter(polnilnaPostaja -> polnilnaPostaja.getLokacija().equals(lokacija)).findFirst();
    }

    @Override
    public Optional<PolnilnaPostaja> getPolnilnaPostajaByHitrost(double hitrost) {
        return polnilnePostaje.stream().filter(polnilnaPostaja -> polnilnaPostaja.getHitrostPolnjenja() <= hitrost).findFirst();
    }

    @Override
    public List<PolnilnaPostaja> getAllPolnilnePostaje() {
        return polnilnePostaje;
    }

    @Override
    public void updatePolnilnaPostaja(PolnilnaPostaja updatedPolnilnaPostaja) {
        for (int i = 0; i < polnilnePostaje.size(); i++) {
            PolnilnaPostaja p = polnilnePostaje.get(i);
            if (p.getIme().equals(updatedPolnilnaPostaja.getIme())) {
                polnilnePostaje.set(i, updatedPolnilnaPostaja);
                return;
            }
        }
        throw new IllegalArgumentException("Polnilna postaja ni bila najdena za posodobitev");
    }

    @Override
    public void updatePolnilnaPostajaIme(String ime, String newIme) {
        getPolnilnaPostajaByIme(ime).ifPresent(polnilnaPostaja -> polnilnaPostaja.setIme(newIme));
    }

    @Override
    public void updatePolnilnaPostajaIsActive(String ime, boolean isActive) {
        getPolnilnaPostajaByIme(ime).ifPresent(polnilnaPostaja -> polnilnaPostaja.setActive(isActive));
    }

    @Override
    public void deletePolnilnaPostajaByIme(String ime) {
        polnilnePostaje.removeIf(polnilnaPostaja -> polnilnaPostaja.getIme().equals(ime));
    }
}