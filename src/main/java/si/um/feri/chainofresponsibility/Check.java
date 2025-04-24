package si.um.feri.chainofresponsibility;

import si.um.feri.vao.PolnilnaPostaja;
import si.um.feri.vao.User;

public interface Check {
    void setNext(Check check);
    void handleRequest(User user, PolnilnaPostaja postaja);
}
