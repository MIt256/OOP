# Лабораторные по ООП. Программное средство рисования фигур.

[Задание №1](https://telegra.ph/OOTPiSP-20-212--Laboratornaya-rabota-1-02-07)
: Реализовать вывод простейших фигур: отрезка, прямоугольника, эллипса, многоугольника, ломаной.

 ## Запуск
 #### Чтобы все работало, надо подключить библиотеки **JavaFX**:

+    Перейти в меню **File->Project Structure**.
     В открывшемся окне перейти к пункту **Libraries**. Нажать на знак "**+**" и в открывшемся диалоговом окне выбрать путь к 
     каталогу **lib** в папке, где распакован **JavaFX SDK**.
+    Необходимо перейти в меню **Run -> Edit Configuration**. В окне перейти к полю **VM options**. И далее в этом поле пропиcать следующий параметр:
     ```
     --module-path "C:\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml
     ```
     ***"C:\javafx-sdk-15.0.1\lib"*** заменить на свой путь к папке **lib** в SDK.
###### [Подробнее...](https://metanit.com/java/javafx/1.8.php)
###### [Скачать JavaFX](https://gluonhq.com/products/javafx/)
     
 ## Результат выполнения
![alt text](https://github.com/MIt256/OOP/blob/master/result.JPG)
