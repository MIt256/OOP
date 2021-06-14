package org.example.drShapes;


import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import org.example.core.Color;
import org.example.core.Point;
import org.example.core.Shape;

public class Polygon implements Shape {

    private final Color lineColor;
    private final boolean isLine;
    private final boolean isFill;
    private final Color fillColor;
    private final int lineWidth;
    private final ArrayList<Point> pointsArr = new ArrayList<>();

    public Polygon(Color lineColor, boolean isLine, boolean isFill, Color fillColor, int lineWidth) {

        this.lineColor = lineColor;
        this.isLine = isLine;
        this.isFill = isFill;
        this.fillColor = fillColor;
        this.lineWidth = lineWidth;

    }

    @Override
    public boolean draw(GraphicsContext gc, Point point) {

        pointsArr.add(point);
        fill(gc);

        return true;
    }

    @Override
    public void fill(GraphicsContext gc) {

        if (pointsArr.size() > 3 ) {

            gc.setStroke(lineColor.getPaintColor());
            gc.setLineWidth(lineWidth);
            gc.setFill(fillColor.getPaintColor());
            gc.setLineCap(StrokeLineCap.ROUND);
            gc.setLineJoin(StrokeLineJoin.ROUND);

            int nPoints = pointsArr.size();
            double[] xPoints = new double[nPoints];
            double[] yPoints = new double[nPoints];

            for (int i = 0; i < pointsArr.size(); i++) {
                xPoints[i] = pointsArr.get(i).getX();
                yPoints[i] = pointsArr.get(i).getY();
            }

            if (isFill) {
                gc.fillPolygon(xPoints, yPoints, nPoints);

                if (isLine) {
                    gc.strokePolygon(xPoints, yPoints, nPoints);
                }

            } else {
                gc.strokePolygon(xPoints, yPoints, nPoints);
            }
        }
    }

    @Override
    public void deleteLastPoint() {

        if (pointsArr.size() > 2) {

            pointsArr.remove(pointsArr.size() - 1);

        }
    }

}
