package Model.Shapes;

import Model.InputProcessing.ProcessCommands;
import Model.Momento.ShapeMomento;

import java.awt.*;
import java.util.ArrayList;

public class Circle extends Shape implements Cloneable{

    private int radius;

    public Circle(int radius){
        this.radius = radius;
        setColor("transparent");
        setOrigin(new Point( 640, 360));
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

    public String getType(){
        return "circle";
    }

    public ArrayList<Float> getSize(){
        ArrayList<Float> arr = new ArrayList<>();
        arr.add((float)getRadius());
        return arr;
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
