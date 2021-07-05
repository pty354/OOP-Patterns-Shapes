package Model.InputProcessing;

import Model.Commands.DrawBoard;
import Model.Commands.ShapeInvoker;

import java.awt.*;
import java.util.Scanner;

public class ReadFromUserInput extends ProcessCommands {

    private String userInput;

    public ReadFromUserInput(){
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
