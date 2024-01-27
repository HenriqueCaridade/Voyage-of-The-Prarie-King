package com.henrique.king.game.elements.items;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTester {
    @Test
    public void despawnTest(){
        // For example Wheel
        Wheel item = new Wheel(null);
        assertFalse(item.despawn());
    }

    @Test
    public void endedTest(){
        // For example Confuse
        Confuse item = new Confuse(null);
        item.use(null);
        assertFalse(item.ended());
    }
}
