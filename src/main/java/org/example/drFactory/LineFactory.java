package org.example.drFactory;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import org.example.drShapes.Line;

public class LineFactory implements FiguresFactory {
    @Override
    public Line newFigure(GraphicsContext gc, Point2D startPoint) {
        return new Line(gc,startPoint);
    }
}
