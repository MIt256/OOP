package org.example.shapes;

import javafx.scene.paint.Color;

public class Ellipse extends Shape {
    //конструктор
    public Ellipse(Color brushColor,Color fillColor, int brushSize,int x1,int y1) {
        this.brushColor = brushColor;
        this.brushSize = brushSize;
        this.fillColor = fillColor;
        this.x1 = x1;
        this.y1 = y1;
    }

    @Override
    public void draw() {

    }
}