package si.um.feri.service;

import jakarta.ejb.Local;
import si.um.feri.vao.Ponudnik;

import java.util.List;
import java.util.Optional;

@Local
public interface PonudnikServiceInterface {
    void createPonudnik(String ime, String email);

    List<Ponudnik> getAllPonudnike();

    Optional<Ponudnik> getPonudnikByEmail(String email);

    void updateProvider(Ponudnik updatedPonudnik);

    void updatePonudnikEmail(String email, String newEmail);

    void deletePonudnikByEmail(String email);
}
