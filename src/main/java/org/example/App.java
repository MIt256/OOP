package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("test");
        Group root = new Group();
        Canvas canvas = new Canvas(700.0D, 500.0D);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.drawShapes(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(0.0D, 0.0D, 700.0D, 500.0D);
        gc.setFill(Color.YELLOW);
        gc.fillOval(530.0D, 30.0D, 80.0D, 80.0D);
        gc.setFill(Color.FORESTGREEN);
        gc.fillRect(0.0D, 300.0D, 700.0D, 200.0D);
        gc.fillRect(0.0D, 300.0D, 700.0D, 200.0D);
        gc.setFill(Color.AQUA);
        gc.fillOval(50.0D, 50.0D, 200.0D, 80.0D);
        gc.setStroke(Color.DARKGREEN);

        for(int i = 0; i < 100; ++i) {
            int x = getRandomX();
            int y = getRandomY();
            gc.strokeLine((double)x, (double)y, (double)(x + 20), (double)y);
        }

        gc.setFill(Color.RED);
        gc.fillPolygon(new double[]{300.0D, 350.0D, 325.0D, 350.0D, 300.0D}, new double[]{100.0D, 100.0D, 125.0D, 150.0D, 150.0D}, 5);
        gc.setFill(Color.rgb(26, 15, 16));
        gc.fillPolygon(new double[]{300.0D, 500.0D, 100.0D}, new double[]{150.0D, 250.0D, 250.0D}, 3);
        gc.setFill(Color.rgb(189, 73, 6));
        gc.fillRect(110.0D, 250.0D, 380.0D, 200.0D);
        gc.setFill(Color.rgb(209, 26, 13));
        gc.fillRect(200.0D, 300.0D, 50.0D, 100.0D);
        gc.fillRect(350.0D, 300.0D, 70.0D, 150.0D);
        gc.strokePolyline(new double[]{380.0D, 410.0D, 410.0D}, new double[]{380.0D, 380.0D, 390.0D}, 3);
    }

    public static int getRandomX() {
        return (int)(Math.random() * 680.0D) + 10;
    }

    public static int getRandomY() {
        return (int)(Math.random() * 190.0D) + 310;
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