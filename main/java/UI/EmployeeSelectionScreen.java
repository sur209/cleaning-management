package UI;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import Models.UserModel;
import Services.UserService;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EmployeeSelectionScreen {

    VBox employeeSelectionLayout;
    Stage primaryStage;

    public EmployeeSelectionScreen(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.employeeSelectionLayout = new VBox(10);
    }

    public Scene getEmployeeSelectionScreen() {
        Button volverAtras = new Button("Volver atras");
        Label selectionLabel = new Label("Elija su usuario");
        ComboBox<UserModel> usersList = new ComboBox<>();
        ArrayList<UserModel> users = new UserService().getUsers();
        usersList.setItems(FXCollections.observableArrayList(users));
        Button login = new Button("Continuar");

        volverAtras.setOnAction(e -> {
            primaryStage.setScene(new MainScreen(primaryStage).getMainScreen());
        });

        login.setOnAction(e -> {
            if (usersList.getValue() != null) {
                primaryStage.setScene(new EmployeeScreen(primaryStage).getEmployeeScreen(usersList.getValue()));
            }
        });

        employeeSelectionLayout.getChildren().addAll(volverAtras, selectionLabel, usersList, login);
        Scene employeeSelectionScene = new Scene(employeeSelectionLayout, 400, 400);
        return employeeSelectionScene;
    }
}
