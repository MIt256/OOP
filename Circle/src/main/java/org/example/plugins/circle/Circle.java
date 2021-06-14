package org.example.plugins.circle;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import javafx.scene.canvas.GraphicsContext;
import org.example.core.Color;
import org.example.core.Point;
import org.example.core.Shape;

public class Circle implements Shape {

  private final Color lineColor;
  private final boolean isLine;
  private final boolean isFill;
  private final Color fillColor;
  private final int lineWidth;
  private Point centerPoint;
  private Point radiusPoint;
  private boolean firstDrawCall = true;

  public Circle(Color lineColor, boolean isLine, boolean isFill, Color fillColor, int lineWidth) {

    this.lineColor = lineColor;
    this.isLine = isLine;
    this.isFill = isFill;
    this.fillColor = fillColor;
    this.lineWidth = lineWidth;

  }

  @Override
  public boolean draw(GraphicsContext gc, Point point){

    if (!firstDrawCall) {

      radiusPoint = point;
      fill(gc);

      return false;
    }

    centerPoint = point;
    firstDrawCall = false;

    return true;
  }

  @Override
  public void fill(GraphicsContext gc) {


    gc.setStroke(lineColor.getPaintColor());
    gc.setLineWidth(lineWidth);
    gc.setFill(fillColor.getPaintColor());

    double radius = countRadius();
    double diameter  = radius*2;

    if (isFill) {

      gc.fillOval(centerPoint.getX() - radius, centerPoint.getY() - radius,
              diameter , diameter );

      if (isLine) {

        gc.strokeOval(centerPoint.getX() - radius, centerPoint.getY() - radius,
                diameter , diameter );

      }

    } else {

      gc.strokeOval(centerPoint.getX() - radius, centerPoint.getY() - radius,
              diameter , diameter );

    }
  }

  private double countRadius() {

    return sqrt(pow((radiusPoint.getX()- centerPoint.getX()), 2)
            + pow((radiusPoint.getY() - centerPoint.getY()), 2));

  }

  @Override
  public void deleteLastPoint() {

  }
}
