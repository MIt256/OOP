package org.example.shapes;

import javafx.scene.paint.Color;

public class Line extends Shape {
    //конструктор
    public Line(Color brushColor, int brushSize) {
        this.brushColor = brushColor;
        this.brushSize = brushSize;
    }

    @Override
    public void draw(int[] points) {

    }
}