package InputProcessing;

import Commands.DrawBoard;
import Commands.ShapeInvoker;

import java.awt.*;
import java.util.Scanner;

public class ReadFromUserImput extends ProcessCommands {

    private String userInput;

    public ReadFromUserImput(){
        invoker = new ShapeInvoker();
        drawBoard = new DrawBoard();
        location = new Point();
        selection = -1;
        userInput = "";
    }

    public String getUserInput(){
        Scanner input = new Scanner(System.in);
        userInput = input.nextLine();
        return userInput;
    }
    public void process(){
        System.out.println("Q to exit");
        while(!userInput.equals("Q")) {
            processCommand(getUserInput());
        }
    }
}
