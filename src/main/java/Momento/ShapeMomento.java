package Momento;
import Shapes.Shape;

public class ShapeMomento {

    private Shape savedShape;
    private int savedSelection;

    public ShapeMomento(Shape savedShape, int savedSelection){
        this.savedShape = savedShape;
        this.savedSelection = savedSelection;
    }

    //Accessors
    public int getSavedSelection() { return savedSelection; }
    public void setSavedSelection(int savedSelection) { this.savedSelection = savedSelection; }
    public Shape getSavedShape() { return savedShape; }
    public void setSavedShape(Shape savedShape) { this.savedShape = savedShape; }
}
