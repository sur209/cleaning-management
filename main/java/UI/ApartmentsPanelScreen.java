package UI;

import Models.ApartmentModel;
import Models.ReservationModel;
import Models.UserModel;
import Services.ApartmentService;
import Services.ReservationService;
import Services.SignService;
import Services.UserService;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class ApartmentsPanelScreen {

    private VBox apartmentsPanelScreenLayout;
    private Stage primaryStage;

    public ApartmentsPanelScreen(Stage primaryStage) {
        apartmentsPanelScreenLayout = new VBox(10);
        this.primaryStage = primaryStage;
    }

    public Scene getApartmentsPanelScreen() {
        Button volverAtras = new Button("Volver atras");
        Button createApartment = new Button("Crear nuevo departamento");

        Label apartmentLabel = new Label("Departamento: ");
        Button addReservationButton = new Button("Crear nueva reserva");
        addReservationButton.setVisible(false);
        Label reservationLabel = new Label("Reservas:");
        ComboBox<ApartmentModel> apartmentComboBox = new ComboBox<>();
        ComboBox<ReservationModel> reservationsComboBox = new ComboBox<>();

        Label anotadoLimpiador = new Label("Anotado como limpiador: ");
        Label anotadoEntregador = new Label("Anotado como entregador: ");
        anotadoLimpiador.setVisible(false);
        anotadoEntregador.setVisible(false);

        Button removerLimpiador = new Button("Remover limpiador de la reserva");
        removerLimpiador.setVisible(false);
        Button removerEntregador = new Button("Remover entregador de la reserva");
        removerEntregador.setVisible(false);

        volverAtras.setOnAction(e -> {
            primaryStage.setScene(new OwnerScreen(primaryStage).getOwnerScreen());
        });

        createApartment.setOnAction(e -> {
            primaryStage.setScene(new ApartmentCreationScreen(primaryStage).getApartmentCreationScreen());
        });

        addReservationButton.setOnAction(e -> {
            ApartmentModel selectedApartment = apartmentComboBox.getValue();
            primaryStage.setScene(new AddReservationScreen(primaryStage, selectedApartment).getAddReservationScene());
        });

        apartmentComboBox.setOnAction(e -> {
            ArrayList<ReservationModel> reservations = new ReservationService().getReservations(apartmentComboBox.getValue().getName());
            reservationsComboBox.setItems(FXCollections.observableArrayList(reservations));
            addReservationButton.setVisible(true);
            anotadoLimpiador.setVisible(false);
            anotadoEntregador.setVisible(false);
            removerLimpiador.setVisible(false);
            removerEntregador.setVisible(false);
        });

        reservationsComboBox.setOnAction(e -> {
            if (reservationsComboBox.getValue() != null) {

                anotadoLimpiador.setVisible(true);
                anotadoEntregador.setVisible(true);
                if (reservationsComboBox.getValue().getLimpiador() == null || reservationsComboBox.getValue().getLimpiador().trim().isEmpty()) {
                    removerLimpiador.setVisible(false);
                } else {
                    anotadoLimpiador.setText("Anotado como limpiador: " + reservationsComboBox.getValue().getLimpiador());
                    removerLimpiador.setVisible(true);
                }
                if (reservationsComboBox.getValue().getEntregador() == null || reservationsComboBox.getValue().getEntregador().trim().isEmpty()) {
                    removerEntregador.setVisible(false);
                } else {
                    anotadoEntregador.setText("Anotado como entregador: " +  reservationsComboBox.getValue().getEntregador());
                    removerEntregador.setVisible(true);
                }
            }
        });

        removerLimpiador.setOnAction( e -> {
            ReservationModel selectedReservation = reservationsComboBox.getValue();
            Integer reservationID = selectedReservation.getID();
            SignService signService = new SignService();
            signService.removeSignUpForCleaning(reservationID);
            anotadoLimpiador.setText("Anotado como limpiador: ");
            removerLimpiador.setVisible(false);
        });

        removerEntregador.setOnAction( e -> {
            ReservationModel selectedReservation = reservationsComboBox.getValue();
            Integer reservationID = selectedReservation.getID();
            SignService signService = new SignService();
            signService.removeSignUpForApartmentDelivery(reservationID);
            anotadoEntregador.setText("Anotado como entregador: ");

            removerEntregador.setVisible(false);
        });

        ArrayList<ApartmentModel> apartments = new ApartmentService().getApartments();
        apartmentComboBox.setItems(FXCollections.observableArrayList(apartments));

        apartmentsPanelScreenLayout.getChildren().addAll(volverAtras, createApartment, apartmentLabel, apartmentComboBox, addReservationButton, reservationLabel, reservationsComboBox, anotadoLimpiador, anotadoEntregador, removerLimpiador, removerEntregador);

        Scene apartmentsPanelScreenScene = new Scene(apartmentsPanelScreenLayout, 400, 400);
        return apartmentsPanelScreenScene;
    }
}
