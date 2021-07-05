package Model.Shapes;
import Model.Momento.ShapeMomento;
import java.awt.*;
import java.util.ArrayList;

public abstract class Shape{

    private String color;
    private Point origin;

    public abstract ShapeMomento saveMomento() throws CloneNotSupportedException;
    public abstract Object clone();
    public abstract String getType();
    public abstract ArrayList<Float> getSize();
    //Accessors
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public Point getOrigin() { return origin; }
    public void setOrigin(Point origin) { this.origin = origin; }

}
