package si.um.feri.initializer;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import si.um.feri.dao.PolnilnaPostajaDAO;
import si.um.feri.dao.PonudnikDAO;
import si.um.feri.dao.UserDAO;
import jakarta.ejb.EJB;
import si.um.feri.service.PolnilnaPostajaServiceInterface;
import si.um.feri.service.PonudnikServiceInterface;
import si.um.feri.service.UserServiceInterface;
import si.um.feri.vao.PolnilnaPostaja;
import si.um.feri.vao.Ponudnik;
import si.um.feri.vao.User;

@Singleton
@Startup
public class DataInitializer {

    @EJB
    private PolnilnaPostajaServiceInterface polnilnaPostajaService;
    @EJB
    private PonudnikServiceInterface ponudnikService;
    @EJB
    private UserServiceInterface userService;

    @PostConstruct
    public void init() {

//        ponudnikService ponudnikService = ponudnikService.getInstance();
//        polnilnaPostajaService polnilnaPostajaService = polnilnaPostajaService.getInstance();
//        userService userService = userService.getInstance();

        Ponudnik ponudnik1 = new Ponudnik("Ponudnik1", "ponudnik1@example.com");
        Ponudnik ponudnik2 = new Ponudnik("Ponudnik2", "ponudnik2@example.com");

        ponudnikService.createPonudnik(ponudnik1.getIme(), ponudnik1.getEmail());
        ponudnikService.createPonudnik(ponudnik2.getIme(), ponudnik2.getEmail());

        PolnilnaPostaja postaja1 = new PolnilnaPostaja("Postaja1", "Lokacija1", ponudnik1, false, 50.0);
        PolnilnaPostaja postaja2 = new PolnilnaPostaja("Postaja2", "Lokacija2", ponudnik2, false, 75.0);

        polnilnaPostajaService.addPostaja(postaja1.getIme(), postaja1.getLokacija(), postaja1.getPonudnik(), postaja1.isActive(), postaja1.getHitrostPolnjenja());
        polnilnaPostajaService.addPostaja(postaja2.getIme(), postaja2.getLokacija(), postaja2.getPonudnik(), postaja2.isActive(), postaja2.getHitrostPolnjenja());

        User user1 = new User("User1", "user1@example.com", 100.0, "CarType1");
        User user2 = new User("User2", "user2@example.com", 80.0, "CarType2");

        userService.createUser(user1.getIme(), user1.getEmail(), user1.getBalance(), user1.getCarType());
        userService.createUser(user2.getIme(), user2.getEmail(), user2.getBalance(), user2.getCarType());
    }
}