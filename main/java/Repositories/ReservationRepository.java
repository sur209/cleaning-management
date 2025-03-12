package Repositories;

import Models.ReservationModel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;



public class ReservationRepository {


    private static final String URL = "jdbc:postgresql://localhost:5432/reservas";
    private static final String USER = "postgres";
    private static final String PASSWORD = "passwordTesting";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void addReservationToDB(ReservationModel reservation) {

        String sql = "INSERT INTO reservation (department, start_date, end_date) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            java.sql.Date sqlStartDate = java.sql.Date.valueOf(reservation.getStartDate());
            java.sql.Date sqlEndDate = java.sql.Date.valueOf(reservation.getEndDate());

            pstmt.setString(1, reservation.getDepartment());
            pstmt.setDate(2, sqlStartDate);
            pstmt.setDate(3, sqlEndDate);

            pstmt.executeUpdate();
            System.out.println("Reservacion guardada con exito.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<ReservationModel> retrieveReservationsFromDB(String apartmentName) {
        ArrayList<ReservationModel> listaReservas = new ArrayList<>();
        final String nombreDeptoFinal = apartmentName;

        String sql = "SELECT id, department, start_date, end_date, cleaner, delivery_person FROM reservation WHERE department = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreDeptoFinal);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String cleaner = "";
                    String deliveryPerson = "";
                    int id = rs.getInt("id");
                    String departmentName = rs.getString("department");
                    LocalDate startDate = rs.getDate("start_date").toLocalDate();
                    LocalDate endDate = rs.getDate("end_date").toLocalDate();

                    if (rs.getString("cleaner") != null) {
                        cleaner = rs.getString("cleaner");
                    }
                    if (rs.getString("delivery_person") != null) {
                        deliveryPerson = rs.getString("delivery_person");
                    }

                    ReservationModel reserva = new ReservationModel(departmentName, startDate, endDate, "clientTest", 3);
                    reserva.setID(id);
                    reserva.setLimpiador(cleaner);
                    reserva.setEntregador(deliveryPerson);
                    listaReservas.add(reserva);
                    System.out.println(reserva.getDepartment());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return listaReservas;
    }

}
