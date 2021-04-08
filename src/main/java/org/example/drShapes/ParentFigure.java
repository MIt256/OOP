package org.example.drShapes;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public abstract class ParentFigure {

    private double penSize;
    private Paint brushColor;
    private Paint fillColor;
    public boolean polyFigure;


    public ParentFigure(GraphicsContext gc){
        fillColor = gc.getStroke();
        brushColor = gc.getFill();
        penSize= gc.getLineWidth();
    }

    public abstract void paint(GraphicsContext gc);

    public boolean isPolyFigure() {
        return polyFigure;
    }

    public abstract void saveLastPoint(Point2D newPoint);

    public void addPoint(Point2D newPoint){}

    public void deleteLastPoint(){}

    public void figureStyle (GraphicsContext gc){
        gc.setStroke(fillColor);
        gc.setFill(brushColor);
        gc.setLineWidth(penSize);
    }
}
