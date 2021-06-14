package org.example.drFactory;

import org.example.core.Color;
import org.example.core.Shape;
import org.example.core.ShapeFactory;
import org.example.drShapes.Polygon;

public class PolygonFactory implements ShapeFactory {

    @Override
    public Shape createShape(Color lineColor, boolean isLine, boolean isFill, Color fillColor,
        int lineWidth) {

        return new Polygon(lineColor, isLine, isFill, fillColor, lineWidth);

    }
}
