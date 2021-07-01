package Commands;

public abstract class Command {

    private int selection;
    private int prevSelection;

    public abstract void execute();
    public abstract void undo();

    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    public int getPrevSelection() {
        return prevSelection;
    }

    public void setPrevSelection(int prevSelection) {
        this.prevSelection = prevSelection;
    }

}
