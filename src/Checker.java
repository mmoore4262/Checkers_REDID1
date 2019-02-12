package sample;

import java.lang.Error;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.beans.binding.When;
import javafx.beans.binding.DoubleBinding;



public class Checker extends Circle {


    private boolean king = false;
    public final String team;
    //for binding the sample to the grid
    private DoubleBinding radiusBinding;

    Checker(sample.CheckerBoard board, String tmp) {
        //decide if checker is red or black
        if(tmp.equals("red")) {
            team = tmp;
            setFill(Color.RED);
        }
        else if(tmp.equals("black")) {
            team = tmp;
            setFill(Color.BLACK);
        }
        else throw new Error("team must be red or black");
        //center piece
        board.setHalignment(this, HPos.CENTER);
        board.setValignment(this, VPos.CENTER);
        radiusBinding = (DoubleBinding)new When(board.cellWidthProperty().lessThan(board.cellHeightProperty()))
                .then(board.cellWidthProperty().multiply(.5*.8))
                .otherwise(board.cellHeightProperty().multiply(.5*.8)
                );
        //bind the radius of the checker to .8 * width of it's container
        radiusProperty().bind(radiusBinding);

        //add local event handlers to highlight and unhighlight the checker when dragged
        addEventHandler(MouseEvent.DRAG_DETECTED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                toFront();
                setStroke(Color.YELLOW);
                startFullDrag();
                transferEvent(e);
            }
        });
        addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                setStroke(null);
                transferEvent(e);
            }
        });
        //register static eventHandler to forward control of the action
        addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                transferEvent(e);
            }
        });
        //add change listener to team
    }

    //convienence method to transfer event to checkerboard
    private <T extends MouseEvent> void transferEvent(T e) {
        //cheating to get dynamic reference within a static object
        sample.CheckerBoard dynamicBoard = (sample.CheckerBoard)((Checker)e.getSource()).getParent();
        dynamicBoard.transferControl(e);
    }

    //getters and setters for king state
    public boolean getKing() {
        return king;
    }
    public void setKing(boolean state) {
        if(this.team.equals("red"))
        {
          this.setFill(Color.PINK);

        }
        else
        {
            this.setFill(Color.GRAY);
        }
        king = state;
    }
}