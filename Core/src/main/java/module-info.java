import org.example.core.IService;

module Core {

  requires javafx.controls;

  exports org.example.core;

  uses IService;
}