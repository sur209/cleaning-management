package UI;

import Models.UserModel;
import Services.UserService;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class UsersPanelScreen {
    // Aca estoy organizando el codigo diferente, al definir esto al principio y no en el button
    UserService userService;

    private VBox userPanelLayout;
    private Stage primaryStage;

    public UsersPanelScreen(Stage primaryStage) {
        this.userService = new UserService();
        this.userPanelLayout = new VBox(10);
        this.primaryStage = primaryStage;
    }

    public Scene getUserPanelScreen() {
        Button volverAtras = new Button("Volver Atras");
        Button createUser = new Button("Crear nuevo usuario");
        Label usuariosLabel = new Label("Usuarios: ");
        ComboBox<UserModel> usersList = new ComboBox<>();
        Button deleteUser = new Button("Eliminar usuario seleccionado");

        volverAtras.setOnAction(e -> {
            primaryStage.setScene(new OwnerScreen(primaryStage).getOwnerScreen());
        });

        createUser.setOnAction(e -> {
            primaryStage.setScene(new UserCreationScreen(primaryStage).getUserCreationScreen());
        });

        ArrayList<UserModel> users = new UserService().getUsers();
        usersList.setItems(FXCollections.observableArrayList(users));

        deleteUser.setOnAction(e -> {
           new UserService().deleteUser(usersList.getValue());
        });

        userPanelLayout.getChildren().addAll(volverAtras, createUser, usuariosLabel, usersList, deleteUser);
        Scene userPanelScene = new Scene(userPanelLayout, 400, 400);
        return userPanelScene;
    }
}