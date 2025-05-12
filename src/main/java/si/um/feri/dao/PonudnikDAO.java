package si.um.feri.dao;

import jakarta.ejb.Stateless;
import si.um.feri.dao.interfaces.PonudnikDAOInterface;
import si.um.feri.vao.Ponudnik;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Stateless
public class PonudnikDAO implements PonudnikDAOInterface {
//    private static PonudnikDAO instance;
    private List<Ponudnik> ponudniki = new ArrayList<>();

//    private PonudnikDAO() {}
//
//    public static PonudnikDAO getInstance() {
//        if (instance == null) {
//            instance = new PonudnikDAO();
//        }
//        return instance;
//    }

    @Override
    public void insertPonudnik(Ponudnik ponudnik) {
        ponudniki.add(ponudnik);
    }

    @Override
    public List<Ponudnik> getAllPonudnike() {
        return ponudniki;
    }

    @Override
    public Optional<Ponudnik> getPonudnikByEmail(String email) {
        return ponudniki.stream().filter(ponudnik -> ponudnik.getEmail().equals(email)).findFirst();
    }

    @Override
    public void updatePonudnik(Ponudnik updatedPonudnik) {
        for (int i = 0; i < ponudniki.size(); i++) {
            Ponudnik p = ponudniki.get(i);
            if (p.getEmail().equals(updatedPonudnik.getEmail())) {
                ponudniki.set(i, updatedPonudnik);
                return;
            }
        }
        throw new IllegalArgumentException("Ponudnik ni bil najden za posodobitev");
    }

    @Override
    public void updatePonudnikEmail(String email, String newEmail) {
        getPonudnikByEmail(email).ifPresent(ponudnik -> ponudnik.setEmail(newEmail));
    }

    @Override
    public void deletePonudnikByEmail(String email) {
        ponudniki.removeIf(ponudnik -> ponudnik.getEmail().equals(email));
    }
}