package si.um.feri.observers;

import si.um.feri.vao.PolnilnaPostaja;
import si.um.feri.vao.Ponudnik;

public class PonudnikNotifier implements PolnilnicaObserver {

    @Override
    public void update(Ponudnik ponudnik, PolnilnaPostaja postaja, String action) {
        if ("prosto".equals(action)) {
            System.out.println(
                    "=====================================================" +
                    "\n" +
                    "\uD83C\uDFE2 Ponudnik obveščen: Polnilnica " + postaja.getLokacija() + " pri ponudniku " + ponudnik.getIme() + " je zdaj prosta."
            );
        } else if ("zasedeno".equals(action)) {
            System.out.println(
                    "=====================================================" +
                    "\n" +
                    "\uD83C\uDFE2 Ponudnik obveščen: Polnilnica " + postaja.getLokacija() + " pri ponudniku " + ponudnik.getIme() + " je zasedena."
            );
        }
    }
}
