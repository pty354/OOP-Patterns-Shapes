package Model.Commands;

import java.util.ArrayList;

public class ShapeInvoker{

    private static ArrayList<Command> commandHistory;

    public ShapeInvoker(){
        commandHistory = new ArrayList<>();
    }

    public void storeAndExecute(Command command) {
        commandHistory.add(command);
        command.execute();
    }

    public void onlyExecute(Command command) {
        command.execute();
    }

    public static void removeLastCommand() {
        commandHistory.remove(commandHistory.size()-1);
    }

    public String showCommandHistory(){
        String output = "";
        for(int command = 0 ; command < commandHistory.size(); command++){
            output += "Command: " + commandHistory.get(command).toString() + "\n";
        }
        return output;
    }

    //Accessors
    public static ArrayList<Command> getCommandHistory() { return commandHistory; }
    public static void setCommandHistory(ArrayList<Command> commandHistory) { ShapeInvoker.commandHistory = commandHistory; }
}
