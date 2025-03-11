package UI;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScreen {

    private VBox mainScreenLayout;
    Stage primaryStage;

    OwnerScreen ownerScreen;
    EmployeeSelectionScreen employeeSelectionScreen;

    public MainScreen(Stage primaryStage) {
            mainScreenLayout = new VBox(10);
            this.primaryStage = primaryStage;
            this.ownerScreen = new OwnerScreen(primaryStage);
            this.employeeSelectionScreen = new EmployeeSelectionScreen(primaryStage);
    }

    public Scene getMainScreen() {
        Button employee = new Button("Empleado");
        employee.setMinSize(400, 200);
        Button owner = new Button("Dueño");
        owner.setMinSize(400, 200);



        employee.setOnAction(e-> {
            primaryStage.setScene(employeeSelectionScreen.getEmployeeSelectionScreen());
        });

        owner.setOnAction(e-> {
            primaryStage.setScene(ownerScreen.getOwnerScreen());
        });


        this.mainScreenLayout.getChildren().addAll(employee, owner);
        Scene mainScreenScene = new Scene(mainScreenLayout, 400, 400);
        return mainScreenScene;
    }





}
