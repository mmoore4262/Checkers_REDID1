package sample;

import javafx.scene.input.MouseEvent;
import javafx.beans.value.ChangeListener;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


@SuppressWarnings("restriction")
public class CheckerBoard extends sample.TrueGrid {

    //the controller object for this checkerboard
    private Controller maestro;
    //Keep track of the turn and alert controller when it changes
    private StringProperty turn;

    //factory method to create checkerboard object
    public static CheckerBoard createCheckerBoard() {
        CheckerBoard ret = new CheckerBoard();
        ret.setTiles();
        ret.setBoard();
        ret.setController(new Controller(ret));
        ret.getController().setValidSelection();
        return ret;
    }
    //create an 8x8 Grid
    //wrap in a factory method
    private CheckerBoard() {
        super(8, 8);
        //set the property, add change listener
        turn = new SimpleStringProperty("red");
        turn.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> property, String oldVal, String newVal) {

            }
        });
    }
    //set the board
    private void setBoard() {
        String team = "red";
        //Only initiailze the first and last three rows with sample
        for(int j=0; j<8; j++){
            if(j > 2 && j <5) {
                team = "black";
                continue;
            }
            for(int i=0; i<8; i+=2) {
                if(j%2 == 0) add(new sample.Checker(this, team), i+1, j);
                else add(new sample.Checker(this, team), i, j);
            }
        }
    }
    //set the tiles
    private void setTiles() {
        for(int j=0; j<8; j++) {
            for(int i=0; i<8; i++) {
                Rectangle tmp = new Rectangle();
                tmp.widthProperty().bind(cellWidthProperty());
                tmp.heightProperty().bind(cellHeightProperty());
                if(j%2 == 0) {
                    if(i%2 == 0) {
                        tmp.setFill(Color.CORNSILK);
                        add(tmp, i, j);
                    }
                    else {
                        tmp.setFill(Color.DARKBLUE);
                        add(tmp, i, j);
                    }
                }
                else {
                    if(i%2 == 0) {
                        tmp.setFill(Color.DARKBLUE);
                        add(tmp, i, j);
                    }
                    else {
                        tmp.setFill(Color.CORNSILK);
                        add(tmp, i, j);
                    }
                }
            }
        }
    }
    //transfers control of the event to the controller for this game of sample
    public <T extends MouseEvent> void transferControl(T e) {
        maestro.recieveControl(e);
    }
    //getters and setters for turn property
    public StringProperty getTurnProperty() {
        return turn;
    }
    public String getTurn() {
        return turn.getValue();
    }
    public Controller getController() {
        return maestro;
    }
    public void setController(Controller tmp) {
        maestro = tmp;
    }
}