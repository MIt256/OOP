package org.example;

import java.io.File;
import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.example.core.Color;
import org.example.core.IService;
import org.example.core.Point;
import org.example.core.Shape;
import org.example.core.ShapeFactory;
import org.example.drFactory.EllipseFactory;
import org.example.drFactory.PolygonFactory;
import org.example.drFactory.PolylineFactory;
import org.example.drFactory.RectangleFactory;


public class PrimaryController {

    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker cpLineColor;
    @FXML
    private ColorPicker cpFillColor;
    @FXML
    private CheckBox cbFill;
    @FXML
    private CheckBox cbBorder;
    @FXML
    private TextField tfLineWidth;
    @FXML
    private Pane figuresPain;

    private ShapeFactory shapeFactory = new PolylineFactory();
    private final History history = new History();
    private boolean isShapeDrawing = false;
    private GraphicsContext gc;
    private Shape shape;


    @FXML
    void initialize() {
        gc = canvas.getGraphicsContext2D();
        loadPlugins();
        tfLineWidth.setText("5");
        cbFill.setSelected(true);
        cbBorder.setSelected(true);
        cpFillColor.setValue(javafx.scene.paint.Color.RED);
        cpLineColor.setValue(javafx.scene.paint.Color.YELLOW);
    }

    public void btnLineClicked() {
        shapeFactory = new PolylineFactory();
    }

    public void btnRectClicked() {
        shapeFactory = new RectangleFactory();
    }

    public void btnEllipseClicked() { shapeFactory = new EllipseFactory(); }

    public void btnPolygonClicked() {
        shapeFactory = new PolygonFactory();
    }

    public void btnClearClicked() {

        history.clearCanvas(gc);
        history.clear();
        isShapeDrawing = false;
    }

    @FXML
    void canvasMouseClicked(MouseEvent mouseEvent) {

            Point newPoint = new Point(mouseEvent.getX(), mouseEvent.getY());


            if (mouseEvent.getButton() == MouseButton.PRIMARY) {

                if (!isShapeDrawing) {

                    int width = Integer.parseInt(tfLineWidth.getText());

                    Color lineColor = new Color(cpLineColor.getValue());
                    Color fillColor  = new Color(cpFillColor.getValue());

                    shape = shapeFactory.createShape(lineColor, cbBorder.isSelected(),
                        cbFill.isSelected(), fillColor, width);

                    history.addShape(shape);
                }
                isShapeDrawing = shape.draw(gc, newPoint);


            } else {
                isShapeDrawing = false;
            }
    }


    @FXML
    void canvasMouseMoved(MouseEvent mouseEvent) {

        if (isShapeDrawing) {

            history.clearCanvas(gc);


            history.drawPreview(gc);

            shape.deleteLastPoint();


            Point newPoint = new Point(mouseEvent.getX(), mouseEvent.getY());
            shape.draw(gc, newPoint);
        }
    }

    @FXML
    void btnUndoClicked() { history.undoShape(gc);}

    @FXML
    void btnRedoClicked() { history.redoShape(gc);}

    @FXML
    void menuSaveAsClicked() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Document");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Binary", "*.bin");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            history.serializeShapeList(file.toString());
        }
    }

    @FXML
    void menuOpenClicked() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Document");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Binary", "*.bin");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            history.deserializeShapeList(file.toString());
            history.clearCanvas(gc);
            history.drawAll(gc);
        }
    }

    public void loadPlugins() {
        Path pluginsDir = Paths.get("");

        ModuleFinder pluginsFinder = ModuleFinder.of(pluginsDir);

        List<String> plugins = pluginsFinder
            .findAll()
            .stream()
            .map(ModuleReference::descriptor)
            .map(ModuleDescriptor::name)
            .collect(Collectors.toList());

        Configuration pluginsConfiguration = ModuleLayer
            .boot()
            .configuration()
            .resolve(pluginsFinder, ModuleFinder.of(), plugins);

        ModuleLayer layer = ModuleLayer
            .boot()
            .defineModulesWithOneLoader(pluginsConfiguration, ClassLoader.getSystemClassLoader());

        List<IService>  services = IService.getServices(layer);

        for (IService service : services) {

            Button button = new Button();
            button.setOnAction(e -> {
                shapeFactory = service.createFactory();
            });

            button.setText(service.getShapeName());
            button.setPrefWidth(70);
            button.setPrefHeight(38);

            figuresPain.getChildren().add(button);

        }
    }
}




