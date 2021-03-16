package org.example.shapes;

import javafx.scene.paint.Color;

public class Polygon extends Shape {
    //конструктор
    public Polygon(Color brushColor,Color fillColor, int brushSize) {
        this.brushColor = brushColor;
        this.brushSize = brushSize;
        this.fillColor = fillColor;

    }

    @Override
    public void draw() {

    }
}