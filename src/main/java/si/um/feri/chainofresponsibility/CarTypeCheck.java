package si.um.feri.chainofresponsibility;

import si.um.feri.vao.PolnilnaPostaja;
import si.um.feri.vao.User;

public class CarTypeCheck implements Check {
    private Check next;

    @Override
    public void setNext(Check check) {
        this.next = check;
    }

    @Override
    public void handleRequest(User user, PolnilnaPostaja postaja) {
        if (!user.getCarType().equals("elektricni")) {
            throw new IllegalStateException("Polnilna postaja " + postaja.getLokacija() + " ni kompatibilna z vašim vozilom.");
        }
        if (next != null) {
            next.handleRequest(user, postaja);
        }
    }
}
