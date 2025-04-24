package si.um.feri.iterators;

import si.um.feri.vao.PolnilnaPostaja;

import java.util.Iterator;
import java.util.List;

public class AktivnaPolnilnicaIterator implements Iterator<PolnilnaPostaja> {

    private final Iterator<PolnilnaPostaja> iterator;

    public AktivnaPolnilnicaIterator(List<PolnilnaPostaja> polnilnePostaje) {
        polnilnePostaje.removeIf(polnilnaPostaja -> !polnilnaPostaja.isActive());
        this.iterator = polnilnePostaje.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public PolnilnaPostaja next() {
        return iterator.next();
    }
}
