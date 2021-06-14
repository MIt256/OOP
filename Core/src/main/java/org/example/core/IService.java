package org.example.core;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface IService {

  String getShapeName();
  ShapeFactory createFactory();

  static List<IService> getServices(ModuleLayer layer) {
    return ServiceLoader
        .load(layer, IService.class)
        .stream()
        .map(ServiceLoader.Provider::get)
        .collect(Collectors.toList());
  }
}