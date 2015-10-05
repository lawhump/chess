package src.com.lhumphr2.chess.view;

import src.com.lhumphr2.chess.model.Coord;

import javax.swing.*;

/**
 * Created by lawrencehumphrey on 9/23/15.
 */
public class State {
    Coord pos;
    Icon icon;

    public State(Coord pos, Icon i) {
        this.pos = pos;
        this.icon = i;
    }
}
