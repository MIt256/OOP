import org.example.core.IService;
import org.example.plugins.circle.Service;

module Circle {
  requires Core;

  requires javafx.controls;

  exports org.example.plugins.circle;
  provides IService with Service;
}

