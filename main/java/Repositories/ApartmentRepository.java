package Repositories;

import Models.ApartmentModel;
import Models.UserModel;

import java.sql.*;
import java.util.ArrayList;

public class ApartmentRepository {

    public ApartmentRepository() {
    }

    private static final String URL = "jdbc:postgresql://localhost:5432/reservas";
    private static final String USER = "postgres";
    private static final String PASSWORD = "passwordTesting";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public void addApartmentToDB(String apartmentName) {
        // Falta modificar desce aca para abajo
        String sql = "INSERT INTO apartment (name) VALUES (?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, apartmentName);

            pstmt.executeUpdate();
            System.out.println("Departamento agregado con exito a la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeApartmentFromDB(String apartmentName) {
        // Falta modificar desce aca para abajo
        String sql = "DELETE FROM apartment WHERE name = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, apartmentName);

            pstmt.executeUpdate();
            System.out.println("Departamento eliminado con exito de la base datos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ApartmentModel> getApartmentsFromDB() {
        ArrayList<ApartmentModel> apartments = new ArrayList<>();

        String sql = "SELECT name FROM apartment";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    apartments.add(new ApartmentModel(name));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartments;
    }
}
