package Services;

import Models.ReservationModel;
import Repositories.ReservationRepository;

import java.util.ArrayList;

public class ReservationService {
    ReservationRepository reservationRepository = new ReservationRepository();

    public void addReservation(ReservationModel reservation) {
        reservationRepository.addReservationToDB(reservation);
    }

    public ArrayList<ReservationModel> getReservations(String apartmentName) {
        return reservationRepository.retrieveReservationsFromDB(apartmentName);
    }
}
