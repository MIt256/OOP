package org.example.drFactory;


import org.example.core.Color;
import org.example.core.Shape;
import org.example.core.ShapeFactory;
import org.example.drShapes.Polyline;

public class PolylineFactory implements ShapeFactory {

    @Override
    public Shape createShape(Color lineColor, boolean isLine, boolean isFill, Color fillColor,
        int lineWidth) {

        return new Polyline(lineColor, isLine, isFill, fillColor, lineWidth);

    }
}
