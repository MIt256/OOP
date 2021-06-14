package org.example.plugins.circle;

import org.example.core.Color;
import org.example.core.Shape;
import org.example.core.ShapeFactory;

public class CircleFactory implements ShapeFactory {

  @Override
  public Shape createShape(Color lineColor, boolean isLine, boolean isFill, Color fillColor,
                           int lineWidth) {
    return new Circle(lineColor, isLine, isFill, fillColor, lineWidth);
  }
}
