package si.um.feri.observers;

import si.um.feri.vao.PolnilnaPostaja;
import si.um.feri.vao.Ponudnik;

public interface PolnilnicaObserver {
    void update(Ponudnik ponudnik, PolnilnaPostaja postaja, String action);
}
