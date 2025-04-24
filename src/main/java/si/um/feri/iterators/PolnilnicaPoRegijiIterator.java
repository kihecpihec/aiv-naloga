package si.um.feri.iterators;

import si.um.feri.vao.PolnilnaPostaja;

import java.util.Iterator;
import java.util.List;

public class PolnilnicaPoRegijiIterator implements Iterator<PolnilnaPostaja> {

    private final Iterator<PolnilnaPostaja> iterator;

    public PolnilnicaPoRegijiIterator(List<PolnilnaPostaja> polnilnePostaje, String regija) {
        polnilnePostaje.removeIf(polnilnaPostaja -> !polnilnaPostaja.getLokacija().equals(regija));
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
