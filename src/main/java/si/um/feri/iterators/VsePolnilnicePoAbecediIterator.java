package si.um.feri.iterators;

import si.um.feri.vao.PolnilnaPostaja;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class VsePolnilnicePoAbecediIterator implements Iterator<PolnilnaPostaja> {

    private final Iterator<PolnilnaPostaja> iterator;

    public VsePolnilnicePoAbecediIterator(List<PolnilnaPostaja> polnilnePostaje) {
        polnilnePostaje.sort(Comparator.comparing(PolnilnaPostaja::getLokacija));
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