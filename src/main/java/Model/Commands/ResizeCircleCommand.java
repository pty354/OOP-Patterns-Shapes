package Model.Commands;

import Model.InputProcessing.ProcessCommands;

import java.util.ArrayList;

public class ResizeCircleCommand extends Command{
    private int radius;
    private int prevRadius;

    public ResizeCircleCommand(int radius){
        this.radius = radius;
    }

    @Override
    public void execute() {
        try {
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(radius);
            setRadius(DrawBoard.getShapes().get(ProcessCommands.getSelection()).getSize().get(0));
            DrawBoard.getShapes().get(ProcessCommands.getSelection()).setSize(arr);
        }
        catch(IndexOutOfBoundsException e){
            ShapeInvoker.removeLastCommand();
            System.out.println("Error: no shape selected");
        }
    }

    @Override
    public void undo(){
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(radius);
        DrawBoard.getShapes().get(ProcessCommands.getSelection()).setSize(arr);
        ShapeInvoker.removeLastCommand();
    }

    public String toString() {
        return "Size: ";
    }

    //Accessors

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getPrevRadius() {
        return prevRadius;
    }

    public void setPrevRadius(int prevRadius) {
        this.prevRadius = prevRadius;
    }
}
