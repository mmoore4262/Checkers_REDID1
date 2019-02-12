package sample;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import java.util.List;
import java.util.ArrayList;
import javafx.geometry.Point2D;



public class TrueGrid extends GridPane{

    //properties
    private DoubleProperty cellWidth = new SimpleDoubleProperty();
    private DoubleProperty cellHeight = new SimpleDoubleProperty();
    public final int rows;
    public final int columns;

    //constructor
    public TrueGrid(int i, int j) {
        columns = i;
        rows = j;
        initializeColumns(i);
        initializeRows(j);
    }
    //fill the grid with i columns
    private void initializeColumns(int i) {
        for(int c=0; c<i; c++){
            getColumnConstraints().add(new ColumnConstraints());
            getColumnConstraints().get(c).setPercentWidth(100.0/i);
        }
    }
    //fill the grid with j rows
    private void initializeRows(int j) {
        for(int c=0; c<j; c++){
            getRowConstraints().add(new RowConstraints());
            getRowConstraints().get(c).setPercentHeight(100.0/j);
        }
    }
    //bind cell width and height properties to their value within the scene
    public void bindToParent() throws NullPointerException {
        if(getScene() == null) throw new NullPointerException("scene value null! does this TrueGrid belong to a scene?");
        cellWidth.bind(getScene().widthProperty().divide(columns));
        cellHeight.bind(getScene().heightProperty().divide(rows));
    }
    //getter methods
    public DoubleProperty cellWidthProperty() {
        return cellWidth;
    }
    public double cellWidth() {
        return cellWidth.getValue();
    }
    public DoubleProperty cellHeightProperty() {
        return cellHeight;
    }
    public double cellHeight() {
        return cellHeight.getValue();
    }
    //get the child contained at (i,j) coordinate of this grid
    //if T is null no children will be filtered
    public <T extends Node> List<T> getCell(Class<T> conversion, int iIndex, int jIndex) {
        List<T> children = new ArrayList<T>();
        for(Node node : getChildren()) {
            if(conversion.isInstance(node)) {
                if(getColumnIndex(node) == iIndex) {
                    if(getRowIndex(node) == jIndex) {
                        children.add(conversion.cast(node));
                    }
                }
            }
        }
        return children;
    }
    //a wrapper class to allow passing Point2D instead of int coordinates to getCell
    public <T extends Node> List<T> getCell(Class<T> conversion, Point2D p) {
        return getCell(conversion, (int)p.getX(), (int)p.getY());
    }
    //remove all children of a given node type at the coordinates
    public <T extends Node> void removeCell(Class<T> clazz, int i, int j) {
        getChildren().removeAll(getCell(clazz, i, j));
    }

}