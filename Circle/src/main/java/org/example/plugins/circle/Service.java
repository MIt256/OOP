package org.example.plugins.circle;
import org.example.core.IService;
import org.example.core.ShapeFactory;

public class Service implements IService {

  @Override
  public String getShapeName() {
    return "Circle";
  }

  @Override
  public ShapeFactory createFactory() {
    return new CircleFactory();
  }
}
