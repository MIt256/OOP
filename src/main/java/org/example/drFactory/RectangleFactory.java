package org.example.drFactory;


import org.example.core.Color;
import org.example.core.ParentFigure;
import org.example.core.ShapeFactory;
import org.example.drShapes.Rectangle;

public class RectangleFactory implements ShapeFactory {

    @Override
    public ParentFigure createShape(Color lineColor, boolean isLine, boolean isFill, Color fillColor,
                                    int lineWidth) {

        return new Rectangle(lineColor, isLine, isFill, fillColor, lineWidth);

    }
}
