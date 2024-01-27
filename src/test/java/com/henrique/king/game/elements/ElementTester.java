package com.henrique.king.game.elements;

import org.junit.jupiter.api.Test;
import com.henrique.king.game.Vector2D;

import static org.junit.jupiter.api.Assertions.*;

public class ElementTester {
    @Test
    public void setPosTest() {
        King k = new King(new Vector2D(0, 0));
        Vector2D tested = new Vector2D(10, 5);
        k.setPos(tested);
        assertEquals(k.getPos(), tested);
    }

    @Test
    public void getPosTest() {
        Vector2D tested = new Vector2D(6, 8);
        Monster mon = new Monster(tested);
        assertEquals(mon.getPos(), tested);
    }
}