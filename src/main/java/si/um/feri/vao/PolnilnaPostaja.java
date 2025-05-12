package si.um.feri.vao;

import java.io.Serializable;

public class PolnilnaPostaja implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ime;
    private String lokacija;
    private Ponudnik ponudnik;
    private boolean isActive;
    private double hitrostPolnjenja;
    private User currentUser;

    public PolnilnaPostaja(String ime, String lokacija, Ponudnik ponudnik, boolean isActive, double hitrostPolnjenja) {
        this.ime = ime;
        this.lokacija = lokacija;
        this.ponudnik = ponudnik;
        this.isActive = isActive;
        this.hitrostPolnjenja = hitrostPolnjenja;
        this.currentUser = null;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Ponudnik getPonudnik() {
        return ponudnik;
    }

    public void setPonudnik(Ponudnik ponudnik) {
        this.ponudnik = ponudnik;
    }

    public double getHitrostPolnjenja() {
        return hitrostPolnjenja;
    }

    public void setHitrostPolnjenja(double hitrostPolnjenja) {
        this.hitrostPolnjenja = hitrostPolnjenja;
    }

    public void startCharging(User user) {
        if (this.currentUser != null) {
            throw new IllegalStateException("Polnilna postaja je že zasedena s strani uporabnika: " + this.currentUser.getIme());
        }
        this.currentUser = user;
        this.setActive(true);
    }

    public void stopCharging() {
        if (this.currentUser == null) {
            throw new IllegalStateException("Nihče ne polni na tej postaji.");
        }
        this.currentUser = null;
        this.setActive(false);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        if (this.currentUser != null && !this.isActive) {
            this.setActive(true);
        } else if (this.currentUser == null && this.isActive) {
            this.setActive(false);
        }
    }

    @Override
    public String toString() {
        return "PolnilnaPostaja{" +
                "ime='" + ime + '\'' +
                ", lokacija='" + lokacija + '\'' +
                ", ponudnik=" + (ponudnik != null ? ponudnik.getIme() : "null") +
                ", isActive=" + isActive +
                ", hitrostPolnjenja=" + hitrostPolnjenja + " kW" +
                ", currentUser=" + (currentUser != null ? currentUser.getIme() : "null") +
                '}';
    }
}