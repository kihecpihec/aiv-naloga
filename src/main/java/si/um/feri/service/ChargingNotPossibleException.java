package si.um.feri.service;

public class ChargingNotPossibleException extends Exception {
    public ChargingNotPossibleException(String message) {
        super(message);
    }
    public ChargingNotPossibleException(String message, Throwable cause) {
        super(message, cause);
    }
}