package si.um.feri.service;

import si.um.feri.dao.PonudnikDAO;
import si.um.feri.dao.interfaces.PonudnikDAOInterface;
import si.um.feri.vao.Ponudnik;
import si.um.feri.vao.User;

import java.util.List;
import java.util.Optional;

public class PonudnikService {
    private final PonudnikDAOInterface ponudnikDAO = PonudnikDAO.getInstance();

    public void createPonudnik(String ime, String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email ne sme biti prazen");
        }
        ponudnikDAO.insertPonudnik(new Ponudnik(ime, email));
    }

    public List<Ponudnik> getAllPonudnike() {
        return ponudnikDAO.getAllPonudnike();
    }

    public Optional<Ponudnik> getPonudnikByEmail(String email) {
        return ponudnikDAO.getPonudnikByEmail(email);
    }

    public void updateProvider(Ponudnik updatedPonudnik) {
        ponudnikDAO.updatePonudnik(updatedPonudnik);
    }

    public void updatePonudnikEmail(String email, String newEmail) {
        ponudnikDAO.updatePonudnikEmail(email, newEmail);
    }

    public void deletePonudnikByEmail(String email) {
        ponudnikDAO.deletePonudnikByEmail(email);
    }
}