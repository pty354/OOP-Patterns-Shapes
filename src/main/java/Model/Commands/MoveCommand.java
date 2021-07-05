package Model.Commands;

import Model.InputProcessing.ProcessCommands;

import java.awt.*;

public class MoveCommand extends Command{

    private Point location;
    private Point prevLocation;

    public MoveCommand(Point location){
        this.location = new Point(location);
    }

    @Override
    public void execute() {
        try {
            prevLocation = new Point(DrawBoard.getShapes().get(ProcessCommands.getSelection()).getOrigin());
            DrawBoard.getShapes().get(ProcessCommands.getSelection()).setOrigin(getLocation());
        }
        catch(IndexOutOfBoundsException e){
            ShapeInvoker.removeLastCommand();
            System.out.println("Error: no shape selected");
        }
    }

    @Override
    public void undo() {
        DrawBoard.getShapes().get(ProcessCommands.getSelection()).setOrigin(getPrevLocation());
        ShapeInvoker.removeLastCommand();

    }

    public String toString(){
        return "Move selected shape to " + (int)getLocation().getX() + " " + (int)getLocation().getY();
    }

    //Accessors
    public Point getLocation() { return location; }
    public void setLocation(Point location) { this.location = location; }
    public Point getPrevLocation() { return prevLocation; }
    public void setPrevLocation(Point prevLocation) { this.prevLocation = prevLocation; }
}
