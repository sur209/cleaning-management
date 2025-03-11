package Models;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class ApartmentModel {
    private String name;
    private Boolean cleaned;
    private List<ReservationModel> reservations;

    public ApartmentModel(String name) {
        this.name = name;
        this.cleaned = false;
        this.reservations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public boolean getState() {
        return this.cleaned;
    }

    public void setCleaned() {
        this.cleaned = true;
    }

    public List<ReservationModel> getReservations() {
        return this.reservations;
    }

    public boolean addReservation(ReservationModel newReservation) {
        reservations.add(newReservation);
        return true;
    }

    public boolean isAvailable(LocalDate start, LocalDate end) {
        return true;
    }

    public boolean removeReservation(ReservationModel reservationToRemove) {
        return reservations.remove(reservationToRemove);
    }

    @Override
    public String toString() {
        return this.name;
    }
}