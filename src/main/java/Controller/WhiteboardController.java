package Controller;
import Model.Commands.DrawBoard;
import Model.InputProcessing.ProcessCommands;
import Model.InputProcessing.ReadFromUserInput;
import Model.Shapes.Shape;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.canvas.Canvas;

import java.awt.*;

public class WhiteboardController {

    @FXML
    private Pane board;
    @FXML
    private Button createCircle;
    @FXML
    private Button createRect;
    @FXML
    private Button delete;
    @FXML
    private Button undo;
    @FXML
    private Button setColor;
    @FXML
    private Button resize;
    @FXML
    private Button pen;
    @FXML
    private TextField width;
    @FXML
    private TextField height;
    @FXML
    private Label widthLabel;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private VBox widthBox;
    @FXML
    private VBox heightBox;

    Path path;
    private ProcessCommands commands;
    private int selection = 0;
    double orgSceneX, orgSceneY;
    public WhiteboardController(){ }

    @FXML
    void createCircle(ActionEvent event) {
        commands.processCommand("Create circle 50");
        board.getChildren().clear();
        drawScene();
    }
    @FXML
    void createRect(ActionEvent event) {
        commands.processCommand("Create rectangle 80 50");
        board.getChildren().clear();
        drawScene();
    }
    @FXML
    void delete(ActionEvent event){
        commands.processCommand("select " + selection);
        commands.processCommand("delete");
        board.getChildren().clear();
        drawScene();
    }
    @FXML
    void setColor(ActionEvent event){
        commands.processCommand("select " + selection);
        commands.processCommand("colorhex " + toRGBCode(colorPicker.getValue()));
        board.getChildren().clear();
        drawScene();
    }
    @FXML
    void resize(ActionEvent event){
        commands.processCommand("select " + selection);
        commands.processCommand("resizecircle " + width.getText());
        commands.processCommand("resizerect " + height.getText() + " " + width.getText());
        board.getChildren().clear();
        drawScene();
    }
    @FXML
    void undo(ActionEvent event){
        commands.processCommand("undo");
        board.getChildren().clear();
        drawScene();
    }

    @FXML
    void pen(ActionEvent event){
        Canvas canvas = new Canvas(1280, 720);
        GraphicsContext gc;

        try{
            gc = canvas.getGraphicsContext2D();
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(1);

            board.setOnMousePressed(e -> {
                gc.beginPath();
                gc.lineTo(e.getSceneX(), e.getSceneY());
                gc.stroke();
            });

            board.setOnMouseDragged(e -> {
                gc.lineTo(e.getSceneX(), e.getSceneY());
                gc.stroke();
            });

            board.getChildren().add(canvas);

        }catch(Exception e){
            e.printStackTrace();
        }

        //board.getChildren().clear();
        //drawScene();
    }

    void drawScene(){
        int i = 1;
        for(Shape shape : DrawBoard.getShapes()) {
            if(shape.getType().equals("circle")) {
                createCircle(shape, i);
            }
            if(shape.getType().equals("rectangle")) {
                createRect(i, shape);
            }
            i++;
        }

    }

    public void initialize() {
        commands = new ReadFromUserInput();
        if(selection == 0){
            delete.setDisable(true);
        }
    }


    public static String toRGBCode( Color color )
    {
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }

    public void createCircle(Shape shape, int i){
        final Circle circle = new Circle();
        circle.setRadius(shape.getSize().get(0));
        circle.setCenterX(shape.getOrigin().getX());
        circle.setCenterY(shape.getOrigin().getY());
        circle.setFill(Color.web(shape.getColor()));
        circle.setId(i+"");
        circle.setCursor(Cursor.HAND);

        if(selection == Integer.valueOf(circle.getId())) {
            circle.setStroke(Color.RED);
            circle.setStrokeWidth(3.0);
        }else {
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(1.0);
        }
        circle.setOnMouseClicked((e) -> {
            if(e.getButton().equals(MouseButton.PRIMARY)){
                if(selection == Integer.valueOf(circle.getId())) {
                    selection = 0;
                    delete.setDisable(true);
                    widthBox.setVisible(false);
                }
                else {
                    selection = Integer.valueOf(circle.getId());
                    delete.setDisable(false);
                    widthBox.setVisible(true);
                    width.setText(""+(int)circle.getRadius());
                    widthLabel.setText("Radius");
                }
                heightBox.setVisible(false);
                board.getChildren().clear();
                drawScene();
            }
        });

        circle.setOnMousePressed((t) -> {
            if(t.getButton().equals(MouseButton.SECONDARY)) {
                selection = Integer.valueOf(circle.getId());
                circle.setStroke(Color.RED);
                circle.setStrokeWidth(3.0);

                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();

                Circle c = (Circle) (t.getSource());
                c.toFront();
            }
        });
        circle.setOnMouseDragged((t) -> {
            if(t.getButton().equals(MouseButton.SECONDARY)) {
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;

                Circle c = (Circle) (t.getSource());

                c.setCenterX(c.getCenterX() + offsetX);
                c.setCenterY(c.getCenterY() + offsetY);

                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
            }
        });

        circle.setOnMouseReleased((t) -> {
            if(t.getButton().equals(MouseButton.SECONDARY)) {
                commands.processCommand("select " + selection);
                commands.processCommand("move " + (int) circle.getCenterX() + " " + (int) circle.getCenterY());
                selection = 0;
                circle.setStroke(Color.BLACK);
                circle.setStrokeWidth(1.0);
            }
        });

        board.getChildren().addAll(circle);
    }

    public void createRect(int i, Shape shape) {
        final Rectangle rect = new Rectangle();
        rect.setWidth(shape.getSize().get(1));
        rect.setHeight(shape.getSize().get(0));
        rect.setX(shape.getOrigin().getX());
        rect.setY(shape.getOrigin().getY());
        rect.setFill(Color.web(shape.getColor()));
        rect.setId(i +"");
        rect.setCursor(Cursor.HAND);

        if(selection == Integer.valueOf(rect.getId())) {
            rect.setStroke(Color.RED);
            rect.setStrokeWidth(3.0);
        }else {
            rect.setStroke(Color.BLACK);
            rect.setStrokeWidth(1.0);
        }
        rect.setOnMouseClicked((e) -> {
            if(e.getButton().equals(MouseButton.PRIMARY)){
                if(selection == Integer.valueOf(rect.getId())) {
                    selection = 0;
                    delete.setDisable(true);
                    widthBox.setVisible(false);
                    heightBox.setVisible(false);
                }
                else {
                    selection = Integer.valueOf(rect.getId());
                    delete.setDisable(false);
                    widthBox.setVisible(true);
                    heightBox.setVisible(true);
                    width.setText(""+(int)rect.getWidth());
                    height.setText(""+(int)rect.getHeight());
                    widthLabel.setText("Width");
                }
                board.getChildren().clear();
                drawScene();
            }
        });
        rect.setOnMousePressed((t) -> {
            if(t.getButton().equals(MouseButton.SECONDARY)) {
                selection = Integer.valueOf(rect.getId());
                rect.setStroke(Color.RED);
                rect.setStrokeWidth(3.0);

                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();

                Rectangle r = (Rectangle) (t.getSource());
                r.toFront();
            }
        });
        rect.setOnMouseDragged((t) -> {
            if(t.getButton().equals(MouseButton.SECONDARY)) {
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;

                Rectangle r = (Rectangle) (t.getSource());

                r.setX(r.getX() + offsetX);
                r.setY(r.getY() + offsetY);

                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
            }
        });

        rect.setOnMouseReleased((t) -> {
            if(t.getButton().equals(MouseButton.SECONDARY)) {
                commands.processCommand("select " + selection);
                commands.processCommand("move " + (int) rect.getX() + " " + (int) rect.getY());
                selection = 0;
                rect.setStroke(Color.BLACK);
                rect.setStrokeWidth(1.0);
            }
        });

        board.getChildren().addAll(rect);
    }
}
