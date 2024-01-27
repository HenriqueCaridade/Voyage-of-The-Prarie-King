package com.henrique.king.game.elements;

import com.henrique.king.game.Vector2D;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class MonsterTester {
    @Test
    public void tickTest(){
        Monster mon = new Monster(Mockito.mock(Vector2D.class));
        for(int i = 0; i < Monster.ticksToMove - 1; i++){
            assertFalse(mon.tick());
        }
        assertTrue(mon.tick());
        assertFalse(mon.tick());
    }
}