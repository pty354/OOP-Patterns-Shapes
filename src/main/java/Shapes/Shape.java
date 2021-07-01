package Shapes;
import Momento.ShapeMomento;
import java.awt.*;

public abstract class Shape{

    private String color;
    private Point origin;

    public abstract ShapeMomento saveMomento() throws CloneNotSupportedException;
    public abstract Object clone();

    //Accessors
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public Point getOrigin() { return origin; }
    public void setOrigin(Point origin) { this.origin = origin; }

}
