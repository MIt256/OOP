package org.example.drFactory;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import org.example.drShapes.ParentFigure;

public interface FiguresFactory {
     ParentFigure newFigure(GraphicsContext gc, Point2D startPoint);
}
