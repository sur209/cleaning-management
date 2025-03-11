package Services;
import Models.UserModel;
import Repositories.UserRepository;

import java.util.ArrayList;

public class UserService {

    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public boolean createUser(String name) {
        this.userRepository.addUserToDB(name);
        return true;
    }

    public boolean deleteUser(UserModel userName) {
        this.userRepository.removeUserFromDB(userName);
        return true;
    }

    public ArrayList<UserModel> getUsers() {
        return this.userRepository.getUsersFromDB();
    }

}
