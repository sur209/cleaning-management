package Repositories;

import Models.UserModel;

import java.sql.*;
import java.util.ArrayList;

public class UserRepository {

    private static final String URL = "jdbc:postgresql://localhost:5432/reservas";
    private static final String USER = "postgres";
    private static final String PASSWORD = "passwordTesting";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public void addUserToDB(String userName) {
        String sql = "INSERT INTO users (name) VALUES (?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userName);

            pstmt.executeUpdate();
            System.out.println("Usuario agregado con exito a la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserFromDB(UserModel userName) {
        // Falta modificar desce aca para abajo
        String sql = "DELETE FROM users WHERE name = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userName.getUserName());

            pstmt.executeUpdate();
            System.out.println("Usuario eliminado con exito de la base datos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserModel> getUsersFromDB() {
        ArrayList<UserModel> users = new ArrayList<>();

        String sql = "SELECT name FROM users";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    users.add(new UserModel(name));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
