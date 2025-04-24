package si.um.feri.observers;

import si.um.feri.vao.PolnilnaPostaja;

import java.util.List;

public class PolnilnicaDisplay implements VsePolnilniceObserver{

    public void update(List<PolnilnaPostaja> allPostaje) {
        List<String> zasedeneLokacije = allPostaje.stream().filter(PolnilnaPostaja::isActive).map(PolnilnaPostaja::getLokacija).toList();
        List<String> prosteLokacije = allPostaje.stream().filter(p -> !p.isActive()).map(PolnilnaPostaja::getLokacija).toList();

        System.out.println(
                "=====================================================" + "\n" +
                        "\uD83D\uDCDF [Zaslon polnilne postaje] Trenutno stanje polnilnic:\n" +
                        "✅ Proste polnilnice: " + prosteLokacije + "\n" +
                        "⛔ Zasedene polnilnice: " + zasedeneLokacije
        );
    }
}