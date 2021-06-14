package org.example.core;

import java.io.Serializable;
import javafx.scene.canvas.GraphicsContext;

// интерфейс для всех фигур
public interface ParentFigure extends Serializable {

    boolean draw(GraphicsContext gc, Point point);

    void fill(GraphicsContext gc);

    void deleteLastPoint();

}
