package si.um.feri.service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import si.um.feri.chainofresponsibility.BalanceCheck;
import si.um.feri.chainofresponsibility.CarTypeCheck;
import si.um.feri.chainofresponsibility.Check;
import si.um.feri.chainofresponsibility.IsAvailableCheck;
import si.um.feri.dao.interfaces.PolnilnaPostajaDAOInterface;
import si.um.feri.vao.PolnilnaPostaja;
import si.um.feri.vao.Ponudnik;
import si.um.feri.vao.User;

import java.util.List;
import java.util.Optional;

@Stateless
public class PolnilnaPostajaService implements PolnilnaPostajaServiceInterface, startChargingInterface {
    //private final PolnilnaPostajaDAOInterface polnilnaPostajaDAO = PolnilnaPostajaDAO.getInstance();
    //private final PolnilnicaDisplay polnilnicaDisplay = new PolnilnicaDisplay();

    @EJB
    PolnilnaPostajaDAOInterface polnilnaPostajaDAOInterface;

    @Override
    public void addPostaja(String ime, String lokacija, Ponudnik ponudnik, boolean isActive, double hitrostPolnjenja) {
        if (ime == null || ime.isEmpty()) {
            throw new IllegalArgumentException("Ime ne sme biti prazno");
        }
        PolnilnaPostaja postaja = new PolnilnaPostaja(ime, lokacija, ponudnik, isActive, hitrostPolnjenja);
        polnilnaPostajaDAOInterface.insertPolnilnaPostaja(postaja);

        ponudnik.addPolnilnaPostaja(postaja);
    }

    @Override
    public void startCharging(String ime, User user) {
        Optional<PolnilnaPostaja> postaja = polnilnaPostajaDAOInterface.getPolnilnaPostajaByIme(ime);
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

    @Override
    public void stopCharging(String ime) {
        Optional<PolnilnaPostaja> postaja = polnilnaPostajaDAOInterface.getPolnilnaPostajaByIme(ime);
        if (postaja.isEmpty()) {
            throw new IllegalArgumentException("Polnilna postaja ne obstaja");
        }
        postaja.get().stopCharging();
        notifyDisplay();
    }

    @Override
    public void updateStation(PolnilnaPostaja updatedPostaja) {
        polnilnaPostajaDAOInterface.updatePolnilnaPostaja(updatedPostaja);
    }

    @Override
    public void updatePolnilnaPostaja(String oldIme, String newIme) {
        polnilnaPostajaDAOInterface.updatePolnilnaPostajaIme(oldIme, newIme);
    }

    @Override
    public void updatePolinlnaPostajaIsActive(String ime, boolean isActive) {
        polnilnaPostajaDAOInterface.updatePolnilnaPostajaIsActive(ime, isActive);
    }

    @Override
    public void deletePolnilnaPostajaByIme(String ime) {
        polnilnaPostajaDAOInterface.deletePolnilnaPostajaByIme(ime);
    }

    @Override
    public Optional<PolnilnaPostaja> getPolnilnePostajeByIme(String ime) {
        return polnilnaPostajaDAOInterface.getPolnilnaPostajaByIme(ime);
    }

    @Override
    public List<PolnilnaPostaja> getAllPolnilnePostaje() {
        return polnilnaPostajaDAOInterface.getAllPolnilnePostaje();
    }

    @Override
    public void notifyDisplay() {
        List<PolnilnaPostaja> allPostaje = polnilnaPostajaDAOInterface.getAllPolnilnePostaje();
    }

    @Override
    public void canCharge(String ime, User user) {
        System.out.println("Checking if charging is possible...");
        Optional<PolnilnaPostaja> postaja = polnilnaPostajaDAOInterface.getPolnilnaPostajaByIme(ime);
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
}