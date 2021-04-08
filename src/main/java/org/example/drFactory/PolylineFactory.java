package org.example.drFactory;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import org.example.drShapes.Polyline;

public class PolylineFactory implements FiguresFactory {
    @Override
    public Polyline newFigure(GraphicsContext gc,Point2D startPoint){
        return  new Polyline(gc,startPoint,startPoint);
    }

}
