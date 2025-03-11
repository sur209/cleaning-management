import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.HashMap;
import Models.ApartmentModel;
import Models.ReservationModel;
import UI.MainScreen;

public class Cleaning3 extends Application {

    // Vamos a ir cambiando de a poco del hashmap limpiezas al hashmap de departamentos.

    HashMap<String, ApartmentModel> departamentos = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        // Configuración inicial

        MainScreen mainScreen = new MainScreen(primaryStage);

        primaryStage.setTitle("Gestión de Limpiezas");
        primaryStage.setScene(mainScreen.getMainScreen());
        primaryStage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}