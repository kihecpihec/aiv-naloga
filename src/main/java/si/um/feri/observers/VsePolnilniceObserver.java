package si.um.feri.observers;

import si.um.feri.vao.PolnilnaPostaja;

import java.util.List;

public interface VsePolnilniceObserver {
    void update(List<PolnilnaPostaja> polnilnice);
}
