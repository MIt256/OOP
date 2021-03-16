package org.example.shapes;

import javafx.scene.paint.Color;

public class Ellipse extends Shape {
    //конструктор
    public Ellipse(Color brushColor,Color fillColor, int brushSize) {
        this.brushColor = brushColor;
        this.brushSize = brushSize;
        this.fillColor = fillColor;

    }

    @Override
    public void draw() {

    }
}