package UI;

import Models.ApartmentModel;
import Models.ReservationModel;
import Models.UserModel;
import Services.ApartmentService;
import Services.ReservationService;
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
        Label reservationLabel = new Label("Reservas:");
        ComboBox<ApartmentModel> apartmentComboBox = new ComboBox<>();
        ComboBox<ReservationModel> reservationsComboBox = new ComboBox<>();

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
        });

        ArrayList<ApartmentModel> apartments = new ApartmentService().getApartments();
        apartmentComboBox.setItems(FXCollections.observableArrayList(apartments));

        apartmentsPanelScreenLayout.getChildren().addAll(volverAtras, createApartment, apartmentLabel, apartmentComboBox, addReservationButton, reservationLabel, reservationsComboBox);

        Scene apartmentsPanelScreenScene = new Scene(apartmentsPanelScreenLayout, 400, 400);
        return apartmentsPanelScreenScene;
    }
}
