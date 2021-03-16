package org.example.shapes;

import javafx.scene.paint.Color;

public class Polyline extends Shape {
    //конструктор
    public Polyline(Color brushColor, int brushSize) {
        this.brushColor = brushColor;
        this.brushSize = brushSize;
    }

    @Override
    public void draw() {

    }
}