package Model.Commands;

import Model.InputProcessing.ProcessCommands;

import java.util.ArrayList;

public class ResizeRectCommand extends Command{
    private int width;
    private int height;
    private int prevWidth;
    private int prevHeight;

    public ResizeRectCommand(int height, int width){
        this.height = height;
        this.width = width;
    }

    @Override
    public void execute() {
        try {
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(height);
            arr.add(width);
            setHeight(DrawBoard.getShapes().get(ProcessCommands.getSelection()).getSize().get(0));
            setWidth(DrawBoard.getShapes().get(ProcessCommands.getSelection()).getSize().get(1));
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
        arr.add(height);
        arr.add(width);
        DrawBoard.getShapes().get(ProcessCommands.getSelection()).setSize(arr);
        ShapeInvoker.removeLastCommand();
    }

    public String toString() {
        return "Size: ";
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPrevWidth() {
        return prevWidth;
    }

    public void setPrevWidth(int prevWidth) {
        this.prevWidth = prevWidth;
    }

    public int getPrevHeight() {
        return prevHeight;
    }

    public void setPrevHeight(int prevHeight) {
        this.prevHeight = prevHeight;
    }
}
