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

        Button continueButton = new Button("Continuar");

        Label selectReservationLabel = new Label("Seleccione una reserva:");
        ComboBox<ReservationModel> reservationsComboBox = new ComboBox<>();

        Button anotarseLimpieza = new Button("Anotarse a limpieza");
        anotarseLimpieza.setVisible(false);
        Label anotadoLimpiador = new Label("Anotado como limpiador: ");
        Button anotarseEntrega = new Button("Anotarse a entrega");
        anotarseEntrega.setVisible(false);
        Label anotadoEntregador = new Label("Anotado como entregador: ");

        Button removerLimpiador = new Button("Remover limpiador");
        removerLimpiador.setVisible(false);
        Button removerEntregador = new Button("Remover entregador");
        removerEntregador.setVisible(false);

        volverAtras.setOnAction(e -> {
            primaryStage.setScene(new MainScreen(primaryStage).getMainScreen());
        });

        // Continue Button of Department
        continueButton.setOnAction(e -> {
            if (apartmentComboBox.getValue() != null) {
                String apartmentName = apartmentComboBox.getValue().getName();
                ArrayList<ReservationModel> retrieve = new ReservationService().getReservations(apartmentName);
                reservationsComboBox.setItems(FXCollections.observableArrayList(retrieve));
            } else {
                System.out.println("Por favor, seleccione un departamento.");
            }
        });

        reservationsComboBox.setOnAction(e -> {
            ReservationModel selectedReservation = reservationsComboBox.getValue();
            anotarseLimpieza.setVisible(true);
            anotarseEntrega.setVisible(true);
            removerLimpiador.setVisible(true);
            removerEntregador.setVisible(true);
            anotadoLimpiador.setText("Anotado como limpiador: " + selectedReservation.getLimpiador());
            anotadoEntregador.setText("Anotado como entregador: " + selectedReservation.getEntregador());
        });

        anotarseLimpieza.setOnAction(e -> {
            ReservationModel selectedReservation = reservationsComboBox.getValue();
            Integer reservationID = selectedReservation.getID();
            SignService signService = new SignService();
            signService.signUpForCleaning(employee.getUserName(), reservationID);
        });

        removerLimpiador.setOnAction( e -> {
           ReservationModel selectedReservation = reservationsComboBox.getValue();
           Integer reservationID = selectedReservation.getID();
           SignService signService = new SignService();
           signService.removeSignUpForCleaning(reservationID);
        });

        anotarseEntrega.setOnAction(e -> {
            ReservationModel selectedReservation = reservationsComboBox.getValue();
            Integer reservationID = selectedReservation.getID();
            SignService signService = new SignService();
            signService.signUpForApartmentDelivery(employee.getUserName(), reservationID);
        });

        removerEntregador.setOnAction( e -> {
            ReservationModel selectedReservation = reservationsComboBox.getValue();
            Integer reservationID = selectedReservation.getID();
            SignService signService = new SignService();
            signService.removeSignUpForApartmentDelivery(reservationID);
        });

        employeeLayout.getChildren().addAll(volverAtras, employeeName, selectApartmentLabel,
                apartmentComboBox, continueButton, reservationsComboBox, anotarseLimpieza, anotarseEntrega,
                anotadoLimpiador, anotadoEntregador, removerLimpiador, removerEntregador);
        return new Scene(employeeLayout, 400, 600);
    }


}
