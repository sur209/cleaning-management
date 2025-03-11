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

    public Scene getDepartmentScene(Stage primaryStage) {
        // Pantalla de selección de departamento
        VBox departmentSelectionLayout = new VBox(10);
        departmentSelectionLayout.setSpacing(10);
        Label selectDepartmentLabel = new Label("Seleccione un departamento:");
        ComboBox<String> departmentComboBox = new ComboBox<>();
        departmentComboBox.getItems().addAll("Aloha", "Alohita", "A3", "1C");
        Button continueButton = new Button("Continuar");

        departmentSelectionLayout.getChildren().addAll(selectDepartmentLabel, departmentComboBox, continueButton);
        Scene departmentScene = new Scene(departmentSelectionLayout, 400, 200);

        // Navegación entre pantallas
        continueButton.setOnAction(e -> {
            if (departmentComboBox.getValue() != null) {
                primaryStage.setScene(getTaskScene(primaryStage, departmentComboBox.getValue()));
            } else {
                showAlert("Por favor, seleccione un departamento.");
            }
        });
        return departmentScene;
    }


    public Scene getTaskScene(Stage primaryStage, String departamento) {

        ApartmentModel departamentoObject = departamentos.get(departamento);

        // Pantalla de tareas
        VBox taskLayout = new VBox(10);
        taskLayout.setSpacing(10);
        Label departmentName = new Label(departamento);

        Label selectReservationLabel = new Label("Seleccione una reserva:");
        ComboBox<ReservationModel> reservationsComboBox = new ComboBox<>();

        VBox entregadorSection = new VBox(5);
        Label entregadorNameLabel = new Label("Nombre del Entregador");
        TextField entregadorTextField = new TextField();
        Button addEntregador = new Button("Agregar Entregador");
        addEntregador.setOnAction(e -> {
            String entregador = entregadorTextField.getText();
            Label entregadorName = new Label("Entregador: " + entregador);
            int comboIndex = taskLayout.getChildren().indexOf(reservationsComboBox);
            taskLayout.getChildren().add(comboIndex + 1, entregadorName);
            entregadorSection.setVisible(false);
        });

        VBox limpiadorSection = new VBox(5);
        Label limpiadorNameLabel = new Label("Nombre del Limpiador");
        TextField limpiadorTextField = new TextField();
        Button addLimpiador = new Button("Agregar Limpiador");
        addLimpiador.setOnAction(e -> {
            String limpiador = limpiadorTextField.getText();
            Label limpiadorName = new Label("Limpiador: " + limpiador);
            int comboIndex = taskLayout.getChildren().indexOf(reservationsComboBox);
            taskLayout.getChildren().add(comboIndex + 1, limpiadorName);
            limpiadorSection.setVisible(false);
        });

        entregadorSection.getChildren().addAll(entregadorNameLabel, entregadorTextField, addEntregador);
        limpiadorSection.getChildren().addAll(limpiadorNameLabel, limpiadorTextField, addLimpiador);


        reservationsComboBox.setOnAction (e -> {
            if (reservationsComboBox.getValue() != null) {
                int labelIndex = taskLayout.getChildren().indexOf(reservationsComboBox);
                taskLayout.getChildren().add(labelIndex + 1, entregadorSection);
                taskLayout.getChildren().add(labelIndex + 1, limpiadorSection);
            }
        });

        if (departamentoObject.getReservations() != null) {
            reservationsComboBox.getItems().addAll(departamentoObject.getReservations());
        }

        Label taskLabel = new Label("Tareas de limpieza:");
        CheckBox preparado = new CheckBox("Preparado");
        preparado.setSelected(true);
        ListView<CheckBox> taskListView = new ListView<>();
        taskListView.getItems().addAll(
                preparado
        );

        Button addReservationButton = new Button("Crear nueva reserva");
        addReservationButton.setOnAction(e -> {
            primaryStage.setScene(getAddReservationScene(primaryStage, departamento));
        });

        Button saveButton = new Button("Guardar");
        saveButton.setOnAction(e -> {
            showAlert("Las tareas completadas se han guardado exitosamente.");
            primaryStage.setScene(getDepartmentScene(primaryStage)); // Volver a la pantalla de selección
        });
        taskLayout.getChildren().addAll(departmentName, addReservationButton, selectReservationLabel, reservationsComboBox, taskLabel, taskListView, saveButton);
        Scene taskScene = new Scene(taskLayout, 400, 300);
        return taskScene;
    }


    public Scene getAddReservationScene(Stage primaryStage, String departamento) {
        VBox addReservationLayout = new VBox(10);
        addReservationLayout.setSpacing(10);

        Label departmentLabel = new Label("Departamento:");
        TextField department = new TextField();

        // Crear los componentes de la interfaz
        Label startLabel = new Label("Fecha de inicio:");
        DatePicker startDatePicker = new DatePicker();

        Label endLabel = new Label("Fecha de fin:");
        DatePicker endDatePicker = new DatePicker();

        Label huespedLabel = new Label("Huesped:");
        TextField huesped = new TextField();

        Label personasLabel = new Label("Personas:");
        TextField personas = new TextField();

        Button createReservationButton = new Button("Crear Reserva");
        // Crear reserva y volver a pantalla de Tasks
        createReservationButton.setOnAction(e -> {
            if (huesped.getText() != null) {
                ReservationModel nuevaReserva = new ReservationModel(department.getText(), startDatePicker.getValue(), endDatePicker.getValue(), huesped.getText(), Integer.parseInt(personas.getText()));
                departamentos.get(departamento).addReservation(nuevaReserva);
                primaryStage.setScene(getDepartmentScene(primaryStage));
            } else {
                showAlert("Por favor, elige un nombre.");
            }
        });


        addReservationLayout.getChildren().addAll(startLabel, startDatePicker, endLabel, endDatePicker, huespedLabel, huesped, personasLabel, personas, createReservationButton);
        Scene addReservationScene = new Scene(addReservationLayout, 400, 300);
        return addReservationScene;
    }

    public void agregarDepartamentos() {
        ApartmentModel Aloha = new ApartmentModel("Aloha");
        ApartmentModel Alohita = new ApartmentModel("Alohita");
        ApartmentModel A3 = new ApartmentModel("A3");
        ApartmentModel unoC = new ApartmentModel("1C");

        departamentos.put("Aloha", Aloha);
        departamentos.put("Alohita", Alohita);
        departamentos.put("A3", A3);
        departamentos.put("1C", unoC);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}