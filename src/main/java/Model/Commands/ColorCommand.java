package Model.Commands;

import Model.InputProcessing.ProcessCommands;

public class ColorCommand extends Command {

    private String color;
    private String prevColor;

    public ColorCommand(String color){
        this.color = color;
    }

    @Override
    public void execute() {
        try {
            setPrevColor(DrawBoard.getShapes().get(ProcessCommands.getSelection()).getColor());
            DrawBoard.getShapes().get(ProcessCommands.getSelection()).setColor(getColor());
        }
        catch(IndexOutOfBoundsException e){
            ShapeInvoker.removeLastCommand();
            System.out.println("Error: no shape selected");
        }
    }

    @Override
    public void undo(){
        DrawBoard.getShapes().get(ProcessCommands.getSelection()).setColor(getPrevColor());
        ShapeInvoker.removeLastCommand();
    }

    public String toString() {
        return "Color: ";
    }

    //Accessors
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getPrevColor() { return prevColor; }
    public void setPrevColor(String prevColor) { this.prevColor = prevColor; }

}
