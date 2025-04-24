package si.um.feri.observers;

import si.um.feri.vao.PolnilnaPostaja;
import si.um.feri.vao.Ponudnik;

public class UserNotifier implements PolnilnicaObserver {

    @Override
    public void update(Ponudnik ponudnik, PolnilnaPostaja postaja, String action) {
        if ("zasedeno".equals(action)) {
            System.out.println(
                    "=====================================================" +
                            "\n" +
                            "\uD83D\uDCE9 [EMAIL] Od: " + "noreply@chargingstations.com" + "\n" +
                            "\uD83D\uDCE9 Za: " + postaja.getCurrentUser().getEmail() + "\n" +
                            "\uD83D\uDCE9 Zadeva: Polnjenje se je začelo! ⚡ \n" +
                            "\n" +
                            "Pozdravljeni " + postaja.getCurrentUser().getIme() + ", \n" +
                            "\n" +
                            "vaše polnjenje na polnilnici **" + postaja.getLokacija() + "** se je uspešno začelo.  \n" +
                            "\uD83D\uDE97 Moč polnjenja: " + postaja.getHitrostPolnjenja() + " kW \n" +
                            "\n" +
                            "Lep pozdrav, \n" +
                            ponudnik.getIme());
        } else if ("prosto".equals(action)) {
            System.out.println(
                    "=====================================================" +
                            "\n" +
                            "\uD83D\uDCE9 [EMAIL] Od: noreply@chargingstations.com" + "\n" +
                            "\uD83D\uDCE9 Za: user@example.com" + "\n" +
                            "\uD83D\uDCE9 Zadeva: Polnjenje končano! ✅ \n" +
                            "\n" +
                            "Pozdravljeni " + postaja.getCurrentUser().getIme() + ", \n" +
                            "\n" +
                            "vaše polnjenje na polnilnici **" + postaja.getLokacija() + "** je končano.  \n" +
                            "\uD83D\uDD0C" +
                            "\n" +
                            "\n" +
                            "Lep pozdrav, \n" +
                            ponudnik.getIme());
        }
    }
}
