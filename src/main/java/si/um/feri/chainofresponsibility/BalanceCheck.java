package si.um.feri.chainofresponsibility;

import si.um.feri.vao.PolnilnaPostaja;
import si.um.feri.vao.User;

public class BalanceCheck implements Check {
    private Check next;

    @Override
    public void setNext(Check next) {
        this.next = next;
    }

    @Override
    public void handleRequest(User user, PolnilnaPostaja postaja) {
        if (user.getBalance() < 20) {
            throw new IllegalStateException("Nimate dovolj denarja na računu.");
        }
        if (next != null) {
            next.handleRequest(user, postaja);
        }
    }
}
