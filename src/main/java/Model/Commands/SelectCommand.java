package Model.Commands;

import Model.InputProcessing.ProcessCommands;

public class SelectCommand extends Command{

    public SelectCommand(int select, int prevSelect){
        setSelection(select);
        setPrevSelection(prevSelect);
    }

    @Override
    public void execute(){}

    @Override
    public void undo() {
        ProcessCommands.setSelection(getPrevSelection());
        setPrevSelection(getSelection());
        ShapeInvoker.removeLastCommand();
    }

    public String toString(){
        return "Select: " + (getSelection() + 1);
    }
}
