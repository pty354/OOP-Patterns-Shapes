package Model.Commands;

import Model.Shapes.Circle;

public class CreateCircCommand extends Command{

    private int radius;

    public CreateCircCommand(int radius){
        this.radius = radius;
    }

    @Override
    public void execute() {
        DrawBoard.addShape(new Circle(radius));
    }

    @Override
    public void undo(){
        DrawBoard.removeLastShape();
        ShapeInvoker.removeLastCommand();
    }

    public String toString(){
        return "Create Circle radius: " + radius;
    }
}
