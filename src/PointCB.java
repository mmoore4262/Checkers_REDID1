package sample;

import javafx.geometry.Point2D;

//A Point2D linked specific checker
@SuppressWarnings("restriction")
public class PointCB extends Point2D {
    public final sample.Checker checker;
    PointCB(sample.Checker tmp, int x, int y) {
        super(x, y);
        checker = tmp;
    }
    public boolean equals(Object o) {
        if(o.getClass() == PointCB.class) {


            return super.equals(o) && ((PointCB)(o)).checker == checker;
        }
        else return false;
    }
}