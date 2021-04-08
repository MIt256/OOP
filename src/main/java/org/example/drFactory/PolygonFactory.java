package org.example.drFactory;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import org.example.drShapes.Polygon;

public class PolygonFactory implements FiguresFactory {
    @Override
    public Polygon newFigure(GraphicsContext gc, Point2D startPoint){
        return new Polygon(gc,startPoint,startPoint);
    }
}
