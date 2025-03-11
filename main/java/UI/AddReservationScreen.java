package UI;

import Models.ApartmentModel;
import Models.ReservationModel;
import Services.ReservationService;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddReservationScreen {

    private Stage primaryStage;
    ApartmentModel apartment;

    public AddReservationScreen(Stage primaryStage, ApartmentModel apartment) {
        this.primaryStage = primaryStage;
        this.apartment = apartment;
    }

    public Scene getAddReservationScene() {
        VBox addReservationLayout = new VBox(10);

        // Crear los componentes de la interfaz
        Label apartmentLabel = new Label("Apartment:");
        Label selectedApartment = new Label(apartment.getName());

        Label startLabel = new Label("Fecha de inicio:");
        DatePicker startDatePicker = new DatePicker();

        Label endLabel = new Label("Fecha de fin:");
        DatePicker endDatePicker = new DatePicker();

        Label huespedLabel = new Label("Huesped:");
        TextField huesped = new TextField();

        Label personasLabel = new Label("Personas:");
        TextField personas = new TextField();

        Button volverAtras = new Button("Volver atras");
        Button createReservationButton = new Button("Crear Reserva");
        // Crear reserva y volver a pantalla de Tasks
        createReservationButton.setOnAction(e -> {
            if (huesped.getText() != null) {
                ReservationModel nuevaReserva = new ReservationModel(selectedApartment.getText(), startDatePicker.getValue(), endDatePicker.getValue(), huesped.getText(), Integer.parseInt(personas.getText()));
                // Aca habria que agregar un llamado a la clase AddReservation
                new ReservationService().addReservation(nuevaReserva);
                primaryStage.setScene(new MainScreen(primaryStage).getMainScreen());
            } else {
                System.out.println("Please, choose a name.");
            }
        });

        volverAtras.setOnAction(e -> {
            primaryStage.setScene(new ApartmentsPanelScreen(primaryStage).getApartmentsPanelScreen());
        });


        addReservationLayout.getChildren().addAll(volverAtras, apartmentLabel, selectedApartment, startLabel, startDatePicker, endLabel, endDatePicker, huespedLabel, huesped, personasLabel, personas, createReservationButton);
        Scene addReservationScene = new Scene(addReservationLayout, 400, 600);
        return addReservationScene;
    }

}
