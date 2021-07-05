package Model.Commands;

public class DrawCommand extends Command{

    public DrawCommand(int selected){
        setSelection(selected);
    }

    @Override
    public void execute(){
        try {
            System.out.println(DrawBoard.getShapes().get(getSelection()).toString());
        }catch(IndexOutOfBoundsException e){
            System.out.println("ERROR: Select a shape to Draw.");
        }
    }

    @Override
    public void undo(){ }
}
