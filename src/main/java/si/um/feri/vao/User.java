package si.um.feri.vao;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ime;
    private String email;
    private double balance;
    private String carType;

    public User(String ime, String email, double balance, String carType) {
        this.ime = ime;
        this.email = email;
        this.balance = balance;
        this.carType = carType;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        return "User{" +
                "ime='" + ime + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", carType='" + carType + '\'' +
                '}';
    }
}