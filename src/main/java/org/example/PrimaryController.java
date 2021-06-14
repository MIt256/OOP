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
import javafx.geometry.Point2D;
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
import org.example.core.ParentFigure;
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
    private Pane menuPain;

    @FXML
    private TextField step;

    private ShapeFactory shapeFactory = new PolylineFactory();
    private final History history = new History();
    private boolean isShapeDrawing = false;
    private GraphicsContext gc;
    private ParentFigure parentFigure;
    private int Index;
    private ParentFigure currentFigure;
    private boolean isDraw = false;


    @FXML
    void initialize() {
        gc = canvas.getGraphicsContext2D();
        loadPlugins();
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

    }

    @FXML
    void canvasMouseClicked(MouseEvent mouseEvent) {

            Point newPoint = new Point(mouseEvent.getX(), mouseEvent.getY());


            if (mouseEvent.getButton() == MouseButton.PRIMARY) {

                if (!isShapeDrawing) {

                    int width = Integer.parseInt(tfLineWidth.getText());

                    Color lineColor = new Color(cpLineColor.getValue());
                    Color fillColor  = new Color(cpFillColor.getValue());

                    parentFigure = shapeFactory.createShape(lineColor, cbBorder.isSelected(),
                        cbFill.isSelected(), fillColor, width);

                    history.addShape(parentFigure);
                }
                isShapeDrawing = parentFigure.draw(gc, newPoint);


            } else {
                isShapeDrawing = false;
            }
    }


    @FXML
    void canvasMouseMoved(MouseEvent mouseEvent) {


    }

    @FXML
    void btnUndoClicked() { history.stepNext(gc);}

    @FXML
    void btnRedoClicked() { history.stepPrev(gc);}

    @FXML
    void menuSaveAsClicked() {

        //FileChooser fileChooser = new FileChooser();
        //fileChooser.setTitle("Save Document");
       // FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Binary", "*.bin");
        //fileChooser.getExtensionFilters().add(extFilter);
        //File file = fileChooser.showSaveDialog(null);

        //дописать
    }

    @FXML
    void menuOpenClicked() {

       // FileChooser fileChooser = new FileChooser();
       // fileChooser.setTitle("Open Document");
       // FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Binary", "*.bin");
       // fileChooser.getExtensionFilters().add(extFilter);
        //File file = fileChooser.showOpenDialog(null);

       //
    }


    //
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
            button.setPrefWidth(50);
            button.setPrefHeight(30);

            menuPain.getChildren().add(button);
        }
    }

    public void onCanvasMouseMoved(MouseEvent mouseEvent) {

    }
}




