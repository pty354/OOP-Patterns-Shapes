package Model.Commands;

import Model.InputProcessing.ProcessCommands;
import Model.Momento.ShapeMomento;

public class DeleteCommand extends Command {

    private ShapeMomento savedShape;

    public DeleteCommand(int selection){
        setSelection(selection);
    }

    @Override
    public void execute()  {
        try {
            savedShape = DrawBoard.getShapes().get(getSelection()).saveMomento();
            DrawBoard.getShapes().remove(getSelection());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e){
            System.out.println("ERROR: No shape selected");
        }
    }

    @Override
    public void undo() {
        DrawBoard.getShapes().add(getSelection(), savedShape.getSavedShape());
        ProcessCommands.setSelection(savedShape.getSavedSelection());
        ShapeInvoker.removeLastCommand();
    }

    public String toString() {
        return "Delete shape " + getSelection();
    }
}
