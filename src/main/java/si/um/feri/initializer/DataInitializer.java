package si.um.feri.initializer;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import si.um.feri.dao.PolnilnaPostajaDAO;
import si.um.feri.dao.PonudnikDAO;
import si.um.feri.dao.UserDAO;
import si.um.feri.vao.PolnilnaPostaja;
import si.um.feri.vao.Ponudnik;
import si.um.feri.vao.User;

@Singleton
@Startup
public class DataInitializer {

    @PostConstruct
    public void init() {
        PonudnikDAO ponudnikDAO = PonudnikDAO.getInstance();
        PolnilnaPostajaDAO polnilnaPostajaDAO = PolnilnaPostajaDAO.getInstance();
        UserDAO userDAO = UserDAO.getInstance();

        Ponudnik ponudnik1 = new Ponudnik("Ponudnik1", "ponudnik1@example.com");
        Ponudnik ponudnik2 = new Ponudnik("Ponudnik2", "ponudnik2@example.com");

        ponudnikDAO.insertPonudnik(ponudnik1);
        ponudnikDAO.insertPonudnik(ponudnik2);

        PolnilnaPostaja postaja1 = new PolnilnaPostaja("Postaja1", "Lokacija1", ponudnik1, false, 50.0);
        PolnilnaPostaja postaja2 = new PolnilnaPostaja("Postaja2", "Lokacija2", ponudnik2, false, 75.0);

        polnilnaPostajaDAO.insertPolnilnaPostaja(postaja1);
        polnilnaPostajaDAO.insertPolnilnaPostaja(postaja2);

        User user1 = new User("User1", "user1@example.com", 100.0, "CarType1");
        User user2 = new User("User2", "user2@example.com", 80.0, "CarType2");

        userDAO.insertUser(user1);
        userDAO.insertUser(user2);
    }
}