package si.um.feri.ui;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import si.um.feri.dao.PolnilnaPostajaDAO;
import si.um.feri.dao.PonudnikDAO;
import si.um.feri.dao.UserDAO;
import si.um.feri.dao.interfaces.PolnilnaPostajaDAOInterface;
import si.um.feri.service.*;
import si.um.feri.vao.PolnilnaPostaja;
import si.um.feri.vao.Ponudnik;
import si.um.feri.vao.User;

import java.util.List;

public class Bean implements java.io.Serializable {

    @EJB
    private PolnilnaPostajaServiceLocal polnilnaPostajaService;
    @EJB
    private PonudnikServiceInterface ponudnikService;
    @EJB
    private UserServiceInterface userService;

    private List<Ponudnik> providers;
    private List<PolnilnaPostaja> chargingStations;
    private List<User> users;
    private boolean showEditPanel = false;

    // User details
    private String user_name;
    private String user_email;
    private double balance;
    private String carType;
    private User selectedUser;

    // Provider details
    private String provider_name;
    private String provider_email;
    private Ponudnik selectedProvider;

    // Station details
    private String station_name;
    private String station_location;
    private Ponudnik provider;
    private boolean isActive;
    private double station_chargingSpeed;
    private PolnilnaPostaja selectedStation;

    // DAO instances
    //userService userService = userService.getInstance();
    //PonudnikDAO ponudnikDAO = PonudnikDAO.getInstance();
    //PolnilnaPostajaDAO stationDAO = PolnilnaPostajaDAO.getInstance();

    // Service instances
    //UserService userService = new UserService();
    //PonudnikService providerService = new PonudnikService();
    //PolnilnaPostajaService polnilnaPostajaService = new PolnilnaPostajaService();

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public void deleteUser(User user) {
        userService.deleteUserByEmail(user.getEmail());
        System.out.println("User deleted: " + user);
    }

    public void submitUser() {
        User user = new User(user_name, user_email, balance, carType);
        userService.createUser(user_name, user_email, balance, carType);
        System.out.println("User added: " + user);
    }

    public void submitProvider() {
        Ponudnik provider = new Ponudnik(provider_name, provider_email);
        ponudnikService.createPonudnik(provider_name, provider_email);
        displayProviders();
        System.out.println("Provider added: " + provider);
    }

    public void deleteProvider(Ponudnik provider) {
        ponudnikService.deletePonudnikByEmail(provider.getEmail());
        displayProviders();
        System.out.println("Provider deleted: " + provider);
    }

    public List<PolnilnaPostaja> getChargingStations() {
        return chargingStations;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getStation_location() {
        return station_location;
    }

    public void setStation_location(String station_location) {
        this.station_location = station_location;
    }

    public Ponudnik getProvider() {
        return provider;
    }

    public void setProvider(Ponudnik provider) {
        this.provider = provider;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public double getStation_chargingSpeed() {
        return station_chargingSpeed;
    }

    public void setStation_chargingSpeed(double station_chargingSpeed) {
        this.station_chargingSpeed = station_chargingSpeed;
    }

    public PolnilnaPostaja getSelectedStation() {
        return selectedStation;
    }

    public void setSelectedStation(PolnilnaPostaja selectedStation) {
        this.selectedStation = selectedStation;
        this.showEditPanel = true;
    }

    public void submitStation() {
        PolnilnaPostaja station = new PolnilnaPostaja(station_name, station_location, provider, isActive, station_chargingSpeed);
        polnilnaPostajaService.addPostaja(station_name, station_location, provider, isActive, station_chargingSpeed);
        displayStations();
        System.out.println("Station added: " + station);
    }

    public void deleteStation(PolnilnaPostaja station) {
        polnilnaPostajaService.deletePolnilnaPostajaByIme(station.getIme());
        displayStations();
        System.out.println("Station deleted: " + station);
    }

    public List<Ponudnik> getProviders() {
        return providers;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getProvider_email() {
        return provider_email;
    }

    public void setProvider_email(String provider_email) {
        this.provider_email = provider_email;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
        this.showEditPanel = true;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public Ponudnik getSelectedProvider() {
        return selectedProvider;
    }

    public void setSelectedProvider(Ponudnik selectedProvider) {
        this.selectedProvider = selectedProvider;
        this.showEditPanel = true;
    }


    public boolean isShowEditPanel() {
        return showEditPanel;
    }

    public void setShowEditPanel(boolean showEditPanel) {
        this.showEditPanel = showEditPanel;
    }

    public List<User> getUsers() {
        return users;
    }

    public void updateUser() {
        if (selectedUser != null) {
            userService.updateUser(selectedUser);
            displayUsers();
            System.out.println("User updated: " + selectedUser);
        } else {
            System.out.println("No user selected for update.");
        }
        showEditPanel = false;
        displayUsers();
    }

    public void updateProvider() {
        if (selectedProvider != null) {
            ponudnikService.updateProvider(selectedProvider);
            System.out.println("Provider updated: " + selectedProvider);
            displayProviders();
        } else {
            System.out.println("No provider selected for update.");
        }
        showEditPanel = false;
        displayProviders();
    }

    public void updateStation() {
        if (selectedStation != null) {
            polnilnaPostajaService.updateStation(selectedStation);
            System.out.println("Station updated: " + selectedStation);
            displayStations();
        } else {
            System.out.println("No station selected for update.");
        }
        showEditPanel = false;
        displayStations();
    }

    public void cancelEdit() {
        showEditPanel = false; // hide modal
    }

    public void displayProviders() {
        providers = ponudnikService.getAllPonudnike();
        providers.forEach(provider -> System.out.println("Provider: " + provider.getIme() + ", Email: " + provider.getEmail()));
    }

    public void displayUsers() {
        users = userService.getAllUsers();
        users.forEach(user -> System.out.println("User: " + user.getIme() + ", Email: " + user.getEmail() + ", Balance: " + user.getBalance() + ", Car Type: " + user.getCarType()));
    }

    public void displayStations() {
        chargingStations = polnilnaPostajaService.getAllPolnilnePostaje();
        chargingStations.forEach(station -> System.out.println("Station: " + station.getIme() + ", Location: " + station.getLokacija() + ", Speed: " + station.getHitrostPolnjenja() + " kW"));
    }

    @PostConstruct
    public void init() {
        displayUsers();
        displayProviders();
        displayStations();
    }
}