package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 1200, 800);
        stage.setScene(scene);
        stage.setTitle("Paint");
        stage.setResizable(false);
        stage.show();
        /*
        ObservableList<String> itemsForFile = FXCollections.observableArrayList("New", "Open", "Save", "Save as");
        ComboBox<String> ComboBox1 = new ComboBox<String>(itemsForFile);
        ComboBox1.setValue("File");
        ObservableList<String> itemsForShapes = FXCollections.observableArrayList("Line", "Rectangle", "Ellipse", "Polyline", "Polygon", "Add Shape");
        ComboBox<String> ComboBox2 = new ComboBox<String>(itemsForShapes);
        ComboBox2.setValue("Shapes");

        Button undo = new Button("Undo");
        Button redo = new Button("Redo");

// Image image = new Image(getClass().getResourceAsStream("1.png"));
// ImageView img = new ImageView(image);
// img.setFitHeight(25);
// img.setFitWidth(25);
// undo.setGraphic(img);

        TextField input = new TextField();
        input.setPromptText("Line weight");
        Label label1 = new Label("Pen color");
        Label label2 = new Label("Brush color");
        ColorPicker pen = new ColorPicker();
        ColorPicker brush = new ColorPicker();
        Canvas canvas = new Canvas(828, 600);

        FlowPane root = new FlowPane(5, 0, ComboBox1, ComboBox2, undo, redo, input, label1, pen, label2, brush, canvas);
        Scene scene = new Scene(root, 828, 600);

        stage.setScene(scene);
        stage.setTitle("Paint");
        stage.setResizable(false);
        stage.show();
         */
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}