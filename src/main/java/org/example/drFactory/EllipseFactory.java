package org.example.drFactory;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import org.example.drShapes.Ellipse;

public class EllipseFactory implements FiguresFactory {
    @Override
    public Ellipse newFigure(GraphicsContext gc, Point2D startPoint){
        return new Ellipse(gc,0,0,startPoint);
    }
}
