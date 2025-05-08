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
public class PolnilnaPostajaService implements PolnilnaPostajaServiceLocal, PolnilnaPostajaServiceRemote {

    @EJB
    PolnilnaPostajaDAOInterface polnilnaPostajaDAOInterface;

    @Override
    public void addPostaja(String ime, String lokacija, Ponudnik ponudnik, boolean isActive, double hitrostPolnjenja) {
        if (ime == null || ime.isEmpty()) {
            throw new IllegalArgumentException("Ime ne sme biti prazno");
        }
        PolnilnaPostaja postaja = new PolnilnaPostaja(ime, lokacija, ponudnik, isActive, hitrostPolnjenja);
        polnilnaPostajaDAOInterface.insertPolnilnaPostaja(postaja);

        if (ponudnik != null) {
            ponudnik.addPolnilnaPostaja(postaja);
        }
    }

    @Override
    public void startCharging(String ime, User user) {
        Optional<PolnilnaPostaja> postajaOpt = polnilnaPostajaDAOInterface.getPolnilnaPostajaByIme(ime);
        if (postajaOpt.isEmpty()) {
            throw new IllegalArgumentException("Polnilna postaja '" + ime + "' ne obstaja");
        }
        PolnilnaPostaja postaja = postajaOpt.get();

        Check isAvailableCheck = new IsAvailableCheck();
        Check carTypeCheck = new CarTypeCheck();
        Check balanceCheck = new BalanceCheck();

        isAvailableCheck.setNext(carTypeCheck);
        carTypeCheck.setNext(balanceCheck);

        try {
            isAvailableCheck.handleRequest(user, postaja);
            postaja.startCharging(user);
            polnilnaPostajaDAOInterface.updatePolnilnaPostaja(postaja);
        } catch (IllegalStateException e) {
            System.err.println("Polnjenje ni mogoče začeti: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void stopCharging(String ime) {
        Optional<PolnilnaPostaja> postajaOpt = polnilnaPostajaDAOInterface.getPolnilnaPostajaByIme(ime);
        if (postajaOpt.isEmpty()) {
            throw new IllegalArgumentException("Polnilna postaja '" + ime + "' ne obstaja");
        }
        PolnilnaPostaja postaja = postajaOpt.get();
        postaja.stopCharging();
        polnilnaPostajaDAOInterface.updatePolnilnaPostaja(postaja);
    }

    @Override
    public boolean canCharge(String ime, User user) throws ChargingNotPossibleException {
        System.out.println("Lokalno preverjanje možnosti polnjenja za: " + ime);
        Optional<PolnilnaPostaja> postajaOpt = polnilnaPostajaDAOInterface.getPolnilnaPostajaByIme(ime);
        if (postajaOpt.isEmpty()) {
            throw new ChargingNotPossibleException("Polnilna postaja '" + ime + "' ne obstaja.");
        }
        PolnilnaPostaja postaja = postajaOpt.get();

        Check isAvailableCheck = new IsAvailableCheck();
        Check carTypeCheck = new CarTypeCheck();
        Check balanceCheck = new BalanceCheck();

        isAvailableCheck.setNext(carTypeCheck);
        carTypeCheck.setNext(balanceCheck);

        try {
            isAvailableCheck.handleRequest(user, postaja);
            return true;
        } catch (IllegalStateException e) {
            System.err.println("Lokalno preverjanje: Polnjenje ni mogoče na postaji '" + ime + "': " + e.getMessage());
            throw new ChargingNotPossibleException("Polnjenje ni mogoče: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateStation(PolnilnaPostaja updatedPostaja) {
        polnilnaPostajaDAOInterface.updatePolnilnaPostaja(updatedPostaja);
    }

    @Override
    public void updatePolnilnaPostaja(String oldIme, String newIme) {
        Optional<PolnilnaPostaja> postajaOpt = polnilnaPostajaDAOInterface.getPolnilnaPostajaByIme(oldIme);
        if (postajaOpt.isPresent()){
            PolnilnaPostaja p = postajaOpt.get();
            p.setIme(newIme);
            polnilnaPostajaDAOInterface.updatePolnilnaPostaja(p);
        } else {
            throw new IllegalArgumentException("Polnilna postaja " + oldIme + " ne obstaja.");
        }
    }

    @Override
    public void updatePolinlnaPostajaIsActive(String ime, boolean isActive) {
        Optional<PolnilnaPostaja> postajaOpt = polnilnaPostajaDAOInterface.getPolnilnaPostajaByIme(ime);
        if (postajaOpt.isPresent()){
            PolnilnaPostaja p = postajaOpt.get();
            p.setActive(isActive);
            polnilnaPostajaDAOInterface.updatePolnilnaPostaja(p);
        } else {
            throw new IllegalArgumentException("Polnilna postaja " + ime + " ne obstaja.");
        }
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
    public boolean preveriMoznostPolnjenja(String imePostaje, User user) throws ChargingNotPossibleException {
        System.out.println("Oddaljeno preverjanje možnosti polnjenja za postajo: " + imePostaje);

        Optional<PolnilnaPostaja> postajaOpt = polnilnaPostajaDAOInterface.getPolnilnaPostajaByIme(imePostaje);
        if (postajaOpt.isEmpty()) {
            throw new ChargingNotPossibleException("Polnilna postaja '" + imePostaje + "' ne obstaja.");
        }
        PolnilnaPostaja postaja = postajaOpt.get();

        Check isAvailableCheck = new IsAvailableCheck();
        Check carTypeCheck = new CarTypeCheck();
        Check balanceCheck = new BalanceCheck();

        isAvailableCheck.setNext(carTypeCheck);
        carTypeCheck.setNext(balanceCheck);

        try {
            isAvailableCheck.handleRequest(user, postaja);
            return true;
        } catch (IllegalStateException e) {
            System.err.println("Oddaljeno preverjanje: Polnjenje ni mogoče na postaji '" + imePostaje + "': " + e.getMessage());
            throw new ChargingNotPossibleException("Polnjenje ni mogoče: " + e.getMessage(), e);
        }
    }
}