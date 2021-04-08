package org.example.drFactory;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import org.example.drShapes.Rectangle;

public class RectangleFactory implements FiguresFactory {
    @Override
    public Rectangle newFigure(GraphicsContext gc, Point2D startPoint){
        return new Rectangle(gc,0,0,startPoint);
    }
}
