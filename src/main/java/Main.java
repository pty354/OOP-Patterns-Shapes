import InputProcessing.ProcessCommands;
import InputProcessing.ReadFromFile;

public class Main {
    public static void main(String[] args){

        // Use code below to read user input via console, cannot use with read from file simultaneously
        // ProcessCommands commands = new ReadFromUserImput();
        // Can also use commands.processCommand("Create circle 5") to process individual
        // commands
        ProcessCommands commands = new ReadFromFile("Input1.txt");
        commands.process();

    }
}
