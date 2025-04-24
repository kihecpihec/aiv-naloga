package si.um.feri.ejb;

import jakarta.ejb.Stateless;
import si.um.feri.dao.PolnilnaPostajaDAO;

@Stateless
public class PolnilnaPostajaDaoBean implements PolnilnaPostajaDaoLocal, PolnilnaPostajaDaoRemote {

    @Override
    public boolean preveriPolnjenje(String stationName, String currentUserName) {
        return PolnilnaPostajaDAO.getInstance().preveriPolnjenje(stationName, currentUserName);
    }
}
