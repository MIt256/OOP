package org.example;

import javafx.scene.canvas.GraphicsContext;
import org.example.drShapes.ParentFigure;

import java.util.ArrayList;

public class History {
    private static ParentFigure currentFigure;
    public static int currStep = 0;
    public static ArrayList<ParentFigure> parentFigureList = new ArrayList<>();

    public static void stepNext(GraphicsContext gcDraw, double width, double height) {
        if (parentFigureList.size() != currStep) {
            currStep++;
            gcDraw.clearRect(0, 0, width, height);
            for (int t = 0; t < currStep; t++) {
                currentFigure = parentFigureList.get(t);
                currentFigure.paint(gcDraw);
            }
        }
    }

    public static void stepPrev(GraphicsContext gcDraw, double width, double height) {
        if (currStep != 0) {
            currStep--;
            gcDraw.clearRect(0, 0, width, height);
            for (int t = 0; t < currStep; t++) {
                currentFigure = parentFigureList.get(t);
                currentFigure.paint(gcDraw);
            }
        }
    }

    public static void clear(GraphicsContext gcDraw, double width, double height) {
        currStep = 0;
        parentFigureList.clear();
        gcDraw.clearRect(0, 0, width, height);
    }

    public static void addShapes(ParentFigure currentShape) {
        parentFigureList.add(History.currStep - 1, currentShape);
        for (int t = History.parentFigureList.size(); t > History.currStep; t--) {
            History.parentFigureList.remove(t - 1);
        }
    }

}
