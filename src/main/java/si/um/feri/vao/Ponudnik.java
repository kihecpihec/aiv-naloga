package si.um.feri.vao;

import si.um.feri.observers.PonudnikNotifier;
import si.um.feri.observers.UserNotifier;

import java.util.ArrayList;
import java.util.List;

public class Ponudnik {
    private String ime;
    private String email;
    private List<PolnilnaPostaja> polnilnePostaje;
    private List<UserNotifier> userObservers = new ArrayList<>();
    private List<PonudnikNotifier> ponudnikObservers = new ArrayList<>();

    public Ponudnik(String ime, String email) {
        this.ime = ime;
        this.email = email;
        this.polnilnePostaje = new ArrayList<>();
    }

    public String getIme() {
        return this.ime;
    }

    public String getEmail() {
        return email;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PolnilnaPostaja> getPolnilnePostaje() {
        return this.polnilnePostaje;
    }

    public void addPolnilnaPostaja(PolnilnaPostaja postaja) {
        polnilnePostaje.add(postaja);
        notifyUsers(postaja, "added");
    }

    public void addUserObserver(UserNotifier observer) {
        userObservers.add(observer);
    }

    public void addPonudnikObserver(PonudnikNotifier observer) {
        ponudnikObservers.add(observer);
    }

    public void notifyUsers(PolnilnaPostaja postaja, String action) {
        for (UserNotifier userObserver : userObservers) {
            userObserver.update(this, postaja, action);
        }
    }

    public void notifyPonudnike(PolnilnaPostaja postaja, String action) {
        for (PonudnikNotifier ponudnikObserver : ponudnikObservers) {
            ponudnikObserver.update(this, postaja, action);
        }
    }

    @Override
    public String toString() {
        return "Ponudnik{" +
                "ime='" + ime + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}