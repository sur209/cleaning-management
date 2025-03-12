package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignRepository {

    public SignRepository() {
    }

    private static final String URL = "jdbc:postgresql://localhost:5432/reservas";
    private static final String USER = "postgres";
    private static final String PASSWORD = "passwordTesting";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public void addCleaningSignUpToDB(String cleaner, Integer reservationID) {
        // Falta modificar desce aca para abajo
        String sql = "UPDATE reservation SET cleaner = ? WHERE ID = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cleaner);
            pstmt.setInt(2, reservationID);

            pstmt.executeUpdate();
            System.out.println("Limpiador agregado con exito a la reserva.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCleaningSignUpFromDB(Integer reservationID) {
        // Falta modificar desce aca para abajo
        String sql = "UPDATE reservation SET cleaner = NULL WHERE ID = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reservationID);

            pstmt.executeUpdate();
            System.out.println("Limpiador eliminado de la reserva con exito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addApartmentDeliveryToDB(String deliveryPerson, Integer reservationID) {
        // Falta modificar desce aca para abajo
        String sql = "UPDATE reservation SET delivery_person = ? WHERE ID = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, deliveryPerson);
            pstmt.setInt(2, reservationID);

            pstmt.executeUpdate();
            System.out.println("Entregador agregado con exito a la reserva.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeApartmentDeliveryFromDB(Integer reservationID) {
        // Falta modificar desce aca para abajo
        String sql = "UPDATE reservation SET delivery_person = NULL WHERE ID = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reservationID);

            pstmt.executeUpdate();
            System.out.println("Entregador eliminado de la reserva con exito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}