package si.um.feri.service;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import si.um.feri.dao.interfaces.PonudnikDAOInterface;
import si.um.feri.vao.Ponudnik;

import java.util.List;
import java.util.Optional;

@Stateless
public class PonudnikService implements PonudnikServiceInterface {
    @EJB
    PonudnikDAOInterface ponudnikDAO;

    @Override
    public void createPonudnik(String ime, String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email ne sme biti prazen");
        }
        ponudnikDAO.insertPonudnik(new Ponudnik(ime, email));
    }

    @Override
    public List<Ponudnik> getAllPonudnike() {
        return ponudnikDAO.getAllPonudnike();
    }

    @Override
    public Optional<Ponudnik> getPonudnikByEmail(String email) {
        return ponudnikDAO.getPonudnikByEmail(email);
    }

    @Override
    public void updateProvider(Ponudnik updatedPonudnik) {
        ponudnikDAO.updatePonudnik(updatedPonudnik);
    }

    @Override
    public void updatePonudnikEmail(String email, String newEmail) {
        ponudnikDAO.updatePonudnikEmail(email, newEmail);
    }

    @Override
    public void deletePonudnikByEmail(String email) {
        ponudnikDAO.deletePonudnikByEmail(email);
    }
}