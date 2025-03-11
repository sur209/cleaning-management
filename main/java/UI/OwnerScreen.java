package UI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class OwnerScreen {

    private VBox ownerScreenLayout;
    private Stage primaryStage;

    public OwnerScreen(Stage primaryStage) {
        ownerScreenLayout = new VBox(10);
        this.primaryStage = primaryStage;
    }

    public Scene getOwnerScreen() {
        Button volverAtras = new Button("Volver atras");
        Button apartmentAdmin = new Button("Gestion de departamentos");
        Button userAdmin = new Button("Gestion de usuarios");

        volverAtras.setOnAction(e -> {
            primaryStage.setScene(new MainScreen(primaryStage).getMainScreen());
        });

        apartmentAdmin.setOnAction(e -> {
            primaryStage.setScene(new ApartmentsPanelScreen(primaryStage).getApartmentsPanelScreen());
        });

        userAdmin.setOnAction(e -> {
            primaryStage.setScene(new UsersPanelScreen(primaryStage).getUserPanelScreen());
        });

        ownerScreenLayout.getChildren().addAll(volverAtras, apartmentAdmin, userAdmin);

        Scene ownerScreenScene = new Scene(ownerScreenLayout, 400, 400);
        return ownerScreenScene;
    }
}
