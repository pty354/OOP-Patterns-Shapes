package Model.Shapes;

import Model.InputProcessing.ProcessCommands;
import Model.Momento.ShapeMomento;

import java.awt.*;
import java.util.ArrayList;

public class Rectangle extends Shape implements Cloneable{

    private int width;
    private int height;

    public Rectangle(int width, int height){
        this.width = width;
        this.height = height;
        setColor("transparent");
        setOrigin(new Point(640, 360));
    }

    @Override
    public Object clone(){
        Shape savedRect = new Rectangle(width, height);
        savedRect.setColor(getColor());
        savedRect.setOrigin(getOrigin());
        return savedRect;
    }

    @Override
    public ShapeMomento saveMomento() throws CloneNotSupportedException {
        Rectangle savedRect = (Rectangle) this.clone();
        int saveSelection = ProcessCommands.getSelection();
        ProcessCommands.setSelection(-1);
        return new ShapeMomento(savedRect, saveSelection);
    }

    public String getType(){
        return "rectangle";
    }

    public ArrayList<Integer> getSize(){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(getHeight());
        arr.add(getWidth());
        return arr;
    }
    public void setSize(ArrayList<Integer> size){
        setHeight(size.get(0));
        setWidth(size.get(1));
    }
    public String toString() {
        return "Rectangle, Color: "+ getColor() +
                ", Origin: (" + (int)getOrigin().getX() +
                "," + (int)getOrigin().getY() + "), " +
                "Width: "+getWidth()+
                ", Height: "+getHeight();
    }

    //Accessors
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

}
