package org.example.core;

import java.io.Serializable;
import javafx.scene.canvas.GraphicsContext;


public interface Shape extends Serializable {

    boolean draw(GraphicsContext gc, Point point);

    void fill(GraphicsContext gc);

    void deleteLastPoint();

}
