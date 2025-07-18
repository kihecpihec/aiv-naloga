package si.um.feri.dao.interfaces;

import jakarta.ejb.Local;
import si.um.feri.vao.Ponudnik;

import java.util.List;
import java.util.Optional;

@Local
public interface PonudnikDAOInterface {
    void insertPonudnik(Ponudnik ponudnik);
    List<Ponudnik> getAllPonudnike();
    Optional<Ponudnik> getPonudnikByEmail(String email);
    void updatePonudnik(Ponudnik ponudnik);
    void updatePonudnikEmail(String email, String newEmail);
    void deletePonudnikByEmail(String email);
}
