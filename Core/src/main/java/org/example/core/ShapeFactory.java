package org.example.core;


public interface ShapeFactory {

    ParentFigure createShape(Color lineColor, boolean isLine, boolean isFill, Color fillColor, int lineWidth);

}
