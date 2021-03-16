package org.example.shapes;

import javafx.scene.paint.Color;

public class Rectangle extends Shape {
    //конструктор
    public Rectangle(Color brushColor,Color fillColor, int brushSize) {
        this.brushColor = brushColor;
        this.brushSize = brushSize;
        this.fillColor = fillColor;

    }

    @Override
    public void draw() {

    }
}