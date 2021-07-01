package Commands;

import InputProcessing.ProcessCommands;
import Shapes.Shape;

import java.util.ArrayList;

public class DrawBoard implements Cloneable{

    private static ArrayList<Shape> shapes;

    public DrawBoard(){
        shapes = new ArrayList<>();
    }

    public static void removeLastShape() {
        shapes.remove(shapes.size()-1);
    }

    public static void addShape(Shape shape){
        shapes.add(shape);
    }

    public static void removeSelectedShape() {
        try {
            shapes.remove(ProcessCommands.getSelection());
        }catch(IndexOutOfBoundsException e){
            System.out.print("No shape selected");
        }
    }

    //Accessors
    public static ArrayList<Shape> getShapes() { return shapes; }
    public void setShapes(ArrayList<Shape> shapes) { DrawBoard.shapes = shapes; }
}
