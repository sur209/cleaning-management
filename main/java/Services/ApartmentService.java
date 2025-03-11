package Services;

import Models.ApartmentModel;
import Repositories.ApartmentRepository;

import java.util.ArrayList;

public class ApartmentService {

    private ApartmentRepository apartmentRepository;

    public ApartmentService() {
        this.apartmentRepository = new ApartmentRepository();
    }

    public boolean createApartment(String apartmentName) {
        this.apartmentRepository.addApartmentToDB(apartmentName);
        return true;
    }

    public boolean removeApartment(String apartmentName) {
        this.apartmentRepository.removeApartmentFromDB(apartmentName);
        return true;
    }

    public ArrayList<ApartmentModel> getApartments() {
        return new ApartmentRepository().getApartmentsFromDB();
    }

}
