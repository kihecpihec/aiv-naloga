package si.um.feri.vao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ponudnik implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ime;
    private String email;
    private List<PolnilnaPostaja> polnilnePostaje;

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
        if (this.polnilnePostaje == null) {
            this.polnilnePostaje = new ArrayList<>();
        }
        polnilnePostaje.add(postaja);
    }

    @Override
    public String toString() {
        return "Ponudnik{" +
                "ime='" + ime + '\'' +
                ", email='" + email + '\'' +
                ", steviloPostaj=" + (polnilnePostaje != null ? polnilnePostaje.size() : 0) +
                '}';
    }
}