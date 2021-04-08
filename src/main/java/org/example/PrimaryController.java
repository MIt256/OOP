package org.example;

import java.net.URL;
import java.util.*;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.example.drFactory.*;
import org.example.drShapes.ParentFigure;


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
                                                        new PolygonFactory(), new EllipseFactory(), new PolylineFactory() );
        private final List<ParentFigure> parentFigureList = new ArrayList<>();
        private int Index;
        private ParentFigure currentFigure;
        private boolean isDraw = false;

        private int currStep = 0;

        @FXML
        public void onButtonClicked(MouseEvent mouseEvent){
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
                brushColor.setValue(Color.rgb(0,0,0));
                fillColor.setValue(Color.rgb(0,0,0));
                step.setText(String.valueOf(currStep));

                gcDraw = canvasDraw.getGraphicsContext2D();
                gcPreview = canvasPreview.getGraphicsContext2D();
                gcDraw.setFill(brushColor.getValue());
                gcDraw.setStroke(fillColor.getValue());
                gcDraw.setLineWidth(Double.parseDouble(penSize.getText()));
                canvasPreview.setVisible(false);
        }

        @FXML
        void stepNext(MouseEvent event) {
                if ( parentFigureList.size() != currStep) {
                        currStep++;
                        step.setText(String.valueOf(currStep));
                        gcDraw.clearRect(0, 0, canvasDraw.getWidth(), canvasDraw.getHeight());
                        for (int t=0;t<currStep;t++){
                                currentFigure=parentFigureList.get(t);
                                currentFigure.paint(gcDraw);
                        }
                }
        }

        @FXML
        void stepPrev(MouseEvent event) {
                if (currStep != 0 ){
                         currStep--;
                         step.setText(String.valueOf(currStep));
                         gcDraw.clearRect(0, 0, canvasDraw.getWidth(), canvasDraw.getHeight());
                        for (int t=0;t<currStep;t++){
                                currentFigure=parentFigureList.get(t);
                                currentFigure.paint(gcDraw);
                        }
                }
        }

        @FXML
        void onClearClicked(MouseEvent event) {
                currStep=0;
                step.setText(String.valueOf(currStep));
                parentFigureList.clear();
                gcDraw.clearRect(0, 0, canvasDraw.getWidth(), canvasDraw.getHeight());
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
                if(mouseEvent.getButton() == MouseButton.PRIMARY){
                        if (!isDraw) {
                                currStep++;
                                gcDraw.setFill(brushColor.getValue());
                                gcDraw.setStroke(fillColor.getValue());
                                gcDraw.setLineWidth(Double.parseDouble(penSize.getText()));

                                step.setText(String.valueOf(currStep));

                                FiguresFactory figuresFactory = figuresFactoryList.get(Index);
                                currentFigure = figuresFactory.newFigure(canvasDraw.getGraphicsContext2D(), new Point2D(mouseEvent.getX(), mouseEvent.getY()));
                                isDraw = true;

                        } else {
                                if (currentFigure.isPolyFigure()) {
                                        currentFigure.addPoint(new Point2D(mouseEvent.getX(), mouseEvent.getY()));
                                        parentFigureList.add(currStep-1,currentFigure);
                                        clearFigures();

                                        //else parentFigureList.add(currStep+1,currentFigure) ;

                                } else {
                                        currentFigure.paint(gcDraw);
                                        canvasPreview.setVisible(false);

                                        parentFigureList.add(currStep-1,currentFigure) ;
                                        clearFigures();
                                        isDraw = false;
                                }
                        }
                }
                else {
                        currentFigure.deleteLastPoint();
                        currentFigure.paint(gcDraw);
                        canvasPreview.setVisible(false);
                        isDraw = false;
                }
        }

        public void clearFigures(){
                for (int t=parentFigureList.size();t>currStep;t--){
                        parentFigureList.remove(t-1);
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