package Shapes;

import InputProcessing.ProcessCommands;
import Momento.ShapeMomento;

import java.awt.*;

public class Circle extends Shape implements Cloneable{

    private int radius;

    public Circle(int radius){
        this.radius = radius;
        setColor("Blue");
        setOrigin(new Point(0, 0));
    }

    @Override
    public Object clone(){
        Shape savedCirc = new Circle(radius);
        savedCirc.setColor(getColor());
        savedCirc.setOrigin(getOrigin());
        return savedCirc;
    }

    @Override
    public ShapeMomento saveMomento() throws CloneNotSupportedException {
        Circle savedCirc = (Circle) this.clone();
        int saveSelection = ProcessCommands.getSelection();
        ProcessCommands.setSelection(-1);
        return new ShapeMomento(savedCirc, saveSelection);
    }

    public String toString() {
        return "Circle, Color: "+ getColor() +
                ", Origin: (" + (int)getOrigin().getX() +
                "," + (int)getOrigin().getY() + "), " +
                "Radius: "+getRadius();
    }

    //Accessors
    public int getRadius() { return radius; }
    public void setRadius(int radius) { this.radius = radius; }
}
