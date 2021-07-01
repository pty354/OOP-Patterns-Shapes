package Commands;

import Shapes.Shape;

public class DrawsceneCommand extends Command{

    @Override
    public void execute(){
        for(Shape shape : DrawBoard.getShapes()){
            System.out.print(shape.toString() + "\n");
        }
    }

    @Override
    public void undo() {

    }
}
