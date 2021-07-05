package Model.Commands;

import Model.Shapes.Rectangle;

public class CreateRectCommand extends Command{

    private int width;
    private int height;

    public CreateRectCommand(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public void execute() {
        DrawBoard.addShape(new Rectangle(width,height));
    }

    @Override
    public void undo(){
        DrawBoard.removeLastShape();
        ShapeInvoker.removeLastCommand();
    }

    public String toString(){
        return "Create Circle width: " + width + " height: " + height;
    }
}
