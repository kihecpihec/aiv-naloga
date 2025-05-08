package si.um.feri.initializer;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import si.um.feri.service.PolnilnaPostajaServiceLocal;
import si.um.feri.service.PonudnikServiceInterface;
import si.um.feri.service.UserServiceInterface;
import si.um.feri.vao.Ponudnik;

@Singleton
@Startup
public class DataInitializer {

    @EJB
    private PolnilnaPostajaServiceLocal polnilnaPostajaService;
    @EJB
    private PonudnikServiceInterface ponudnikService;
    @EJB
    private UserServiceInterface userService;

    @PostConstruct
    public void init() {
        ponudnikService.createPonudnik("Petrol", "info@petrol.si");
        ponudnikService.createPonudnik("Elektro Ljubljana", "info@elektro-lj.si");

        Ponudnik petrol = ponudnikService.getPonudnikByEmail("info@petrol.si")
                .orElseThrow(() -> new RuntimeException("Petrol ni bil najden po ustvarjanju!"));
        Ponudnik elektroLJ = ponudnikService.getPonudnikByEmail("info@elektro-lj.si")
                .orElseThrow(() -> new RuntimeException("Elektro Ljubljana ni bil najden po ustvarjanju!"));

        polnilnaPostajaService.addPostaja("Petrol MB Center", "Maribor, Center", petrol, false, 50.0);
        polnilnaPostajaService.addPostaja("Petrol Pobrežje", "Maribor, Pobrežje", petrol, false, 22.0);
        polnilnaPostajaService.addPostaja("Elektro LJ Bežigrad", "Ljubljana, Bežigrad", elektroLJ, false, 75.0);

        userService.createUser("Janez Novak", "janez.novak@example.com", 100.0, "elektricni");
        userService.createUser("Marija Kovac", "marija.kovac@example.com", 80.0, "CarType2");

        System.out.println("Data initialized!");

        System.out.println("--- Ponudniki ---");
        ponudnikService.getAllPonudnike().forEach(p -> {
            System.out.println("Ponudnik: " + p.getIme() + ", št. postaj: " + p.getPolnilnePostaje().size());
            p.getPolnilnePostaje().forEach(pp -> System.out.println("  -> Postaja: " + pp.getIme()));
        });
        System.out.println("--- Uporabniki ---");
        userService.getAllUsers().forEach(u -> System.out.println("Uporabnik: " + u.getIme() + ", Car: " + u.getCarType()));
    }
}