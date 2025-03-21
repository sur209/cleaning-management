package UI;

import Models.ApartmentModel;
import Models.ReservationModel;
import Models.UserModel;
import Services.ApartmentService;
import Services.ReservationService;
import Services.SignService;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

import java.util.ArrayList;

public class EmployeeScreen {
    private VBox employeeLayout;
    private Stage primaryStage;

    public EmployeeScreen(Stage primaryStage) {
        employeeLayout = new VBox(10);
        this.primaryStage = primaryStage;
    }

    public Scene getEmployeeScreen(UserModel employee) {
        Button volverAtras = new Button("Volver atras");

        Label employeeName = new Label("Employee name: " + employee.getUserName());
        Label selectApartmentLabel = new Label("Seleccione un departamento:");
        ComboBox<ApartmentModel> apartmentComboBox = new ComboBox<>();
        ArrayList<ApartmentModel> apartmentList = new ApartmentService().getApartments();
        apartmentComboBox.setItems(FXCollections.observableArrayList(apartmentList));

        Label selectReservationLabel = new Label("Seleccione una reserva:");
        ComboBox<ReservationModel> reservationsComboBox = new ComboBox<>();

        Button anotarseLimpieza = new Button("Anotarse a limpieza");
        anotarseLimpieza.setVisible(false);
        Label anotadoLimpiador = new Label("Anotado como limpiador: ");
        anotadoLimpiador.setVisible(false);
        Button anotarseEntrega = new Button("Anotarse a entrega");
        anotarseEntrega.setVisible(false);
        Label anotadoEntregador = new Label("Anotado como entregador: ");
        anotadoEntregador.setVisible(false);

        Button removerLimpiador = new Button("Desanotarse de la limpieza");
        removerLimpiador.setVisible(false);
        Button removerEntregador = new Button("Desanotarse de la entrega");
        removerEntregador.setVisible(false);

        volverAtras.setOnAction(e -> {
            primaryStage.setScene(new MainScreen(primaryStage).getMainScreen());
        });

        apartmentComboBox.setOnAction(e -> {
            if (apartmentComboBox.getValue() != null) {
                String apartmentName = apartmentComboBox.getValue().getName();
                ArrayList<ReservationModel> retrieve = new ReservationService().getReservations(apartmentName);
                reservationsComboBox.setItems(FXCollections.observableArrayList(retrieve));
                anotadoLimpiador.setVisible(false);
                anotadoEntregador.setVisible(false);
                anotarseLimpieza.setVisible(false);
                anotarseEntrega.setVisible(false);
                removerLimpiador.setVisible(false);
                removerEntregador.setVisible(false);
            } else {
                System.out.println("Por favor, seleccione un departamento.");
            }
        });

        reservationsComboBox.setOnAction(e -> {
            ReservationModel selectedReservation = reservationsComboBox.getValue();
            anotadoLimpiador.setVisible(true);
            anotadoEntregador.setVisible(true);
            if (!employee.getUserName().equals(selectedReservation.getLimpiador())) {
                anotarseLimpieza.setVisible(true);
                removerLimpiador.setVisible(false);
            } else {
                removerLimpiador.setVisible(true);
                anotarseLimpieza.setVisible(false);
            }

            if (!employee.getUserName().equals(selectedReservation.getEntregador())) {
                anotarseEntrega.setVisible(true);
                removerEntregador.setVisible(false);
            } else {
                removerEntregador.setVisible(true);
                anotarseEntrega.setVisible(false);
            }

            anotadoLimpiador.setText("Anotado como limpiador para la proxima reserva: " + selectedReservation.getLimpiador());
            anotadoEntregador.setText("Anotado como entregador para la proxima reserva: " + selectedReservation.getEntregador());
        });

        anotarseLimpieza.setOnAction(e -> {
            ReservationModel selectedReservation = reservationsComboBox.getValue();
            Integer reservationID = selectedReservation.getID();
            SignService signService = new SignService();
            signService.signUpForCleaning(employee.getUserName(), reservationID);
            anotadoLimpiador.setText("Anotado como limpiador: " + employee.getUserName());

            anotarseLimpieza.setVisible(false);
            removerLimpiador.setVisible(true);
        });

        removerLimpiador.setOnAction( e -> {
           ReservationModel selectedReservation = reservationsComboBox.getValue();
           Integer reservationID = selectedReservation.getID();
           SignService signService = new SignService();
           signService.removeSignUpForCleaning(reservationID);
           anotadoLimpiador.setText("Anotado como limpiador: ");

           anotarseLimpieza.setVisible(true);
           removerLimpiador.setVisible(false);
        });

        anotarseEntrega.setOnAction(e -> {
            ReservationModel selectedReservation = reservationsComboBox.getValue();
            Integer reservationID = selectedReservation.getID();
            SignService signService = new SignService();
            signService.signUpForApartmentDelivery(employee.getUserName(), reservationID);
            anotadoEntregador.setText("Anotado como entregador: " + employee.getUserName());

            anotarseEntrega.setVisible(false);
            removerEntregador.setVisible(true);
        });

        removerEntregador.setOnAction( e -> {
            ReservationModel selectedReservation = reservationsComboBox.getValue();
            Integer reservationID = selectedReservation.getID();
            SignService signService = new SignService();
            signService.removeSignUpForApartmentDelivery(reservationID);
            anotadoEntregador.setText("Anotado como entregador: ");

            anotarseEntrega.setVisible(true);
            removerEntregador.setVisible(false);
        });

        employeeLayout.getChildren().addAll(volverAtras, employeeName, selectApartmentLabel,
                apartmentComboBox, selectReservationLabel, reservationsComboBox, anotarseLimpieza, anotarseEntrega,
                anotadoLimpiador, anotadoEntregador, removerLimpiador, removerEntregador);
        return new Scene(employeeLayout, 400, 600);
    }


}
