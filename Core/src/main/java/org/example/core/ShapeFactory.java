package org.example.core;


public interface ShapeFactory {

    Shape createShape(Color lineColor, boolean isLine, boolean isFill, Color fillColor, int lineWidth);

}
