package ru.kulikovman.atomiccircles;


public interface ICanvasView {

    void drawCircle(SimpleCircle circle);

    void redraw();

    void showMessage(String text);
}
