package si.um.feri.service;

import si.um.feri.chainofresponsibility.BalanceCheck;
import si.um.feri.chainofresponsibility.CarTypeCheck;
import si.um.feri.chainofresponsibility.Check;
import si.um.feri.chainofresponsibility.IsAvailableCheck;
import si.um.feri.dao.PolnilnaPostajaDAO;
import si.um.feri.dao.interfaces.PolnilnaPostajaDAOInterface;
import si.um.feri.observers.PolnilnicaDisplay;
import si.um.feri.vao.PolnilnaPostaja;
import si.um.feri.vao.Ponudnik;
import si.um.feri.vao.User;

import java.util.List;
import java.util.Optional;

public class PolnilnaPostajaService {
    private final PolnilnaPostajaDAOInterface polnilnaPostajaDAO = PolnilnaPostajaDAO.getInstance();
    private final PolnilnicaDisplay polnilnicaDisplay = new PolnilnicaDisplay();

    public void addPostaja(String ime, String lokacija, Ponudnik ponudnik, boolean isActive, double hitrostPolnjenja) {
        if (ime == null || ime.isEmpty()) {
            throw new IllegalArgumentException("Ime ne sme biti prazno");
        }
        PolnilnaPostaja postaja = new PolnilnaPostaja(ime, lokacija, ponudnik, isActive, hitrostPolnjenja);
        polnilnaPostajaDAO.insertPolnilnaPostaja(postaja);

        ponudnik.addPolnilnaPostaja(postaja);
    }

    public void startCharging(String ime, User user) {
        Optional<PolnilnaPostaja> postaja = polnilnaPostajaDAO.getPolnilnaPostajaByIme(ime);
        if (postaja.isEmpty()) {
            throw new IllegalArgumentException("Polnilna postaja ne obstaja");
        }

        Check isAvailableCheck = new IsAvailableCheck();
        Check carTypeCheck = new CarTypeCheck();
        Check balanceCheck = new BalanceCheck();

        isAvailableCheck.setNext(carTypeCheck);
        carTypeCheck.setNext(balanceCheck);

        try {
            isAvailableCheck.handleRequest(user, postaja.get());
            postaja.get().startCharging(user);
            notifyDisplay();
        } catch (IllegalStateException e) {
            System.err.println("Polnjenje ni mogoče: " + e.getMessage());
        }
    }

    public void stopCharging(String ime) {
        Optional<PolnilnaPostaja> postaja = polnilnaPostajaDAO.getPolnilnaPostajaByIme(ime);
        if (postaja.isEmpty()) {
            throw new IllegalArgumentException("Polnilna postaja ne obstaja");
        }
        postaja.get().stopCharging();
        notifyDisplay();
    }

    public void updateStation(PolnilnaPostaja updatedPostaja) {
        polnilnaPostajaDAO.updatePolnilnaPostaja(updatedPostaja);
    }

    public void updatePolnilnaPostaja(String oldIme, String newIme) {
        polnilnaPostajaDAO.updatePolnilnaPostajaIme(oldIme, newIme);
    }

    public void updatePolinlnaPostajaIsActive(String ime, boolean isActive) {
        polnilnaPostajaDAO.updatePolnilnaPostajaIsActive(ime, isActive);
    }

    public void deletePolnilnaPostajaByIme(String ime) {
        polnilnaPostajaDAO.deletePolnilnaPostajaByIme(ime);
    }

    public Optional<PolnilnaPostaja> getPolnilnePostajeByIme(String ime) {
        return polnilnaPostajaDAO.getPolnilnaPostajaByIme(ime);
    }

    public List<PolnilnaPostaja> getAllPolnilnePostaje() {
        return polnilnaPostajaDAO.getAllPolnilnePostaje();
    }

    private void notifyDisplay() {
        List<PolnilnaPostaja> allPostaje = polnilnaPostajaDAO.getAllPolnilnePostaje();
        polnilnicaDisplay.update(allPostaje);
    }
}