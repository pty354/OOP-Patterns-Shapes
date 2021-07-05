package Model.InputProcessing;

import Model.Commands.DrawBoard;
import Model.Commands.ShapeInvoker;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadFromFile extends ProcessCommands {

    private String file;

    public ReadFromFile(String file){
        this.file = file;
        invoker = new ShapeInvoker();
        drawBoard = new DrawBoard();
        location = new Point();
        selection = -1;
    }

    public ArrayList<String> readFile(String file){
        String strLine = "";
        ArrayList<String> lines = new ArrayList<>();
        try {
            FileInputStream fstream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            while ((strLine = br.readLine()) != null) {
                lines.add(strLine);
            }
            fstream.close();
        } catch (IOException e){
            System.out.println("File not found");
        }
        return lines;
    }

    public void process(){
        for(String line : readFile(file)){
            processCommand(line);
        }
    }
    public void showFileContent(){

        for(String line : readFile(file)){
            System.out.println(line);
        }
    }
}
