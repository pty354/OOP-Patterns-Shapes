package InputProcessing;

import Commands.*;

import java.awt.*;

public abstract class ProcessCommands {

    private int rectWidth;
    private int rectHeight;
    private int circleRadius;
    private String[] instruction;

    public static String color;
    protected static int selection;
    protected Point location;
    protected ShapeInvoker invoker;
    protected DrawBoard drawBoard;

    public abstract void process();

    public void processCommand(String command) {
        instruction = command.split("\\s+");

        try {
            switch (instruction[0].toUpperCase()) {
                case "CREATE":
                    if (instruction[1].toUpperCase().equals("RECTANGLE")) {
                        processCreateRect();
                    } else if (instruction[1].toUpperCase().equals("CIRCLE")) {
                        processCreateCirc();
                    }
                    break;
                case "SELECT":
                    processSelect();
                    break;
                case "MOVE":
                    processMove();
                    break;
                case "DRAW":
                    processDraw();
                    break;
                case "COLOR":
                    processColor();
                    break;
                case "DELETE":
                    processDelete();
                    break;
                case "DRAWSCENE":
                    processDrawscene();
                    break;
                case "UNDO":
                    processUndo();
                    break;
                default:
                    System.out.println("Unrecognized command " + instruction[0]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Incorrect input.");
        }
    }

    public void processCreateRect(){
        setRectWidth(Integer.parseInt(instruction[2]));
        setRectHeight(Integer.parseInt(instruction[3]));
        invoker.storeAndExecute(new CreateRectCommand(getRectWidth(), getRectHeight()));

    }

    public void processCreateCirc(){
        setCircleRadius(Integer.parseInt(instruction[2]));
        invoker.storeAndExecute(new CreateCircCommand(getCircleRadius()));

    }

    public void processSelect(){
        if(DrawBoard.getShapes().size() >= Integer.parseInt(instruction[1])){
            int prevSelection = getSelection();
            setSelection(Integer.parseInt(instruction[1]) - 1);
            invoker.storeAndExecute(new SelectCommand(getSelection(), prevSelection));
        }
        else{
            System.out.println("ERROR: invalid shape for SELECT");
        }
    }

    public void processMove(){
        location.setLocation(Integer.parseInt(instruction[1]), Integer.parseInt(instruction[2]));
        invoker.storeAndExecute(new MoveCommand(getLocation()));
    }

    public void processDraw(){
        invoker.onlyExecute(new DrawCommand(getSelection()));
    }

    public void processColor(){
        switch (instruction[1].toUpperCase()){
            case "RED":
                setColor("Red");
                break;
            case "BLUE":
                setColor("Blue");
                break;
            case "YELLOW":
                setColor("Yellow");
                break;
            case "ORANGE":
                setColor("Orange");
                break;
            case "GREEN":
                setColor("Green");
                break;
            default:
                System.out.println("ERROR: Only colors Red, Blue, Yellow, Orange and Green.");
                return;
        }
        invoker.storeAndExecute(new ColorCommand(getColor()));
    }

    public void processDelete(){
        invoker.storeAndExecute(new DeleteCommand(getSelection()));
    }

    public void processDrawscene(){
        invoker.onlyExecute(new DrawsceneCommand());
    }

    public void processUndo(){
        ShapeInvoker.getCommandHistory().get(ShapeInvoker.getCommandHistory().size()-1).undo();
    }

    //Accessors
    public int getRectWidth() { return rectWidth; }
    public void setRectWidth(int rectWidth) { this.rectWidth = rectWidth; }
    public int getRectHeight() { return rectHeight; }
    public void setRectHeight(int rectHeight) { this.rectHeight = rectHeight; }
    public int getCircleRadius() { return circleRadius; }
    public void setCircleRadius(int circleRadius) { this.circleRadius = circleRadius; }
    public static int getSelection() { return ProcessCommands.selection; }
    public static void setSelection(int selection) { ProcessCommands.selection = selection; }
    public Point getLocation() { return location; }
    public void setLocation(Point location) { this.location = location; }
    public String getColor() { return color; }
    public static void setColor(String color) { ProcessCommands.color = color; }
    public ShapeInvoker getInvoker() { return invoker; }
    public void setInvoker(ShapeInvoker invoker) { this.invoker = invoker; }
}


