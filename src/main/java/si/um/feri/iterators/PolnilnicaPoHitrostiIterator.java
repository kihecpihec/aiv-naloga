package si.um.feri.iterators;

import si.um.feri.vao.PolnilnaPostaja;

import java.util.Iterator;
import java.util.List;

public class PolnilnicaPoHitrostiIterator implements Iterator<PolnilnaPostaja> {

    private final Iterator<PolnilnaPostaja> iterator;

    public PolnilnicaPoHitrostiIterator(List<PolnilnaPostaja> polnilnePostaje, double minHitrost) {
        polnilnePostaje.removeIf(polnilnaPostaja -> polnilnaPostaja.getHitrostPolnjenja() <= minHitrost);
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
