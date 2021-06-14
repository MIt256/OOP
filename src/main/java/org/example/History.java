package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import org.example.core.Shape;

public class History {
  private final ArrayList<Shape> shapeList = new ArrayList<>();
  private int shapesCounter = 0;

  public void addShape(Shape shape) {

    clearUndoShapes();
    shapesCounter++;
    shapeList.add(shape);

  }

  public void undoShape(GraphicsContext gc) {

    if  (shapesCounter > 0) {
      shapesCounter--;
    }

    clearCanvas(gc);
    drawShapesFromCounter(gc);
  }

  public void redoShape(GraphicsContext gc) {

    if  (shapesCounter < shapeList.size()) {
      shapesCounter++;
    }

    clearCanvas(gc);
    drawShapesFromCounter(gc);

  }

  public void clear() {

    shapeList.clear();
    shapesCounter = 0;

  }

  public void drawAll(GraphicsContext gc) {

    for (Shape shape : shapeList) {
      shape.fill(gc);
    }

  }

  public void drawPreview(GraphicsContext gc) {

    for (int i = 0; i < shapeList.size()-1; i++) {
      shapeList.get(i).fill(gc);
    }

  }

  private void drawShapesFromCounter(GraphicsContext gc) {

    for (int i = 0; i < shapesCounter; i++) {
      shapeList.get(i).fill(gc);
    }

  }

  private void clearUndoShapes() {

    if (shapesCounter < shapeList.size()) {
      shapeList.subList(shapesCounter, shapeList.size()).clear();
    }
  }

  public void clearCanvas(GraphicsContext gc){

    double width = gc.getCanvas().getWidth();
    double height = gc.getCanvas().getHeight();

    gc.clearRect(0, 0, width, height);

  }

  public void serializeShapeList (String fileName){

    try (

        FileOutputStream fileOut = new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut)

    ) {

      clearUndoShapes();
      out.writeInt(shapeList.size());

      for (Shape shape: shapeList) {

        out.writeObject(shape);

      }

    } catch (IOException e){

      e.printStackTrace();

    }
  }

  public void deserializeShapeList(String fileName) {

    try(

        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn)

    ) {

      shapeList.clear();
      shapesCounter = 0;

      int size = in.readInt();

      for (int i=0; i<size; i++) {

        shapeList.add((Shape) in.readObject());
        shapesCounter++;

      }

    } catch (IOException | ClassNotFoundException e) {

      e.printStackTrace();

    }
  }





}
