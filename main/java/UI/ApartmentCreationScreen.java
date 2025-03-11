package UI;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import Services.ApartmentService;

public class ApartmentCreationScreen {

    private VBox ApartmentCreationScreenLayout;
    private Stage primaryStage;

    public ApartmentCreationScreen(Stage primaryStage) {
        ApartmentCreationScreenLayout = new VBox(10);
        this.primaryStage = primaryStage;
    }

    public Scene getApartmentCreationScreen() {
        Button volverAtras = new Button("Volver Atras");
        Label apartmentNameLabel = new Label("Nombre del departamento:");
        TextField apartmentNameField = new TextField();
        Button createApartment = new Button("Crear departamento");

        volverAtras.setOnAction(e -> {
            primaryStage.setScene(new ApartmentsPanelScreen(primaryStage).getApartmentsPanelScreen());
        });

        createApartment.setOnAction(e -> {
            System.out.println("Boton de creacion de departamento pulsado");
            ApartmentService apartmentService = new ApartmentService();
            String apartmentName = apartmentNameField.getText();
            apartmentService.createApartment(apartmentName);
            primaryStage.setScene(new ApartmentsPanelScreen(primaryStage).getApartmentsPanelScreen());
        });

        ApartmentCreationScreenLayout.getChildren().addAll(volverAtras, apartmentNameLabel, apartmentNameField, createApartment);

        Scene apartmentCreationScreenScene = new Scene(ApartmentCreationScreenLayout, 400, 400);
        return apartmentCreationScreenScene;
    }



}
