package org.example.shapes;

import javafx.scene.paint.Color;

public  abstract class Shape {
    protected int brushSize;
    protected Color brushColor;
    protected Color fillColor;
    //для сериализации и хранения точек
    int[][] pointArr;

    public void draw() {

    }
}
