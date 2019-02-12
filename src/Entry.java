package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.text.TableView;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Entry extends Application {

    //create window, add checkerboard, start game loop
    public void start(Stage stage) {
        sample.CheckerBoard board = sample.CheckerBoard.createCheckerBoard();
        Scene scene = new Scene(board, 500, 500);
        board.bindToParent();
        stage.setScene(scene);
        board.setGridLinesVisible(true);
        stage.show();

        Stage secondOne = new Stage();
        secondOne.setHeight(500);
        secondOne.setWidth(500);
        Group root = new Group();
        secondOne.setTitle("CSV stuff");
        secondOne.setScene(new Scene(root));
        //root.getChildren().add(new Label("TotalGames"));
        secondOne.show();

        String readIn3= "src/sample/Checkers.csv";
        BufferedReader br = null;
        String row = "";
        String col = ",";

        try
        {
            //Buffered Reader only reads. Here is a wiki with all of it's methods. https://www.javatpoint.com/java-bufferedreader-class
            br = new BufferedReader(new FileReader(readIn3));
            int offsetX = 0;
            int offsetY=0;

            //While there is a line with text in it, this loop will trigger.
            while((row=br.readLine())!=null) {
                String[] stats = row.split(col);
               // root.getChildren().add((new Label(stats[0] + " ")));

                for (int i = 0; i < stats.length; i++)
                {
                    try {
                        Label label= new Label();
                        label.setLayoutX(offsetX);
                        label.setLayoutY(offsetY);
                        label.setText(stats[i]);
                        root.getChildren().add(label);
                        offsetX+=190;
                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                        break;
                    }
                }
                offsetY+=100;
                offsetX=0;


            }
        }
        catch(FileNotFoundException e)
        {

            e.printStackTrace();
        }
        catch(IOException e)
        {

            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch();
    }
}