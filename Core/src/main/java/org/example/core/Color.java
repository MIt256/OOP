package org.example.core;

import java.io.Serializable;

public class Color implements Serializable {

  private double r;
  private double g;
  private double b;
  private double a;

  public Color(javafx.scene.paint.Color color) {

    this.r = color.getRed();
    this.g = color.getGreen();
    this.b = color.getBlue();
    this.a = color.getOpacity();

  }

  public javafx.scene.paint.Color getPaintColor() {

    javafx.scene.paint.Color c = new javafx.scene.paint.Color(r,g,b,a);
    return  c;

  }

}
