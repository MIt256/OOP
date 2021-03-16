package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.example.shapes.Shape;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button figure_none;

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
        void initialize() {


        }



}
