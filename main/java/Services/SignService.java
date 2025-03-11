package Services;
import Repositories.SignRepository;

public class SignService {

    private SignRepository signRepository;

    public SignService() {
        this.signRepository = new SignRepository();
    }

    public boolean signUpForCleaning(String name, Integer reservationID) {
        this.signRepository.addCleaningSignUpToDB(name, reservationID);
        return true;
    }

    public boolean signUpForApartmentDelivery(String name, Integer reservationID) {
        this.signRepository.addApartmentDeliveryToDB(name, reservationID);
        return true;
    }

    public boolean removeSignUpForCleaning(Integer reservationID) {
        this.signRepository.removeCleaningSignUpFromDB(reservationID);
        return true;
    }
}
