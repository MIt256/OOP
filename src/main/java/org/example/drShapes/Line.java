package org.example.drShapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Line extends ParentFigure {
    private Point2D startPoint,lastPoint;

    public Line(GraphicsContext gc,Point2D startPoint){
        super(gc);
        polyFigure=false;
        this.startPoint=startPoint;
        this.lastPoint=startPoint;
        polyFigure=false;
    }
    @Override
    public void paint(GraphicsContext gc){
        figureStyle(gc);
        gc.strokeLine(startPoint.getX(),startPoint.getY(),lastPoint.getX(),lastPoint.getY());
    }

    @Override
    public void saveLastPoint(Point2D newPoint) {
        this.lastPoint = newPoint;
    }
}