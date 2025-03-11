package UI;
import Services.UserService;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class UserCreationScreen {

    // Aca estoy organizando el codigo diferente, al definir esto al principio y no en el button
    UserService userService;

    private VBox userCreationLayout;
    private Stage primaryStage;

    public UserCreationScreen(Stage primaryStage) {
        this.userService = new UserService();
        this.userCreationLayout = new VBox(10);
        this.primaryStage = primaryStage;
    }

    public Scene getUserCreationScreen() {
        Button volverAtras = new Button("Volver Atras");

        Label labelName = new Label("Nombre de nuevo usuario:");
        TextField fieldName = new TextField();
        Button createUser = new Button("Crear nuevo usuario");


        volverAtras.setOnAction(e -> {
            primaryStage.setScene(new UsersPanelScreen(primaryStage).getUserPanelScreen());
        });

        createUser.setOnAction(e -> {
            new UserService().createUser(fieldName.getText());
            primaryStage.setScene(new UsersPanelScreen(primaryStage).getUserPanelScreen());
        });

        userCreationLayout.getChildren().addAll(volverAtras, labelName, fieldName, createUser);
        Scene userCreationScene = new Scene(userCreationLayout, 400, 400);
        return userCreationScene;
    }







}
