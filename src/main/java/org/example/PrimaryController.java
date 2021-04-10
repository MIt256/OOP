package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.example.drFactory.*;
import org.example.drShapes.ParentFigure;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class PrimaryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Button figure_line;

    @FXML
    private Button figure_rectangle;

    @FXML
    private Button figure_polygon;

    @FXML
    private Button figure_ellipse;

    @FXML
    private Button figure_polyline;

    @FXML
    private MenuItem menuNew;

    @FXML
    private MenuItem menuOpen;

    @FXML
    private MenuItem menuSave;

    @FXML
    private MenuItem menuLoad;

    @FXML
    private ImageView backStep;

    @FXML
    private ImageView nextStep;

    @FXML
    private TextField penSize;

    @FXML
    private ColorPicker brushColor;

    @FXML
    private ColorPicker fillColor;

    @FXML
    private Button add_figure;

    @FXML
    private Canvas canvasPreview;

    @FXML
    private Canvas canvasDraw;

    @FXML
    private Button clear;

    @FXML
    private Button backBtn;

    @FXML
    private Button nextBtn;

    @FXML
    private VBox ButtonBox;

    @FXML
    private TextField step;

    private GraphicsContext gcDraw;
    private GraphicsContext gcPreview;

    private final List<FiguresFactory> figuresFactoryList = Arrays.asList(new LineFactory(), new RectangleFactory(),
            new PolygonFactory(), new EllipseFactory(), new PolylineFactory());
    private int Index;
    private ParentFigure currentFigure;
    private boolean isDraw = false;

    @FXML
    public void onButtonClicked(MouseEvent mouseEvent) {
        figure_line.setOnMouseClicked(event -> {

            System.out.println("set line");
            Index = 0;
        });
        figure_rectangle.setOnMouseClicked(event -> {
            System.out.println("set rect");
            Index = 1;
        });
        figure_polygon.setOnMouseClicked(event -> {
            System.out.println("set polygon");
            Index = 2;
        });
        figure_ellipse.setOnMouseClicked(event -> {
            System.out.println("set ellipse");
            Index = 3;
        });
        figure_polyline.setOnMouseClicked(event -> {
            System.out.println("set polyline");
            Index = 4;
        });
    }

    @FXML
    public void initialize() {
        penSize.setText("1");
        brushColor.setValue(Color.rgb(0, 0, 0));
        fillColor.setValue(Color.rgb(0, 0, 0));
        step.setText(String.valueOf(History.currStep));
        gcDraw = canvasDraw.getGraphicsContext2D();
        gcPreview = canvasPreview.getGraphicsContext2D();
        gcDraw.setFill(brushColor.getValue());
        gcDraw.setStroke(fillColor.getValue());
        gcDraw.setLineWidth(Double.parseDouble(penSize.getText()));
        canvasPreview.setVisible(false);
    }

    @FXML
    void stepNext(MouseEvent event) {
        History.stepNext(gcDraw, canvasDraw.getWidth(), canvasDraw.getHeight());
        step.setText(String.valueOf(History.currStep));
    }

    @FXML
    void stepPrev(MouseEvent event) {
        History.stepPrev(gcDraw, canvasDraw.getWidth(), canvasDraw.getHeight());
        step.setText(String.valueOf(History.currStep));
    }

    @FXML
    void onClearClicked(MouseEvent event) {
        History.clear(gcDraw, canvasDraw.getWidth(), canvasDraw.getHeight());
        step.setText(String.valueOf(History.currStep));
    }

    public void brushColorChange(ActionEvent actionEvent) {
        gcDraw.setStroke(brushColor.getValue());
    }

    public void fillColorChange(ActionEvent actionEvent) {
        gcDraw.setFill(fillColor.getValue());
    }

    public void penSizeChange(ActionEvent actionEvent) {
        gcDraw.setLineWidth(Double.parseDouble(penSize.getText()));
    }

    public void onCanvasClicked(MouseEvent mouseEvent) {
        if ((mouseEvent.getButton() == MouseButton.PRIMARY)) {
            if (!isDraw) {
                History.currStep++;
                gcDraw.setFill(brushColor.getValue());
                gcDraw.setStroke(fillColor.getValue());
                gcDraw.setLineWidth(Double.parseDouble(penSize.getText()));

                step.setText(String.valueOf(History.currStep));

                FiguresFactory figuresFactory = figuresFactoryList.get(Index);
                currentFigure = figuresFactory.newFigure(canvasDraw.getGraphicsContext2D(), new Point2D(mouseEvent.getX(), mouseEvent.getY()));
                isDraw = true;

            } else {
                if (currentFigure.isPolyFigure()) {
                    currentFigure.addPoint(new Point2D(mouseEvent.getX(), mouseEvent.getY()));
                    History.addShapes(currentFigure);
                } else {
                    currentFigure.paint(gcDraw);
                    canvasPreview.setVisible(false);
                    History.addShapes(currentFigure);
                    isDraw = false;
                }
            }
        } else if ((mouseEvent.getButton() == MouseButton.SECONDARY) && (currentFigure.isPolyFigure())) {
            currentFigure.deleteLastPoint();
            currentFigure.paint(gcDraw);
            canvasPreview.setVisible(false);
            isDraw = false;
        }
    }

    public void onCanvasMouseMoved(MouseEvent mouseEvent) {
        if (isDraw) {
            canvasPreview.setVisible(true);
            gcPreview.clearRect(0, 0, canvasPreview.getWidth(), canvasPreview.getHeight());
            currentFigure.saveLastPoint(new Point2D(mouseEvent.getX(), mouseEvent.getY()));
            currentFigure.paint(gcPreview);
        }
    }


}