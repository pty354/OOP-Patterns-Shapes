package Model;

import Model.InputProcessing.ProcessCommands;
import Model.InputProcessing.ReadFromFile;
import Controller.WhiteboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){

        // Use code below to read user input via console, cannot use with read from file simultaneously
        // ProcessCommands commands = new ReadFromUserImput();
        // Can also use commands.processCommand("Create circle 5") to process individual
        // commands
        //ProcessCommands commands = new ReadFromFile("Input1.txt");
        //commands.process();
        launch(args);

    }

    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/whiteBoard.fxml"));
        loader.setController(new WhiteboardController());
        Scene scene = new Scene((Parent) loader.load());
        stage.setScene(scene);
        stage.setTitle("Whiteboard");
        stage.show();
    }
}
