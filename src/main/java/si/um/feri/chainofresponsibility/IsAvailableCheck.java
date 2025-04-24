package si.um.feri.chainofresponsibility;

import si.um.feri.vao.PolnilnaPostaja;
import si.um.feri.vao.User;

public class IsAvailableCheck implements Check {
    private Check next;

    @Override
    public void setNext(Check next) {
        this.next = next;
    }

    @Override
    public void handleRequest(User user, PolnilnaPostaja postaja) {
        if (postaja.isActive()) {
            throw new IllegalStateException("Polnilna postaja " + postaja.getLokacija() + " je že aktivna.");
        }
        if (next != null) {
            next.handleRequest(user, postaja);
        }
    }
}
