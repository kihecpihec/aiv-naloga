package si.um.feri.vao;

public class PolnilnaPostaja {
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
        return this.lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Ponudnik getPonudnik() {
        return ponudnik;
    }

    public double getHitrostPolnjenja() {
        return hitrostPolnjenja;
    }

    public void setHitrostPolnjenja(double hitrostPolnjenja) {
        this.hitrostPolnjenja = hitrostPolnjenja;
    }

    public void startCharging(User user) {
        if (this.isActive) {
            throw new IllegalStateException("Polnilna postaja je že aktivna.");
        }
        this.currentUser = user;
        this.setActive(true);
    }

    public void stopCharging() {
        if (!this.isActive) {
            throw new IllegalStateException("Polnilna postaja ni aktivna.");
        }
        this.currentUser = null;
        this.setActive(false);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
        if (isActive) {
            ponudnik.notifyUsers(this, "zasedeno");
            ponudnik.notifyPonudnike(this, "zasedeno");
        } else {
            ponudnik.notifyUsers(this, "prosto");
            ponudnik.notifyPonudnike(this, "prosto");
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setPonudnik(Ponudnik ponudnik) {
        this.ponudnik = ponudnik;
    }

    @Override
    public String toString() {
        return "PolnilnaPostaja{" +
                "ime='" + ime + '\'' +
                ", lokacija='" + lokacija + '\'' +
                ", isActive=" + isActive +
                ", hitrostPolnjenja=" + hitrostPolnjenja + " kW" +
                '}';
    }
}
